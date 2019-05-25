function loginOS() {
	var user = $("input[name=usuario]").val();
	var password = $("input[name=senha]").val();

	if (user != "" && user != null && password != "" && password != null) {
		var passCoded = btoa(password);

		$.ajax({
			type : 'POST',
			url : 'ServletConsultaLogin',
			data : {'user':user, 'passCoded':passCoded},

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
	var nome = $("#nomeaparelho").val();
	var categoria = $("#categoriaaparelho").val();
	var marca = $("#marcaaparelho").val();
	var modelo = $("#modeloaparelho").val();
	var nsaparelho = $("#nsaparelho").val();
	var ativo = $("#statusaparelho").val();

	$.ajax({
		type : 'POST',
		url : '../../../ServletCadastroAparelho',
		data : {
			'nome' : nome,
			'marca' : marca,
			'modelo' : modelo,
			'nsaparelho' : nsaparelho,
			'categoria' : categoria,
			'ativo':ativo
		},
		success : function(resposta) {
			buscarAparelho();
		},
		error : function(resposta) {
			alert(resposta);
		}
	});
}


function buscarAparelho(){
	var valorBusca = $('#buscaAparelhoInput').val();
	$.ajax({
		type: 'POST',
		url: '../../../ServletBuscarAparelho',
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
		url : '../../../ServletBuscarPorId',
		data:{'idaparelho':idaparelho},
		success: function(aparelho){
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
		url : '../../../ServletEditarAparelho',
		data:{'idaparelho': idaparelho, 'nome':nome, 'categoria':categoria, 'marca':marca, 'modelo':modelo, 'nsaparelho':nsaparelho, 'ativo':ativo},
		success: function(resposta){
			alert("Atualizado");
			buscarAparelho();
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
		url: '../../../ServletFiltroAtivos',
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
		var nome = 			 $("#nomecliente").val();
		var cpf = 			 $("#cpfcliente").val();
		var rg = 			 $("#rgcliente").val();
		var dataNascimento = $("#datanascimento").val();
		var email = 		 $("#emailcliente").val();
		var telefone = 		 $("#celularcliente").val();
		var endereco = 		 $("#enderecocliente").val();
		var numero = 		 $("#numerocliente").val();
		var complemento = 	 $("#complementocliente").val();
		var estado = 		 $("#estadocliente").val();
		var cidade = 		 $("#cidadecliente").val();
		var ativo = 		 $("#statuspessoa").val();

		var pessoaNova = {'nome':nome, 'cpf':cpf, 'dataNascimento':dataNascimento, 'rg':rg, 'email':email, 'telefone':telefone, 
					'endereco':endereco, 'numero':numero, 'complemento':complemento, 'estado':estado, 'cidade':cidade, 'ativo':ativo
		};
		var pessoa = JSON.stringify(pessoaNova);
	$.ajax({
		type:'POST',
		url: '/DigitalOS/rest/RestPessoa/addPessoaObj',
		data: pessoa,
		success: function(listaPessoasAchadas){
			carregarTabela(listaPessoasAchadas);
		},
		error: function(){
			alert("Erro ao cadastrar nova pessoa");
		}
	});
}

function buscarPessoa(){
	var valorBusca = $('#buscaPessoaInput').val();
	$.ajax({
		type: 'POST',
		url: '/DigitalOS/rest/RestPessoa/buscarPessoaPorNome',
		data: valorBusca,
		success: function(listaPessoasAchadas){
			tabelaPessoa(listaPessoasAchadas);
		},
		error: function(listaPessoasAchadas){
			tabelaPessoa(listaPessoasAchadas);
		}
	});
};

function tabelaPessoa(listaPessoasAchadas){
	console.log(listaPessoasAchadas);
	var html = "<div class='teble-reponsive'>" +
			   "<table class='table table-striped table-condensed table-bordered'>";
		html += "<tr>" +
				"<th># ID</th> <th>Nome</th> <th>CPF</th> <th>RG</th> <th>Data de Nascimento</th> <th>Cidade</th> <th>Estado</th> <th>Ativo</th> <th>Edição</th>" +
				"</tr>"
	for(var i = 0; i < listaPessoasAchadas.length; i++){
		html += "<tr>" +
					"<td>" + listaPessoasAchadas[i].id + "</td>" +
					"<td>" + listaPessoasAchadas[i].nome + "</td>" +
					"<td>" + listaPessoasAchadas[i].cpf + "</td>" +
					"<td>" + listaPessoasAchadas[i].rg + "</td>" +
					"<td>" + listaPessoasAchadas[i].dataNascimento + "</td>" +
					"<td>" + listaPessoasAchadas[i].estado + "</td>" +
					"<td>" + listaPessoasAchadas[i].cidade + "</td>" +
					"<td>" + listaPessoasAchadas[i].ativo + "</td>" +
					"<td>" +
						"<div class='btn glyphicon glyphicon-pencil' onclick='exibirEdicaoPessoa("+listaPessoasAchadas[i].id+")'></div>" +					
					"</td>" +
				"</tr>"
	}
	html += "</table>" +
			"</div>"
	$("#resultadoPessoa").html(html);
}

function exibirEdicaoPessoa(id){
	$.ajax({
		type : 'POST',
		url : '/DigitalOS/rest/RestPessoa/buscarPessoaPeloId/'+id,
		success: function(pessoa){
			console.log(pessoa);
			$("#EditIdPessoa").val(id);
			$("#EditNomePessoa").val(pessoa.nome);
			$("#EditCpfPessoa").val(pessoa.cpf);
			$("#EditRgPessoa").val(pessoa.rg);
			$("#EditDataNascimentoPessoa").val(pessoa.dataNascimento);
			$("#EditEnderecoPessoa").val(pessoa.endereco);
			$("#EditComplementoPessoa").val(pessoa.complemento);
			$("#EditNumeroPessoa").val(pessoa.numero);
			$("#EditEstadoPessoa").val(pessoa.estado);
			$("#EditCidadePessoa").val(pessoa.cidade);
			$("#EditTelefonePessoa").val(pessoa.telefone);
			$("#EditCelularPessoa").val(pessoa.celular);
			$("#EditStatusPessoa").val(pessoa.ativo);
			$("#msgEditPessoa").modal(pessoa);
		},
		error: function(pessoa){
			alert("Erro ao editar Pessoa!");
		}
	});
}

function editarPessoa(){
	var pessoaEditar = $("#formEditPessoa").serialize();
	alert(pessoaEditar);
	$.ajax({
		type : 'POST',
		url : '/DigitalOS/rest/RestPessoa/editarPessoa',
		data:pessoaEditar,
		success: function(resposta){
			alert("Atualizado");
			buscarPessoa();
		},
		error:function(){
			alert("Erro ao Atualizar");
		}
	});
}