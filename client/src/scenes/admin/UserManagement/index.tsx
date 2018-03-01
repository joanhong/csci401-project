import * as React from 'react';

interface UserListProps {
}

interface UserListState {
users: Array<{}>;
isLoading: boolean;
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
isLoading: false
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
    <table>
    <tr>
    <th>Full Name</th>
    <th>User Type</th>
    <th>Year</th>
    <th>Email</th>
    </tr>
    {users.map((user: User) =>
    <tr key={user.id}>
    <td>{user.fullName}</td>
    <td>{user.userType}</td>
    <td>{user.year}</td>
    <td>{user.email}</td>
    </tr>
    )}
    </table>
</div>);
}
}
export default UserManagement;
