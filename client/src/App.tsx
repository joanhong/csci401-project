import * as React from 'react';
import {
  Route, BrowserRouter, Switch
} from 'react-router-dom';
import './App.css';
import Navigation from './components/navigation/UserTypes';
import AdminHome from './scenes/admin/AdminNavigation';
import StakeholderHome from './scenes/stakeholder/StakeholderNavigation';
import StudentHome from './scenes/student/StudentNavigation';
const logo = require('./logo.svg');

class App extends React.Component {
  
  render() {
    return (
      <div className="App">
        <header className="App-header">
          <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"/>
          <img src={logo} className="App-logo" alt="logo" />
          <h1 className="App-title">Welcome to CSCI 401</h1>
        </header>
        <BrowserRouter>
          <Switch>
            <Route exact={true} path="/" component={Navigation}/>
            <Route path="/admin" component={AdminHome}/>
            <Route path="/stakeholder" component={StakeholderHome}/>
            <Route path="/student" component={StudentHome}/>
          </Switch>
        </BrowserRouter>
        
      </div>
    );
  }
}

export default App;
