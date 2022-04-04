import { i18n } from '@/plugins/i18n'

// For more info see -> https://vuetifyjs.com/en/components/forms

/**
 * Email W3C regex
 * @type {RegExp}
 */
// eslint-disable-next-line
const emailRegex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
/**
 * Alphanumeric regex
 * @type {RegExp}
 */
const alphanumericRegex = /^[a-zA-Z0-9]*$/
/**
 * Alphanumeric regex allowing hyphens, underscores and blank spaces
 * @type {RegExp}
 */
const alphanumericPlusRegex = /^[a-zA-Z0-9\-_ ]*$/
/**
 * Numeric regex
 * @type {RegExp}
 */
const numericRegex = /^[0-9]*$/

/**
 * Probability Regex allowing decimals
 * @type {RegExp}
 */
const probabilityRegex = /^[0-9]+%$/

/**
 * Spanish CIF regex (Codigo de Identificacion Fiscal)
 * @type {RegExp}
 */
const cifRegex = /^([ABCDEFGHJKLMNPQRSUVW])(\d{7})([0-9A-J])$/

/**
 * Phone number and fax regex. Accepts '+', ' ', '-'
 * @type {RegExp}
 */
const phoneNumberRegex = /^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\s\./0-9]*$/

/**
 * Url regex
 * @type {RegExp}
 */
const webRegex = /[(http(s)?):\/\/(www\.)?a-zA-Z0-9@:%._\+~#=]{2,256}\.[a-z]{2,6}\b([-a-zA-Z0-9@:%_\+.~#?&//=]*)/

/**
 * Returns the strength of the given password
 * @param p the password to check its strength
 * @return {number} the strength of the password
 */
function checkStrength (p) {
    // modify p in case of undefined or null
    if (p === undefined || p === null) p = ''
    // define the variable to store the strength of the password
    let strength = 0

    // execute the regex to test the password's strength
    const lowerLetters = /[a-z]+/.test(p)
    const upperLetters = /[A-Z]+/.test(p)
    const numbers = /[0-9]+/.test(p)
    // eslint-disable-next-line
    const symbols = /[$-/:-?{-~!"^_`\[\]]/g.test(p)

    // check how many flags the password has matched
    const passedMatches = [lowerLetters, upperLetters, numbers, symbols].filter(el => el === true).length

    // calculate the strength
    strength += 2 * p.length + ((p.length >= 10) ? 1 : 0)
    strength += passedMatches * 10

    // penalty (short password)
    strength = (p.length < 8) ? Math.min(strength, 10) : strength

    // penalty (poor variety of characters)
    strength = (passedMatches === 1) ? Math.min(strength, 10) : strength
    strength = (passedMatches === 2) ? Math.min(strength, 20) : strength
    strength = (passedMatches === 3) ? Math.min(strength, 40) : strength

    return strength
}

/**
 * Return false if the current date without time is before the passed date in the param, true otherwise
 * @param date String of a date without time
 * @returns {boolean}
 */
function checkDate(date){
    let today = new Date()
    let todayDate = new Date(today.getFullYear() + '/' + (today.getMonth() + 1) + '/' + today.getDate())
    return (Date.parse(date) - todayDate) >= 0

}

function checkifDateIsAfterCurrent(date){
    let today = new Date()
    let todayDate = new Date(today.getFullYear() + '/' + (today.getMonth() + 1) + '/' + today.getDate())
    return (Date.parse(date) - todayDate) <= 0

}

function checkIfDate1IsBeforeDate2(date1, date2){
    return Date.parse(date1) <= Date.parse(date2)
}

/**
 * Return false if the current time without date is before the passed time in the param, true otherwise.
 * Will return true if
 * @param date String of a time without date
 * @returns {boolean}
 */
function checkTime(time, date){
    let today = new Date()
    let todayTime = today.setSeconds(0,0)
    let passedTime = new Date( date + ' ' + time + ':00')
    return (passedTime - todayTime)  > 0
}


const validations = {
    required: value => !!value || i18n.t('lRequired'),
    minLength1: value =>  value.length>0 || i18n.t('lRequired'),
    maxLength2: value => ((value && !!value.trim()) && (value.length <= 2)) || i18n.t('lMaxLength2'),
    notEmptyText: value => (value && !!value.trim()) || i18n.t('lNotEmpty'),
    passwordStrength: value => (!value || checkStrength(value) > 40) || i18n.t('validations.passwordStrength'),
    numeric: value => numericRegex.test(value) || i18n.t('lNumeric'),
    alphanumeric: value => alphanumericRegex.test(value) || i18n.t('lAlphaNumeric'),
    alphanumericPlus: value => alphanumericPlusRegex.test(value) || i18n.t('validations.alphanumericPlus'),
    email: value => emailRegex.test(value) || i18n.t('lEmailRequired'),
    fileSize: file => (file && file.size < 1048576) || i18n.t('validations.fileSize'),
    probability: value => (probabilityRegex.test(value) && (value.length <= 4) && (Number(value.substr(0,value.length - 1)) <= 100)) || i18n.t('lBadProbability'),
    notAfterCurrentDate: value => checkifDateIsAfterCurrent(value) || i18n.t('lDateAfterCurrent'),
    notBeforeCurrentDate: (value, dirty) => (!dirty || checkDate(value)) || i18n.t('lDateBeforeCurrent'),
    notBeforeCurrentTime: (time, date, dirty) => (!dirty || checkTime(time, date)) || i18n.t('lTimeBeforeCurrent'),
    notAfterOtherDate: (value, otherDate) => checkIfDate1IsBeforeDate2(value, otherDate) || i18n.t('lInitialDateAterFinalDate'),
    noNegativeValue: value => ((!value || (value) && (value >= 0))) || i18n.t('lNoNegativeValue'),
    requiredNumeric: value => value !== null || i18n.t('lRequired'),
    cif: value => (!value || (value) && cifRegex.test(value) )|| i18n.t('lInvalidCIF'),
    phoneNumber: value => (!value || (value) && phoneNumberRegex.test(value)  && (value.length <= 13)) || i18n.t('lInvalidContactNumber'),
    web: value => (!value || (value) && (webRegex.test(value)) && (value.length <= 100)) || i18n.t('lInvalidUrl')

}

export default validations
