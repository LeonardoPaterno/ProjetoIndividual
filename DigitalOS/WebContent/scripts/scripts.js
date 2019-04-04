function loginOS() {
	var user = $("input[name=usuario]").val();
	var password = $("input[name=senha]").val();

	alert(user + " | " + password);

	if (user != "" && user != null && password != "" && password != null) {
		var passCoded = btoa(password);

		$.ajax({
			type : 'POST',
			url : 'ServletConsultaLogin',
			data : 'user=' + user + '&passCoded=' + passCoded,

			success : function(resposta) {

			},
			error : function(resposta) {

			}
		});
	} else {
		alert("E-mail ou Senha Inválido!");
	}
}

function CadastroUsuario() {

	var formulario = $('input[type=submit]').closest("#formCadastroCliente");
	alert(formulario);
	$.ajax({
		type : 'POST',
		url : 'ServletCadastroCliente',
		data : {
			formulario : 'formulario'
		},

		success : function(resposta) {

		},
		error : function(resposta) {

		}
	});
}