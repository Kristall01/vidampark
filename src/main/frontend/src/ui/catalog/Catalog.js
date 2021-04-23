import Window from "ui/window/Window"
import Building from "ui/building/Building"

import "./Catalog.css"

const Catalog = ({currentMoney, setBuildTarget, catalogData, closeWindow, hidden}) => {

	let i = 0;
	let data = catalogData.map(record => {
		return <Building currentMoney={currentMoney} buyHandler={
			() => {
				setBuildTarget(record.type);
				closeWindow();
			}
		} key={++i} {...record}></Building>;
	});

	return (
		<Window hidden={hidden} onclose={closeWindow} title="katalógus" height="500px" width="800px" centered>
			<div className="building-catalog">
				{data}
			</div>
		</Window>
	);

}

/*

<Building name="hotdog árufffffffffs" price="10" url="/buildings/hot-dog-stand.svg"></Building> <Building name="óriáskerék" price="10" url="/buildings/ferris-wheel.png"></Building> <Building name="óriáskerék" price="10" url="/buildings/ferris-wheel.png"></Building> <Building name="óriáskerék" price="10" url="/buildings/ferris-wheel.png"></Building> <Building name="óriáskerék" price="10" url="/buildings/ferris-wheel.png"></Building> <Building name="óriáskerék" price="10" url="/buildings/ferris-wheel.png"></Building> <Building name="óriáskerék" price="10" url="/buildings/ferris-wheel.png"></Building> <Building name="óriáskerék" price="10" url="/buildings/ferris-wheel.png"></Building> <Building name="óriáskerék" price="10" url="/buildings/ferris-wheel.png"></Building>

*/

export default Catalog;