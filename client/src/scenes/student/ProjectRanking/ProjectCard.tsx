import * as React from 'react';
import PropTypes from 'prop-types';
import { DragSource, DropTarget } from 'react-dnd';
import ItemTypes from './ItemTypes';

const style = {
    border: '1px dashed gray',
    padding: '0.5rem 1rem',
    marginBottom: '.5rem',
    backgroundColor: 'white',
    cursor: 'move',
};

const cardSource = {
    beginDrag(props: any) {
        return {
            id: props.id,
            originalIndex: props.findCard(props.id).index,
        };
    },

    endDrag(props: any, monitor: any) {
        const { id: droppedId, originalIndex } = monitor.getItem();
        const didDrop = monitor.didDrop();

        if (!didDrop) {
            props.moveCard(droppedId, originalIndex);
        }
    },
};

const cardTarget = {
    canDrop() {
        return false;
    },

    hover(props: any, monitor: any) {
        const { id: draggedId } = monitor.getItem();
        const { id: overId } = props;

        if (draggedId !== overId) {
            const { index: overIndex } = props.findCard(overId);
            props.moveCard(draggedId, overIndex);
        }
    },
};

interface Props {
    connectDragSource?: PropTypes.func.isRequired;
    connectDropTarget?: PropTypes.func.isRequired;
    isDragging?: PropTypes.bool.isRequired;
    id: PropTypes.any.isRequired;
    text: PropTypes.string.isRequired;
    moveCard: PropTypes.func.isRequired;
    findCard: PropTypes.func.isRequired;
}

@DropTarget(ItemTypes.CARD, cardTarget, connect => ({
    connectDropTarget: connect.dropTarget(),
}))
@DragSource(ItemTypes.CARD, cardSource, (connect, monitor) => ({
    connectDragSource: connect.dragSource(),
    isDragging: monitor.isDragging(),
}))

class ProjectCard extends React.Component<Props> {
    constructor(props: Props) {
       super(props);
    }

    render() {
        const {
            text,
            isDragging,
            connectDragSource,
            connectDropTarget,
        } = this.props;
        const opacity = isDragging ? 0 : 1;

        return connectDragSource(
            connectDropTarget(<div style={{ ...style, opacity }}>{text}</div>),
        );
    }
}

export default ProjectCard;