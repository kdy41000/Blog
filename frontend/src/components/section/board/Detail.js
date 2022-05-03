import React, { useEffect, useState } from 'react';
import http from 'http/customAxios';
import { useParams } from 'react-router-dom';
import { validateHandler } from 'utils/utils';
import { Form } from 'react-bootstrap';

const Detail = () => {
    let params = useParams();
    const [detailInfo, setDetailInfo] = useState({});

    useEffect(() => {
        getBoardDetail();
    },[]);

    const getBoardDetail = async () => {
        await http.get('/api/v1/board/detail', {params: {id: params.id}})
        .then(data => {
            if(validateHandler(data)) {
                console.log("data:",data);
                setDetailInfo(data);
            }
        })
        .catch(err => {
            console.log(err);
        });
    }

    return (
        <div className='section'>
        <Form>
            <h2>Title: {detailInfo.title}</h2>
            <Form.Group className="mb-3" controlId="title" style={{width: '25%'}}>
                <Form.Label>Language: {detailInfo.language}</Form.Label>
            </Form.Group>
            <Form.Group className="mb-3" controlId="title" style={{width: '25%'}}>
                <Form.Label>Writer: {detailInfo.writer}</Form.Label>
            </Form.Group>
            <Form.Group className="mb-3" controlId="title" style={{width: '25%'}}>
                <Form.Label>WriteDate: {detailInfo.writeDate}</Form.Label>
            </Form.Group>
            <Form.Group className="mb-3" controlId="title" style={{width: '25%'}}>
                <Form.Label>View: {detailInfo.view}</Form.Label>
            </Form.Group>
        </Form>
        <hr />
        <div dangerouslySetInnerHTML={{__html: detailInfo.content}}></div>
        </div>
    );
};

export default Detail;