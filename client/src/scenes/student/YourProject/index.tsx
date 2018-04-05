import * as React from 'react';
import {
    Panel,
    Button
} from 'react-bootstrap';

class StudentProject extends React.Component {
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
                    <Panel.Body/>
                </Panel>
                <Panel>
                    <Panel.Heading>
                        Stakeholder Contact Information
                    </Panel.Heading>
                    <Panel.Body/>
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
