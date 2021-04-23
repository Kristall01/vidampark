import React, {Component } from "react";

class Navigraph extends Component {

	constructor(props) {
		super(props);
		this.canvas = React.createRef();
	}

	componentDidUpdate() {
		let {width, height, renderWidth, renderHeight, graph} = this.props;
		/**
		 * @type {CanvasRenderingContext2D}
		 */
		let ctx = this.canvas.current.getContext("2d");
		ctx.fillStyle = "white";
		ctx.fillRect(0, 0, renderWidth, renderHeight);
		this.drawGrid(ctx);
		if(graph != null) {
			this.drawNodes(ctx, this.props.graph.nodes);
			this.drawRoads(ctx, this.props.graph.roads);
		}
	}

	/**
	 * @param {CanvasRenderingContext2D} ctx 
	 */
	drawGrid(ctx) {
		ctx.lineWidth = 2;
		ctx.strokeStyle = 'lightblue';
		let {width, height, renderWidth, renderHeight} = this.props;
		let segment = renderWidth / width;
		ctx.beginPath();
		for(let i = 1; i < (width); ++i) {
			ctx.moveTo(segment*i, 0);
			ctx.lineTo(segment*i, renderHeight);
		}
		segment = renderHeight / height;
		for(let i = 1; i < height; ++i) {
			ctx.moveTo(0, segment*i);
			ctx.lineTo(renderWidth, segment*i);
		}
		ctx.stroke();
		ctx.closePath();
	}

	/**
	 * @param {CanvasRenderingContext2D} ctx 
	 */
	drawNodes(ctx, nodes) {
		let cellSize = this.props.renderWidth / this.props.width;
		ctx.fillStyle = "red";
		ctx.strokeStyle = "black";
		nodes.forEach(node => {
			ctx.beginPath();
			ctx.rect(node.x*cellSize, node.y*cellSize, cellSize, cellSize)
			//ctx.arc(node.x*cellSize + cellSize/2, node.y*cellSize + cellSize/2, cellSize*0.25, 0, 2 * Math.PI);
			ctx.fill();
			ctx.closePath();
		});
	}

	/**
	 * @param {CanvasRenderingContext2D} ctx 
	 */
	drawRoads(ctx, roads) {
		roads.forEach(road => {
			ctx.beginPath();
			ctx.lineWidth = 10;
			ctx.strokeStyle = "black";
			ctx.fillStyle = "black";
			let cellSize = this.props.renderWidth / this.props.width;
			if(road.from.y == road.to.y) {
				ctx.fillRect(
					(road.from.x+0.8)*cellSize,
					(road.from.y+0.3)*cellSize,
					((road.to.x - road.from.x - 0.6)*cellSize),
					((0.4)*cellSize),
				);
			}
			/*
			switch(node.to.direction) {
				case "up": {
					ctx.lineTo(node.from.x*cellSize+cellSize/2, (node.from.y-node.to.amount)*cellSize + cellSize/2)
					break;
				}
				case "down": {
					ctx.rect(
						(node.from.x + 0.25)*cellSize,
						(node.from.y+0.8)*cellSize,
						(cellSize*0.5),
						(module.to.amount-0.6)*cellSize
					);
					ctx.lineTo((node.from.x + 0.6)*cellSize, (node.from.y+node.to.amount)*cellSize + cellSize/2)
					break;
				}
				case "right": {
					ctx.lineTo((node.from.x + node.to.amount)*cellSize+cellSize/2, (node.from.y*cellSize + cellSize/2));
					break;
				}
				case "left": {
					ctx.lineTo((node.from.x - node.to.amount)*cellSize+cellSize/2, (node.from.y*cellSize + cellSize/2));
					break;
				}
			}*/
			ctx.closePath();
		});
	}

	render () {
		let {renderWidth, renderHeight, width, height, hidden} = this.props;
		let style = {width: renderWidth, height: renderHeight};
		if(hidden) {
			style['display'] = 'none';
		}
		return (
			<div style={style}>
				<canvas className="navigraph-component" ref={this.canvas} height={renderHeight} width={renderWidth}>

				</canvas>
			</div>
		);
	}
}


export default Navigraph;