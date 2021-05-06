import React from "react";
import Dot from "ui/Dot/Dot"

//const path = [{"x":0,"y":1},{"x":0,"y":2},{"x":0,"y":3},{"x":0,"y":4},{"x":0,"y":5},{"x":0,"y":6},{"x":1,"y":6},{"x":2,"y":6},{"x":3,"y":6},{"x":4,"y":6},{"x":4,"y":7},{"x":5,"y":7},{"x":6,"y":7},{"x":6,"y":6},{"x":7,"y":6},{"x":8,"y":6},{"x":8,"y":7},{"x":9,"y":7},{"x":10,"y":7},{"x":10,"y":6},{"x":10,"y":5},{"x":10,"y":4},{"x":10,"y":3},{"x":10,"y":2},{"x":9,"y":2},{"x":8,"y":2},{"x":7,"y":2},{"x":6,"y":2},{"x":5,"y":2},{x:0,y:0}]
const path = [{"x":0,"y":0},{"x":0,"y":1}];

const Uitestpage = props => {
return (
	<div style={{width: 800, height: 800}}>
		<Dot cellSize={800/10} time={500} path={path}/>
	</div>
);
}

export default Uitestpage