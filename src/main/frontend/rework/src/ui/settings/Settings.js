import { Component } from "react"
import "./Settings.css"
import Window from "ui/window/Window"

class Settings extends Component {

    render() {
        let {hidden, closeWindow, okWindow, buildings} = this.props;

        return(
            <div className="settings">
                <Window onclose={closeWindow} hidden={hidden} height="350px" width="310px" bgColor="#f3f3f3" borderColor="#1477ff" title="Árak beállítása">
                    <div className="labels">
                        <label>Belépőjegy</label>
                        {buildings.map( building => 
                            <label>{ building.name }</label>
                        )}
                    </div>
                    <div className="inputs">
                        <input type="number" defaultValue="10" />
                        {buildings.map( building => 
                            <input type="number" defaultValue={ building.usePrice }/>
                        )}
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







