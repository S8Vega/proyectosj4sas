/**
 * guarda el token del formulario de la pagina donde se invoque la funcion en 'sesion storage'
 */
function save_csrf_token() {
	let input = document.getElementsByName("_csrf")[0];
	let csrf_token = {
		name: input.name,
		value: input.value
	}
	sessionStorage.setItem("_csrf", JSON.stringify(csrf_token));
}

/**
 * pila para mantener el orden de las rutas de navegacion usadas en la app
 */
function save_path() {
	let p_anterior = [];
	let p_siguiente = [];
	sessionStorage.setItem("p_anterior", JSON.stringify(p_anterior));
	sessionStorage.setItem("p_siguiente", JSON.stringify(p_siguiente));
}

/**
 *   console.log(stack.size()); // 0
  console.log(stack.push('John Cena')); // { '0': 'John Cena' }
  console.log(stack.size()); // 1
  console.log(stack.peek()); // John Cena
  console.log(stack.push('The Rock')); // { '0': 'John Cena', '1': 'The Rock' }
  console.log(stack.size()); // 2
  stack.print(); // { '0': 'John Cena', '1': 'The Rock' }
  console.log(stack.peek()); // The Rock
  console.log(stack.pop()); // The Rock
  stack.print(); // { '0': 'John Cena' }
  console.log(stack.size()); // 1
  console.log(stack.peek()); // John Cena
 */

/**
 * function save_path(){
	  let path = {
		name: "path",
		value: {}
	}
	sessionStorage.setItem("path", JSON.stringify(path));
}
 */