import {Component} from "react"
import "./Window.css"

export default class Window extends Component {

	render() {
		let {hidden, width, height, x, y, centered, title, bgColor, borderColor } = this.props;
		let classList = ["window-component"];
		title = title || "window"
		let s = {
			width: width || 500,
			height: height || 200,
			backgroundColor: bgColor,
			borderColor: borderColor,
		};
		let hs = {
			backgroundColor: borderColor,
			borderColor: borderColor,
		}
		if(hidden) {
			classList.push("hidden");
		}
		if(centered) {
			classList.push("centered");
		}
		if(x) {
			s.x = x;
		}
		if(y) {
			s.y = y;
		}

		return(
			<div className={classList.join(" ")} style={s}>
				<div className="header" style={hs}>
					<span>{title}</span>
					<div className="close" onClick={this.props.onclose}>
						<svg viewBox="0 0 20 20" className="shape">
							<path></path>
						</svg>
					</div>
				</div>
				<div className="content">
					{this.props.children}
				</div>
			</div>
		)
	}

}