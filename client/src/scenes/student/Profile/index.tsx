import * as React from 'react';
import {
    Panel,
    Button,
    Table,
    Form,
    FormGroup,
    Col,
    FormControl,
    ControlLabel    
} from 'react-bootstrap';

interface ProfileProps {
}
interface ProfileState {
    name: string;
    email: string;
    phone: string;
    isLoading: boolean;
}
var studentName = '';

class StudentProfile extends React.Component<ProfileProps, ProfileState> {
    constructor(props: ProfileProps) {
        super(props);
        this.state = {
            name: '',
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
        var data = 'loggedIn';
        request.setRequestHeader('Cache-Control', 'no-cache');
        request.send(data);

        var that = this;
        request.onreadystatechange = function() {
            if (request.readyState === 4) {
                var response = request.responseText;
                var jsonResponse = JSON.parse(response);
                var fullNameLiteral = 'fullName';
                var emailLiteral = 'email';
                var phoneLiteral = 'phone';

                studentName = jsonResponse[fullNameLiteral];

                that.setState({
                    name: jsonResponse[fullNameLiteral], 
                    email: jsonResponse[emailLiteral],
                    phone: jsonResponse[phoneLiteral],
                    isLoading: false
                });
            }
        };
    }
    submitClicked() {
   /*     var request = new XMLHttpRequest();
        request.withCredentials = true;
        request.open('POST', 'http://localhost:8080//');
        request.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
        var data = JSON.stringify({
            fullName: this.state.name,
            email: this.state.email,
            phone: this.state.phone
        });
        request.setRequestHeader('Cache-Control', 'no-cache');
        request.send(data);
        alert(request.responseText + 'Logging you in...');
        request.onreadystatechange = function() {
            if (request.readyState === 4) {
            }
        }; */
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
                    <Col sm={10}>
                        <FormControl 
                            type="text" 
                            id="name"
                            value={studentName}
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
                            onChange={e => this.handleChange(e)} 
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