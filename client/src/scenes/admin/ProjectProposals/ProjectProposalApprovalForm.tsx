import * as React from 'react';

import {
  Button,
  Table,
  Form,
  FormGroup,
  FormControl
} from 'react-bootstrap';

interface ProjectListProps {
}

interface ProjectListState {
projects: Array<{}>;
isLoading: boolean;
selected: boolean;
// <Button onClick={this.toggleCheckboxes}>Select All</Button>
}

interface Project {
    id: number;
    projectName: string;
    projectNumber: number;
    status: string;
    minSize: number;
    maxSize: number;
}

class ProjectProposalApprovalForm extends React.Component<ProjectListProps, ProjectListState> {
    constructor(props: ProjectListProps) {
        super(props);
        
        this.state = {
            projects: [],
            isLoading: false,
            selected: false,
        };
        this.submitClicked = this.submitClicked.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.toggleCheckboxes = this.toggleCheckboxes.bind(this);
    }
    submitClicked() {
        var request = new XMLHttpRequest();
        request.withCredentials = true;
        request.open('POST', 'http://localhost:8080/projectsrep/');
        request.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
        var data = JSON.stringify({
        projects: this.state.projects,
        });
        request.setRequestHeader('Cache-Control', 'no-cache');
        request.send(data); 
        alert('Projects have been approved!');

    }
    handleChange(e: any) {
        let projects = this.state.projects;
        let name = e.target.value;
        {projects.map((project: Project) => {
            if (project.projectName === name && e.target.checked) {
                project.status = 'Approved';
            } else if (project.projectName === name && !e.target.checked) {
                project.status = 'Pending Approval';
            }
        }); }

        this.setState({
            projects: projects
         });
    }
    toggleCheckboxes() {
        if (this.state.selected === false) {
            this.setState({
                selected: true
            });
        } else {
            this.setState({
                selected: false
            });
        }
    }    
    componentDidMount() {
        this.setState({isLoading: true});
        
        fetch('http://localhost:8080/projectsrep')
            .then(response => response.json())
            .then(data => this.setState({projects: data, isLoading: false}));
    }
    
    render() {
        const {projects, isLoading} = this.state;
        
        if (isLoading) {
            return <p>Loading...</p>;
        }

        return(
            <div>
                <h2>Project Proposals</h2>
                <Form>
                    <FormGroup>
                        <Button type="submit" onClick={this.submitClicked}>Approve Projects</Button>
                    </FormGroup>
                <Table bordered={true}>
                    <thead>
                        <tr>
                            <th>Select</th>
                            <th>Project Name</th>
                            <th>Project Number</th>
                            <th>Project Status</th>
                            <th>Min Size</th>
                            <th>Max Size</th>
                        </tr>
                    </thead>
                    <tbody>
                        {projects.map((project: Project) =>
                            <tr key={project.projectNumber}>
                                <td>               
                                    <FormControl
                                        type="checkbox"
                                        id="select"
                                        checked={this.state.selected[project.projectNumber]}
                                        value={project.projectName}
                                        onChange={e => this.handleChange(e)}
                                    />
                                </td>
                                <td>{project.projectName}</td>
                                <td>{project.projectNumber}</td>
                                <td>{project.status}</td>
                                <td>{project.minSize}</td>
                                <td>{project.maxSize}</td>
                            </tr>
                        )}
                    </tbody>
                </Table>
                </Form>
            </div>);
    }
}

export default ProjectProposalApprovalForm;
