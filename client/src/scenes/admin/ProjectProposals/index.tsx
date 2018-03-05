import * as React from 'react';

interface ProjectListProps {
}

interface ProjectListState {
projects: Array<{}>;
isLoading: boolean;
}

interface Project {
id: number;
projectName: string;
projectNumber: number;
status: string;
minSize: number;
maxSize: number;
}

class ProjectProposals extends React.Component<ProjectListProps, ProjectListState> {
constructor(props: ProjectListProps) {
super(props);

this.state = {
projects: [],
isLoading: false
};
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
<table>
<tr>
<th>Project Name</th>
<th>Project Number</th>
<th>Project Status</th>
<th>Min Size</th>
<th>Max Size</th>
</tr>
{projects.map((project: Project) =>
<tr key={project.id}>
<td>{project.projectName}</td>
<td>{project.projectNumber}</td>
<td>{project.status}</td>
<td>{project.minSize}</td>
<td>{project.maxSize}</td>
</tr>
)}
</table>
</div>);
}
}

export default ProjectProposals;
