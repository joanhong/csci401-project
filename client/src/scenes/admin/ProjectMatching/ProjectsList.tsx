import * as React from 'react';
import ProjectCard from './ProjectCard';
import { DragDropContext } from 'react-dnd'
import HTML5Backend from 'react-dnd-html5-backend'
import {
    StudentInfo,
    Project,
} from './index';

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
}

export default ProjectsList;