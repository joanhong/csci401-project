import * as React from 'react';
import {
    Panel,
    Table
} from 'react-bootstrap';
import { 
    LinkContainer
} from 'react-router-bootstrap';
const viewIcon = require('../../../svg/viewIcon.svg');

class StakeholderHome extends React.Component {

    render() {
        return (
            <div>
            <Panel>
                <Panel.Heading>
                    <Panel.Title componentClass="h3">Projects</Panel.Title>
                </Panel.Heading>
                <Panel.Body>
                    <Table>
                        <thead>
                            <th>Name</th>
                            <th>Submission Date</th>
                            <th>Status</th>
                            <th>View/Edit</th>
                        </thead>
                        <tbody>
                            <tr>
                                <td>Project1</td>
                                <td>Jan 2, 2018</td>
                                <td>Approved</td>
                                <td>
                                    <LinkContainer to="/stakeholder/project">
                                    <img src={viewIcon}/>
                                    </LinkContainer>
                                </td>
                            </tr>
                        </tbody>
                    </Table>
                </Panel.Body>
            </Panel>
            </div>
        );
    }
}

export default StakeholderHome;