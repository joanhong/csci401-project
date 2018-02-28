import * as React from 'react';
import {
    Form,
    FormGroup,
    Col,
    FormControl,
    Button,
    ControlLabel
} from 'react-bootstrap';

class ProposalForm extends React.Component {
    render() {
        return (
            <Form horizontal={true} >
            <FormGroup controlId="formHorizontalProjectName">
                <Col componentClass={ControlLabel} sm={2}>
                    Project Name
                </Col>
                <Col sm={10}>
                <FormControl type="text" placeholder="Project Name" />
                </Col>
            </FormGroup>

            <FormGroup controlId="formHorizontalNumberStudents">
                <Col componentClass={ControlLabel} sm={2}>
                    Number of Students
                </Col>
                <Col sm={10}>
                <FormControl type="text" placeholder="Number of Students" />
                </Col>
            </FormGroup>
            
            <FormGroup controlId="formHorizontalTechnologies">
                <Col componentClass={ControlLabel} sm={2}>
                    Technologies Expected
                </Col>
                <Col sm={10}>
                <FormControl type="text" placeholder="Technologies expected" />
                </Col>
            </FormGroup>
            
            <FormGroup controlId="formHorizontalBackground">
                <Col componentClass={ControlLabel} sm={2}>
                    Background Requested
                </Col>
                <Col sm={10}>
                <FormControl type="text" placeholder="Background requested" />
                </Col>
            </FormGroup>
            
            <FormGroup controlId="formHorizontalDescription">
                <Col componentClass={ControlLabel} sm={2}>
                    Description
                </Col>
                <Col sm={10}>
                <FormControl type="text" placeholder="Description" />
                </Col>
            </FormGroup>

            <FormGroup>
                <Col smOffset={2} sm={10}>
                <Button type="submit">Submit</Button>
                </Col>
            </FormGroup>
        </Form>
        );
    }
}

export default ProposalForm;