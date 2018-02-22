const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');

class App extends React.Component {

	constructor(props) {
		super(props);
	}

	componentDidMount() {
		
	}

	render() {
		return (
			<h1>Welcome to the CSCI401 Portal!</h1>
		)
	}
}

ReactDOM.render(
		<App />,
		document.getElementById('react')
	)