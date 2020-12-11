/**
 * guarda el token de la sesion en 'sesion storage'
 */
function save_csrf_token(){
    let input = document.getElementsByName("_csrf")[0];
    let csrf_token = {
		name: input.name,
		value: input.value
	}
	sessionStorage.setItem("_csrf", JSON.stringify(csrf_token));
}



window.onload = ()=>{
    save_csrf_token();
}