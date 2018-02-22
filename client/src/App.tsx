import * as React from 'react';
import './App.css';
import UserList from './UserList';

const logo = require('./logo.svg');

class App extends React.Component {
  
  render() {
    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <h1 className="App-title">Welcome to CSCI 401</h1>
        </header>
        <UserList/>
      </div>
    );
  }
}

export default App;
