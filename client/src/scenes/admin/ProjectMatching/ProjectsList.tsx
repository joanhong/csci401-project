import * as React from 'react';
import ProjectCard from './ProjectCard';
import { DragDropContext } from 'react-dnd';
import HTML5Backend from 'react-dnd-html5-backend';
import {
    StudentInfo,
    Project,
} from './index';
import {
    Table,
} from 'react-bootstrap';

interface ProjectsListProps {
    projects: Array<Project>;
}

interface ProjectsListState {

}

@DragDropContext(HTML5Backend)
class ProjectsList extends React.Component<ProjectsListProps, ProjectsListState> {
    constructor(props: ProjectsListProps) {
        super(props);
    
        this.state = {
        };
      }
    
    render() {
        const {projects} = this.props;
        return (
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
            <ProjectCard project={project} key={project.projectId} />      
            )}
            </tbody>
          </Table>
        );
    }
}

export default ProjectsList;