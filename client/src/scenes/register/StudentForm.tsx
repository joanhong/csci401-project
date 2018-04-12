import * as React from 'react';
import {
    Form,
    FormGroup,
    Col,
    FormControl,
    Button,
    ControlLabel
} from 'react-bootstrap';

interface StudentRegistrationProps {
}
interface StudentRegistrationState {
name: string;
email: string;
phone: string;
password: string;
confirm: string;
}
class StudentRegistrationForm extends React.Component<StudentRegistrationProps, StudentRegistrationState> {
    constructor(props: StudentRegistrationProps) {
    super(props);
    this.state = {
        name: '',
        email: '',
        phone: '',
        password: '',
        confirm: ''
    };
    this.submitClicked = this.submitClicked.bind(this);
    this.handleChange = this.handleChange.bind(this);
    }

    submitClicked() {
        var request = new XMLHttpRequest();
        request.withCredentials = true;
        request.open('POST', 'http://localhost:8080/users/studentRegistrationAttempt/');
        request.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
        var data = JSON.stringify({
            name: this.state.name,
            email: this.state.email,
            phone: this.state.phone,
            password: this.state.password
        });
        request.setRequestHeader('Cache-Control', 'no-cache');
        request.send(data);
        request.onreadystatechange = function() {
        /*if (request.readyState === 4) {
                if (request.responseText.length > 4) {
                    alert('Student registration SUCCESSFUL!');
                } else {
                    alert('Student registration FAILED.');
        }
        }*/
        };
    }

    handleChange(e: any) {
    this.setState({ [e.target.id]: e.target.value });
    }

    formGroup(controlId: string, id: string, placeholder: string, value: any) {
        return (
            <FormGroup controlId={controlId}>
                <Col componentClass={ControlLabel} sm={2}>
                {placeholder}
                </Col>
                <Col sm={10}>
                <FormControl
                    type="text"
                    id={id}
                    value={value}
                    placeholder={placeholder}
                    onChange={e => this.handleChange(e)}
                />
                </Col>
            </FormGroup>
        );
        
    }

    render() {
        return (
            <div>
            <Form horizontal={true} >
            {this.formGroup('formHorizontalName', 'name', 'Name', this.state.name)}
            {this.formGroup('formHorizontalEmail', 'email', 'Email', this.state.email)}
            {this.formGroup('formHorizontalPhone', 'phone', 'Phone', this.state.phone)}
            {this.formGroup('formHorizontalPassword', 'password', 'Password', this.state.password)}
            {this.formGroup('formHorizontalConfirm', 'confirm', 'Confirm Password', this.state.confirm)}

            <FormGroup>
                <Col smOffset={2} sm={10}>
                <Button type="submit" onClick={this.submitClicked}>Register</Button>
                </Col>
            </FormGroup>
        </Form>
        </div>
        );
    }
}

export default StudentRegistrationForm;
