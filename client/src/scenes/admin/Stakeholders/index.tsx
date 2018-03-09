import * as React from 'react';
import {
    Panel,
    Button,
    Table,
    Form,
    FormGroup,
    FormControl,
} from 'react-bootstrap';

class Stakeholders extends React.Component {
    render() {
        return (
        <div>
            <Panel>
            <Form horizontal={true}>
                <FormGroup controlId="formInlineSearch">
                    <FormControl type="text" placeholder="Search..." />
                </FormGroup>
                <Button type="submit" bsStyle="primary">Search Stakeholders</Button>
            </Form>
            </Panel>
            <Panel>
              <Panel.Heading>
                  Stakeholders
              </Panel.Heading>
              <Panel.Body>
                  <Table>
                      <thead>
                          <tr>
                              <th>Name</th>
                              <th>Company/Organization</th>
                              <th>Contact Email</th>
                              <th>Average Rating</th>
                              <th>Student Reviews</th>
                          </tr>
                      </thead>
                      <tbody>
                          <tr>
                              <td>Bill Smith</td>
                              <td>USC Computer Science Professor</td>
                              <td>bjsmith@usc.edu</td>
                              <td>4.5</td>
                              <td>View</td>
                          </tr>
                            <tr>
                              <td>LAPD</td>
                              <td>Los Angeles Police Department</td>
                              <td>jburns@lapd.la.ca.gov</td>
                              <td>4.2</td>
                              <td>View</td>
                          </tr>
                      </tbody>
                  </Table>
              </Panel.Body>
          </Panel>
        </div>
        );
    }
}

export default Stakeholders;