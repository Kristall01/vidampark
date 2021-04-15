import "./Selectpage.css"
import Gamestate from "./Gamestate/Gamestate"
import Button from "ui/button/Button"

/*const Newstate = props =>
<>
	<div className="new-game">
		<div className="cross crossX"></div>
		<div className="cross crossY"></div>
	</div>
</>*/


const Selectpage =  () =>
<div className="select-page">
	<div className="header">
		<Button>új játék</Button>
		<Button>kijelentkezés</Button>
	</div>
	<div className="content">
		<Gamestate startTime="5500"></Gamestate>
		<Gamestate startTime="5500"></Gamestate>

		<Gamestate startTime="5500"></Gamestate>

	
	</div>
</div>

export default Selectpage