import * as React from 'react';
import {
    Panel,
    Table, 
    Alert,
    Button
} from 'react-bootstrap';
import { 
    LinkContainer
} from 'react-router-bootstrap';
const viewIcon = require('../../../svg/viewIcon.svg');
const style = {
    width: 1000,
    float: 'none',
    margin: 'auto'
};
interface Project {
    projectId: number;
    name: string;
    status: string;
}

interface HomeState {
    projects: Array<{}>;
    isLoading: Boolean;
}

interface HomeProps {
}

class StakeholderHome extends React.Component<HomeProps, HomeState> {
    constructor(props: HomeProps) {
        super(props);
        this.state = {
            projects: [],
            isLoading: true
        };
    }

    componentDidMount() {
        this.setState({isLoading: true});

        fetch('http://localhost:8080/projects/' + sessionStorage.getItem('email'))
        .then(response => response.json())
        .then(data => this.setState({projects: data, isLoading: false}));
    }

    render() {
        const {projects, isLoading} = this.state;

        if (isLoading) {
            return <p>Loading...</p>;
        }

        return (
            <div style={style}>

            <h3>Welcome back!</h3>

            <Panel>
                <Panel.Heading>
                    <Panel.Title componentClass="h3">Your Projects</Panel.Title>
                </Panel.Heading>
                <Panel.Body>
                    <Table>
                        <thead>
                            <th>Project</th>
                            <th>Status</th>
                            <th>View/Edit</th>
                        </thead>
                        <tbody>
                            {projects.map((project: Project, index: number) =>
                                <tr key={project.projectId}>
                                    <td>{project.name}</td>
                                    <td>{project.status}</td>
                                    <td>
                                        <LinkContainer to={{pathname: 'stakeholder/project/' + project.projectId}}>
                                        <img src={viewIcon}/>
                                        </LinkContainer>
                                    </td>
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

export default StakeholderHome;