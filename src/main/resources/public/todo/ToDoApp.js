import React from 'react';
import ReactDOM from 'react-dom';
import {RootView} from "./components/RootView";
import {fakeTodoHelper} from "./utils/FakeTodoHelper";

let data = {
	person: "Jan Kowalski",
	appDate: Date.now(),
	todos: fakeTodoHelper(5)
};

ReactDOM.render(
	<RootView appData={data} />,
	document.getElementById('app')
);