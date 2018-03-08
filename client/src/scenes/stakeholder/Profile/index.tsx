import * as React from 'react';
import {
    Panel,
    Button,
    Form,
    FormGroup,
    Col,
    FormControl,
    ControlLabel    
} from 'react-bootstrap';

class StakeholderProfile extends React.Component {
    render() {
        return (
            <div>
            <Panel>
            <Panel.Heading>
                Profile
            </Panel.Heading>
            <Panel.Body>
            <Form horizontal={true}>
                <FormGroup controlId="formHorizontalStakeholderName">
                    <Col componentClass={ControlLabel} sm={2}>
                        Name:
                    </Col>
                    <Col sm={10}>
                        <FormControl type="text" value="Stakeholder Name" />
                    </Col>             
                </FormGroup>
                
                <FormGroup controlId="formHorizontalStakeholderEmail">
                    <Col componentClass={ControlLabel} sm={2}>
                        Email:
                    </Col>
                    <Col sm={10}>
                        <FormControl type="email" value="stakeholdername@usc.edu" />
                    </Col>             
                </FormGroup>

                <FormGroup controlId="formHorizontalStakeholderCompany">
                    <Col componentClass={ControlLabel} sm={2}>
                        Company/Organization:
                    </Col>
                    <Col sm={10}>
                        <FormControl type="text" value="USC" />
                    </Col>             
                </FormGroup>                
                
                <FormGroup controlId="formHorizontalStakeholderPhone">
                    <Col componentClass={ControlLabel} sm={2}>
                        Phone:
                    </Col>
                    <Col sm={10}>
                        <FormControl type="tel" />
                    </Col>             
                </FormGroup> 
                
                <FormGroup>
                    <Col smOffset={2} sm={10}>
                        <Button type="submit" bsStyle="primary">Edit/Save Profile</Button>
                    </Col>
                </FormGroup>               
            </Form>    
            </Panel.Body>
            </Panel>
            </div>
        );
    }
}

export default StakeholderProfile;