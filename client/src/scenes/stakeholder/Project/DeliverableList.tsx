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
}

class DeliverableList extends React.Component<DeliverableProps, DeliverableState> {

    constructor(props: DeliverableProps) {
        super(props);
        
        this.state = {
            deliverables: [],
            isLoading: false
        };
    }

    componentDidMount() {
        this.setState({isLoading: true});
        
        fetch('http://localhost:8080/deliverable/0')
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
                                        <Button>Approve</Button>
                                        <Button>Reject</Button>
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