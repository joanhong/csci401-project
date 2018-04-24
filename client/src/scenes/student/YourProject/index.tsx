import * as React from 'react';
import {
    Panel,
    Button
} from 'react-bootstrap';

interface ProjectProps {
}

interface ProjectState {
    projectName: string;
    stakeholderName: string;
    stakeholderEmail: string;
    stakeholderPhone: string;
    isLoading: boolean;
  }
  
interface User {
    name: string;
    email: string;
    phone: string;
}

class StudentProject extends React.Component<ProjectProps, ProjectState> {
    constructor(props: ProjectProps) {
        super(props);
        this.state = {
            projectName: '',
            stakeholderName: '',
            stakeholderEmail: '',
            stakeholderPhone: '',
            isLoading: false,
        };
    }
    componentDidMount() {
        this.setState({isLoading: true});

        var request = new XMLHttpRequest();
        request.withCredentials = true;
        request.open('POST', 'http://localhost:8080/getStakeholderByStudent/');
        request.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
        var data = sessionStorage.getItem('email');
        request.setRequestHeader('Cache-Control', 'no-cache');
        request.send(data);

        var that = this;
        request.onreadystatechange = function() {
            if (request.readyState === 4) {
                var response = request.responseText;
                if (response != null) {
                    var jsonResponse = JSON.parse(response);
                    var firstNameLiteral = 'firstName';
                    var lastNameLiteral = 'lastName';
                    var emailLiteral = 'email';
                    var phoneLiteral = 'phone';
                    that.setState({
                        stakeholderName: jsonResponse[firstNameLiteral] + ' ' + jsonResponse[lastNameLiteral], 
                        stakeholderEmail: jsonResponse[emailLiteral],
                        stakeholderPhone: jsonResponse[phoneLiteral],
                        isLoading: false
                    });
                }
            }
        };

        var request2 = new XMLHttpRequest();
        request2.withCredentials = true;
        request2.open('POST', 'http://localhost:8080/getProjectByStudent/');
        request2.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
        var data2 = sessionStorage.getItem('email');
        request2.setRequestHeader('Cache-Control', 'no-cache');
        request2.send(data2);

        var that2 = this;
        request2.onreadystatechange = function() {
            if (request2.readyState === 4) {
                var response2 = request2.responseText;
                if (response2 != null) {
                    var jsonResponse2 = JSON.parse(response2);
                    var projectNameLiteral = 'projectName';
                    that2.setState({
                        projectName: jsonResponse2[projectNameLiteral],
                        isLoading: false
                    });
                }
            }
        };
    }
    render() {
        return (
            <div>
            <Panel>
            <Panel.Heading>
                <Panel.Title componentClass="h3">Overview</Panel.Title>
            </Panel.Heading>
            <Panel.Body>
                <Panel>
                    <Panel.Heading>
                        Project
                    </Panel.Heading>
                    <Panel.Body>
                        {this.state.projectName}
                    </Panel.Body>
                </Panel>
                <Panel>
                    <Panel.Heading>
                        Stakeholder Contact Information
                    </Panel.Heading>
                    <Panel.Body>
                        {this.state.stakeholderName}    {this.state.stakeholderEmail}   {this.state.stakeholderPhone}
                    </Panel.Body>
                </Panel>
            </Panel.Body>
            </Panel>

            <Panel>
              <Panel.Heading>
                  Actions
              </Panel.Heading>
              <Panel.Body>
                  <Button>Submit Deliverable</Button>
                  <Button href="./weeklyreport">Submit Weekly Status Report</Button>
                  <Button href="./peerreview">Submit Peer Review Form</Button>
                  <Button>Submit Stakeholder Review Form</Button>
              </Panel.Body>
          </Panel>
        </div>
        );
    }
}

export default StudentProject;
