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


function buscarAparelho(){
	var valorBusca = $('#buscaAparelhoInput').val();
	$.ajax({
		type: 'POST',
		url: '../ServletBuscarAparelho',
		data: {'valorBusca': valorBusca},
		success: function(listaAparelhosAchados){
			carregarTabela(listaAparelhosAchados);
		},
		error: function(){
			alert("Error, nenhum aparelho encontrado");
		}
	});
};

function carregarTabela(listaAparelhosAchados){
	var html = "<div class='teble-reponsive'>" +
			   "<table class='table table-striped table-condensed table-bordered'>";
		html += "<tr>" +
				"<th># ID</th> <th>Nome</th> <th>Categoria</th> <th>Marca</th> <th>Modelo</th> <th>Némero Série</th> <th>Edição / Exclusão</th>" +
				"</tr>"
	for(var i = 0; i < listaAparelhosAchados.length; i++){
		html += "<tr>" +
					"<td>" + listaAparelhosAchados[i].idaparelho + "</td>" +
					"<td>" + listaAparelhosAchados[i].nome + "</td>" +
					"<td>" + listaAparelhosAchados[i].categoria + "</td>" +
					"<td>" + listaAparelhosAchados[i].marca + "</td>" +
					"<td>" + listaAparelhosAchados[i].modelo + "</td>" +
					"<td>" + listaAparelhosAchados[i].nsaparelho + "</td>" +
					"<td>" +
						"<div class='btn glyphicon glyphicon-pencil' onclick='editarAparelho("+listaAparelhosAchados[i].id+")'></div>" +
						"<div class='btn glyphicon glyphicon-remove' onclick='deletarAparelho("+listaAparelhosAchados[i].id+")'></div>" +
					"</td>";
				"</tr>"
	}
	html += "</table>" +
			"</div>"
	$("#resultadoAparelhos").html(html);
}

function editarAparelho(id){
	$.ajax({
		type : 'POST',
		url : '../ServletCadastroAparelho',
		success: function(aparelho){
			$("#nomeEdit").val(aparelho.nome);
			$("#categoriaEdit").val(aparelho.endereco);
			$("#marcaEdit").val(aparelho.telefone);
			$("#modeloEdit").val(aparelho.email);
			$("#nsaparelhoEdit").val(aparelho.senha);
			$("#idaparelhoEdit").val(aparelho.id);
			var cfg = {
					title : "Alteração de Aparelho",
					heigth : 250,
					width : 400,
					modal : true,
					buttons : {
						"Ok" : function() {
							$(this).dialog("close");
						}
					}
				};
				$(cfg).modal({escapeClose: false,  clickClose: false,  showClose: false});
				$("#msgEditAparelho").dialog(cfg);
		},
		error: function(err){
			alert("Erro ao editar aparelho!" + err.responseText);
		}
	});
}
