import "./Gamestate.css"

import Button from "ui/button/Button"

const Gamestate = ({data, rename, select, remove}) => {

	const renameFn = () => {
		let newname = window.prompt("új név:");
		if(!newname)
			return;
		rename(data.id, newname);
	};

	const selectGame = () => {
		select(data.id);
	}

	const deleteGame = () => {
		let confirm = window.confirm("Biztosan törölni akarod?");
		if(!confirm)
			return;
		remove(data.id);
	}

	return (
		<>
			<div className="gamestate-component">
				<div className="inner">
					<div className="title">{data.name || "???"}</div>
					<div className="content">
						{null /*props.startTime ? ("kezdés ideje: " + props.startTime) : null*/}
					</div>
					<div className="button-line">
						<Button onClick={deleteGame}>🗑️</Button>
						<Button onClick={renameFn}>🖊 Átnevezés</Button>
						<Button onClick={selectGame}>▶ Játék</Button>
					</div>
				</div>
			</div>
		</>
	)
}


export default Gamestate