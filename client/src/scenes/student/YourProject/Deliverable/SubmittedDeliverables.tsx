import * as React from 'react';
import {
    Panel,
    Table,
    Button
} from 'react-bootstrap';

interface Deliverable {
    deliverableNumber: number;
    deliverableFile: File;
    name: string;
    description: string;
    dueDate: Date;
    status: string;
    dateSubmitted: Date;
    projectNumber: number;
    fileName: string;
}

interface DeliverableProps {
}

interface DeliverableState {
    deliverables: Array<{}>;
    isLoading: boolean;
    deliverableFile: File;
    reload: boolean;
}

class SubmittedDeliverables extends React.Component<DeliverableProps, DeliverableState> {
    
    constructor(props: DeliverableProps) {
        super(props);
        
        this.state = {
            deliverables: [],
            isLoading: false,
            deliverableFile: new File([], ''),
            reload: true
        };
    }

    handleFileChange(e: any) {
        this.setState({ deliverableFile: e.target.files[0] });
    }

    uploadFile(deliverableNumber: number) {
        var request = new XMLHttpRequest();
        request.withCredentials = true;
        request.open('POST', 'http://localhost:8080/upload');
        // request.setRequestHeader('Content-Type', 'multipart/form-data; charset=UTF-8');
        request.setRequestHeader('Cache-Control', 'no-cache');
        const formData = new FormData();
        formData.append('file', this.state.deliverableFile);
        request.send(formData);
        alert(request.responseText + 'Uploaded file');
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
                                    <td>{deliverable.dateSubmitted}</td>
                                    <td><Button>{deliverable.fileName}</Button></td>
                                    <td>{deliverable.status}</td>
                                    <td>
                                        <input type="file" onChange={e => this.handleFileChange(e)} />
                                        <Button onClick={() => this.uploadFile(deliverable.deliverableNumber)}>Save</Button>
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

export default SubmittedDeliverables;
