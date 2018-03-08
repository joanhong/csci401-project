import * as React from 'react';
import {
  Route, BrowserRouter, Switch
} from 'react-router-dom';
import './App.css';
import LandingPage from './scenes/login/index';
import RegisterPage from './scenes/register/index';
import AdminHome from './scenes/admin/AdminNavigation';
import StakeholderHome from './scenes/stakeholder/StakeholderNavigation';
import StudentHome from './scenes/student/StudentNavigation';

class App extends React.Component {
  
  render() {
    return (
      <div className="App">
        <BrowserRouter>
          <Switch>
            <Route exact={true} path="/" component={LandingPage}/>
            <Route path="/register" component={RegisterPage}/>
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
