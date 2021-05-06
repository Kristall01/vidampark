import { Component } from "react"
import "./Settings.css"
import Window from "ui/window/Window"

class Settings extends Component {

    render() {
        let {hidden, closeWindow, okWindow, buildings} = this.props;

		let buildingComponents = [];
		let buildingKey = 0;
		buildings.forEach(building => {
			buildingComponents.push(<label key={buildingKey++}>{ building.name }</label>);
		});

		let buildingInputs = [];
		buildingKey = 0;
		buildings.forEach( building => {
			buildingInputs.push(<input key={buildingKey++} type="number" defaultValue={ building.usePrice }/>);
		});

        return(
            <div className="settings">
                <Window onclose={closeWindow} hidden={hidden} height="350px" width="310px" bgColor="#f3f3f3" borderColor="#1477ff" title="Árak beállítása">
                    <div className="labels">
                        <label>Belépőjegy</label>
						{buildingComponents}
                    </div>
                    <div className="inputs">
                        <input type="number" defaultValue="10" />
						{buildingInputs}
                    </div>
                    <div className="buttons">
                        <button onClick={okWindow}>Beállítás</button>
                        <button onClick={closeWindow}>Mégse</button>
                    </div>
                </Window>
            </div>
        );
    }
}

export default Settings;







