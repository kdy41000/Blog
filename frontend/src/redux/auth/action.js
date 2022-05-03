import { LOGIN, AUTO_LOGIN, BYPASS_LOGIN, LOGOUT } from "./actionType"

export const loginHandler = (param) => {
    return {
        type: LOGIN,
        payload : param
    }
}

export const autoLoginHandler = () => {
    return {
        type: AUTO_LOGIN
    }
}

export const logoutHandler = () => {
    return {
        type: LOGOUT
    }
}