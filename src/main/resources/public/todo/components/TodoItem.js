import React from 'react';

export class TodoItem extends React.Component {

	getFullStatus(status) {
		return status ? "Closed" : "Open";
	}

	render() {
		let todo = this.props.todo;

		//let status = todo.status ? "Closed" : "Open";

		return (
			<div>
				{this.props.index}<br/>
				{todo.name}<br/>
				{todo.desc}<br/>
				{this.getFullStatus(todo.status)}
		</div>);
	}
}