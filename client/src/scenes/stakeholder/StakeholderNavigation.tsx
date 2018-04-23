import * as React from 'react';
import {
  Route,
  BrowserRouter
} from 'react-router-dom';
import {
  Navbar,
  Nav,
  NavItem,
  FormGroup,
  Button
} from 'react-bootstrap';
import {
  LinkContainer
} from 'react-router-bootstrap';
import Home from './Home/index';
import Profile from './Profile/index';
import ProjectProposal from './ProjectProposal/index';
const logo = require('../../svg/logo.svg');

class StakeholderNavigation extends React.Component {
  logOutClicked() {
    sessionStorage.removeItem('jwt');
    sessionStorage.removeItem('userType');
    window.location.href = '/';  
  }

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
                <LinkContainer to="/stakeholder">
                  <a>CSCI 401</a>
                </LinkContainer>
              </Navbar.Brand> 
            </Navbar.Header>
            <Nav>
              <LinkContainer to="/stakeholder/profile">
                <NavItem eventKey={1}>
                  Profile
                </NavItem>
              </LinkContainer>
              
              <LinkContainer to="/stakeholder/proposals">
                <NavItem eventKey={2}>
                  Project Proposal
                </NavItem>
              </LinkContainer>
              <NavItem eventKey={6}>
                <FormGroup>
                  <Button type="submit" onClick={this.logOutClicked}>Log Out</Button>
              </FormGroup>
              </NavItem>
            </Nav>
          </Navbar>
          <div className="content">
            <Route exact={true} path="/stakeholder" component={Home}/>
            <Route path="/stakeholder/profile" component={Profile}/>
            <Route path="/stakeholder/proposals" component={ProjectProposal}/>
          </div>
        </div>
      </BrowserRouter>
    );
  }
}

export default StakeholderNavigation;