import * as React from 'react';

import {
  Table,
  Button,
  Modal,
  Form,
  FormGroup,
  Col,
  FormControl,
  ControlLabel
} from 'react-bootstrap';

interface UserListProps {
}

interface UserListState {
    users: Array<{}>;
    isLoading: boolean;
    userIndexToEdit: number;
    userToEdit?: User;
    userToDelete?: User;
    editName?: string;
    editUserType?: string;
    editYear?: string;
    editEmail?: string;
}

interface User {
    id: number;
    fullName: string;
    userType: string;
    year: string;
    email: string;
}

class UserManagement extends React.Component<UserListProps, UserListState> {
    constructor(props: UserListProps) {
        super(props);
        
        this.state = {
            users: [],
            isLoading: false,
            userIndexToEdit: -1,
        };
    }
    
    componentDidMount() {
        this.setState({isLoading: true});
        
        fetch('http://localhost:8080/users')
            .then(response => response.json())
            .then(data => this.setState({users: data, isLoading: false}));
    }

    cancelEdit = () => {
        this.setState({userIndexToEdit: -1});
    }

    submitEdit = () => {
        // interact w db
        this.setState({userIndexToEdit: -1});
    }

    handleChange(e: any) {
        this.setState({ [e.target.id]: e.target.value });
    }

    editUser(index: number, user: User) {
        this.setState({
            userIndexToEdit: index,
            userToEdit: user,
            editName: user.fullName,
            editUserType: user.userType,
            editEmail: user.email,
            editYear: user.year
        });
    }

    deleteUser(user: User) {
        const name = user.fullName;
        var submit = confirm('Are you sure you want to delete ' + name + '?');
        if (submit) {
            this.setState({userToDelete: user});
        }
    }

    render() {
        const {users, isLoading, userIndexToEdit, userToEdit} = this.state;
        
        if (isLoading) {
            return <p>Loading...</p>;
        }
        
        return(
            <div>
                <h2>User Management</h2>
                <Button bsStyle="primary" href="/register" style={{margin: 10}}>Add New User</Button>
                <Table bordered={true} condensed={true}>
                    <thead>
                        <tr>
                            <th>Full Name</th>
                            <th>User Type</th>
                            <th>Year</th>
                            <th>Email</th>
                            <th>Edit/Delete</th>
                        </tr>
                    </thead>
                    <tbody>
                        {users.map((user: User, index: number) =>
                            <tr key={user.id}>
                                <td>{user.fullName}</td>
                                <td>{user.userType}</td>
                                <td>{user.year}</td>
                                <td>{user.email}</td>
                                <td>
                                    <Button style={{margin: 3}} bsSize="small" onClick={() => this.editUser(index, user)}>
                                        Edit User
                                    </Button>
                                    <Button bsStyle="warning" bsSize="small" onClick={() => this.deleteUser(user)}>Delete User</Button>
                                </td>
                            </tr>
                        )}
                    </tbody>
                </Table>
                {
                    typeof userToEdit !== 'undefined'
                    ? <Modal show={userIndexToEdit !== -1} onHide={this.cancelEdit}>
                        <Modal.Header closeButton={true}>
                            <Modal.Title>Edit User</Modal.Title>
                        </Modal.Header>
                        <Modal.Body>
                            <Form horizontal={true} >
                                <FormGroup controlId="formHorizontalName">
                                    <Col componentClass={ControlLabel} sm={2}>
                                    Name
                                    </Col>
                                    <Col sm={10}>
                                    <FormControl
                                        type="text"
                                        id="editName"
                                        value={this.state.editName}
                                        placeholder="Name"
                                        onChange={e => this.handleChange(e)}
                                    />
                                    </Col>
                                </FormGroup>
                                <FormGroup controlId="formHorizontalEmail">
                                    <Col componentClass={ControlLabel} sm={2}>
                                    Year
                                    </Col>
                                    <Col sm={10}>
                                    <FormControl
                                        type="text"
                                        placeholder="Email"
                                        id="editEmail"
                                        value={this.state.editEmail}
                                        onChange={e => this.handleChange(e)}
                                    />
                                    </Col>
                                </FormGroup>
                                <FormGroup controlId="formHorizontalUserType">
                                    <Col componentClass={ControlLabel} sm={2}>
                                    User Type
                                    </Col>
                                    <Col sm={10}>
                                    <FormControl componentClass="select" placeholder="select" id="editUserType" value={this.state.editUserType} onChange={e => this.handleChange(e)}>
                                        <option value="Student">Student</option>
                                        <option value="Admin">Admin</option>
                                        <option value="Stakeholder">Stakeholder</option>
                                    </FormControl>
                                    </Col>
                                </FormGroup>
                                <FormGroup controlId="formHorizontalYear">
                                    <Col componentClass={ControlLabel} sm={2}>
                                    Year
                                    </Col>
                                    <Col sm={10}>
                                    <FormControl
                                        type="text"
                                        placeholder="Year"
                                        id="editYear"
                                        value={this.state.editYear}
                                        onChange={e => this.handleChange(e)}
                                    />
                                    </Col>
                                </FormGroup>
                            </Form>
                        </Modal.Body>
                        <Modal.Footer>
                            <Button onClick={this.cancelEdit}>Cancel</Button>
                            <Button onClick={this.submitEdit} bsStyle="primary">Save</Button>
                        </Modal.Footer>
                    </Modal>
                    : <div/>
                }
            </div>);
    }
}
export default UserManagement;
