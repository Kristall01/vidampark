import "./Gamestate.css"

import Button from "ui/button/Button"

const Gamestate =  props =>
<>
	<div className="gamestate-component">
		<div className="inner">
			<div className="title">{props.name || "???"}</div>
			<div className="content">
				{props.startTime ? ("kezdés ideje: " + props.startTime) : null}
			</div>
			<div className="button-line">
				<Button>indítás</Button>
			</div>
		</div>
	</div>
</>

export default Gamestate