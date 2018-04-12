import * as React from 'react';
import {
    Panel,
    Col
} from 'react-bootstrap';
import LoginForm from './Form';
import UserNav from '../../components/navigation/UserTypes';

class Login extends React.Component {
    render() {
        return (
            <div>
            <UserNav showTabs={false}/>

            <Col mdOffset={1} lg={5} md={5} sm={5}>
            <Panel>
                <LoginForm/>
            </Panel>
            </Col>
            </div>
        );
    }
}

export default Login;