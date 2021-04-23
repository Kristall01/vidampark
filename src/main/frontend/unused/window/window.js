import interact from 'https://cdn.interactjs.io/v1.10.11/interactjs/index.js'

export default class Window {

	constructor(props) {
		this.closeInit = false;
		this.element = document.createElement("div");
		this.element.classList.add("window");
		this.element.innerHTML = `<div class="window-header"><span class="window-title"></span><div class="close-btn"><svg viewBox="0 0 20 20"><path class="shape"></path></svg></div></div><div class="window-content"></div>`;
		this.events = props.events || {};

		if(props) {
			this.position = {x: props.x || 0, y: props.y || 0}
			this.setTitle(props.title || "window");
			this.setWidth(props.width || 500);
			this.setHeight(props.height || 200);
		}

		this.moveTo(this.position.x, this.position.y);
		this.setSize(500, 300);
		this.shown = true;
		this.hide();
		this.properties = {};

		interact(this.element.querySelector(".window-header")).draggable({
			listeners: {
				move: (event) => {
					this.moveTo(this.position.x + event.dx, this.position.y + event.dy);
					this.closeInit = false;
				}
			}
		});

		interact(this.element).resizable({
			edges: {right: true, bottom: true},
			listeners: {
				move: e => {
					this.setSize(e.rect.width, e.rect.height);
				}
			}
		});

		let closeBtn = this.element.querySelector(".close-btn");
		closeBtn.addEventListener("mousedown", e => {
			this.closeInit = true;
		})
		closeBtn.addEventListener("mouseup", e => {
			if(this.closeInit) {
				this.hide();
				this.closeInit = false;
			}
		});
	}

	moveTo(x,y) {
		this.position.x = x;
		this.position.y = y;
		this.element.style.left = x+"px";
		this.element.style.top = y+"px";
	}

	setTitle(title) {
		this.element.querySelector(".window-title").innerText = title;
	}

	setWidth(width) {
		if(width < 100)
			width = 100;
		this.element.style.width = width+"px";
	}

	setHeight(height) {
		let minHeight = getComputedStyle(document.querySelector(":root")).getPropertyValue('--min-window-height');
		if(height < 50)
			height = 50;
		this.element.style.height = height+"px";
	}

	setSize(width, height) {
		this.setWidth(width);
		this.setHeight(height);
	}

	hide() {
		if(!this.shown) {
			return;
		}
		this.shown = false;
		this.element.classList.add("hidden");
		if(this.events.hide) {
			this.events.hide();
		}
	}

	show() {
		if(this.shown) {
			return;
		}
		this.shown = true;
		if(this.events.show) {
			this.events.show();
		}
		this.element.classList.remove("hidden");
	}

}
