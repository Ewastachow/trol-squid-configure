import React from 'react';

//css-modules
///import asddas from "plik.css";

export class ActionBar extends React.Component {
	render() {
		let styleA = {
			backgroundColor: "#f00",
			fontSize: "18px"
		};
		return <section className="asdas___1231323 ActionBar">
			<a style={styleA}>Toggle all</a>
			<a style={{backgroundColor: "#0f0"}}>Remove all</a>
			<a>Save</a>
		</section>;
	}
}