/*INICIO LOGIN*/
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
/*FIM LOGIN*/

/*INICIO CRUD APARELHO*/
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
/*FIM CRUD APARELHO*/

/*INICIO CRUD PESSOA*/
function inserirPessoa(){
		var nome = 			 $("#nomecliente").val();
		var dataNascimento = $("#datanascimento").val();
		var cpf = 			 $("#cpfcliente").val();
		var rg = 			 $("#rgcliente").val();
		var email = 		 $("#emailcliente").val();
		var telefone = 		 $("#telefonecliente").val();
		var celular = 		 $("#celularcliente").val();
		var endereco = 		 $("#enderecocliente").val();
		var numero = 		 $("#numerocliente").val();
		var tipomorada = 	 $("#tipomorada").val();
		var estado = 		 $("#estadocliente").val();
		var cidade = 		 $("#cidadecliente").val();
		var tipopessoa = 	 $("#tipopessoa").val();
		var profissao =  	 $("#profissaopessoa").val();
		var ativo = 		 $("#statuspessoa").val();
		var funcionario = 	 $("#funcionario").val();

		var pessoaNova = {'nome':nome, 'cpf':cpf,'rg':rg,'dataNascimento':dataNascimento,'profissao':profissao,'endereco':endereco,    
						  'numero':numero,'telefone':telefone,'celular':celular,'email':email,'cidade':cidade,'estado':estado,  
						  'tipomorada':tipomorada,'tipopessoa':tipopessoa,'ativo':ativo,'funcionario':funcionario};	
		var pessoa = JSON.stringify(pessoaNova);
	$.ajax({
		type:'POST',
		url: '/DigitalOS/rest/RestPessoa/addPessoaObj',
		data: pessoa,
		success: function(resposta){
			alert(resposta);
			buscarPessoa();
		},
		error: function(){
			alert("Erro ao cadastrar nova pessoa.");
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
				"<th># ID</th> <th>Nome</th> <th>CPF</th> <th>RG</th> <th>Endereco</th> <th>Cidade</th> <th>Estado</th> <th>Telefone</th> <th>Tipo Pessoa</th> <th>Ativo</th> <th>Edição</th>" +
				"</tr>"
	for(var i = 0; i < listaPessoasAchadas.length; i++){
		html += "<tr>" +
					"<td>" + listaPessoasAchadas[i].id + "</td>" +
					"<td>" + listaPessoasAchadas[i].nome + "</td>" +
					"<td>" + listaPessoasAchadas[i].cpf + "</td>" +
					"<td>" + listaPessoasAchadas[i].rg + "</td>" +
					"<td>" + listaPessoasAchadas[i].endereco + "</td>" +
					"<td>" + listaPessoasAchadas[i].cidade + "</td>" +
					"<td>" + listaPessoasAchadas[i].estado + "</td>" +
					"<td>" + listaPessoasAchadas[i].telefone + "</td>" +
					"<td>" + listaPessoasAchadas[i].tipopessoa + "</td>" +
					"<td id='td'>" + listaPessoasAchadas[i].ativo + "</td>" +
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
			alert(pessoa);
			$("#EditIdPessoa").val(id);
			$("#EditNomePessoa").val(pessoa.nome);
			$("#EditCpfPessoa").val(pessoa.cpf);
			$("#EditRgPessoa").val(pessoa.rg);
			$("#EditDataNascimentoPessoa").val(pessoa.dataNascimento);
			$("#EditEnderecoPessoa").val(pessoa.endereco);
			$("#EditTipoPessoa").val(pessoa.tipopessoa);
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
	var id = $("#EditIdPessoa").val();
	var nome = $("#EditNomePessoa").val();
	var cpf = $("#EditCpfPessoa").val();
	var rg = $("#EditRgPessoa").val();
	var datanascimento = $("#EditDataNascimentoPessoa").val();
	var endereco = $("#EditEnderecoPessoa").val();
	var numero = $("#EditNumeroPessoa").val();
	var cidade = $("#EditCidadePessoa").val();
	var estado = $("#EditEstadoPessoa").val();
	var telefone = $("#EditTelefonePessoa").val();
	var celular = $("#EditCelularPessoa").val();
	var tipopessoa = $("#EditTipoPessoa").val();
	var tipomorada = $("#EditTipoMoradaPessoa").val();
	var ativo = $("#EditStatusPessoa").val();

	var pessoaEdit = {'id':id, 'nome':nome, 'cpf':cpf, 'rg':rg, 'dataNascimento':datanascimento, 'endereco':endereco,	
					  'numero':numero, 'estado':estado, 'cidade':cidade, 
					  'telefone':telefone, 'celular':celular, 'tipopessoa':tipopessoa, 'tipomorada':tipomorada, 'ativo':ativo};
	var pessoa = JSON.stringify(pessoaEdit);
	
	$.ajax({
		type : 'POST',
		url : '/DigitalOS/rest/RestPessoa/editarPessoa',
		data:pessoa,
		success: function(resposta){
			alert(resposta);
			buscarPessoa();
		},
		error:function(){
			alert("Erro ao Atualizar");
		}
	});
}

function filtroAtivosPessoa(){
	var ativo = JSON.stringify($("#filtroPessoa").val());
	$.ajax({
		type:'POST',
		url: '/DigitalOS/rest/RestPessoa/filtroAtivo',
		data: $("#filtroPessoa").val(),
		success: function(listaPessoasAchadas){
			tabelaPessoa(listaPessoasAchadas);
		},
		error: function(){
			alert("Erro ao Filtrar");
		}
	});
}
/*FIM CRUD PESSOA*/

/*INICIO CRUD FUNCIONARIO*/
function inserirFuncionario(){
	alert("funcionario");
	var nome = 			 	 $("#nomefuncionario").val();
	var cpf = 			 	 $("#cpffuncionario").val();
	var rg = 			 	 $("#rgfuncionario").val();
	var sexo = 	 		 	 $("#sexofuncionario").val();
	var datanascimento = 	 $("#datanascimentofuncionario").val();
	var cargo =  	 	 	 $("#cargofuncionario").val();
	var setor =  	 	 	 $("#setorfuncionario").val();
	var numeropis =		 	 $("#numeropispasep").val();
	var numeroct = 		 	 $("#numeroct").val();
	var salario = 			 $("#salariofuncionario").val();
	var dataadmissao = 	 	 $("#dataadmissaofuncionario").val();
	var email = 		 	 $("#emailfuncionario").val();
	var endereco = 		 	 $("#enderecofuncionario").val();
	var numero = 		 	 $("#numerofuncionario").val();
	var telefone = 		 	 $("#telefonefuncionario").val();
	var celular = 		 	 $("#celularfuncionario").val();
	var estado = 		 	 $("#estadofuncionario").val();
	var cidade = 		 	 $("#cidadefuncionario").val();
	var ativo = 		 	 $("#statusfuncionario").val();

	var funcNovo = {'nome':nome, 'cpf':cpf, 'rg':rg, 'sexo':sexo, 'datanascimento':datanascimento, 'cargo':cargo, 'setor':setor, 'numeropis':numeropis,
					'numeroct':numeroct, 'salario':salario, 'dataadmissao':dataadmissao, 'email':email, 'endereco':endereco, 'numero':numero, 
					'telefone':telefone, 'celular':celular, 'estado':estado, 'cidade':cidade, 'ativo':ativo};	
	alert(funcNovo);
	var funcionario = JSON.stringify(funcNovo);
	$.ajax({
		type:'POST',
		url: '/DigitalOS/rest/RestFuncionario/addFuncionario',
		data: funcionario,
		success: function(resposta){
			alert(resposta);
			buscarPessoa();
			$('#modalfuncionario').modal('close');
		},
		error: function(){
			alert("Erro ao cadastrar nova pessoa.");
		}
	});
}
/*FIM CRUD FUNCIONARIO*/

/*INICIO CRUD SERVICO*/
function inserirServico(){
	var tiposervico = 		$("#tiposervico").val();
	var nomeservico = 	 	$("#nomeservico").val();
	var descricaoservico = 	$("#descricaoservico").val();
	var ativo = 			$("#statusservico").val();
	
	if(tiposervico == "" || nomeservico == "" || descricaoservico == ""){
		alert("Preencha todos os campos do formulário!");
		return false;
	}else{
		var pessoaNova = {'tiposervico':tiposervico, 'nomeservico':nomeservico,'descricaoservico':descricaoservico, 'ativo':ativo};	
		var pessoa = JSON.stringify(pessoaNova);
		$.ajax({
			type:'POST',
			url: '/DigitalOS/rest/RestServico/addServico',
			data: pessoa,
			success: function(resposta){
				alert(resposta);
				buscarServico();
					$('#modalservico').modal('hide');
			},
			error: function(){
				alert("Erro ao cadastrar nova pessoa.");
				buscarServico();
			}
		});
	}
}

function buscarServico(){
	var valorBusca = $('#buscarservico').val();
	$.ajax({
		type: 'POST',
		url: '/DigitalOS/rest/RestServico/buscarServicoPorNome',
		data: valorBusca,
		success: function(listaServicoAchados){
			tabelaServico(listaServicoAchados);
		},
		error: function(listaServicoAchados){
			tabelaServico(listaServicoAchados);
		}
	});
}

function tabelaServico(listaServicoAchados){
	var html = "<div class='teble-reponsive'>" +
			   "<table class='table table-striped table-condensed table-bordered'>";
		html += "<tr>" +
				"<th># ID</th> <th>Tipo Servico</th> <th>Nome Servico</th> <th>Descricao</th> <th>Ativo</th> <th>Edição</th>" +
				"</tr>"
	for(var i = 0; i < listaServicoAchados.length; i++){
		html += "<tr>" +
					"<td>" + listaServicoAchados[i].idservico + "</td>" +
					"<td>" + listaServicoAchados[i].tiposervico + "</td>" +
					"<td>" + listaServicoAchados[i].nomeservico + "</td>" +
					"<td>" + listaServicoAchados[i].descricaoservico + "</td>" +
					"<td id='td'>" + listaServicoAchados[i].ativo + "</td>" +
					"<td>" +
						"<div class='btn glyphicon glyphicon-pencil' onclick='exibirEdicaoServico("+listaServicoAchados[i].idservico+")'></div>" +					
					"</td>" +
				"</tr>"
	}
	html += "</table>" +
			"</div>"
	$("#resultadoServico").html(html);
}

function exibirEdicaoServico(idservico){
	alert("Exibe Edicao");
	$.ajax({
		type : 'POST',
		url : '/DigitalOS/rest/RestServico/buscarServicoPeloId/'+idservico,
		success: function(servico){
			alert(servico);
			$("#EditIdServico").val(idservico);
			$("#EditNomeTipoServico").val(servico.tiposervico);
			$("#EditNomeServico").val(servico.nomeservico);
			$("#EditDescricaoServico").val(servico.descricaoservico);
			$("#EditStatusServico").val(servico.ativo);
			$("#msgEditServico").modal(servico);
		},
		error: function(servico){
			alert("Erro ao editar Servico!");
		}
	});
}

function editarServico(){
	var idservico = 		$("#EditIdServico").val();
	var tiposervico = 		$("#EditNomeTipoServico").val();
	var nomeservico = 		$("#EditNomeServico").val();
	var descricaoservico = 	$("#EditDescricaoServico").val();
	var ativo = 			$("#EditStatusServico").val();
	
	var servicoEdit = {'idservico':idservico, 'tiposervico':tiposervico, 'nomeservico':nomeservico,'descricaoservico':descricaoservico, 'ativo':ativo};
	var servico = JSON.stringify(servicoEdit);
	
	$.ajax({
		type : 'POST',
		url : '/DigitalOS/rest/RestServico/editarServico',
		data:servico,
		success: function(resposta){
			alert(resposta);
			$('#msgEditServico').modal('hide');
			buscarServico();
		},
		error:function(){
			alert("Erro ao Atualizar Servico");
		}
	});
}

function filtroServico(){
	$.ajax({
		type:'POST',
		url: '/DigitalOS/rest/RestServico/filtroServicoAtivo',
		data: $("#filtroServicoAtivo").val(),
		success: function(listaServicoAchados){
			tabelaServico(listaServicoAchados);
		},
		error: function(){
			alert("Erro ao Filtrar Servicos");
		}
	});
}
/*FIM CRUD SERVICO*/