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

	$.ajax({
		type : 'POST',
		url : '../ServletCadastroAparelho',
		data : {
			'nome' : nome,
			'marca' : marca,
			'modelo' : modelo,
			'nsaparelho' : nsaparelho,
			'categoria' : categoria
		},
		success : function(resposta) {
			var modalzinha = {
				title : "Mensagem",
				heigth : 250,
				width : 400,
				modal : true,
				buttons : {
					"OK" : function() {
						$(this).modal("close");
					}
				}
			};
			$("#msg").html(resposta.resposta);
			$("#msg").modal(modalzinha);

		},
		error : function(resposta) {
			var modalzinha = {
				title : "Mensagem",
				heigth : 250,
				width : 400,
				modal : true,
				buttons : {
					"Ok" : function() {
						$(this).modal("close");
					}
				}
			};
			$("#msg").html(resposta.resposta);
			$("#msg").modal(modalzinha);
		}
	});
	
	
}

/*function timer(){
	var timerBusca = setTimeOut(function(){exibirAparelhos()}, 2000);
}

*/
function buscarAparelho(){
	var valorBusca = $("input[name=buscaAparelho]").val();
	alert(valorBusca);
	exibirAparelhos(undefined, valorBusca);
};

function exibirAparelhos(listaAparelhosAchados, valorBusca){
	var html = "<div class='teble-reponsive'>" +
				"<table class='table table-striped table-condesed'>";
		html += "<tr>"
					+ "<th>Nome</th> <th>Categoria</th> <th>Marca</th> <th>Modelo</th> <th>Numero Serie</th> " +
				"</tr>"
		if(listaAparelhosAchados != undefined && listaAparelhosAchados.jength > 0 && listaAparelhosAchados[0].id != undefined){
			for(var i = 0; i < listaAparelhosAchados.length; i++){
				html += "<tr>"
							+"<td>" + listaAparelhosAchados[i].id + "</td>"
							+"<td>" + listaAparelhosAchados[i].nome + "</td>"
							+"<td>" + listaAparelhosAchados[i].categoria + "</td>"
							+"<td>" + listaAparelhosAchados[i].marca + "</td>"
							+"<td>" + listaAparelhosAchados[i].modelo + "</td>"
							+"<td>" + listaAparelhosAchados[i].nsaparelho + "</td>"
						+"</tr>"
			}
		}else{
			if(listaAparelhosAchados == undefined || (listaAparelhosAchados != undefined && listaAparelhosAchados.length > 0)){
				$.ajax({
					type: 'POST',
					url: '../ServletBuscarAparelho',
					data: {'valorBusca': valorBusca},
					success: function(listaAparelhosAchados){
						exibirAparelhos(listaAparelhosAchados);
					},
					error: function(){
						alert("Error, nenhum aparelho encontrado");
					}
				});
			}else{
				alert("Ferrou Tudo");
			}
		}
		html += "</table> </div>"
		$("#resultadoAparelhos").html(html);
		exibirAparelhos(undefined, "");
}

