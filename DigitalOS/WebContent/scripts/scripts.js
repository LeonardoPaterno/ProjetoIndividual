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
			var modal = {
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
			$("#msg").modal(modal);

		},
		error : function(resposta) {
			var modal = {
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
			$("#msg").modal(modal);
		}
	});
}

function buscarAparelho(){
	var valorBusca = $("#buscaAparelho").val();
	alert(valorBusca);
	exibirAparelhos(undefined, valorBusca);
};

function exibirAparelhos(listaDeAparelhos, valorBusca) {
	var html = "<div class='table-responsive'><table class='table table-striped table-condensed'>";
	html += "<tr> <th>Nome</th> <th>Catergoria</th> <th>Marca</th> <th>Modelo</th> <th>Numero Serie</th> </tr>";
	if (listaDeAparelhos != undefined && listaDeAparelhos.length > 0 && listaDeAparelhos[0].id != undefined) {
		for (var i = 0; i < listaDeAparelhos.length; i++) {
			html += "<tr>"+ 
						"<td>" + listaDeAparelhos[i].id + "</td>" + 
						"<td>" + listaDeAparelhos[i].nome + "</td>" + 
						"<td>" + listaDeAparelhos[i].categoria + "</td>" + 
						"<td>" + listaDeAparelhos[i].marca + "</td>" + 
						"<td>" + listaDeAparelhos[i].modelo + "</td>" + 
						"<td>" + listaDeAparelhos[i].nsaparelho + "</td>" + 
						"<td>" + 
							"<a class='link'onclick='editarAparelho(" + listaDeAparelhos[i].id + ")'>Editar</a>" + 
							"<a class='link'onclick='deletarAparelho(" + listaDeAparelhos[i].id + ")'>Deletar</a>" + 
						"</td>"	+ 
					"</tr>";
		}
	} else {
		if (listaDeAparelhos == undefined || (listaDeAparelhos != undefined && listaDeAparelhos.length > 0)) {
			$.ajax({
				type : "POST",
				url : "../ServletBuscarAparelho",
				data : "buscarAparelho=" + valorBusca,
				success : function(listaDeAparelhos) {
						exibirAparelhos(listaDeAparelhos);
				},
				error : function(resposta) {
					alert("Erro ao Consultar Aparelhos");
				}
			});
		} else {
			html += "<tr style='display:inline-block'><td colspan='6'>Nenhum Registro Encontrado</td></tr>";
		}
	}
	html += "</table></div>";
	$("#resultadoAparelhos").html(html);
};
exibirAparelhos(undefined, "");