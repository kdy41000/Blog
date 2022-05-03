import http from 'http/customAxios';
import React, { useEffect, useState } from 'react';
import { Table, Button } from 'react-bootstrap';
import { useSelector, useDispatch } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { validateHandler } from 'utils/utils';

const Board = () => {
    const navigate = useNavigate();
    const { loginStatus, loginInfo } = useSelector((state) => state.authReducer);
    const [board, setBoard] = useState([]);
    //console.log("loginStatus: " , loginStatus);
    //console.log("loginInfo: " , loginInfo);
    useEffect(() => {
        getBoardList();
    },[]);

    const getBoardList = async () => {
        console.log("호출가즈아")
        await http.get('/api/v1/board/list')
        .then(data => {
            if(validateHandler(data)) {
                console.log("data:",data);
                setBoard(data);
                console.log("board:",board);
            }
        })
        .catch(err => {
            console.log(err);
        });
    }

    return (
        <div className='section'>
        <Table striped bordered hover variant="white">
            <thead>
                <tr>
                <th width="5%">No</th>
                <th width="10%">Language</th>
                <th width="30%">Title</th>
                <th width="10%">Writer</th>
                <th width="10%">WriterDate</th>
                <th width="5%">View</th>
                </tr>
            </thead>
            <tbody>
                {
                    board.length === 0 &&
                    <tr>
                        <td colSpan="6">게시글이 없습니다.</td>
                    </tr>
                }
                 {
                    board.length > 0 &&
                    board.map(p => 
                        <tr>
                            <td>{p.id}</td>
                            <td>{p.language}</td>
                            <td><a href="#" onClick={(e) => {e.preventDefault(); navigate(`/board/detail/${p.id}`); }}>{p.title}</a></td>
                            <td>{p.writer}</td>
                            <td>{p.writeDate}</td>
                            <td>{p.view}</td>
                        </tr>    
                    )
                }
            </tbody>
        </Table>
        {
            (loginStatus) && (loginInfo.roles === 'ROLE_MANAGER' || loginInfo.roles === 'ROLE_ADMIN') &&
            <Button variant="primary" style={{float:'right'}} onClick={() => navigate('/board/write')}>
                글쓰기
            </Button>
        }
        </div>
    );
};

export default Board;