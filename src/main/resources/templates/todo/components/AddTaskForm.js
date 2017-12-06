import React from "react";

export class AddTaskForm extends React.Component {

	constructor(props) {
		super(props);
		this.state = {inputName: "pustawartosc"};
		this.onButtonClick = this.onButtonClick.bind(this);
		this.onChangeNameInput = this.onChangeNameInput.bind(this);
	}

	componentWillMount() {
		//console.log(this.input2.value); źle
	}

	componentDidMount() {
		// var ctx = this.canvas.getContent("2d");
	}

	onButtonClick() {
		console.log("onButtonClick");
		this.props.onNewTaskFn({
			name: this.state.inputName,
			desc: "test",
			status: false
		});
		this.setState({inputName: ""});
		console.log(this.input2.value);
	}

	onChangeNameInput(event) {
		console.log(event.target.value);
		this.setState({inputName: event.target.value});
		event.preventDefault();
	}

	render() {
		return <div>
			<input
				value={this.state.inputName}
				type="text"
				onChange={this.onChangeNameInput} />

			<input type="text" ref={(input) => this.input2 = input} />

			<button onClick={this.onButtonClick}>
				Add
			</button>
		</div>;
	}
}