function loginOS() {
	var user = $("input[name=usuario]").val();
	var password = $("input[name=senha]").val();

	if (user != "" && user != null && password != "" && password != null) {
		var passCoded = btoa(password);

		$.ajax({
			type : 'POST',
			url : 'ServletConsultaLogin',
			data : 'user=' + user + '&passCoded=' + passCoded,

			success : function(resposta) {
				location.href = resposta.url;
			},
			error : function(resposta) {
				location.href = resposta.url;
			}
		});
	} else {
		alert("E-mail ou Senha Inválido!");
	}
}

function CadastroAparelho() {

	var nome = $("input[name=nomeaparelho]").val();
	var categoria = $("select.categoria").children("option:selected").val();
	var marca = $("select.marca").children("option:selected").val();
	var modelo = $("input[name=modeloaparelho]").val();
	var nsaparelho = $("input[name=nsaparelho]").val();
	
	alert(nome + "\n" + categoria + "\n" + marca + "\n" + modelo + "\n" + nsaparelho);
	$.ajax({
		type : 'POST',
		url : 'ServletCadastroAparelho',
		data : {
			marca : 'marca',
			modelo : 'modelo',
			nsaparelho : 'nsaparelho'
		},

		success : function(resposta) {
			
		},
		error : function(resposta) {

		}
	});
}