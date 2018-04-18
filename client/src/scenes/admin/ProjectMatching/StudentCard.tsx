import * as React from 'react';
import PropTypes from 'prop-types';
import { DragSource } from 'react-dnd';
import ItemTypes from './ItemTypes';
import {
    StudentInfo,
} from './index';

interface StudentCardProps {
    student: StudentInfo;
    key: number;
    connectDragSource?: PropTypes.func.isRequired;
    isDragging?: PropTypes.bool.isRequired;
}

interface StudentCardState {

}

const studentSource = {
    beginDrag() {
        return {};
    },
};

function collect(connect: any, monitor: any) {
    return {
        connectDragSource: connect.dragSource(),
        isDragging: monitor.isDragging(),
    };
}

@DragSource(ItemTypes.STUDENT, studentSource, collect)
class StudentCard extends React.Component<StudentCardProps, StudentCardState> {
    constructor(props: StudentCardProps) {
        super(props);
    
        this.state = {
        };
    }

    render() {
        const {student, key, connectDragSource, isDragging} = this.props;
        return connectDragSource(
            <div 
                key={key} 
                style={{
                    cursor: 'move',
                    opacity: isDragging ? 0.5 : 1
                }}
            >
                {student.firstName + ' ' + student.lastName}
            </div>
        );
    }
}

export default StudentCard;