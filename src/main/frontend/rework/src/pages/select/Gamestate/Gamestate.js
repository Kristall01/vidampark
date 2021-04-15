import "./Gamestate.css"

import Button from "ui/button/Button"

const Gamestate = ({data, rename}) => {

	const renameFn = () => {
		let newname = window.prompt("új név:");
		if(!newname)
			return;
		rename(data.id, newname);
	};

	return (
		<>
			<div className="gamestate-component">
				<div className="inner">
					<div className="title">{data.name || "???"}</div>
					<div className="content">
						{null /*props.startTime ? ("kezdés ideje: " + props.startTime) : null*/}
					</div>
					<div className="button-line">
						<Button>indítás</Button>
						<Button onClick={renameFn}>átnevezés</Button>
					</div>
				</div>
			</div>
		</>
	)
}


export default Gamestate