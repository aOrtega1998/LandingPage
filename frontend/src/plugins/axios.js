import { AUTH_LOGOUT, REFRESH_TOKEN } from '@/store/actions/auth'
import axios from 'axios'
import store from '@/store'
import router from '@/router'

export const AXIOS = axios.create({})

AXIOS.interceptors.request.use((config) => {
  // if access token exists
  if (store.getters.token) {
    // add it to the header
    // eslint-disable-next-line
    config.headers['Authorization'] = store.getters.token
  }
  return config
}, (error) => {
  return Promise.reject(error)
})

AXIOS.interceptors.response.use((response) => {
  // if a new token is given
  // eslint-disable-next-line
  if (response.headers['Authorization']) {
    // update the token
    // eslint-disable-next-line
    store.commit(REFRESH_TOKEN, response.headers['Authorization'])
  }
  // return the response
  return response
}, (error) => {
  // if the error hasn't defined the response
  if (!error.response) {
    // trace the message and reject it
    console.error('Something went wrong. Message: ', error.message)
    return Promise.reject(error)
  }
  // otherwise, trace the status of the response, and process the error
  console.error('Something went wrong. Status: ', error.response.status)
  // if the url path doesn't include auth and the error is a 401 (unauthorized), redirect to the login
  if (!error.config.url.includes('auth') && error.response.status === 401) {
    // execute the logout, marking it as expired
    store.dispatch(AUTH_LOGOUT, true).then()
  } else if (error.response.status === 403) {
    // is status is forbidden, redirect to 403 view
    router.push('/').then()
  } else {
    // for other errors, reject the response
    return Promise.reject(error)
  }
})
