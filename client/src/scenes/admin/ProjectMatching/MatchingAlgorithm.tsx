import * as React from 'react';

class MatchingAlgorithm extends React.Component {
  
  runMatchingAlgorithm() {
    var request = new XMLHttpRequest();
    request.withCredentials = true;
    request.open('POST', 'http://localhost:8080/assignments/run');
    request.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
    var data = JSON.stringify({
    });
    request.setRequestHeader('Cache-Control', 'no-cache');
    request.send(data);
    alert('Your project proposal has been submitted!');
  }

  render() {
    return (
          <div>
            <button onClick={this.runMatchingAlgorithm}>
              Run Matching Algorithm
            </button>
         </div>
    );
  }
}

export default MatchingAlgorithm;