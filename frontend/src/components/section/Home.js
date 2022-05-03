import React from 'react';
import { Alert } from 'react-bootstrap';

const Home = () => {
    return (
        <div className='section'>
            <Alert variant="success">
                <Alert.Heading>DEVYoung개발 블로그 입니다.</Alert.Heading>
                <p>
                   매니저 권한을 가진 개발자들은 개발기술공유 페이지에 글을 작성할 수 있습니다. 
                </p>
                <p>
                   내 프로필에서 매니저 등급 신청을 진행해 주세요.(관리자 승인 후 등급 UP!)
                </p>
                <hr />
                <p className="mb-0">
                    일반 사용자는 댓글작성만 가능합니다.
                </p>
            </Alert>
        </div>
    );
};

export default Home;