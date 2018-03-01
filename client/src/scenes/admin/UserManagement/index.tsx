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

return (
<div>
<h2>User List</h2>
{users.map((user: User) =>
<div key={user.id}>
{user.fullName}
</div>
)}
</div>
);
}
}
export default UserManagement;
