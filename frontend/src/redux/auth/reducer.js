import { LOGIN, AUTO_LOGIN, LOGOUT } from "./actionType"

const initialState = {
    loginInfo: {userId: '', userName: '', userEmail: '', roles: ''},
    loginStatus: false,
}

const authReducer = (state=initialState, action) => {
    let userInfo;
    switch(action.type) {
        case LOGIN:
            console.log("=======로그인 리듀서================");
            userInfo = action.payload;
            console.log("user:",userInfo);
            return {
                ...state,
                loginStatus: true, 
                loginInfo: {userId: userInfo.userId, userName : userInfo.userName, userEmail: userInfo.userEmail, roles: userInfo.roles}
            }
        case AUTO_LOGIN:
            console.log("=======AUTO_LOGIN 리듀서================");
            localStorage.setItem('autoLoginInfo',JSON.stringify(state.loginInfo));
            return {
                ...state
            };
        case LOGOUT:
            localStorage.removeItem('jwtToken');
            localStorage.removeItem('autoLoginInfo');
            return {
                ...state,
                loginStatus: false, 
                loginInfo: {userId: '', userName: '', userEmail: '', roles: ''}
             }

        default: return state;
    }
}

export default authReducer;