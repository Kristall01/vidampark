function setMap(table, width, height) {
	let buffer = "<tbody>";
	for (let y = 0; y < height; y++) {
		buffer += "<tr>";
		for (let x = 0; x < width; x++) {
			buffer += "<td></td>"
		}
		buffer += "</tr>"
	}
	table.querySelector("tbody").innerHTML = buffer;
	document.querySelectorAll("td").forEach(e => {
		e.addEventListener("click", ev => {
			console.log(ev.target);
			ev.target.classList.add("road");
		})
	});
}

setMap(document.getElementById("gamespace"), 5, 5);

