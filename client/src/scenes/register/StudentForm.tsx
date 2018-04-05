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
emails: string;
}
class StudentRegistrationForm extends React.Component<StudentRegistrationProps, StudentRegistrationState> {
constructor(props: StudentRegistrationProps) {
super(props);
this.state = {
emails: ''
};
this.submitClicked = this.submitClicked.bind(this);
this.handleChange = this.handleChange.bind(this);
}
submitClicked() {
var request = new XMLHttpRequest();
request.withCredentials = true;
request.open('POST', 'http://localhost:8080/StudentRegistrationAttempt/');
request.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
var data = JSON.stringify({
emails: this.state.emails,
});
request.setRequestHeader('Cache-Control', 'no-cache');
request.send(data);
alert(request.responseText + 'Sending out invites...');
request.onreadystatechange = function() {
if (request.readyState === 4) {
    alert('Invites sent succesfully!');
}
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
            <FormGroup controlId="formHorizontalEmails">
            <Col componentClass={ControlLabel} sm={2}>
            Emails
            </Col>
            <Col sm={10}>
            <FormControl
                type="text"
                id="emails"
                value={this.state.emails}
                placeholder="Emails"
                onChange={e => this.handleChange(e)}
                componentClass="textarea"
                style={{height: '500px', width: '750px'}}
            />
            </Col>
            </FormGroup>
            <FormGroup>
                <Col smOffset={2} sm={10}>
                <Button type="submit" onClick={this.submitClicked}>Send Invites</Button>
                </Col>
            </FormGroup>
        </Form>
        </div>
        );
    }
}

export default StudentRegistrationForm;
