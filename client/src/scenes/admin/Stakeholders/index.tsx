import * as React from 'react';
import {
    Panel,
    Button,
    Table,
    Form,
    FormGroup,
    FormControl,
} from 'react-bootstrap';

interface StakeholderListProps {
}

interface StakeholderListState {
stakeholders: Array<{}>;
isLoading: boolean;
}

interface Stakeholder {
    name: string;
    companyName: string;
    email: string;
}

class Stakeholders extends React.Component<StakeholderListProps, StakeholderListState> {
    constructor(props: StakeholderListProps) {
        super(props);
        
        this.state = {
            stakeholders: [],
            isLoading: false
        };
    }
    componentDidMount() {
        this.setState({isLoading: true});
        
        fetch('http://localhost:8080/users/stakeholders')
            .then(response => response.json())
            .then(data => this.setState({stakeholders: data, isLoading: false}));
    }

    render() {
        const {stakeholders, isLoading} = this.state;

        if (isLoading) {
            return <p>Loading...</p>;
        }

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
                        {stakeholders.map((stakeholder: Stakeholder) =>
                            <tr key={stakeholder.name}>
                                <td>{stakeholder.name}</td>
                                <td>{stakeholder.companyName}</td>
                                <td>{stakeholder.email}</td>
                                <td>0</td>
                                <td><Button>View</Button></td>
                            </tr>
                        )}
                    </tbody>
                  </Table>
              </Panel.Body>
          </Panel>
        </div>
        );
    }
}

export default Stakeholders;