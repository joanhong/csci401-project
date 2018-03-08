import * as React from 'react';
import {
  Panel,
  Alert
} from 'react-bootstrap';

class AdminHome extends React.Component {
  
  render() {
    return (
          <div>
            <Panel>
                <Panel.Heading>
                    <Panel.Title componentClass="h3">Notifications</Panel.Title>
                </Panel.Heading>
                <Panel.Body>
                    <Alert bsStyle="info">
                        Notification 1.
                    </Alert>
                </Panel.Body>
            </Panel>
          </div>
    );
  }
}

export default AdminHome;