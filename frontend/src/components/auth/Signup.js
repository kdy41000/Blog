import React, { useState } from 'react';
import http from 'http/customAxios';
import { Routes, Route, Link, useNavigate } from 'react-router-dom';
import { Form, Button } from 'react-bootstrap';

const Signup = () => {
    const navigate = useNavigate();

    const [id, setId] = useState('devyoung');
    const [password, setPassword] = useState('1234');
    const [name, setName] = useState('김동영');
    const [email, setEmail] = useState('kdy41000@naver.com');

    const onSignupHandler = async () => {
        const params = {};
        params.userId = id;
        params.password = password;
        params.userName = name;
        params.userEmail = email;

        await http.post('/api/v1/auth/signup', params)
        .then(data => {
            console.log("data:",data);
            alert(data.returnMsg);

            if(data.returnCode > 0) {
                navigate('/login');
            }
        })
        .catch(err => {
            console.log(err);
        });

    }

    return (
        <>
        <Form style={{padding:'50px'}}>
            <h2>회원가입</h2>
            <Form.Group className="mb-3" controlId="id" style={{width: '25%'}}>
                <Form.Label>Id</Form.Label>
                <Form.Control type="text" placeholder="Enter Id" value={id} onChange={(e) => setId(e.target.value)} />
            </Form.Group>

            <Form.Group className="mb-3" controlId="password" style={{ width: '25%'}}>
                <Form.Label>Password</Form.Label>
                <Form.Control type="password" placeholder="Enter Password" value={password} onChange={(e) => setPassword(e.target.value)} />
            </Form.Group>
            <Form.Group className="mb-3" controlId="name" style={{ width: '25%'}}>
                <Form.Label>Name</Form.Label>
                <Form.Control type="text" placeholder="Enter Name" value={name} onChange={(e) => setName(e.target.value)} />
            </Form.Group>
            <Form.Group className="mb-3" controlId="password" style={{ width: '25%'}}>
                <Form.Label>Email</Form.Label>
                <Form.Control type="email" placeholder="Enter Email" value={email} onChange={(e) => setEmail(e.target.value)} />
            </Form.Group>
            <Button variant="danger" onClick={onSignupHandler}>
                회원가입
            </Button>
        </Form>
        </>
    );
};

export default Signup;