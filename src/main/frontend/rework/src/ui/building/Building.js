import "./Building.css";

import Button from "ui/button/Button"

const Building = props =>

<div className="building-component">
	<div className="building-inner">
		<div className="title">{props.name || "Lorem ipsum"}</div>
		<div className="content">
			<div className="image">
				<img src={props.url}></img>
			</div>
			<div className="info">
				<div className="label">
					<span>{props.price || "?"}</span>
					<i class="icon fas fa-money-bill-wave"></i>
				</div>
				<div className="label">
					<span>{props.time || "?"}</span>
					<i class="icon far fa-clock"></i>
				</div>
			</div>
		</div>
		<div className="button">
			<Button bgcolor="blue">vásárlás</Button>
		</div>
	</div>
</div>

export default Building;