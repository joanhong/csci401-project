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

class SubmittedDeliverables extends React.Component<DeliverableProps, DeliverableState> {
    
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
                        Submitted Deliverables
                    </Panel.Heading>
                    <Panel.Body>
                    <Table>
                        <thead>
                            <tr>
                                <th>Deliverable Number</th>
                                <th>Submission Date</th>
                                <th>Download</th>
                                <th>Status</th>
                                <th>Resubmit</th>
                            </tr>
                        </thead>
                        <tbody>
                            {deliverables.map((deliverable: Deliverable) =>
                                <tr key={deliverable.projectNumber}>
                                    <td>{deliverable.deliverableNumber}</td>
                                    <td>Date</td>
                                    <td>{deliverable.name}</td>
                                    <td>Status</td>
                                    <td><Button>Resubmit</Button></td>
                                </tr>
                            )}
                        </tbody>
                    </Table>
                    </Panel.Body>
                </Panel>
        );
    }
}

export default SubmittedDeliverables;
