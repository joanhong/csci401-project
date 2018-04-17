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
    userId: number;
    firstName: string;
    lastName: string;
    userType: string;
    email: string;
}

class UserManagement extends React.Component<UserListProps, UserListState> {
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
        
        fetch('http://localhost:8080/users')
            .then(response => response.json())
            .then(data => this.setState({allUsers: data, usersToDisplay: data, isLoading: false}));
    }

    cancelEdit = () => {
        this.setState({userIndexToEdit: -1});
    }

    submitEdit = () => {
        var request = new XMLHttpRequest();
        request.withCredentials = true;
        request.open('POST', 'http://localhost:8080/userInfoUpdate/');
        request.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
        var data = JSON.stringify({
        name: this.state.editName,
        userType: this.state.editUserType,
        year: this.state.editYear,
        email: this.state.editEmail
        });
        request.setRequestHeader('Cache-Control', 'no-cache');
        request.send(data);
        alert('User has been updated succesfully!');
        window.location.reload();
        this.setState({userIndexToEdit: -1});
    }

    handleChange(e: any) {
        this.setState({ [e.target.id]: e.target.value });
    }

    handleUserFilterChange = (e: any) => {
        var _usersToDisplay: User[] = [];
        const {allUsers} = this.state;
        var userFilterType = '';

        if (e === 1) {
            userFilterType = 'All';
        } else if (e === 2) {
            userFilterType = 'Student';
        } else if (e === 3) {
            userFilterType = 'Stakeholder';
        } else if (e === 4) {
            userFilterType = 'Admin';
        }

        allUsers.forEach((user: User) => {
            if (user.userType === userFilterType) {
                _usersToDisplay.push(user);
            } else if (userFilterType === 'All') {
                _usersToDisplay.push(user);
            }
        });

        this.setState({usersToDisplay: _usersToDisplay});
    }

    editUser(index: number, user: User) {
        this.setState({
            userIndexToEdit: index,
            userToEdit: user,
            editName: user.firstName,
            editUserType: user.userType,
            editEmail: user.email,
        });
    }

    deleteUser(user: User) {
        const name = user.firstName;
        var submit = confirm('Are you sure you want to delete ' + name + '?');
        if (submit) {
            this.setState({userToDelete: user});
        }
    }

    render() {
        const {allUsers, usersToDisplay, isLoading, userIndexToEdit, userToEdit} = this.state;
        
        if (isLoading) {
            return <p>Loading...</p>;
        }

        var modalEditUser = <div/>;
        if (typeof userToEdit !== 'undefined') {
            modalEditUser = (
                <Modal show={userIndexToEdit !== -1} onHide={this.cancelEdit}>
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
            );
        }
        
        return(
            <div>
                <h2>User Management</h2>
                
                <div>
                <Button bsStyle="primary" href="/register/student" style={{margin: 10}}>Add New User</Button>
                </div>

                <div>
                <ButtonToolbar>
                    <ToggleButtonGroup
                        onChange={this.handleUserFilterChange}
                        type="radio"
                        name="userFilter"
                        defaultValue={1}
                    >
                    <ToggleButton value={1}>All</ToggleButton>
                    <ToggleButton value={2}>Student</ToggleButton>
                    <ToggleButton value={3}>Stakeholder</ToggleButton>
                    <ToggleButton value={4}>Admin</ToggleButton>
                    </ToggleButtonGroup>
                </ButtonToolbar>
                </div>

                <Table bordered={true} condensed={true}>
                    <thead>
                        <tr>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>User Type</th>
                            <th>Email</th>
                            <th>Edit/Delete</th>
                        </tr>
                    </thead>
                    <tbody>
                        {usersToDisplay.map((user: User, index: number) =>
                            <tr key={user.userId}>
                                <td>{user.firstName}</td>
                                <td>{user.lastName}</td>
                                <td>{user.userType}</td>
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
                {modalEditUser}
            </div>);
    }
}
export default UserManagement;
