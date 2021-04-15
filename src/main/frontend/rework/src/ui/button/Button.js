import "./Button.css"

const Button = props => {
	let {wide, disabled, bgcolor, color, onClick, ...others} = props;
	let className = "button";

	let addedProps = {...others};
	if(wide) {
		className += " wide";
	}
	if(disabled) {
		className += " disabled";
	}

	let style = {};
	if(bgcolor) {
		style.backgroundColor = bgcolor;
	}
	if(color) {
		style.color = color;
	}
	if(onClick && !disabled) {
		addedProps.onClick = onClick;
	}
	addedProps.className = className;
	addedProps.style = style;
	return (
		
	<div {...addedProps}>
		<div className="dark">
			{props.children}
		</div>
	</div>

	)
;}

export default Button;