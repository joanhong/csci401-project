import * as React from 'react';
import ProjectInformation from './ProjectInformation';
import DeliverableList from './DeliverableList';

class ProjectPage extends React.Component {
    render() {
        return(
            <div>
            <ProjectInformation/>
            <DeliverableList/>
            </div>
        );
    }
}
export default ProjectPage;