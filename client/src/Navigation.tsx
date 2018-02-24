import * as React from 'react';
import {
  Route,
  NavLink,
  HashRouter
} from 'react-router-dom';
import UserList from './UserList';
import AlgorithmOutput from './AlgorithmOutput';

class Navigation extends React.Component {
  render() {
    return (
      <HashRouter>
        <div>
            <ul className="header">
              <li><NavLink to="/">Home</NavLink></li>
              <li><NavLink to="/users">Users</NavLink></li>
              <li><NavLink to="/projects">Projects</NavLink></li>
            </ul>
          <div className="content">
            <Route path="/users" component={UserList}/>
            <Route path="/projects" component={AlgorithmOutput}/>
          </div>
        </div>
      </HashRouter>
    );
  }
}

export default Navigation;