import * as React from 'react';
import {
  Route,
  NavLink,
  HashRouter
} from 'react-router-dom';
import Profile from './Profile/index';
import ProjectProposal from './ProjectProposal/index';

class StakeholderNavigation extends React.Component {
  render() {
    return (
      <HashRouter>
        <div>
            <ul className="header">
              <li><NavLink to="/stakeholder/">Home</NavLink></li>
              <li><NavLink to="/stakeholder/profile">Profile</NavLink></li>
              <li><NavLink to="/stakeholder/projectproposal">Project Proposal</NavLink></li>
            </ul>
          <div className="content">
            <Route path="/stakeholder/profile" component={Profile}/>
            <Route path="/stakeholder/projectproposal" component={ProjectProposal}/>
          </div>
        </div>
      </HashRouter>
    );
  }
}

export default StakeholderNavigation;