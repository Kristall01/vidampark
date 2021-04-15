import React, { Component } from "react";
import Map from './Map/Map';
import "./Gamepage.css";
//import Window from './Window/window';

export default class Gamepage extends Component {

    constructor()
    {
        super();

        
    }

    getMoney() {
        //return this.props.money;
        return 1234567890;
    }

	render() {
        return (
            <div className="Gamepage">
                <div className="header">
                    <div className="money">Money: ${this.getMoney()} </div>
                    <div className="buttons">
                        <button className="openParkButton" onClick={ () => console.log("Open Park") }>🚪Open Park</button>
                        <button className="pauseButton" onClick={ () => console.log("Building Catalog") }>🏢 Building Catalog</button>
                        <button className="pauseButton" onClick={ () => console.log("Pause") }>⏸ Pause</button>
                        <button className="pauseButton" onClick={ () => console.log("Menu") }>Menu</button>
                    </div>
                </div>
                <div className="main">
                    <Map/>
                </div>
            </div>
        );
	}
}