import * as React from 'react';
import {
    Panel,
    Table,
    Button
} from 'react-bootstrap';

interface Deliverable {
    deliverableNumber: number;
    name: string;
    description: string;
    projectNumber: number;
}
interface DeliverableProps {
}

interface DeliverableState {
    deliverables: Array<{}>;
    isLoading: boolean;
    deliverableNumber: number;
}

class DeliverableList extends React.Component<DeliverableProps, DeliverableState> {

    constructor(props: DeliverableProps) {
        super(props);
        
        this.state = {
            deliverables: [],
            isLoading: false,
            deliverableNumber: 0
        };
    }

    approveDeliverable(name) {
        var request = new XMLHttpRequest();
        request.withCredentials = true;
        request.open('POST', 'http://localhost:8080/deliverables/0/'+ name + '/approve');
        request.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
        var data = JSON.stringify({
            status: 'Approved'
        });
        request.setRequestHeader('Cache-Control', 'no-cache');
        request.send(data);
    }

    rejectDeliverable(name) {
        var request = new XMLHttpRequest();
        request.withCredentials = true;
        request.open('POST', 'http://localhost:8080/deliverables/0/'+ name + '/reject');
        request.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
        var data = JSON.stringify({
            status: 'Rejected'
        });
        request.setRequestHeader('Cache-Control', 'no-cache');
        request.send(data);
    }

    componentDidMount() {
        this.setState({isLoading: true});
        
        fetch('http://localhost:8080/deliverables/0')
            .then(response => response.json())
            .then(data => this.setState({deliverables: data, isLoading: false}));
    }

    render() {
        const {deliverables, isLoading} = this.state;
        
        if (isLoading) {
            return <p>Loading...</p>;
        }
        return(
            <Panel>
                <Panel.Heading>
                    <Panel.Title componentClass="h3">Projects</Panel.Title>
                </Panel.Heading>
                <Panel.Body>
                    <Table>
                        <thead>
                            <th>Deliverable Number</th>
                            <th>Submission Date</th>
                            <th>Download</th>
                            <th>Approve</th>
                        </thead>
                        <tbody>
                            {deliverables.map((deliverable: Deliverable) =>
                                <tr key={deliverable.projectNumber}>
                                    <td>Deliverable {deliverable.deliverableNumber}</td>
                                    <td>Date</td>
                                    <td><Button>Download</Button></td>
                                    <td>
                                        <input type="Button" name="'{deliverable.deliverableNumber}'" onClick={this.approveDeliverable(this)}>Approve</input>
                                        <Button onClick={this.rejectDeliverable}>Reject</Button>
                                    </td>
                                </tr>
                            )}
                        </tbody>
                    </Table>
                </Panel.Body>
            </Panel>
        );
    }
}

export default DeliverableList;