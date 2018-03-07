import * as React from 'react';
import {
  Route,
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
import ProjectRanking from './ProjectRanking/index';
import YourProject from './YourProject/index';
import FinalPresentationReviews from './FinalPresentationReviews/index';
const logo = require('../../logo.svg');

class StudentNavigation extends React.Component {
  render() {
    return (
      <BrowserRouter>
        <div>
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
                
                <LinkContainer to="/student/ranking">
                  <NavItem eventKey={2}>
                    Project Ranking
                  </NavItem>
                </LinkContainer>
                <LinkContainer to="/student/project">
                  <NavItem eventKey={3}>
                    Your Project
                  </NavItem>
                </LinkContainer>
                <LinkContainer to="/student/reviews">
                  <NavItem eventKey={4}>
                    Final Presentation Reviews
                  </NavItem>
                </LinkContainer>
              </Nav>
            </Navbar>
            <div className="content">
              <Route path="/student/profile" component={Profile}/>
              <Route path="/student/ranking" component={ProjectRanking}/>
              <Route path="/student/project" component={YourProject}/>
              <Route path="/student/reviews" component={FinalPresentationReviews}/>
            </div>
        </div>
      </BrowserRouter>
    );
  }
}

export default StudentNavigation;