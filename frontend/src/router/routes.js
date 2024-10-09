import LazyLoad from "@/utils/AsyncRouteHelper";
import AppRoutes from "./routes/app-routes";

let routes;

export async function setupRoutes() {
    try {

        let AllRoutes;

        AllRoutes = [...AppRoutes];


        routes = AllRoutes.map((route) => {
            const { params, defaultComponentPath, navs } = route;
            const componentObj = navs
                ? {
                    components: {
                        default: LazyLoad(defaultComponentPath),
                        sidebar: AppSidebar,
                        header: AppToolbar,
                        footer: AppFooter,
                    },
                }
                : {
                    component: LazyLoad(defaultComponentPath),
                };
            return {
                ...params,
                ...componentObj,
            };
        });

        const dynamicPath = await generateDynamicPath(window.location);
        const mainRedirect = { path: "/portal/", redirect: dynamicPath };

        return [mainRedirect, ...routes];
    } catch (error) {
        console.error(error);
        return [];
    }
}

async function generateDynamicPath(to) {
    try {
        const port = to.port;

        if (port === '8080') {
            return '/portal/int/login';
        } else if(port === '8081'){
            return '/portal/ext/login';
        }
    } catch (error) {
        console.error(error);
        return '*';  // Otra ruta de error o manejo adecuado
    }
}


export default async function initializeRoutes() {
    return await setupRoutes();
}