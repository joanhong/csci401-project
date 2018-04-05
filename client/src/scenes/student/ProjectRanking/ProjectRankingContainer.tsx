import * as React from 'react';
import update from 'immutability-helper';
import { DropTarget, DragDropContext } from 'react-dnd';
import HTML5Backend from 'react-dnd-html5-backend';
import ProjectCard from './ProjectCard';
import ItemTypes from './ItemTypes';
import {
    Button,
    Grid,
    Row,
    Col,
} from 'react-bootstrap';

const style = {
    width: 600,
    float: 'none',
    margin: 'auto',
};

const cardTarget = {
    drop() {
        return undefined;
    },
};

interface Props {
    connectDropTarget?: any;
}

interface State {
    isLoading: boolean;
    projectCards: Array<Project>;
}

interface Project {
    projectNumber: number;
    projectName: string;
    status: string;
    minSize: number;
    maxSize: number;
    technologiesExpected: string;
    backgroundRequested: string;
    projectDescription: string;
}

@DragDropContext(HTML5Backend)
@DropTarget(ItemTypes.CARD, cardTarget, connect => ({
    connectDropTarget: connect.dropTarget(),
}))

class ProjectRankingContainer extends React.Component<Props, State> {

    constructor(props: any) {
        super(props);
        this.moveCard = this.moveCard.bind(this);
        this.findCard = this.findCard.bind(this);
        this.state = {
            isLoading: false,
            projectCards: [],
        };
    }
    
    componentDidMount() {
        this.setState({isLoading: true});
   
        fetch('http://localhost:8080/projectsrep')
            .then(response => response.json())
            .then(data => this.setState({projectCards: data, isLoading: false}));
    }

    moveCard(id: number, atIndex: number) {
        const { projectCard, index } = this.findCard(id);
        this.setState(
            update(this.state, {
                projectCards: {
                    $splice: [[index, 1], [atIndex, 0, projectCard]],
                },
            }),
        );
    }

    findCard(id: number) {
        const { projectCards } = this.state;
        const projectCard = projectCards.filter(c => c.projectNumber === id)[0];

        return {
            projectCard,
            index: projectCards.indexOf(projectCard),
        };
    }

    render() {
        const { connectDropTarget } = this.props;
        const {projectCards, isLoading} = this.state;

        if (isLoading) {
            return <p>Loading...</p>;
        }

        return connectDropTarget(
            <div style={style}>
                <div style={{width: 600}}>
                    <h3>Rank Projects</h3>
                    <Grid>
                        <Row>
                            <Col lg={4}>
                                Drag to reorder projects by priority. 
                                Click "Submit Rankings" when finished. 
                                Rankings can only be submitted once.
                            </Col>
                            <Col lg={2}>
                                <Button bsStyle="primary">
                                    Submit Rankings
                                </Button>
                            </Col>
                        </Row>
                    </Grid>
                </div>
                <br />
                {projectCards.map((projectCard: Project) => (
                    <ProjectCard
                        key={projectCard.projectNumber}
                        id={projectCard.projectNumber}
                        name={projectCard.projectName}
                        minSize={projectCard.minSize}
                        maxSize={projectCard.maxSize}
                        technologiesExpected={projectCard.technologiesExpected}
                        backgroundRequested={projectCard.backgroundRequested}
                        projectDescription={projectCard.projectDescription}
                        moveCard={this.moveCard}
                        findCard={this.findCard}
                    />
                ))}
            </div>
        );
    }
}

export default ProjectRankingContainer;