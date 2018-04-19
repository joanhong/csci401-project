import * as React from 'react';
import {
    Form,
    FormGroup,
    Col,
    FormControl,
    Button,
    ControlLabel,
    Panel
} from 'react-bootstrap';

interface ProjectProps {
    projectId: string;
}
interface Project {
    name: string;
    minSize: string;
    technologies: string;
    background: string;
    description: string;
}
interface ProjectState {
    project: Project;
    isLoading: Boolean;
}

class ProjectInformation extends React.Component<ProjectProps, ProjectState> {
constructor(props: ProjectProps) {
    super(props);
    this.state = {
        project: {name: '',
        minSize: '',
        technologies: '',
        background: '',
        description: ''},
        isLoading: true
    };
    this.handleChange = this.handleChange.bind(this);
}

    componentDidMount() {
        fetch('http://localhost:8080/projects/' + sessionStorage.getItem('email') + '/' + this.props.projectId)
            .then(response => response.json())
            .then(data => this.setState({project: data, isLoading: false}));
    }

    handleChange(e: any) {
    this.setState({ [e.target.id]: e.target.value });

    }

    render() {
        return (
        <Panel>
        <Panel.Heading>Project Information</Panel.Heading>
        <Panel.Body>
        <Form horizontal={true} >
            <FormGroup controlId="formHorizontalProjectName">
                <Col componentClass={ControlLabel} sm={2}>
                    <b>Project Name</b>
                </Col>
                <Col sm={10}>
                <FormControl
                    type="text"
                    id="projectName"
                    value={this.state.project.name}
                    onChange={e => this.handleChange(e)}
                    placeholder="Project Name"
                />
                </Col>
            </FormGroup>

            <FormGroup controlId="formHorizontalNumberStudents">
                <Col componentClass={ControlLabel} sm={2}>
                    <b>Number of Students</b>
                </Col>
                <Col sm={10}>
                <FormControl
                    type="text"
                    id="projectSize"
                    placeholder="Number of Students"
                    onChange={e => this.handleChange(e)}
                    value={this.state.project.minSize}
                />
                </Col>
            </FormGroup>
            
            <FormGroup controlId="formHorizontalTechnologies">
                <Col componentClass={ControlLabel} sm={2}>
                    <b>Technologies Expected</b>
                </Col>
                <Col sm={10}>
                <FormControl
                    type="text"
                    id="technologiesExpected"
                    value={this.state.project.technologies}
                    placeholder="Technologies expected"
                    onChange={e => this.handleChange(e)}
                />
                </Col>
            </FormGroup>
            
            <FormGroup controlId="formHorizontalBackground">
                <Col componentClass={ControlLabel} sm={2}>
                    <b>Background Requested</b>
                </Col>
                <Col sm={10}>
                <FormControl
                    type="text"
                    id="backgroundRequested"
                    value={this.state.project.background}
                    placeholder="Background requested"
                    onChange={e => this.handleChange(e)}
                />
                </Col>
            </FormGroup>
            
            <FormGroup controlId="formHorizontalDescription">
                <Col componentClass={ControlLabel} sm={2}>
                    <b>Description</b>
                </Col>
                <Col sm={10}>
                <FormControl
                    componentClass="textarea"
                    type="text"
                    id="projectDescription"
                    value={this.state.project.description}
                    placeholder="Description"
                    onChange={e => this.handleChange(e)}
                />
                </Col>
            </FormGroup>

            <FormGroup>
                <Col smOffset={2} sm={10}>
                <Button type="submit">Edit/Save</Button>
                </Col>
            </FormGroup>
        </Form>
        </Panel.Body>
        </Panel>
        );

    }
}

export default ProjectInformation;