import * as React from 'react';
import {
  Route,
  BrowserRouter
} from 'react-router-dom';
import {
  Navbar,
  Nav,
  NavItem,
  Button
} from 'react-bootstrap';
import {
  LinkContainer
} from 'react-router-bootstrap';
import Profile from './Profile/index';
import ProjectRanking from './ProjectRanking/index';
import YourProject from './YourProject/index';
import SubmitDeliverable from './YourProject/Deliverable/index';
import FinalPresentationReviews from './FinalPresentationReviews/index';
import WeeklyReportForm from './WeeklyReportForm/index';
import PeerReviewForm from './PeerReviewForm/index';
const logo = require('../../svg/logo.svg');

class StudentNavigation extends React.Component {

  logOutClicked() {
    var request = new XMLHttpRequest();
    request.withCredentials = true;
    request.open('POST', 'http://localhost:8080/users/logoutAttempt/');
    request.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
    var data = 'logout';
    request.setRequestHeader('Cache-Control', 'no-cache');
    request.send(data);
    alert(request.responseText + 'Logging you out...');
    request.onreadystatechange = function() {
        if (request.readyState === 4) {
            if (request.responseText.length > 4) {
                if (request.responseText === 'LoggedOut') {
                    alert('Logged out succesfully!');
                    window.location.href = '/';
                    return;
                }
                if (request.responseText === 'Failed') {
                    alert('No one is logged in.');
                }
            } else {
                    alert('Error communicating with server.');
            }
        }
    };
    
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
                  <LinkContainer to="/student">
                    <a>CSCI 401</a>
                  </LinkContainer>
                </Navbar.Brand> 
              </Navbar.Header>
              <Nav>
                
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
                <NavItem eventKey={5}>
                  <Button onClick={this.logOutClicked}>Logout</Button>
                </NavItem>
              </Nav>
            </Navbar>
            <div className="content">
              <Route exact={true} path="/student" component={Profile}/>
              <Route path="/student/ranking" component={ProjectRanking}/>
              <Route exact={true} path="/student/project" component={YourProject}/>
              <Route path="/student/project/deliverable" component={SubmitDeliverable}/>
              <Route path="/student/reviews" component={FinalPresentationReviews}/>
              <Route path="/student/weeklyreport/" component={WeeklyReportForm}/>
              <Route path="/student/peerreview/" component={PeerReviewForm}/>
            </div>
        </div>
      </BrowserRouter>
    );
  }
}

export default StudentNavigation;
