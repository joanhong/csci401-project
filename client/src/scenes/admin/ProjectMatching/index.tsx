import * as React from 'react';
import {
  Table,
  Button,
  FormGroup,
  FormControl
} from 'react-bootstrap';

interface ProjectMatchingProps {
}

interface ProjectMatchingState {
  projects: Array<{}>;
  isLoading: boolean;
  isLaunched: boolean;
}

interface StudentInfo {
  studentId: number;
  name: string;
  rankings: Array<{}>;
}

interface Project {
  projectId: number;
  name: string;
  minSize: number;
  maxSize: number;
  members: Array<StudentInfo>;
}

class ProjectMatching extends React.Component<ProjectMatchingProps, ProjectMatchingState> {
  
  constructor(props: ProjectMatchingProps) {
    super(props);

    this.state = {
      projects: [],
      isLoading: false,
      isLaunched: false
    };
  }

  componentDidMount() {
    this.setState({isLoading: false});
  }

  launch = () => {
    this.setState({isLaunched: true});
    fetch('http://localhost:8080/projects')
      .then(response => response.json())
      .then(data => this.setState({projects: data}));
  }
  
  render() {
    const isLoading = this.state.isLoading;
    const isLaunched = this.state.isLaunched;
    const projects = this.state.projects;

    if (isLoading) {
      return <p>Loading...</p>;
    }

    if (!isLaunched) {
      return (
        <div>
        <h2>Project Matching</h2>

        <form>
          <FormGroup controlId="formBasicText">
            <FormControl
              type="text"
              placeholder="Enter NUM_RANKED"
            />
          <FormControl.Feedback />
          <Button onClick={this.launch} bsStyle="primary">
            Let the games begin.
          </Button>
        </FormGroup>
      </form>

      </div>
      );
    }

    return (
      <div>
      <h2>Project Matching</h2> 

      <form>
        <FormGroup controlId="formBasicText">
          <FormControl
            type="text"
            placeholder="Enter NUM_RANKED"
          />
        <FormControl.Feedback />
        <Button onClick={this.launch} bsStyle="primary">
          Let the games begin.
        </Button>
        </FormGroup>
      </form>

      <Table bordered={true}>
        <thead>
        <tr>
          <th>Project Name</th>
          <th>Min Size</th>
          <th>Max Size</th>
          <th>Members</th>
        </tr>
        </thead>
        <tbody>
      {projects.map((project: Project) =>
        <tr key={project.projectId}>
          <td>{project.name}</td>
          <td>{project.minSize}</td>
          <td>{project.maxSize}</td>
          <td>
          {project.members.map((student: StudentInfo) =>
            <div key={student.studentId}>
              {student.name}
            </div>
          )}
          </td>
        </tr>      
        )}
        </tbody>
      </Table>

      </div>
    );
  }
}

export default ProjectMatching;