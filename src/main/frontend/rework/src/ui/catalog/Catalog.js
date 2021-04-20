import Window from "ui/window/Window"
import Building from "ui/building/Building"

import "./Catalog.css"

const Catalog = ({closeWindow, hidden}) => {

	return (
		<Window hidden={hidden} onclose={closeWindow} title="katalógus" height="500px" width="800px" centered>
			<div className="building-catalog">
				<Building name="hotdog árufffffffffs" price="10" url="/buildings/hot-dog-stand.svg"></Building>
				<Building name="óriáskerék" price="10" url="/buildings/ferris-wheel.png"></Building>
				<Building name="óriáskerék" price="10" url="/buildings/ferris-wheel.png"></Building>
				<Building name="óriáskerék" price="10" url="/buildings/ferris-wheel.png"></Building>
				<Building name="óriáskerék" price="10" url="/buildings/ferris-wheel.png"></Building>
				<Building name="óriáskerék" price="10" url="/buildings/ferris-wheel.png"></Building>
				<Building name="óriáskerék" price="10" url="/buildings/ferris-wheel.png"></Building>
				<Building name="óriáskerék" price="10" url="/buildings/ferris-wheel.png"></Building>
				<Building name="óriáskerék" price="10" url="/buildings/ferris-wheel.png"></Building>
			</div>
		</Window>
	);

}

export default Catalog;