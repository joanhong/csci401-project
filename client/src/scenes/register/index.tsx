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
            <Panel>
                <Nav bsStyle="tabs">
                <NavItem eventKey="1" href="/register/student">
                    Student
                </NavItem>
                <NavItem eventKey="2" href="/register/stakeholder">
                    Stakeholder
                </NavItem>
                <NavItem eventKey="3" href="/register/admin">
                    Admin
                </NavItem>
                </Nav>
            </Panel>
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