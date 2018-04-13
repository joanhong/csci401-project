import * as React from 'react';

import {
  Table,
  Button,
  ButtonGroup,
  Modal,
  Form,
  FormGroup,
  Col,
  FormControl,
  ControlLabel,
  ButtonToolbar,
  ToggleButtonGroup,
  ToggleButton

} from 'react-bootstrap';

interface UserListProps {
}

interface UserListState {
    allUsers: Array<{}>;
    usersToDisplay: Array<{}>;
    userIndexToEdit: number;
    userToEdit?: User;
    userToDelete?: User;
    editName?: string;
    editUserType?: string;
    editYear?: string;
    editEmail?: string;
    isLoading: boolean;
}

interface User {
    id: number;
    fullName: string;
    companyName: string;
    email: string;
}

class Stakeholders extends React.Component<UserListProps, UserListState> {
    constructor(props: UserListProps) {
        super(props);
        
        this.state = {
            allUsers: [],
            usersToDisplay: [],
            userIndexToEdit: -1,
            isLoading: false,
        };
    }
    
    componentDidMount() {
        this.setState({isLoading: true});
        
        fetch('http://localhost:8080/stakeholders')
            .then(response => response.json())
            .then(data => this.setState({allUsers: data, usersToDisplay: data, isLoading: false}));
    }

    handleChange(e: any) {
        this.setState({ [e.target.id]: e.target.value });
    }

    render() {
        const {allUsers, usersToDisplay, isLoading, userIndexToEdit, userToEdit} = this.state;
        
        if (isLoading) {
            return <p>Loading...</p>;
        }
        
        return(
            <div>
                <h2>Stakeholders</h2>

                <Table bordered={true} condensed={true}>
                    <thead>
                        <tr>
                            <th>Full Name</th>
                            <th>Organization</th>
                            <th>Email</th>

                        </tr>
                    </thead>
                    <tbody>
                        {usersToDisplay.map((user: User, index: number) =>
                            <tr key={user.id}>
                                <td>{user.fullName}</td>
                                <td>{user.companyName}</td>
                                <td>{user.email}</td>
                            </tr>
                        )}
                    </tbody>
                    
                </Table>
            </div>);
    }
}
export default Stakeholders;
