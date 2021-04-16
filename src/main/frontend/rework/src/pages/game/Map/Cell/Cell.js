import React, { Component } from "react";
import "./Cell.css";

export default class Cell extends Component {

    constructor(props)
    {
        super(props);
    }

    render() {
        return (
            <div className="Cell" onClick={ e => console.log(this.props.coords) }>
				<div className="inner"></div>
			</div>
        );
    }
}