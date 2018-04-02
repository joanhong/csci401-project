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
    request.open('POST', 'http://localhost:8080/users/login');
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
                alert('LOGIN SUCCESSFUL!');
                alert(request.responseText);
                if (request.responseText === 'student') {
                    window.location.href = '/student';
                } else if (request.responseText === 'stakeholder') {
                    window.location.href = '/stakeholder';
                } else if (request.responseText === 'admin') {
                    window.location.href = '/admin';
                } 
            } else {
                alert('LOGIN FAILED.');
            }
        }
    };
    return false;
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
                <Button type="reset" onClick={this.submitClicked}>Sign in</Button>
                </Col>
            </FormGroup>

            <FormGroup>
                <Col smOffset={2} sm={10}>
                <Button href="/register">Register</Button>
                </Col>
            </FormGroup>
        </Form>
        </div>
        );
    }
}

export default LoginForm;
