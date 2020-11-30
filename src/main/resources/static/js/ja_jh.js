document.body.onload = function () {
    setTimeout(eliminarNotificaciones(), 2000);
};

function eliminarNotificaciones(){
    let notificacion = document.getElementById("notificaciones");
    notificacion.remove();
}

$("#menu-toggle").click(function(e) {
			e.preventDefault();
			$("#wrapper").toggleClass("toggled");
		});
		
		$('.count').each(function () {
			$(this).prop('Counter',0).animate({
				Counter: $(this).text()
			}, {
				duration: 3000,
				easing: 'swing',
				step: function (now) {
					$(this).text(Math.ceil(now));
				}
			});
		});