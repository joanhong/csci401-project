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

class StudentProfile extends React.Component {
    render() {
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
                        <FormControl type="text" value="Student Name" />
                    </Col>             
                </FormGroup>
                
                <FormGroup controlId="formHorizontalStudentEmail">
                    <Col componentClass={ControlLabel} sm={2}>
                        Name:
                    </Col>
                    <Col sm={10}>
                        <FormControl type="email" value="studentname@usc.edu" />
                    </Col>             
                </FormGroup>
                
                <FormGroup controlId="formHorizontalStudentPhone">
                    <Col componentClass={ControlLabel} sm={2}>
                        Phone:
                    </Col>
                    <Col sm={10}>
                        <FormControl type="tel" />
                    </Col>             
                </FormGroup> 
                
                <FormGroup>
                    <Col smOffset={2} sm={10}>
                        <Button type="submit" bsStyle="primary">Edit/Save Profile</Button>
                    </Col>
                </FormGroup>               
            </Form>    
            </Panel.Body>
            </Panel>

            <Panel>
              <Panel.Heading>
                  Course Conflicts
              </Panel.Heading>
              <Panel.Body>
                  <Table>
                      <thead>
                          <tr>
                              <th>Course</th>
                              <th>Start</th>
                              <th>End</th>
                          </tr>
                      </thead>
                      <tbody>
                          <tr>
                              <td>Course 1</td>
                              <td>4:00 PM</td>
                              <td>5:00 PM</td>
                          </tr>
                      </tbody>
                  </Table>
                  <Button bsStyle="primary">Add Course Conflict</Button>
              </Panel.Body>
          </Panel>
        </div>
        );
    }
}

export default StudentProfile;