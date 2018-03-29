import * as React from 'react';
import {
    Form,
    FormGroup,
    Col,
    FormControl,
    Button,
    ControlLabel
} from 'react-bootstrap';

interface PeerReviewProps {
}

interface PeerReviewState {
name: string;
uscusername: string;
project: string;
reviewdate: string;

peername: string;
peerpositivefeedback: string;
peernegativefeedback: string;

}
class PeerReviewForm extends React.Component<PeerReviewProps, PeerReviewState> {
constructor(props: PeerReviewProps) {
super(props);
this.state = {
name: '',
uscusername: '',
project: '',
reviewdate: '',

peername: '',
peerpositivefeedback: '',
peernegativefeedback: ''
};
this.submitClicked = this.submitClicked.bind(this);
this.handleChange = this.handleChange.bind(this);
}
submitClicked() {

var request = new XMLHttpRequest();
request.withCredentials = true;
request.open('POST', 'http://localhost:8080/peerReviewForm/');
request.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
var data = JSON.stringify({
name: this.state.name,
uscusername: this.state.uscusername,
project: this.state.project,
peerreviewreviewdate: this.state.reviewdate,
peername: this.state.peername,
peerpositivefeedback: this.state.peerpositivefeedback,
peernegativefeedback: this.state.peernegativefeedback,
});
request.setRequestHeader('Cache-Control', 'no-cache');
request.send(data);
alert(request.responseText + 'Your request has been submitted!');
request.onreadystatechange = function() {
if (request.readyState === 4) {
    alert('Peer Review submission SUCCESSFUL!');
}
};

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
                    onChange={e => this.handleChange(e)}
                    value={this.state.name}
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
                    id="uscusername"
                    onChange={e => this.handleChange(e)}
                    value={this.state.uscusername}
                    placeholder="ttrojan"
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
                    id="project"
                    onChange={e => this.handleChange(e)}
                    value={this.state.project}
                    placeholder="Project name"
                />
                </Col>
            </FormGroup>
            <FormGroup controlId="formHorizontalReviewDate">
                <Col componentClass={ControlLabel} sm={2}>
                    <b>Review Date</b>
                </Col>
                <Col sm={10}>
                <FormControl
                    type="text"
                    id="reviewdate"
                    value={this.state.reviewdate}
                    placeholder="MM/DD/YYYY"
                    onChange={e => this.handleChange(e)}
                />
                </Col>
            </FormGroup>
            
            <FormGroup controlId="formHorizontalPeerName">
                <Col componentClass={ControlLabel} sm={2}>
                    <b>Peer Name</b>
                </Col>
                <Col sm={10}>
                <FormControl
                    type="text"
                    id="peername"
                    onChange={e => this.handleChange(e)}
                    placeholder="Traveler"
                    value={this.state.peername}
                />
                </Col>
            </FormGroup>
            
            <FormGroup controlId="formHorizontalPositiveFeedback">
                <Col componentClass={ControlLabel} sm={2}>
                    <b>Positive Feedback</b>
                </Col>
                <Col sm={10}>
                <FormControl
                    type="text"
                    id="peerpositivefeedback"
                    value={this.state.peerpositivefeedback}
                    placeholder="What this person did well."
                    onChange={e => this.handleChange(e)}
                />
                </Col>
            </FormGroup>
            
             <FormGroup controlId="formHorizontalNegativeFeedback">
                <Col componentClass={ControlLabel} sm={2}>
                    <b>Negative Feedback</b>
                </Col>
                <Col sm={10}>
                <FormControl
                    type="text"
                    id="peernegativefeedback"
                    value={this.state.peernegativefeedback}
                    placeholder="What this person can work on."
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

export default PeerReviewForm;
