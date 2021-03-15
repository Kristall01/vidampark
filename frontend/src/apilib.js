const codeMessages = {
	0: "OK",
	1: "API logic exception",
	2: "auth required",
	3: "insufficient permission",
	4: "bad endpoint implementation"
}

/**
 * @param {string} path 
 * @param {FormData} params
 * @returns {Response}
 */
function fetchRawResource(path, params, config) {
	//config = config || {timeout = 1000, validResults = [200]};

	if(params !== undefined && !(params instanceof FormData)) {
		let builder = new FormData();
		Object.keys(params).forEach(key => {
			builder.set(key, params[key]);
		});
		params = builder;
	}

	return new Promise(async (resolve, reject) => {

		function rejectError(logElement) {
			if(logElement)
				console.error(logElement)
			resolve(null);
		}

		let done = false;

		let timeoutTask = undefined;
		try {
			let options = {method: "POST", credentials: "same-origin", redirect: "error"};
			if(params)
				options.body = params;
			let request = fetch(path, options);
			timeoutTask = setTimeout(() => {
				if(!done) {
					rejectError("request timed out");
				}
			}, config.timeout);
			let response = await request;
			clearTimeout(timeoutTask);
			if(!config.validResults.includes(response.status)) {
				rejectError();
				return;
			}
			resolve(response);
		}
		catch(ex) {
			if(timeoutTask) {
				clearTimeout(timeoutTask);
			}
			rejectError(ex);
		}
	});
}

/**
 * @param {string} path 
 * @param {FormData} params
 * @returns {Promise<string>}
 */
async function fetchTextResource(path, params, config) {
	return fetchRawResource(path, params, config).text();
}

/**
 * @param {string} path 
 * @param {FormData} params
 * @returns {Promise<any>}
 */
async function fetchJsonResource(path, params, config) {
	return fetchRawResource(path, params, config).json();
}

function fetchForm(endpoint, formElement) {
	return fetchResource(endpoint, new FormData(formElement));
}

function sleep(time) {
	return new Promise((resolve, reject) => {
		setTimeout(resolve, time);
	});
}

export {fetchRawResource, fetchTextResource, fetchJsonResource, fetchForm, sleep};