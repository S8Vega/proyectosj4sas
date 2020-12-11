/**
 * guarda el token del formulario de la pagina donde se invoque la funcion en 'sesion storage'
 */
function save_csrf_token(){
    let input = document.getElementsByName("_csrf")[0];
    let csrf_token = {
		name: input.name,
		value: input.value
	}
	sessionStorage.setItem("_csrf", JSON.stringify(csrf_token));
}



document.getElementById("btn_login").onclick = () => {
    save_csrf_token();
}
