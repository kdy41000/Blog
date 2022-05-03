import React, { useEffect } from 'react';
import { Nav, NavItem, NavLink, NavbarBrand, Button } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';
import { useSelector, useDispatch } from 'react-redux';
import { loginHandler, logoutHandler } from 'redux/auth/action';

const HeaderComponent = () => {
    const navigate = useNavigate();
    const dispatch = useDispatch();
    const { loginStatus, loginInfo } = useSelector((state) => state.authReducer);
  
    const onLogout = () => {
      dispatch(logoutHandler());
      navigate('/'); 
    }

    // autoLogin
    useEffect(() => {
      let autoLoginInfo = localStorage.getItem('autoLoginInfo');
      console.log("autoLoginInfo:",autoLoginInfo);
      if(autoLoginInfo != null) {
        autoLoginInfo = JSON.parse(autoLoginInfo);
        dispatch(loginHandler(autoLoginInfo));
      }
    }, []);

    return (
        <>
        <Nav style={{padding:'15px',width:'80%'}}>
        <NavItem>
          <NavbarBrand>
             Blog
          </NavbarBrand>
         </NavItem>
         <NavItem>
           <NavLink active onClick={() => navigate('/')}>
             홈
             {/* <Link to="/">홈</Link> */}
           </NavLink>
         </NavItem>
         <NavItem>
           <NavLink active onClick={() => navigate('/about')}>
             About
           </NavLink>
         </NavItem>
         <NavItem>
           <NavLink active onClick={() => navigate('/board')}>
             개발기술공유
           </NavLink>
         </NavItem>

         {
          !loginStatus ?
          (<Button className="header_btn" onClick={() => navigate('/login')}>로그인</Button>) :
          (<Button className="header_btn" variant="danger" onClick={onLogout}>로그아웃</Button>)
         } 
       </Nav>
       </>
    );
};

export default HeaderComponent;