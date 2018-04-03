import * as React from 'react';
import {
  Panel,
  FormGroup,
  ControlLabel,
  Button,
  Col,
  Row
} from 'react-bootstrap';
import DatePicker from 'react-date-picker';

interface AdminState {
  deliverableOneDate: Date;
  deliverableTwoDate: Date;
  deliverableThreeDate: Date;
  deliverableFourDate: Date;
  deliverableFiveDate: Date;
  deliverableSixDate: Date;
  deliverableSevenDate: Date;
  numRankedProjects: number;
}

interface AdminProps {
}

class AdminHome extends React.Component<AdminProps, AdminState> {
  
  constructor(props: AdminProps) {
    super(props);
    this.state = {
      deliverableOneDate: new Date(),
      deliverableTwoDate: new Date(),
      deliverableThreeDate: new Date(),
      deliverableFourDate: new Date(),
      deliverableFiveDate: new Date(),
      deliverableSixDate: new Date(),
      deliverableSevenDate: new Date(),
      numRankedProjects: 5
    };
    this.handleChangeOne = this.handleChangeOne.bind(this);
    this.handleChangeTwo = this.handleChangeTwo.bind(this);
    this.handleChangeThree = this.handleChangeThree.bind(this);
    this.handleChangeFour = this.handleChangeFour.bind(this);
    this.handleChangeFive = this.handleChangeFive.bind(this);
    this.handleChangeSix = this.handleChangeSix.bind(this);
    this.handleChangeSeven = this.handleChangeSeven.bind(this);
    this.saveConfigurations = this.saveConfigurations.bind(this);
  }

  onChange(e: any) {
    this.setState({numRankedProjects: e.target.value});
  }
  handleChangeOne = (deliverableOneDate: Date) => this.setState({ deliverableOneDate });
  handleChangeTwo = (deliverableTwoDate: Date) => this.setState({ deliverableTwoDate });
  handleChangeThree = (deliverableThreeDate: Date) => this.setState({ deliverableThreeDate });
  handleChangeFour = (deliverableFourDate: Date) => this.setState({ deliverableFourDate });
  handleChangeFive = (deliverableFiveDate: Date) => this.setState({ deliverableFiveDate });
  handleChangeSix = (deliverableSixDate: Date) => this.setState({ deliverableSixDate });
  handleChangeSeven = (deliverableSevenDate: Date) => this.setState({ deliverableSevenDate });

  saveConfigurations() {
    var request = new XMLHttpRequest();
    request.withCredentials = true;
    request.open('POST', 'http://localhost:8080/admin/configurations/save');
    request.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
    var data = JSON.stringify({
      deliverableOneDate: this.state.deliverableOneDate,
      deliverableTwoDate: this.state.deliverableTwoDate,
      deliverableThreeDate: this.state.deliverableThreeDate,
      deliverableFourDate: this.state.deliverableFourDate,
      deliverableFiveDate: this.state.deliverableFiveDate,
      deliverableSixDate: this.state.deliverableSixDate,
      deliverableSevenDate: this.state.deliverableSevenDate,
      numRankedProjects: this.state.numRankedProjects
    });
    request.setRequestHeader('Cache-Control', 'no-cache');
    request.send(data);
    alert('Configurations saved!');
  }

  render() {
    return (
          <div>
            <Panel>
              <Panel.Heading>
                <Panel.Title componentClass="h3">Configurations</Panel.Title>
              </Panel.Heading>
              <Panel.Body>
                <Row>
                <Col mdOffset={1}>
                <ControlLabel>
                  Set deliverable due dates below.
                </ControlLabel>
                </Col>
                </Row>
                <Row>
                <FormGroup>
                  <Col mdOffset={1}>
                  <ControlLabel>
                    Deliverable 1
                  </ControlLabel>
                  </Col>
                  <Col mdOffset={1}>
                  <DatePicker
                    onChange={this.handleChangeOne}
                    value={this.state.deliverableOneDate}
                  />
                  </Col>
                </FormGroup>
                <FormGroup>
                  <Col mdOffset={1}>
                  <ControlLabel>
                    Deliverable 2
                  </ControlLabel>
                  </Col>
                  <Col mdOffset={1}>
                  <DatePicker
                    onChange={this.handleChangeTwo}
                    value={this.state.deliverableTwoDate}
                  />
                  </Col>
                </FormGroup>
                <FormGroup>
                  <Col mdOffset={1}>
                  <ControlLabel>
                    Deliverable 3
                  </ControlLabel>
                  </Col>
                  <Col mdOffset={1}>
                  <DatePicker
                    onChange={this.handleChangeThree}
                    value={this.state.deliverableThreeDate}
                  />
                  </Col>
                </FormGroup>
                <FormGroup>
                  <Col mdOffset={1}>
                  <ControlLabel>
                    Deliverable 4
                  </ControlLabel>
                  </Col>
                  <Col mdOffset={1}>
                  <DatePicker
                    onChange={this.handleChangeFour}
                    value={this.state.deliverableFourDate}
                  />
                  </Col>
                </FormGroup>
                <FormGroup>
                  <Col mdOffset={1}>
                  <ControlLabel>
                    Deliverable 5
                  </ControlLabel>
                  </Col>
                  <Col mdOffset={1}>
                  <DatePicker
                    onChange={this.handleChangeFive}
                    value={this.state.deliverableFiveDate}
                  />
                  </Col>
                </FormGroup>
                <FormGroup>
                  <Col mdOffset={1}>
                  <ControlLabel>
                    Deliverable 6
                  </ControlLabel>
                  </Col>
                  <Col mdOffset={1}>
                  <DatePicker
                    onChange={this.handleChangeSix}
                    value={this.state.deliverableSixDate}
                  />
                  </Col>
                </FormGroup>
                <FormGroup>
                  <Col mdOffset={1}>
                  <ControlLabel>
                    Deliverable 7
                  </ControlLabel>
                  </Col>
                  <Col mdOffset={1}>
                  <DatePicker
                    onChange={this.handleChangeSeven}
                    value={this.state.deliverableSevenDate}
                  />
                  </Col>
                </FormGroup>
                </Row>
                <Row>
                <Col mdOffset={1}>
                <input value={this.state.numRankedProjects} onChange={e => this.onChange(e)}/>
                </Col>
                </Row>
                <Row>
                <Col mdOffset={1}>
                <Button type="submit" bsStyle="primary" onClick={this.saveConfigurations}>
                  Save
                </Button>
                </Col>
                </Row>
              </Panel.Body>
            </Panel>
          </div>
    );
  }
}

export default AdminHome;