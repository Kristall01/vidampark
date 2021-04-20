import "./Building.css";

import Button from "ui/button/Button"

const Building = ({buyHandler, buildCost, nickname, buildTime, shopIcon}) =>

<div className="building-component">
	<div className="building-inner">
		<div className="title">{nickname || "Lorem ipsum"}</div>
		<div className="content">
			<div className="image">
				<img src={"/buildings/shop/"+shopIcon} alt=""></img>
			</div>
			<div className="info">
				<div className="label">
					<span>{buildCost || "?"}</span>
					<i className="icon fas fa-money-bill-wave"></i>
				</div>
				<div className="label">
					<span>{buildTime || "?"}</span>
					<i className="icon far fa-clock"></i>
				</div>
			</div>
		</div>
		<div className="button">
			<Button onClick={buyHandler} bgcolor="blue">vásárlás</Button>
		</div>
	</div>
</div>

export default Building;