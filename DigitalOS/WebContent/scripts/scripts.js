function loginOS() {
	obj.user = $("input[name=usuario]").val();
	obj.password = $("input[name=senha]").val();

	if (user != "" && user != null && password != "" && password != null) {
		obj.passCoded = btoa(password);

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
	obj.nome = $("input[name=nomeaparelho]").val();
	obj.categoria = $("#categoriaaparelho").val();
	obj.marca = $("#marcaaparelho").val();
	obj.modelo = $("input[name=modeloaparelho]").val();
	obj.nsaparelho = $("input[name=nsaparelho]").val();
	obj.ativo = $("#statusaparelho").val();

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
	obj.valorBusca = $('#buscaAparelhoInput').val();
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
	obj.html = "<div class='teble-reponsive'>" +
			   "<table class='table table-striped table-condensed table-bordered'>";
		html += "<tr>" +
				"<th># ID</th> <th>Nome</th> <th>Categoria</th> <th>Marca</th> <th>Modelo</th> <th>Número Série</th> <th>Ativo</th> <th>Edição</th>" +
				"</tr>"
	for(obj.i = 0; i < listaAparelhosAchados.length; i++){
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
			obj.cfg = {
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
	obj.idaparelho = $("#EditIdAparelho").val();
	obj.nome = $("#EditNomeAparelho").val();
	obj.categoria = $("#EditCategoriaAparelho").val();
	obj.marca = $("#EditMarcaAparelho").val();
	obj.modelo = $("#EditModeloAparelho").val();
	obj.nsaparelho = $("#EditNsAparelho").val();
	obj.ativo = $("#EditStatusAparelho").val();
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
	obj.ativo = $("#filtroAtivoOpc").val();
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
/*	var obj = new Object;
	obj.nome = $("#nomecliente").val();
	obj.cpf = $("#cpfcliente").val();
	obj.rg = $("#rgcliente").val();
	obj.datanascimento = $("#datanascimento").val();
	obj.email = $("#emailcliente").val();
	obj.telefone = $("#celularcliente").val();
	obj.endereco = $("#enderecocliente").val();
	obj.numero = $("#numerocliente").val();
	obj.complemento = $("#complementocliente").val();
	obj.estado = $("#estadocliente").val();
	obj.cidade = $("#cidadecliente").val();
	obj.ativo = $("#statuspessoa").val();*/
		var nome = $("#nomecliente").val();
		var cpf = $("#cpfcliente").val();
		var rg = $("#rgcliente").val();
		var datanascimento = $("#datanascimento").val();
		var email = $("#emailcliente").val();
		var telefone = $("#celularcliente").val();
		var endereco = $("#enderecocliente").val();
		var numero = $("#numerocliente").val();
		var complemento = $("#complementocliente").val();
		var estado = $("#estadocliente").val();
		var cidade = $("#cidadecliente").val();
		var ativo = $("#statuspessoa").val();

		var pessoa = ('{"nome":'+nome, '"cpf":'+cpf,'"rg":'+rg, '"datanascimento":'+ datanascimento +"}'");
	$.ajax({
		type:'POST',
		url: '/DigitalOS/rest/RestPessoa/addPessoaObj',
		data: pessoa,
		success: function(listaAparelhosAchados){
			carregarTabela(listaAparelhosAchados);
		},
		error: function(){
			alert("Erro ao cadastrar nova pessoa");
		}
	});
}