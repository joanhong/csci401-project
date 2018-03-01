import * as React from 'react';
import Navigation from '../StudentNavigation';

const logo = require('../logo.svg');

class StudentHome extends React.Component {
  
  render() {
    return (
      <div className="StudentHome">
        <header className="StudentHome-header">
          <img src={logo} className="StudentHome-logo" alt="logo" />
          <h1 className="StudentHome-title">Welcome to CSCI 401</h1>
        </header>
        <Navigation/>
      </div>
    );
  }
}

export default StudentHome;
