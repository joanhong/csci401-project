import * as React from 'react';
import {
  Route,
  NavLink,
  BrowserRouter
} from 'react-router-dom';
import {
  Navbar,
  Nav,
  NavItem
} from 'react-bootstrap';
import {
  LinkContainer
} from 'react-router-bootstrap';
import Profile from './Profile/index';
import ProjectMatching from './ProjectMatching/index';
import YourProject from './YourProject/index';
import FinalPresentationReviews from './FinalPresentationReviews/index';
const logo = require('../../logo.svg');

class StudentNavigation extends React.Component {
  render() {
    return (
      <BrowserRouter>
        <div>
            <ul className="header">
              <li><NavLink to="/student/">Home</NavLink></li>
              <li><NavLink to="/student/profile">Profile</NavLink></li>
              <li><NavLink to="/student/projectmatching">Project Matching</NavLink></li>
              <li><NavLink to="/student/project">Your Project</NavLink></li>
              <li><NavLink to="/student/finalpresentationreviews">Final Presentation Reviews</NavLink></li>
            </ul>
            <Navbar>
              <Navbar.Header>
                <Navbar.Brand>
                  <img src={logo} className="App-logo" alt="logo" />
                </Navbar.Brand>
                
                <Navbar.Brand>
                  <LinkContainer to="/student">
                    <a>CSCI 401</a>
                  </LinkContainer>
                </Navbar.Brand> 
              </Navbar.Header>
              <Nav>
                <LinkContainer to="/student/profile">
                  <NavItem eventKey={1}>
                    Profile
                  </NavItem>
                </LinkContainer>
                
                <LinkContainer to="/student/matching">
                  <NavItem eventKey={2}>
                    ProjectProposal
                  </NavItem>
                </LinkContainer>
                <LinkContainer to="/student/project">
                  <NavItem eventKey={2}>
                    ProjectProposal
                  </NavItem>
                </LinkContainer>
                <LinkContainer to="/student/reviews">
                  <NavItem eventKey={2}>
                    ProjectProposal
                  </NavItem>
                </LinkContainer>
              </Nav>
            </Navbar>
            <div className="content">
              <Route path="/student/profile" component={Profile}/>
              <Route path="/student/matching" component={ProjectMatching}/>
              <Route path="/student/project" component={YourProject}/>
              <Route path="/student/reviews" component={FinalPresentationReviews}/>
            </div>
        </div>
      </BrowserRouter>
    );
  }
}

export default StudentNavigation;