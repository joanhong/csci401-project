import * as React from 'react';
import './App.css';
import Navigation from './Navigation';

const logo = require('./logo.svg');

class App extends React.Component {
  
  render() {
    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <h1 className="App-title">Welcome to CSCI 401</h1>
        </header>
        <Navigation/>
      </div>
    );
  }
}

export default App;
