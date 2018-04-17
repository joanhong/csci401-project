import * as React from 'react';
import {
    StudentInfo,
} from './index';

interface StudentCardProps {
    student: StudentInfo;
}

interface StudentCardState {

}

class StudentCard extends React.Component<StudentCardProps, StudentCardState> {
    constructor(props: StudentCardProps) {
        super(props);
    
        this.state = {
        };
      }
}

export default StudentCard;