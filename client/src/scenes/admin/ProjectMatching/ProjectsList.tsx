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
    projects: Array<Project>;
}

@DragDropContext(HTML5Backend)
class ProjectsList extends React.Component<ProjectsListProps, ProjectsListState> {
    constructor(props: ProjectsListProps) {
        super(props);
    
        this.state = {
            projects: props.projects
        };
    }

    moveCard(student: StudentInfo, oldProjectIndex: number, newProjectIndex: number) {
        const { projectCard, index } = this.findCard(newProjectIndex);
        const { projects } = this.state;
        const newMembers = projects[index].members.concat(student);
        projectCard.members = newMembers;
        var newProjects = projects.splice(index, 1, projectCard);

        const result = this.findCard(oldProjectIndex);
        const oldProjectCard = result.projectCard;
        const oldIndex = result.index;
        const oldMembersUpdated = projects[oldIndex].members.filter((s: StudentInfo) => 
            s.userId !== student.userId
        ); // fix this
        oldProjectCard.members = oldMembersUpdated;
    
        newProjects.splice(oldProjectIndex, 1, oldProjectCard);
        this.setState({projects: newProjects});
    }

    findCard(projectId: number) {
        const { projects } = this.state;
        const projectCard = projects.filter(c => c.projectId === projectId)[0];

        return {
            projectCard,
            index: projects.indexOf(projectCard),
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
                <ProjectCard 
                    project={project}
                    key={project.projectId}
                    moveCard={this.moveCard}
                    findCard={this.findCard} 
                />      
            )}
            </tbody>
          </Table>
        );
    }
}

export default ProjectsList;