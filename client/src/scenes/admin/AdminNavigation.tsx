import * as React from 'react';
import {
  Route,
  BrowserRouter,
  Redirect
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
import AdminHome from './Home/index';
import UserManagement from './UserManagement/index';
import ProjectProposals from './ProjectProposals/index';
import ClassOverview from './ClassOverview/index';
import Stakeholders from './Stakeholders/index';
import ProjectMatching from './ProjectMatching/index';
const logo = require('../../svg/logo.svg');

const PrivateRoute = ({ component: Component, ...rest }) => (
  <Route 
    {...rest} 
    render={(props) => (
    sessionStorage.getItem('jwt') !== null
      ? <Component {...props} />
      : 
      <Redirect 
          to={{
          pathname: '/',
          state: { from: props.location }
          }} 
      />
  )} 
  />
);

class AdminNavigation extends React.Component {
  
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
              <LinkContainer to="/admin">
                <a>CSCI 401</a>
                </LinkContainer>
              </Navbar.Brand> 
              
            </Navbar.Header>
            <Nav>
              <LinkContainer to="/admin/users">
                <NavItem eventKey={1}>
                  User Management
                </NavItem>
              </LinkContainer>
              <LinkContainer to="/admin/proposals">
                <NavItem eventKey={2}>
                  Project Proposals
                </NavItem>
              </LinkContainer>
              {/* <LinkContainer to="/admin/class">
                <NavItem eventKey={3}>
                  Class Overview
                </NavItem>
              </LinkContainer> */}
              <LinkContainer to="/admin/stakeholders">
                <NavItem eventKey={4}>
                  Stakeholders
                </NavItem>
              </LinkContainer>
              <LinkContainer to="/admin/matching">
              <NavItem eventKey={5}>
                Project Matching
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
            <PrivateRoute exact={true} path="/admin" component={AdminHome}/>
            <PrivateRoute path="/admin/users" component={UserManagement}/>
            <PrivateRoute path="/admin/proposals" component={ProjectProposals}/>
            <PrivateRoute path="/admin/class" component={ClassOverview}/>
            <PrivateRoute path="/admin/stakeholders" component={Stakeholders}/>
            <PrivateRoute path="/admin/matching" component={ProjectMatching}/>
          </div>
        </div>
      </BrowserRouter>
    );
  }
}

export default AdminNavigation;