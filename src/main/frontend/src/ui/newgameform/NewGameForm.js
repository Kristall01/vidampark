import { Component } from "react"
import "./NewGameForm.css"
import Window from "ui/window/Window"

class NewGameForm extends Component {

    constructor(props) {
        super(props);

        
        this.state = {
            name: "Név",
            height: 15,
            width: 25
        }
    }

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
                        <input type="text" name="name" value={ this.state.name } onChange={event => this.setState({name: event.target.value})} />
                        <input type="number" height="height" value={ this.state.height } onChange={event => this.setState({height: event.target.value})}/>
                        <input type="number" width="width" value={ this.state.width } onChange={event => this.setState({width: event.target.value})}/>
                    </div>
                    <div className="buttons">
                        <button onClick={() => {okWindow(this.state.name, this.state.height, this.state.width);}}>Létrehoz</button>
                        <button onClick={closeWindow}>Mégse</button>
                    </div>
                </Window>
            </div>
        );
    }
}

export default NewGameForm;







