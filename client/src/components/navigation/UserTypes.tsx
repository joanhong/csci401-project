import * as React from 'react';
import {
  Link
} from 'react-router-dom';
import LoginForm from '../../scenes/login/Form';

class Navigation extends React.Component {
  render() {
    return (
      <div>
          <ul className="header">
            <li><Link to="/admin">Admin</Link></li>
            <li><Link to="/stakeholder">Stakeholder</Link></li>
            <li><Link to="/student">Student</Link></li>
          </ul>
          <LoginForm/>
      </div>
    );
  }
}

export default Navigation;