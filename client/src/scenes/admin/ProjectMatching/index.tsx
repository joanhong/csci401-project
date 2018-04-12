import * as React from 'react';
import {
  Table,
  Button,
  FormGroup,
  FormControl,
  Grid,
  Row,
  Col,
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

    const header = (
      <div>
        <h2>Project Matching</h2>
        <form>
          <Grid>
            <Row>
                <Col lg={8}>
                <FormGroup controlId="formBasicText">
                  <FormControl
                    type="text"
                    placeholder="Enter NUM_RANKED"
                  />
                  <FormControl.Feedback />
                  <Button onClick={this.launch} style={{margin: 5}}>
                    Let the games begin.
                  </Button>
                </FormGroup>
                </Col>
                <Col lg={4}>
                  <Button bsStyle="primary" disabled={projects.length === 0}>
                      Assign Projects
                  </Button>
                </Col>
            </Row>
          </Grid>
        </form>
      </div>
    );

    if (!isLaunched) {
      return header;
    }

    return (
      <div>
      {header}

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