import {Component} from "react"
import "./BuildingInfo.css"

export default class BuildingInfo extends Component {

	render() {
		let {data, onDestroyClick} = this.props;

		let title = data.title;
        let x = data.x;
        let y = data.y;

		let s = {
            top: `${10 + 50}px`,
            left: `${10}px`,
		};

        let labels = [];
        let infos = [];

        let i = 1;
        for (const [key, value] of Object.entries(data.data)) {
            labels.push(<label key={i}>{key}</label>);
            infos.push(<label key={i+1}>{value}</label>);
            i+=2;
        }

        return(
            <div className="building-info" style={s}>
                <h1 className="building-info-header">{title}</h1>
                <div className="building-info-body">
                    <div className="building-info-body-left">
                        {labels}
                    </div>
                    <div className="building-info-body-right">
                        {infos}
                    </div>
                </div>
                <button className="destroy-building-button" onClick={() => this.props.onDestroyClick(x,y)}>Destroy</button>
            </div>
        )
	}
}