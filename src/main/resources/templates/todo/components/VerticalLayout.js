import React from 'react';

export class VerticalLayout extends React.Component {
	//{this.props.children[0]}
	render() {
		return <div className="verticalLay">
			{this.props.children}
		</div>;
	}
}