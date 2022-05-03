import React from 'react';
import { Routes, Route, Link, useNavigate } from 'react-router-dom';
import Login from 'components/auth/Login';
import Signup from 'components/auth/Signup';
import About from 'components/section/About';
import Home from 'components/section/Home';
import Board from 'components/section/board/Board';
import Write from 'components/section/board/Write';
import Detail from 'components/section/board/Detail';

const RoutesComponent = () => {
    return (
       <Routes>
           <Route path="/" element={<Home />} />
           <Route path="/about" element={<About />} />
           
           <Route path="/board" element={<Board />} />
           <Route path="/board/write" element={<Write />}/>
           <Route path="/board/detail/:id" element={<Detail />}/>

           <Route path="/login" element={<Login />} />
           <Route path="/signup" element={<Signup />} />
       </Routes>
    );
};

export default RoutesComponent;