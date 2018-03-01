import * as React from 'react';
import {
  Route,
  NavLink,
  BrowserRouter
} from 'react-router-dom';
import Profile from './Profile/index';
import ProjectMatching from './ProjectMatching/index';
import YourProject from './YourProject/index';
import FinalPresentationReviews from './FinalPresentationReviews/index';

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
          <div className="content">
            <Route path="/student/profile" component={Profile}/>
            <Route path="/student/projectmatching" component={ProjectMatching}/>
            <Route path="/student/project" component={YourProject}/>
            <Route path="/student/finalpresentationreviews" component={FinalPresentationReviews}/>
          </div>
        </div>
      </BrowserRouter>
    );
  }
}

export default StudentNavigation;