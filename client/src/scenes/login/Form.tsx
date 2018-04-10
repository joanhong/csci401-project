import * as React from 'react';
import {
    Form,
    FormGroup,
    Col,
    FormControl,
    Button,
    ControlLabel
} from 'react-bootstrap';

interface LoginProps {
}
interface LoginState {
    email: string;
    password: string;
}
class LoginForm extends React.Component<LoginProps, LoginState> {
    constructor(props: LoginProps) {
        super(props);
        this.state = {
            email: '',
            password: ''
        };
        this.submitClicked = this.submitClicked.bind(this);
        this.handleChange = this.handleChange.bind(this);
    }
    submitClicked() {
        var request = new XMLHttpRequest();
        request.withCredentials = true;
        request.open('POST', 'http://localhost:8080/loginAttempt/');
        request.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
        var data = JSON.stringify({
            email: this.state.email,
            password: this.state.password
        });
        request.setRequestHeader('Cache-Control', 'no-cache');
        request.send(data);
        alert(request.responseText + 'Logging you in...');
        request.onreadystatechange = function() {
            if (request.readyState === 4) {
                if (request.responseText.length > 4) {
                    if (request.responseText === 'MultipleLogins') {
                    alert('Please log out of existing login.');
                    return;
                    }
                    alert('LOGIN SUCCESSFUL!');
                    if (request.responseText === 'Student') {
                        window.location.href = '/student';
                    }
                    if (request.responseText === 'Admin') {
                        window.location.href = '/admin';
                    }
                    if (request.responseText === 'Stakeholder') {
                        window.location.href = '/stakeholder';
                    }
                } else {
                        alert('LOGIN FAILED.');
                }
            }
        };
    }
    
    logOutClicked() {
        var request = new XMLHttpRequest();
        request.withCredentials = true;
        request.open('POST', 'http://localhost:8080/logoutAttempt/');
        request.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
        var data = 'logout';
        request.setRequestHeader('Cache-Control', 'no-cache');
        request.send(data);
        alert(request.responseText + 'Logging you out...');
        request.onreadystatechange = function() {
            if (request.readyState === 4) {
                if (request.responseText.length > 4) {
                    if (request.responseText === 'LoggedOut') {
                        alert('Logged out succesfully!');
                        return;
                    }
                    if (request.responseText === 'Failed') {
                        alert('No one is logged in.');
                    }
                } else {
                        alert('Error communicating with server.');
                }
            }
        };
    }
    
    handleChange(e: any) {
        this.setState({ [e.target.id]: e.target.value });
    }

    render() {
        return (
            <div>
            <Form horizontal={true} >
            <FormGroup controlId="formHorizontalEmail">
                <Col componentClass={ControlLabel} sm={2}>
                Email
                </Col>
                <Col sm={10}>
                <FormControl
                    type="text"
                    id="email"
                    value={this.state.email}
                    placeholder="Email"
                    onChange={e => this.handleChange(e)}
                />
                </Col>
            </FormGroup>

            <FormGroup controlId="formHorizontalPassword">
                <Col componentClass={ControlLabel} sm={2}>
                Password
                </Col>
                <Col sm={10}>
                <FormControl
                    type="password"
                    placeholder="Password"
                    id="password"
                    value={this.state.password}
                    onChange={e => this.handleChange(e)}
                />
                </Col>
            </FormGroup>

            <FormGroup>
                <Col smOffset={2} sm={10}>
                <Button type="submit" onClick={this.submitClicked}>Sign in</Button>
                </Col>
            </FormGroup>
            <FormGroup>
                <Col smOffset={2} sm={10}>
                <Button type="submit" onClick={this.logOutClicked}>Log Out</Button>
                </Col>
            </FormGroup>

        </Form>
        </div>
        );
    }
}

export default LoginForm;
