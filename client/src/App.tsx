import * as React from 'react';
import {
  Route, BrowserRouter, Switch, Redirect
} from 'react-router-dom';
import './App.css';
import LandingPage from './scenes/login/index';
import RegisterPage from './scenes/register/index';
import AdminHome from './scenes/admin/AdminNavigation';
import StakeholderHome from './scenes/stakeholder/StakeholderNavigation';
import StudentHome from './scenes/student/StudentNavigation';

const AdminPrivateRoute = ({ component: Component, ...rest }) => (
  <Route 
    {...rest} 
    render={(props) => (
    sessionStorage.getItem('jwt') !== null
    && sessionStorage.getItem('userType') === '1'
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

const StudentPrivateRoute = ({ component: Component, ...rest }) => (
  <Route 
    {...rest} 
    render={(props) => (
    sessionStorage.getItem('jwt') !== null
    && sessionStorage.getItem('userType') === '3'
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

const StakeholderPrivateRoute = ({ component: Component, ...rest }) => (
  <Route 
    {...rest} 
    render={(props) => (
    sessionStorage.getItem('jwt') !== null
    && sessionStorage.getItem('userType') === '2'
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

class App extends React.Component {
  
  render() {
    return (
      <div className="App">
        <BrowserRouter>
          <Switch>
            <Route exact={true} path="/" component={LandingPage}/>
            <Route path="/register" component={RegisterPage}/>
            <AdminPrivateRoute path="/admin" component={AdminHome}/>
            <Route path="/stakeholder" component={StakeholderHome}/>
            <Route path="/student" component={StudentHome}/>
          </Switch>
        </BrowserRouter>
      </div>
    );
  }
}

export default App;
