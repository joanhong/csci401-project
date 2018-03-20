import * as React from 'react';
import {
    Form,
    FormGroup,
    Col,
    FormControl,
    Button,
    ControlLabel
} from 'react-bootstrap';

interface WeeklyReportProps {
}

interface WeeklyReportState {
name: string;
uscusername: string;
project: string;
reportdate: string;
lastWeekTasksH1: number;
lastWeekTasksH2: number;
lastWeekTasksH3: number;
lastWeekTasksH4: number;
lastWeekTasksH5: number;
lastWeekTasksH6: number;
lastWeekTasksH7: number;

lastWeekTasksD1: string;
lastWeekTasksD2: string;
lastWeekTasksD3: string;
lastWeekTasksD4: string;
lastWeekTasksD5: string;
lastWeekTasksD6: string;
lastWeekTasksD7: string;

nextWeekTasksH1: number;
nextWeekTasksH2: number;
nextWeekTasksH3: number;
nextWeekTasksH4: number;
nextWeekTasksH5: number;
nextWeekTasksH6: number;
nextWeekTasksH7: number;

nextWeekTasksD1: string;
nextWeekTasksD2: string;
nextWeekTasksD3: string;
nextWeekTasksD4: string;
nextWeekTasksD5: string;
nextWeekTasksD6: string;
nextWeekTasksD7: string;

}
class WeeklyReportForm extends React.Component<WeeklyReportProps, WeeklyReportState> {
constructor(props: WeeklyReportProps) {
super(props);
this.state = {
name: '',
uscusername: '',
project: '',
reportdate: '',
lastWeekTasksH1: 0,
lastWeekTasksH2: 0,
lastWeekTasksH3: 0,
lastWeekTasksH4: 0,
lastWeekTasksH5: 0,
lastWeekTasksH6: 0,
lastWeekTasksH7: 0,

lastWeekTasksD1: '',
lastWeekTasksD2: '',
lastWeekTasksD3: '',
lastWeekTasksD4: '',
lastWeekTasksD5: '',
lastWeekTasksD6: '',
lastWeekTasksD7: '',

nextWeekTasksH1: 0,
nextWeekTasksH2: 0,
nextWeekTasksH3: 0,
nextWeekTasksH4: 0,
nextWeekTasksH5: 0,
nextWeekTasksH6: 0,
nextWeekTasksH7: 0,

nextWeekTasksD1: '',
nextWeekTasksD2: '',
nextWeekTasksD3: '',
nextWeekTasksD4: '',
nextWeekTasksD5: '',
nextWeekTasksD6: '',
nextWeekTasksD7: ''
};
this.submitClicked = this.submitClicked.bind(this);
this.handleChange = this.handleChange.bind(this);
}
submitClicked() {
/*
var request = new XMLHttpRequest();
request.withCredentials = true;
request.open('POST', 'http://localhost:8080/WeeklyReportSubmission/');
request.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
var data = JSON.stringify({
email: this.state.email,
password: this.state.password
});
request.setRequestHeader('Cache-Control', 'no-cache');
request.send(data);
alert(request.responseText + 'Your request has been submitted!');
request.onreadystatechange = function() {
if (request.readyState === 4) {
        if (request.responseText.length > 4) {
            alert('Admin registration SUCCESSFUL!');
        } else {
            alert('Admin registration FAILED.');
}
}
};
*/
}

handleChange(e: any) {
this.setState({ [e.target.id]: e.target.value });
}
    render() {
        return (
            <Form horizontal={true} >
            <FormGroup controlId="formHorizontalStudentName">
                <Col componentClass={ControlLabel} sm={2}>
                    <b>Student Name</b>
                </Col>
                <Col sm={10}>
                <FormControl
                    type="text"
                    id="name"
                    value={this.state.name}
                    onChange={e => this.handleChange(e)}
                    placeholder="Student Name"
                />
                </Col>
            </FormGroup>

            <FormGroup controlId="formHorizontalUsername">
                <Col componentClass={ControlLabel} sm={2}>
                    <b>USC Username</b>
                </Col>
                <Col sm={10}>
                <FormControl
                    type="text"
                    id="USCUsername"
                    placeholder="ttrojan@usc.edu"
                    onChange={e => this.handleChange(e)}
                    value={this.state.uscusername}
                />
                </Col>
            </FormGroup>
            
            <FormGroup controlId="formHorizontalProject">
                <Col componentClass={ControlLabel} sm={2}>
                    <b>Project Name</b>
                </Col>
                <Col sm={10}>
                <FormControl
                    type="text"
                    id="projectName"
                    value={this.state.project}
                    placeholder="Project name"
                    onChange={e => this.handleChange(e)}
                />
                </Col>
            </FormGroup>
            <FormGroup controlId="formHorizontalDate">
                <Col componentClass={ControlLabel} sm={2}>
                    <b>Report Date</b>
                </Col>
                <Col sm={10}>
                <FormControl
                    type="text"
                    id="reportDate"
                    value={this.state.reportdate}
                    placeholder="MM/DD/YYYY"
                    onChange={e => this.handleChange(e)}
                />
                </Col>
            </FormGroup>
            
            <FormGroup controlId="formHorizontalLWH1">
                <Col componentClass={ControlLabel} sm={2}>
                    <b>Last Week Task 1 Hours</b>
                </Col>
                <Col sm={10}>
                <FormControl
                    type="number"
                    id="lwtaskh1"
                    value={this.state.lastWeekTasksH1}
                    placeholder="4"
                    onChange={e => this.handleChange(e)}
                />
                </Col>
            </FormGroup>
            
            <FormGroup controlId="formHorizontalLWD1">
                <Col componentClass={ControlLabel} sm={2}>
                    <b>Task 1 Description</b>
                </Col>
                <Col sm={10}>
                <FormControl
                    type="text"
                    id="lwtaskd1"
                    value={this.state.lastWeekTasksD1}
                    placeholder="4"
                    onChange={e => this.handleChange(e)}
                />
                </Col>
            </FormGroup>
            
             <FormGroup controlId="formHorizontalLWH2">
                <Col componentClass={ControlLabel} sm={2}>
                    <b>Last Week Task 2 Hours</b>
                </Col>
                <Col sm={10}>
                <FormControl
                    type="number"
                    id="lwtaskh2"
                    value={this.state.lastWeekTasksH2}
                    placeholder="4"
                    onChange={e => this.handleChange(e)}
                />
                </Col>
            </FormGroup>
            
            <FormGroup controlId="formHorizontalLWD2">
                <Col componentClass={ControlLabel} sm={2}>
                    <b>Task 2 Description</b>
                </Col>
                <Col sm={10}>
                <FormControl
                    type="text"
                    id="lwtaskd2"
                    value={this.state.lastWeekTasksD2}
                    placeholder="4"
                    onChange={e => this.handleChange(e)}
                />
                </Col>
            </FormGroup>
            
            <FormGroup controlId="formHorizontalLWH3">
                <Col componentClass={ControlLabel} sm={2}>
                    <b>Last Week Task 3 Hours</b>
                </Col>
                <Col sm={10}>
                <FormControl
                    type="number"
                    id="lwtaskh3"
                    value={this.state.lastWeekTasksH3}
                    placeholder="4"
                    onChange={e => this.handleChange(e)}
                />
                </Col>
            </FormGroup>
            
            <FormGroup controlId="formHorizontalLWD3">
                <Col componentClass={ControlLabel} sm={2}>
                    <b>Task 3 Description</b>
                </Col>
                <Col sm={10}>
                <FormControl
                    type="text"
                    id="lwtaskd3"
                    value={this.state.lastWeekTasksD3}
                    placeholder="4"
                    onChange={e => this.handleChange(e)}
                />
                </Col>
            </FormGroup>
            
            <FormGroup controlId="formHorizontalLWH4">
                <Col componentClass={ControlLabel} sm={2}>
                    <b>Last Week Task 4 Hours</b>
                </Col>
                <Col sm={10}>
                <FormControl
                    type="number"
                    id="lwtaskh4"
                    value={this.state.lastWeekTasksH4}
                    placeholder="4"
                    onChange={e => this.handleChange(e)}
                />
                </Col>
            </FormGroup>
            
            <FormGroup controlId="formHorizontalLWD4">
                <Col componentClass={ControlLabel} sm={2}>
                    <b>Task 4 Description</b>
                </Col>
                <Col sm={10}>
                <FormControl
                    type="text"
                    id="lwtaskd4"
                    value={this.state.lastWeekTasksD4}
                    placeholder="4"
                    onChange={e => this.handleChange(e)}
                />
                </Col>
            </FormGroup>
            
             <FormGroup controlId="formHorizontalLWH5">
                <Col componentClass={ControlLabel} sm={2}>
                    <b>Last Week Task 4 Hours</b>
                </Col>
                <Col sm={10}>
                <FormControl
                    type="number"
                    id="lwtaskh4"
                    value={this.state.lastWeekTasksH5}
                    placeholder="4"
                    onChange={e => this.handleChange(e)}
                />
                </Col>
            </FormGroup>
            
            <FormGroup controlId="formHorizontalLWD5">
                <Col componentClass={ControlLabel} sm={2}>
                    <b>Task 5 Description</b>
                </Col>
                <Col sm={10}>
                <FormControl
                    type="text"
                    id="lwtaskd5"
                    value={this.state.lastWeekTasksD5}
                    placeholder="4"
                    onChange={e => this.handleChange(e)}
                />
                </Col>
            </FormGroup>
            
             <FormGroup controlId="formHorizontalLWH6">
                <Col componentClass={ControlLabel} sm={2}>
                    <b>Last Week Task 6 Hours</b>
                </Col>
                <Col sm={10}>
                <FormControl
                    type="number"
                    id="lwtaskh6"
                    value={this.state.lastWeekTasksH6}
                    placeholder="4"
                    onChange={e => this.handleChange(e)}
                />
                </Col>
            </FormGroup>
            
            <FormGroup controlId="formHorizontalLWD6">
                <Col componentClass={ControlLabel} sm={2}>
                    <b>Task 6 Description</b>
                </Col>
                <Col sm={10}>
                <FormControl
                    type="text"
                    id="lwtaskd6"
                    value={this.state.lastWeekTasksD6}
                    placeholder="4"
                    onChange={e => this.handleChange(e)}
                />
                </Col>
            </FormGroup>
            
            <FormGroup controlId="formHorizontalLWH7">
                <Col componentClass={ControlLabel} sm={2}>
                    <b>Last Week Task 7 Hours</b>
                </Col>
                <Col sm={10}>
                <FormControl
                    type="number"
                    id="lwtaskh7"
                    value={this.state.lastWeekTasksH7}
                    placeholder="4"
                    onChange={e => this.handleChange(e)}
                />
                </Col>
            </FormGroup>
            
            <FormGroup controlId="formHorizontalLWD7">
                <Col componentClass={ControlLabel} sm={2}>
                    <b>Task 7 Description</b>
                </Col>
                <Col sm={10}>
                <FormControl
                    type="text"
                    id="lwtaskd7"
                    value={this.state.lastWeekTasksD7}
                    placeholder="4"
                    onChange={e => this.handleChange(e)}
                />
                </Col>
            </FormGroup>
            
            <FormGroup controlId="formHorizontalNWH1">
                <Col componentClass={ControlLabel} sm={2}>
                    <b>Next Week Task 1 Hours</b>
                </Col>
                <Col sm={10}>
                <FormControl
                    type="number"
                    id="nwtaskh1"
                    value={this.state.nextWeekTasksH1}
                    placeholder="4"
                    onChange={e => this.handleChange(e)}
                />
                </Col>
            </FormGroup>
            
            <FormGroup controlId="formHorizontalNWD1">
                <Col componentClass={ControlLabel} sm={2}>
                    <b>Task 1 Description</b>
                </Col>
                <Col sm={10}>
                <FormControl
                    type="text"
                    id="nwtaskd1"
                    value={this.state.nextWeekTasksD1}
                    placeholder="4"
                    onChange={e => this.handleChange(e)}
                />
                </Col>
            </FormGroup>
            
            <FormGroup controlId="formHorizontalNWH2">
                <Col componentClass={ControlLabel} sm={2}>
                    <b>Next Week Task 2 Hours</b>
                </Col>
                <Col sm={10}>
                <FormControl
                    type="number"
                    id="nwtaskh2"
                    value={this.state.nextWeekTasksH2}
                    placeholder="4"
                    onChange={e => this.handleChange(e)}
                />
                </Col>
            </FormGroup>
            
            <FormGroup controlId="formHorizontalNWD2">
                <Col componentClass={ControlLabel} sm={2}>
                    <b>Task 2 Description</b>
                </Col>
                <Col sm={10}>
                <FormControl
                    type="text"
                    id="nwtaskd2"
                    value={this.state.nextWeekTasksD2}
                    placeholder="4"
                    onChange={e => this.handleChange(e)}
                />
                </Col>
            </FormGroup>
            
            <FormGroup controlId="formHorizontalNWH3">
                <Col componentClass={ControlLabel} sm={2}>
                    <b>Next Week Task 3 Hours</b>
                </Col>
                <Col sm={10}>
                <FormControl
                    type="number"
                    id="nwtaskh3"
                    value={this.state.nextWeekTasksH3}
                    placeholder="4"
                    onChange={e => this.handleChange(e)}
                />
                </Col>
            </FormGroup>
            
            <FormGroup controlId="formHorizontalNWD3">
                <Col componentClass={ControlLabel} sm={2}>
                    <b>Task 3 Description</b>
                </Col>
                <Col sm={10}>
                <FormControl
                    type="text"
                    id="nwtaskd3"
                    value={this.state.nextWeekTasksD3}
                    placeholder="4"
                    onChange={e => this.handleChange(e)}
                />
                </Col>
            </FormGroup>
            
            <FormGroup controlId="formHorizontalNWH4">
                <Col componentClass={ControlLabel} sm={2}>
                    <b>Next Week Task 4 Hours</b>
                </Col>
                <Col sm={10}>
                <FormControl
                    type="number"
                    id="nwtaskh4"
                    value={this.state.nextWeekTasksH4}
                    placeholder="4"
                    onChange={e => this.handleChange(e)}
                />
                </Col>
            </FormGroup>
            
            <FormGroup controlId="formHorizontalNWD4">
                <Col componentClass={ControlLabel} sm={2}>
                    <b>Task 4 Description</b>
                </Col>
                <Col sm={10}>
                <FormControl
                    type="text"
                    id="nwtaskd4"
                    value={this.state.nextWeekTasksD4}
                    placeholder="4"
                    onChange={e => this.handleChange(e)}
                />
                </Col>
            </FormGroup>
            
            <FormGroup controlId="formHorizontalNWH5">
                <Col componentClass={ControlLabel} sm={2}>
                    <b>Next Week Task 5 Hours</b>
                </Col>
                <Col sm={10}>
                <FormControl
                    type="number"
                    id="nwtaskh5"
                    value={this.state.nextWeekTasksH5}
                    placeholder="4"
                    onChange={e => this.handleChange(e)}
                />
                </Col>
            </FormGroup>
            
            <FormGroup controlId="formHorizontalNWD5">
                <Col componentClass={ControlLabel} sm={2}>
                    <b>Task 5 Description</b>
                </Col>
                <Col sm={10}>
                <FormControl
                    type="text"
                    id="nwtaskd5"
                    value={this.state.nextWeekTasksD5}
                    placeholder="4"
                    onChange={e => this.handleChange(e)}
                />
                </Col>
            </FormGroup>
            
             <FormGroup controlId="formHorizontalNWH6">
                <Col componentClass={ControlLabel} sm={2}>
                    <b>Next Week Task 6 Hours</b>
                </Col>
                <Col sm={10}>
                <FormControl
                    type="number"
                    id="nwtaskh6"
                    value={this.state.nextWeekTasksH6}
                    placeholder="4"
                    onChange={e => this.handleChange(e)}
                />
                </Col>
            </FormGroup>
            
            <FormGroup controlId="formHorizontalNWD6">
                <Col componentClass={ControlLabel} sm={2}>
                    <b>Task 6 Description</b>
                </Col>
                <Col sm={10}>
                <FormControl
                    type="text"
                    id="nwtaskd6"
                    value={this.state.nextWeekTasksD6}
                    placeholder="4"
                    onChange={e => this.handleChange(e)}
                />
                </Col>
            </FormGroup>
            
             <FormGroup controlId="formHorizontalNWH7">
                <Col componentClass={ControlLabel} sm={2}>
                    <b>Next Week Task 7 Hours</b>
                </Col>
                <Col sm={10}>
                <FormControl
                    type="number"
                    id="nwtaskh7"
                    value={this.state.nextWeekTasksH7}
                    placeholder="4"
                    onChange={e => this.handleChange(e)}
                />
                </Col>
            </FormGroup>
            
            <FormGroup controlId="formHorizontalNWD7">
                <Col componentClass={ControlLabel} sm={2}>
                    <b>Task 7 Description</b>
                </Col>
                <Col sm={10}>
                <FormControl
                    type="text"
                    id="nwtaskd7"
                    value={this.state.nextWeekTasksD7}
                    placeholder="4"
                    onChange={e => this.handleChange(e)}
                />
                </Col>
            </FormGroup>
            
            <FormGroup>
                <Col smOffset={2} sm={10}>
                <Button type="submit" onClick={this.submitClicked}>Submit</Button>
                </Col>
            </FormGroup>
        </Form>
        );
    }
}

export default WeeklyReportForm;
