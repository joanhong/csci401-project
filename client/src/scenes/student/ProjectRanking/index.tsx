import * as React from 'react';
import ProjectRankingContainer from './ProjectRankingContainer';

const style = {
    margin: 'auto',
};

class StudentProjectRanking extends React.Component {

    render() {
        return (
            <div style={{style}}>
                <ProjectRankingContainer />
            </div>
        );
    }
}

export default StudentProjectRanking;