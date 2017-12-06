import React from "react";
import {TodoItem} from "./TodoItem";

export class TodoList extends React.Component {
	render() {
		//this.props.list
		// {this.props.list.map(
		// 	function(item) {
		// 		return <TodoItem/>;
		// 	}
		// )}
		// {this.props.list.map((item) => {
		// 		return <TodoItem/>;
		// 	}
		// )}
		//{this.props.list.map((item) => <TodoItem/>)}
		// {this.props.list.map((item) => {
		// 	return <TodoItem/>;
		// })}
		return <section>
			{this.props.list.map((item, index) =>
				<TodoItem key={item.name} todo={item} idx={index} />)}
		</section>
	}
}