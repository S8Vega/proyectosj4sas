/**
 * ARCHIVO .JS QUE CONTIENE EL CODIGO JAVASCRIPT UTILIZADO UNICAMENTE EN LA RUTA 'vistas/obreros/crear/id'
 */
function guargarListaEnSessionStorage(url, nombre_lista, id_select, mensaje) {

	if (nombre_lista in sessionStorage) {
		llenarSelect(id_select, nombre_lista, mensaje);
	} else {
		let options = {
			method: 'get',
			headers: {
				'Content-Type': 'application/json'
			}
		}
		fetch(url, options

		).then(response => {
			if (response.ok)
				return response.text();
			else
				throw new Error(response.status);
		})
			.then(data => {
				sessionStorage.setItem(nombre_lista, JSON.stringify(JSON.parse(data)));
				llenarSelect(id_select, nombre_lista, mensaje);
			})
			.catch(err => {
				console.error("ERROR: ", err.message)
			});
	}


}


function llenarSelect(id_select, nombre_lista, mensaje) {
	let lista = JSON.parse(sessionStorage.getItem(nombre_lista));
	let select = document.getElementById(id_select);

	lista.forEach(item => {
		let option = document.createElement("option");
		option.value = item["id"];
		option.textContent = item["nombre"];
		select.appendChild(option);
	});

	let option = document.createElement("option");
	option.value = -1;
	option.textContent = mensaje;
	select.appendChild(option);
}
guargarListaEnSessionStorage("/arl/", "lista_arl", "id_arl", "No tiene Arl");
guargarListaEnSessionStorage("/eps/", "lista_eps", "id_eps", "No tiene Eps");
guargarListaEnSessionStorage("/afp/", "lista_afp", "id_afp", "No tiene Afp");