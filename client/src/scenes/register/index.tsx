import * as React from 'react';
import {
    Panel,
    Nav,
    NavItem
} from 'react-bootstrap';
import {
    BrowserRouter,
    Route,
    Switch
} from 'react-router-dom';
import StudentForm from './StudentForm';
import StakeholderForm from './StakeholderForm';
import AdminForm from './AdminForm';

class Register extends React.Component {
    render() {
        return (
            <div>
            <BrowserRouter>
            <Switch>
              <Route path="/register/student" component={StudentForm}/>
              <Route path="/register/stakeholder" component={StakeholderForm}/>
              <Route path="/register/admin" component={AdminForm}/>
            </Switch>
          </BrowserRouter>
          </div>

        );
    }
}

export default Register;