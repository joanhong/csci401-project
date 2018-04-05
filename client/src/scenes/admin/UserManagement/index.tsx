import * as React from 'react';

import {
  Table,
  Button
} from 'react-bootstrap';

interface UserListProps {
}

interface UserListState {
    users: Array<{}>;
    isLoading: boolean;
    userIndexToEdit: number;
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

    render() {
        const {users, isLoading} = this.state;
        
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
                                            <Button style={{margin: 3}} bsSize="small" onClick={() => this.setState({userIndexToEdit: index})}>
                                                Edit User
                                            </Button>
                                            <Button bsStyle="warning" bsSize="small">Delete User</Button>
                                        </td>
                                    </tr>
                                )}
                            </tbody>
                        </Table>
            </div>);
    }
}
export default UserManagement;
