function loginOS() {
	var user = $("input[name=usuario]").val();
	var password = $("input[name=senha]").val();

	if (user != "" && user != null && password != "" && password != null) {
		var passCoded = btoa(password);

		$.ajax({
			type : 'POST',
			url : 'ServletConsultaLogin',
			data : 'user=' + user + '&passCoded=' + passCoded,

			success : function(url) {
				location.href = url.url;
			},
			error : function(url) {
				location.href = url.url;
			}
		});
	} else {
		alert("E-mail ou Senha Inválido!");
	}
}

function CadastroAparelho() {
	var nome = $("input[name=nomeaparelho]").val();
	var categoria = $("#categoriaaparelho").val();
	var marca = $("#marcaaparelho").val();
	var modelo = $("input[name=modeloaparelho]").val();
	var nsaparelho = $("input[name=nsaparelho]").val();
	var ativo = $("#statusaparelho").val();

	$.ajax({
		type : 'POST',
		url : '../ServletCadastroAparelho',
		data : {
			'nome' : nome,
			'marca' : marca,
			'modelo' : modelo,
			'nsaparelho' : nsaparelho,
			'categoria' : categoria,
			'ativo':ativo
		},
		success : function(resposta) {
			alert(resposta);
		},
		error : function(resposta) {
			alert(resposta);
		}
	});
	buscarAparelho();
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
				"<th># ID</th> <th>Nome</th> <th>Categoria</th> <th>Marca</th> <th>Modelo</th> <th>Número Série</th> <th>Ativo</th> <th>Edição</th>" +
				"</tr>"
	for(var i = 0; i < listaAparelhosAchados.length; i++){
		html += "<tr>" +
					"<td>" + listaAparelhosAchados[i].idaparelho + "</td>" +
					"<td>" + listaAparelhosAchados[i].nome + "</td>" +
					"<td>" + listaAparelhosAchados[i].categoria + "</td>" +
					"<td>" + listaAparelhosAchados[i].marca + "</td>" +
					"<td>" + listaAparelhosAchados[i].modelo + "</td>" +
					"<td>" + listaAparelhosAchados[i].nsaparelho + "</td>" +
					"<td>" + listaAparelhosAchados[i].ativo + "</td>" +
					"<td>" +
						"<div class='btn glyphicon glyphicon-pencil' onclick='exibirEdicaoAparelho("+listaAparelhosAchados[i].idaparelho+")'></div>" +
					"</td>" +
				"</tr>"
	}
	html += "</table>" +
			"</div>"
	$("#resultadoAparelhos").html(html);
}

function exibirEdicaoAparelho(idaparelho){
	$.ajax({
		type : 'POST',
		url : '../ServletBuscarPorId',
		data:{'idaparelho':idaparelho},
		success: function(aparelho){
			console.log(aparelho);
			$("#EditIdAparelho").val(aparelho.idaparelho);
			$("#EditNomeAparelho").val(aparelho.nome);
			$("#EditCategoriaAparelho").val(aparelho.categoria);
			$("#EditMarcaAparelho").val(aparelho.marca);
			$("#EditModeloAparelho").val(aparelho.modelo);
			$("#EditNsAparelho").val(aparelho.nsaparelho);
			$("#EditStatusAparelho").val(aparelho.ativo);
			var cfg = {
					heigth : 600,
					width : 400,
					modal : true,
					buttons : {
						"Ok" : function() {
							$(this).modal("close");
						}
					}
				};
				$("#msgEditAparelho").modal(cfg);
		},
		error: function(err){
			alert("Erro ao editar aparelho!");
		}
	});
}

function editarAparelho(){
	var idaparelho = $("#EditIdAparelho").val();
	var nome = $("#EditNomeAparelho").val();
	var categoria = $("#EditCategoriaAparelho").val();
	var marca = $("#EditMarcaAparelho").val();
	var modelo = $("#EditModeloAparelho").val();
	var nsaparelho = $("#EditNsAparelho").val();
	var ativo = $("#EditStatusAparelho").val();
	$.ajax({
		type : 'POST',
		url : '../ServletEditarAparelho',
		data:{'idaparelho': idaparelho, 'nome':nome, 'categoria':categoria, 'marca':marca, 'modelo':modelo, 'nsaparelho':nsaparelho, 'ativo':ativo},
		success: function(resposta){
			buscarAparelho();
			alert("Atualizado");
		},
		error:function(){
			alert("Erro ao Atualizar");
		}
	});
}

function filtroAparelhosAtivos(){
	var ativo = $("#filtroAtivoOpc").val();
	$.ajax({
		type:'POST',
		url: '../ServletFiltroAtivos',
		data: {'ativo':ativo},
		success: function(listaAparelhosAchados){
			carregarTabela(listaAparelhosAchados);
		},
		error: function(){
			alert("Erro ao Filtrar");
		}
	});
}

function inserirPessoa(){
	alert($("#formCadastroCliente").serialize());
	var pessoa = new Object();
		pessoa.nome = $("#nomecliente").val();
		pessoa.datanascimento = $("#datanscimento").val();
		pessoa.cpf = $("#cpfcliente").val();
		pessoa.rg = $("#rgcliente").val();
		pessoa.email = $("#emailcliente").val();
		pessoa.telefone = $("#telefonecliente").val();
		pessoa.celular = $("#celularcliente").val();
		pessoa.edereco = $("#enderecocliente").val();
		pessoa.numero = $("#numerocliente").val();
		pessoa.complemento = $("#complementocliente").val();
		pessoa.estado = $("#estadocliente").val();
		pessoa.cidade = $("#cidadecliente").val();
		pessoa.ativo = ("#statuspessoa").val();
	$.ajax({
		type:'POST',
		url: '../rest/RestPessoa/addPessoa',
		data: pessoa,
		success: function(listaAparelhosAchados){
			carregarTabela(listaAparelhosAchados);
		},
		error: function(){
			alert("Erro ao cadastrar nova pessoa");
		}
	});
}