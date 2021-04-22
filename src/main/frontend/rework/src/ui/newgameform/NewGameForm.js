import { Component } from "react"
import "./NewGameForm.css"
import Window from "ui/window/Window"

class NewGameForm extends Component {

    render() {
        let {hidden, closeWindow, okWindow} = this.props;
        
        return(
            <div className="new-game-form">
                <Window onclose={closeWindow} hidden={hidden} height="200px" width="465px" bgColor="#f3f3f3" borderColor="#9147ff" title="Új játék adatainak megadása">
                    <div className="labels">
                        <label>Név</label>
                        <label>Magasság</label>
                        <label>Szélesség</label>
                    </div>
                    <div className="inputs">
                        <input type="text" defaultValue="Név" />
                        <input type="number" defaultValue="10" />
                        <input type="number" defaultValue="20" />
                    </div>
                    <div className="buttons">
                        <button onClick={okWindow}>Létrehoz</button>
                        <button onClick={closeWindow}>Mégse</button>
                    </div>
                </Window>
            </div>
        );
    }
}

export default NewGameForm;