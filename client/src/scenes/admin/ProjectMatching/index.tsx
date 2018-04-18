import * as React from 'react';
import ProjectsList from './ProjectsList';
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
  projects: Array<Project>;
  isLoading: boolean;
  isLaunched: boolean;
}

export type StudentInfo = {
  studentId: number;
  firstName: string;
  lastName: string;
  rankings: Array<{}>;
};

export type Project = {
  projectId: number;
  projectName: string;
  minSize: number;
  maxSize: number;
  members: Array<StudentInfo>;
};

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
                    placeholder="Enter number of ranked projects to consider"
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

    if (isLaunched && !projects.length) {
      return (
        <div>
          {header}
          <p>Loading...</p>
        </div>
      );
    }

    return (
      <div>
      {header}

      <ProjectsList projects={this.state.projects} />

      </div>
    );
  }
}

export default ProjectMatching;