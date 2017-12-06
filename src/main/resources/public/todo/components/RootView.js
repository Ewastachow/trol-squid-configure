import React from 'react';
import {AppFooter} from "./AppFooter";
import {AppHeader} from "./AppHeader";
import {AddTaskForm} from "./AddTaskForm";
import {TodoList} from "./TodoList";
import {ActionBar} from "./ActionBar";
import {VerticalLayout} from "./VerticalLayout";

export class RootView extends React.Component {

	constructor(props) {
		super(props);
		this.state = this.props.appData;
		this.onNewTodo = this.onNewTodo.bind(this);
	}

	componentWillMount() {
		console.log("will mount");
	}
	
	onNewTodo(todoDesc) {
		console.log("onNewTodo", todoDesc);
		let todos = this.state.todos;
		todos.push(todoDesc);
		this.setState({
			todos: todos
		});
	}

	/*
		jeden element jsx + dzieci
		null, false, undefined
		tablica elmentów jsx
		int
	 */
	render() {
		// let appData = {}...
		return <div>
			<VerticalLayout>
				<AppHeader person={this.state.person} appDate={this.state.appDate} />
				<ActionBar />
				<TodoList list={this.state.todos} />
				<AddTaskForm onNewTaskFn={this.onNewTodo} />
				<AppFooter content={"warsztaty"} />
			</VerticalLayout>
		</div>
	}
}