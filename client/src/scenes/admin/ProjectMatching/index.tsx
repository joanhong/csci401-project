import * as React from 'react';

import {
  Table
} from 'react-bootstrap';

interface ProjectMatchingProps {
}

interface ProjectMatchingState {
  projects: Array<{}>;
  isLoading: boolean;
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
      isLoading: false
    };
  }

  componentDidMount() {
    this.setState({isLoading: true});

    fetch('http://localhost:8080/projects')
      .then(response => response.json())
      .then(data => this.setState({projects: data, isLoading: false}));
  }

  createProjectsTable() {
    const projects = this.state.projects;
    return(
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
     );
  }
  
  render() {
    const isLoading = this.state.isLoading;

    if (isLoading) {
      return <p>Loading...</p>;
    }

    return (
      <div>
      <h2>Project Matching</h2> 
      {this.createProjectsTable()}
      </div>
    );
  }
}

export default ProjectMatching;