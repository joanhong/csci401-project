import * as React from 'react';
import {
    Panel,
    Button
} from 'react-bootstrap';

interface ProjectProps {
}

interface ProjectState {
   // groupMembers: Array<{}>;
    stakeholder: string;
    isLoading: boolean;
  }
  
interface User {
    name: string;
    email: string;
    phone: string;
}
var x = 'd';

class StudentProject extends React.Component<ProjectProps, ProjectState> {
    constructor(props: ProjectProps) {
        super(props);
        this.state = {
            stakeholder: '',
            isLoading: false,
        };
    }
    componentDidMount() {
        this.setState({isLoading: true});
/*
        var request = new XMLHttpRequest();
        request.withCredentials = true;
        request.open('POST', 'http://localhost:8080/getProjectByUser/');
        request.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
        var data = 'getproject';
        request.setRequestHeader('Cache-Control', 'no-cache');
        request.send(data);

        var that = this;
        request.onreadystatechange = function() {
            if (request.readyState === 4) {
                var response = request.responseText;
                if (response != null) {
                    var jsonResponse = JSON.parse(response);
                    var stakeholderNameLiteral = 'stakeholderName';
                    that.setState({
                        stakeholder: jsonResponse[stakeholderNameLiteral], 
                        isLoading: false
                    });
                }
            }
        }; */
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
                        Team Contact Information
                    </Panel.Heading>
                    <Panel.Body>
                        {this.state.stakeholder};
                    </Panel.Body>
                </Panel>
                <Panel>
                    <Panel.Heading>
                        Stakeholder Contact Information
                    </Panel.Heading>
                    <Panel.Body>
                        {this.state.stakeholder};
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
