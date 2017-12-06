import React from "react";

export class AppHeader extends React.Component {
	render() {
		return <header>
			<h2>TodoApp</h2>
			<span>{this.props.person}</span>
			<span>{this.props.appDate}</span>
		</header>;
	}
}