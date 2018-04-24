import * as React from 'react';
import {
    Panel,
    Button,
    Table,
    Form,
    FormGroup,
    Col,
    FormControl,
    ControlLabel,
} from 'react-bootstrap';

interface ProfileProps {
}
interface ProfileState {
    firstName: string;
    lastName: string;
    email: string;
    phone: string;
    isLoading: boolean;
}

class StudentProfile extends React.Component<ProfileProps, ProfileState> {
    constructor(props: ProfileProps) {
        super(props);
        this.state = {
            firstName: '',
            lastName: '',
            email: '',
            phone: '',
            isLoading: false,
        };
        this.submitClicked = this.submitClicked.bind(this);
        this.handleChange = this.handleChange.bind(this);
    }
    componentDidMount() {
        this.setState({isLoading: true});

        var request = new XMLHttpRequest();
        request.withCredentials = true;
        request.open('POST', 'http://localhost:8080/loggedInUser/');
        request.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
        var data = sessionStorage.getItem('email');
        request.setRequestHeader('Cache-Control', 'no-cache');
        request.send(data);

        var that = this;
        request.onreadystatechange = function() {
            if (request.readyState === 4) {
                var response = request.responseText;
                var jsonResponse = JSON.parse(response);
                var firstNameLiteral = 'firstName';
                var lastNameLiteral = 'lastName';
                var emailLiteral = 'email';
                var phoneLiteral = 'phone';

                that.setState({
                    firstName: jsonResponse[firstNameLiteral], 
                    lastName: jsonResponse[lastNameLiteral],
                    email: jsonResponse[emailLiteral],
                    phone: jsonResponse[phoneLiteral],
                    isLoading: false
                });
            }
        };
    }
    submitClicked() {
        var request = new XMLHttpRequest();
        request.withCredentials = true;
        request.open('POST', 'http://localhost:8080/userProfileUpdate/');
        request.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
        var data = JSON.stringify({
            firstName: this.state.firstName,
            lastName: this.state.lastName,
            email: this.state.email,
            phone: this.state.phone
        });
        request.setRequestHeader('Cache-Control', 'no-cache');
        request.send(data);
        alert(request.responseText + 'Profile updating ...');
        request.onreadystatechange = function() {
            if (request.readyState === 4) {
                alert('Profile update submission SUCCESSFUL!');
            }
        }; 
    }
    handleChange(e: any) {
        this.setState({ [e.target.id]: e.target.value });
    }
    render() {
        if (this.state.isLoading) {
            return <p>Loading...</p>;
        }

        return (
            <div>
            <Panel>
            <Panel.Heading>
                Profile
            </Panel.Heading>
            <Panel.Body>
            <Form horizontal={true}>
                <FormGroup controlId="formHorizontalStudentName">
                    <Col componentClass={ControlLabel} sm={2}>
                        Name:
                    </Col>
                    <Col sm={5}>
                        <FormControl 
                            type="text" 
                            id="firstName"
                            value={this.state.firstName}
                            onChange={e => this.handleChange(e)} 
                        />
                    </Col>  
                    <Col sm={5}>
                        <FormControl 
                            type="text" 
                            id="lastName"
                            value={this.state.lastName}
                            onChange={e => this.handleChange(e)} 
                        />
                    </Col>                                 
                </FormGroup>
                
                <FormGroup controlId="formHorizontalStudentEmail">
                    <Col componentClass={ControlLabel} sm={2}>
                        Email:
                    </Col>
                    <Col sm={10}>
                        <FormControl 
                            type="email" 
                            id="email"
                            value={this.state.email} 
                            disabled={true} 
                        />
                    </Col>             
                </FormGroup>
                
                <FormGroup controlId="formHorizontalStudentPhone">
                    <Col componentClass={ControlLabel} sm={2}>
                        Phone:
                    </Col>
                    <Col sm={10}>
                        <FormControl 
                            type="tel" 
                            id="phone"
                            value={this.state.phone}
                            onChange={e => this.handleChange(e)} 
                        />
                    </Col>             
                </FormGroup> 
                
                <FormGroup>
                    <Col smOffset={2} sm={10}>
                        <Button type="submit" bsStyle="primary" onClick={this.submitClicked}>Edit/Save Profile</Button>
                    </Col>
                </FormGroup>               
            </Form>    
            </Panel.Body>
            </Panel>
        </div>
        );
    }
}

export default StudentProfile;