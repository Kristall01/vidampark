import React, { Component } from "react";
import Cell from "./Cell/Cell";
import "./Map.css";

export default class Map extends Component {
    constructor(props) {
        super(props);

        let map = [];
        for(let i = 0; i < this.getHeight(); ++i) 
        {
            let row = [];
            for(let j = 0; j < this.getWidth(); ++j) 
            {
                row.push({});
            }
            map.push(row);
        }        

        this.state = {
            map: map,
        }
    }

    getWidth() 
    {
        //return this.props.sizeWidth;
        return this.props.width;
    }

    getHeight() 
    {
        //return this.props.sizeHeight;
        return this.props.height;
    }

    generateMap() 
    {
        let map = [];
        for(let i = 0; i < this.getHeight(); ++i) 
        {
            let row = [];
            for(let j = 0; j < this.getWidth(); ++j) 
            {
                row.push(<Cell key={i+j} coords={ {x: j, y: i} } />);
            }
            map.push(<div key={i} className="row">{row}</div>);
        }
        return <div className="Map">{map}</div>;
    }

    render() {
        return this.generateMap();
    }
}