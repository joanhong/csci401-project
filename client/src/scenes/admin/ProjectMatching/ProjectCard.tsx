import * as React from 'react';
import StudentCard from './StudentCard';
import {
    StudentInfo,
    Project,
} from './index';

interface ProjectCardProps {
    project: Project;
}

interface ProjectCardState {

}

class ProjectCard extends React.Component<ProjectCardProps, ProjectCardState> {
    constructor(props: ProjectCardProps) {
        super(props);
    
        this.state = {
        };
      }
}

export default ProjectCard;