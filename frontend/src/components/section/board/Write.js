import React, { useState } from 'react';
import { Form, Button, Dropdown, DropdownButton } from 'react-bootstrap';
import { validateHandler } from 'utils/utils';
import { CustomCKEditor } from 'utils/ckeditor/ckeditor';
import http from 'http/customAxios';
import { useSelector, useDispatch } from 'react-redux';
import { useNavigate } from 'react-router-dom';

const Write = () => {
    const navigate = useNavigate();
    const [title, setTitle] = useState('');
    const [language, setLanguage] = useState('');
    const [content, setContent] = useState('');

    const { loginStatus, loginInfo } = useSelector((state) => state.authReducer);

    const onClickWriteHandler = async () => {
        if(!validateHandler(title)) return alert('Title을 작성하세요.');
        if(!validateHandler(language)) return alert('언어를 선택하세요.');

        const params = {};
        params.title = title;
        params.language = language;
        params.content = content;
        params.writer = loginInfo.userId;
        params.view = 0;
console.log("params:",params);
        await http.post('/api/v1/manager/board/write', params)
        .then(data => {
            console.log("data:",data);
            alert(data.returnMsg);

            if(data.returnCode > 0) {
                console.log("완료");
                navigate(-1);
            }
        })
        .catch(err => {
            console.log(err);
        });
    }

    return (
        <div className='section'>
        <Form>
            <h2>개발기술 글쓰기</h2>
            <Form.Group className="mb-3" controlId="title" style={{width: '25%'}}>
                <Form.Label>Title</Form.Label>
                <Form.Control type="text" placeholder="Enter Title" value={title} onChange={(e) => setTitle(e.target.value)} />
            </Form.Group>

            <Form.Group className="mb-3" controlId="password" style={{ width: '25%'}}>
                <Form.Label>Language</Form.Label>
                <DropdownButton id="dropdown-basic-button" title={language === '' ? '언어를 선택하세요.': language} onSelect={(eventKey) => setLanguage(eventKey)}>
                    <Dropdown.Item eventKey="자바">자바</Dropdown.Item>
                    <Dropdown.Item eventKey="파이썬">파이썬</Dropdown.Item>
                    <Dropdown.Item eventKey="리액트">리액트</Dropdown.Item>
                </DropdownButton>
            </Form.Group>
        </Form>
        <CustomCKEditor setContent={setContent} />
            <Button variant="danger" onClick={onClickWriteHandler}>
                글쓰기
            </Button>
        </div>
    );
};

export default Write;