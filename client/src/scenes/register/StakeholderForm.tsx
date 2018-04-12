import * as React from 'react';
import {
    Form,
    FormGroup,
    Col,
    FormControl,
    Button,
    ControlLabel,
    Row
} from 'react-bootstrap';

const style = {
    width: 600,
    float: 'none',
    margin: 'auto',
};

interface StakeholderRegistrationProps {
}
interface StakeholderRegistrationState {
    name: string;
    email: string;
    phone: string;
    company: string;
    password: string;
    confirm: string;
}
class StakeholderRegistrationForm extends React.Component<StakeholderRegistrationProps, StakeholderRegistrationState> {
    constructor(props: StakeholderRegistrationProps) {
        super(props);
        this.state = {
            name: '',
            email: '',
            phone: '',
            company: '',
            password: '',
            confirm: ''
        };
        this.submitClicked = this.submitClicked.bind(this);
        this.handleChange = this.handleChange.bind(this);
    }
    submitClicked() {
        var request = new XMLHttpRequest();
        request.withCredentials = true;
        request.open('POST', 'http://localhost:8080/users/stakeholderRegistrationAttempt/');
        request.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
        var data = JSON.stringify({
            name: this.state.name,
            email: this.state.email,
            phone: this.state.phone,
            company: this.state.company,
            password: this.state.password
        });
        request.setRequestHeader('Cache-Control', 'no-cache');
        request.send(data);
        request.onreadystatechange = function() {
        /*if (request.readyState === 4) {
            if (request.responseText.length > 4) {
                alert('Stakeholder registration SUCCESSFUL!');
            } else {
                alert('Stakeholder registration FAILED.');
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
            <Col componentClass={ControlLabel} sm={3}>
            {placeholder}
            </Col>
            <Col sm={8}>
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
            <div style={style}>
            <h2>Stakeholder Registration</h2>
            <Row>
                <Col>
                <div>
                <Form horizontal={true} >
                    {this.formGroup('formHorizontalName', 'name', 'Name', this.state.name)}
                    {this.formGroup('formHorizontalEmail', 'email', 'Email', this.state.email)}
                    {this.formGroup('formHorizontalPhone', 'phone', 'Phone', this.state.phone)}
                    {this.formGroup('formHorizontalCompany', 'company', 'Company/Organization', this.state.company)}
                    {this.formGroup('formHorizontalPassword', 'password', 'Password', this.state.password)}
                    {this.formGroup('formHorizontalConfirm', 'confirm', 'Confirm Password', this.state.confirm)}

                    <FormGroup>
                        <Col smOffset={3} sm={10}>
                        <Button type="submit" onClick={this.submitClicked}>Register</Button>
                        </Col>
                    </FormGroup>
                </Form>
                </div>
            </Col>
            </Row>
        </div>
        );
    }
}

export default StakeholderRegistrationForm;
