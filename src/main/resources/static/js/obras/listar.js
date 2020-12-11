function modificarEstado(id_estado) {
	let div = document.getElementById("form_dinamico");
	if (div.hasChildNodes) {
		eliminarFormularioDinamico();
	}

	csrf_token = JSON.parse(sessionStorage.getItem("_csrf"));



	let form = document.createElement("form");
	let obra = document.createElement("input");
	let csrf_input = document.createElement("input");
	let estado = document.createElement("input");

	form.setAttribute("id", "formulario");

	obra.setAttribute("name", "id_obra");
	obra.setAttribute("value", id_estado);
	obra.setAttribute("type","hidden");

	estado.setAttribute("name", "estado");
	estado.setAttribute("value", sessionStorage.getItem("nuevo_estado"));
	estado.setAttribute("type","hidden");

	csrf_input.setAttribute("name", csrf_token["name"]);
	csrf_input.setAttribute("value", csrf_token["value"]);
	csrf_input.setAttribute("type","hidden");


	form.appendChild(obra);
	form.appendChild(estado);
	form.appendChild(csrf_input);

	div.appendChild(form);

	formdata = $('#formulario').serialize();
	$.ajax({
		url: "/obras/estados/",
		type: "POST",
		data: formdata,
		dataType: 'json',
		success: function (status, result) {
			if (status["transaccion"]) {
				let fila_obrero = document.getElementById("td_" + id_estado);
				let elements=fila_obrero.getElementsByTagName("td");
				let td_estado = elements["estado"];
				let span = td_estado.getElementsByTagName("span")[0];
				
				if(status["estado"]=="activo"){
					span.innerText="activo";
					span.className="hudsoft-badge-pill";
				}else{
					span.innerText="inactivo";
					span.className="hudsoft-badge-pill-danger";
				}
			}
			notificacion("success","Estado de obra modificado correctamente");

		},
		fail: function () {
			alert("error");
		},
		complete: function (status, resul) {

		}
	});
	limpiarSession();
}

function smodal(id) {
	sessionStorage.setItem("id_obra_a_editar", id);
	sessionStorage.setItem("nuevo_estado", document.getElementById("select_estado_a_modificar").value);
	let fila_obrero = document.getElementById("td_" + id);
	

	let nombre = fila_obrero.getElementsByTagName("td")["nombre"].innerText;
	document.getElementById("obra_nombre").innerText = nombre;
	$('#modal_trabajador').modal('show');
}

let btn_cambiar = document.getElementById("btn_cambiar_estado");
btn_cambiar.onclick = () => {
	if ("id_obra_a_editar" in sessionStorage) {
		modificarEstado(sessionStorage.getItem("id_obra_a_editar"));
		sessionStorage.removeItem("id_obra_a_editar");
	} else {
		console.error("NO HAY ID PARA EDITAR ESTADO DE OBRA");
	}

}

window.onload = () => {
	save_csrf_token();

 	/**
	  * let input = document.getElementById("csrf_token");

	let csrf_token = {
		name: input.name,
		value: input.value
	}
	sessionStorage.setItem("csrf_token", JSON.stringify(csrf_token));
	  */
 
	eliminarFormularioDinamico();
}

function eliminarFormularioDinamico() {
	let div = document.getElementById("form_dinamico");
	div.innerHTML = "";
}
function ShowSelected() {
	let combo = document.getElementById("select_estado_a_modificar");
	let selected = combo.options[combo.selectedIndex].text;
	sessionStorage.setItem("nuevo_estado", selected);
}
function limpiarSession() {
	sessionStorage.removeItem("nuevo_estado");
	sessionStorage.removeItem("id_obra_a_editar");
}

function notificacion(tipo, mensaje){
	let div_notify = document.getElementById("notificaciones");
	div_notify.innerHTML = "";
	if (tipo=="success"){
		let div = document.createElement("div");
		div.className="alert alert-success notificaciones d-flex align-content-start flex-wrap";
		div.innerText=mensaje;
		
		div_notify.appendChild(div);
	}
}
