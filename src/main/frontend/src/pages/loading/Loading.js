import "./Loading.css"

const Loading = ({data}) => {

	let {text, spin} = data;

	let spinner = spin ? <div className="spinner"></div> : null;

	return <div className="loading-component">
		<div className="loading">{text}</div>
		{spinner}
	</div>

}



export default Loading