import React, { useState } from 'react';
import http from 'http/customAxios';
import { Routes, Route, Link, useNavigate } from 'react-router-dom';
import { validateHandler } from 'utils/utils';
import { useSelector, useDispatch } from 'react-redux';
import { autoLoginHandler, loginHandler } from 'redux/auth/action';
import { Card, Form, Button } from 'react-bootstrap';

const Login = () => {
    const navigate = useNavigate();
    const dispatch = useDispatch();

    const [id, setId] = useState('devyoung');
    const [password, setPassword] = useState('1234');

    const [autoLogin, setAutoLogin] = useState(false);

    const onLoginHandler = async () => {
        //alert('cl');
        const params = {};
        params.userId = id;
        params.password = password;

       await http.post('/login', params)
        .then(response => {
            if(response === 200) processLoginHandler(params);
            else return alert('로그인 실패');
        })
        .catch(err => {
            console.log("Err:",err);
        });

    }

    const processLoginHandler = async (params) => {
        await http.post('/api/v1/user/info', params)
        .then(response => {
            if(validateHandler(response)) {
                dispatch(loginHandler(response));
                if(autoLogin) {
                    dispatch(autoLoginHandler());
                }
                navigate('/');
            }
        })
        .catch(err => {
            console.log("Err:",err);
        });
    }

    return (
        <div className='section'>
        <Form>
            <h2>로그인</h2>
            <Form.Group className="mb-3" controlId="id" style={{width: '25%'}}>
                <Form.Label>Id</Form.Label>
                <Form.Control type="text" placeholder="Enter Id" value={id} onChange={(e) => setId(e.target.value)} />
            </Form.Group>

            <Form.Group className="mb-3" controlId="password" style={{ width: '25%'}}>
                <Form.Label>Password</Form.Label>
                <Form.Control type="password" placeholder="Enter Password" value={password} onChange={(e) => setPassword(e.target.value)} />
            </Form.Group>
            <Form.Group className="mb-3" controlId="formBasicCheckbox">
            <Form.Check 
                    type="checkbox"
                    label="자동로그인"
                    onChange={(e) => { setAutoLogin(!autoLogin) }}
                />
            </Form.Group>
            <Button variant="primary" onClick={onLoginHandler}>
                로그인
            </Button><br/><br/>
            <a href="javascript:void(0)" onClick={(e) => { e.preventDefault(); navigate('/signup');}}>아직 계정이 없으면 회원가입을 진행하세요.</a>
        </Form>
        </div>
    );
};

export default Login;