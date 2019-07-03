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
		data : {'nome' : nome,'marca' : marca,'modelo' : modelo,'nsaparelho' : nsaparelho,'categoria' : categoria,'ativo':ativo },
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
		var tipopessoa = 	 $("#tipopessoa").val();
		var nome = 			 $("#nomecliente").val();
		var cpf = 			 $("#cpfcliente").val();
		var rg = 			 $("#rgcliente").val();
		var datanascimento = $("#datanascimento").val();
		var sexo = 			 $("#sexocliente").val();
		var email = 		 $("#emailcliente").val();
		var telefone = 		 $("#telefonecliente").val();
		var celular = 		 $("#celularcliente").val();
		var cep = 			 $("#cepcliente").val();
		var endereco = 		 $("#enderecocliente").val();
		var numero = 		 $("#numerocliente").val();
		var bairro = 		 $("#bairrocliente").val();
		var cidade = 		 $("#cidadecliente").val();
		var estado = 		 $("#estadocliente").val();
		var tipomorada = 	 $("#tipomorada").val();
		var profissao =  	 $("#profissaocliente").val();
		var ativo = 		 $("#statuspessoa").val();

		var pessoaNova = {'tipopessoa':tipopessoa, 'nome':nome, 'cpf':cpf,'rg':rg,'datanascimento':datanascimento, 'sexo':sexo, 
						  'email':email,'telefone':telefone,'celular':celular,'cep':cep,'endereco':endereco,'numero':numero,'bairro':bairro,
						  'cidade':cidade,'estado':estado,'tipomorada':tipomorada, 'profissao':profissao, 'ativo':ativo};	
		var pessoa = JSON.stringify(pessoaNova);
	$.ajax({
		type:'POST',
		url: '/DigitalOS/rest/RestPessoa/addPessoaObj',
		data: pessoa,
		success: function(resposta){
			alert(resposta);
			$("#modalcliente").find('form')[0].reset();
			$("#modalcliente").modal('hide');
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
			$("#idpessoaedit").val(id);
			$("#tipopessoaedit").val(pessoa.tipopessoa);
			$("#nomeclienteedit").val(pessoa.nome);
			$("#cpfclienteedit").val(pessoa.cpf);
			$("#rgclienteedit").val(pessoa.rg);
			$("#datanascimentoedit").val(pessoa.datanascimento);
			$("#sexoclienteedit").val(pessoa.sexo);
			$("#emailclienteedit").val(pessoa.email);
			$("#telefoneclienteedit").val(pessoa.telefone);
			$("#celularclienteedit").val(pessoa.celular);
			$("#cepclienteedit").val(pessoa.cep);
			$("#enderecoclienteedit").val(pessoa.endereco);
			$("#numeroclienteedit").val(pessoa.numero);
			$("#bairroclienteedit").val(pessoa.bairro);
			$("#cidadeclienteedit").val(pessoa.cidade);
			$("#estadoclienteedit").val(pessoa.estado);
			$("#tipomoradaedit").val(pessoa.tipomorada);
			$("#profissaoclienteedit").val(pessoa.profissao);
			$("#statuspessoaedit").val(pessoa.ativo);
			$("#idenderecoedit").val(pessoa.idendereco);
			$("#msgEditPessoa").modal(pessoa);
		},
		error: function(pessoa){
			alert("Erro ao editar Pessoa!");
		}
	});
}
function editarPessoa(id){
	var id = 			 $("#idpessoaedit").val();
	var tipopessoa = 	 $("#tipopessoaedit").val();
	var nome = 			 $("#nomeclienteedit").val();
	var cpf = 			 $("#cpfclienteedit").val();
	var rg = 			 $("#rgclienteedit").val();
	var datanascimento = $("#datanascimentoedit").val();
	var sexo = 			 $("#sexoclienteedit").val();
	var email = 		 $("#emailclienteedit").val();
	var telefone = 		 $("#telefoneclienteedit").val();
	var celular = 		 $("#celularclienteedit").val();
	var cep = 			 $("#cepclienteedit").val();
	var endereco = 		 $("#enderecoclienteedit").val();
	var numero = 		 $("#numeroclienteedit").val();
	var bairro = 		 $("#bairroclienteedit").val();
	var cidade = 		 $("#cidadeclienteedit").val();
	var estado = 		 $("#estadoclienteedit").val();
	var tipomorada = 	 $("#tipomoradaedit").val();
	var profissao = 	 $("#profissaoclienteedit").val();
	var ativo = 		 $("#statuspessoaedit").val();
	var idendereco = 	 $("#idenderecoedit").val();

	var pessoaEdit = {'id':id, 'tipopessoa':tipopessoa, 'nome':nome, 'cpf':cpf, 'rg':rg, 'datanascimento':datanascimento, 'sexo':sexo,
					  'email':email, 'telefone':telefone, 'celular':celular, 'cep':cep, 'endereco':endereco, 'numero':numero, 'bairro':bairro, 
					  'cidade':cidade, 'estado':estado, 'tipomorada':tipomorada, 'profissao':profissao, 'ativo':ativo, 'idendereco':idendereco};
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
	var nome = 			 	 $("#nomefuncionario").val();
	var cpf = 			 	 $("#cpffuncionario").val();
	var rg = 			 	 $("#rgfuncionario").val();
	var sexo = 	 		 	 $("#sexofuncionario").val();
	var datanascimento = 	 $("#datanascimentofuncionario").val();
	var cargo =  	 	 	 $("#cargofuncionario").val();
	var setor =  	 	 	 $("#setorfuncionario").val();
	var numeropis =		 	 $("#numeropis").val();
	var numeroct = 		 	 $("#numeroct").val();
	var salario = 			 $("#salario").val();
	var dataadmissao = 	 	 $("#dataadmissaofuncionario").val();
	var email = 		 	 $("#emailfuncionario").val();
	var endereco = 		 	 $("#enderecofuncionario").val();
	var cep = 			 	 $("#cepfuncionario").val();
	var numero = 		 	 $("#numerofuncionario").val();
	var telefone = 		 	 $("#telefonefuncionario").val();
	var celular = 		 	 $("#celularfuncionario").val();
	var estado = 		 	 $("#estadofuncionario").val();
	var cidade = 		 	 $("#cidadefuncionario").val();
	var ativo = 		 	 $("#statusfuncionario").val();

	var funcNovo = {'nome':nome, 'cpf':cpf, 'rg':rg, 'sexo':sexo, 'datanascimento':datanascimento, 'cargo':cargo, 'setor':setor, 'numeropis':numeropis,
					'numeroct':numeroct, 'salario':salario, 'dataadmissao':dataadmissao, 'email':email, 'endereco':endereco, 'numero':numero, 
					'telefone':telefone, 'celular':celular, 'estado':estado, 'cidade':cidade, 'ativo':ativo};	
	var funcionario = JSON.stringify(funcNovo);
	$.ajax({
		type:'POST',
		url: '/DigitalOS/rest/RestFuncionario/addFuncionario',
		data: funcionario,
		success: function(resposta){
			alert(resposta);
			$("#modalfuncionario").find('form')[0].reset();
			$("#modalfuncionario").modal('hide');
			buscarFuncionario();
		},
		error: function(){
			alert("Erro ao cadastrar nova pessoa.");
		}
	});
}
function buscarFuncionario(){
	var valorBusca = $('#buscaFuncionario').val();
	$.ajax({
		type: 'POST',
		url: '/DigitalOS/rest/RestFuncionario/buscarFuncionarioPorNome',
		data: valorBusca,
		success: function(listaFuncionariosAchadas){
			tabelaFuncionario(listaFuncionariosAchadas);
		},
		error: function(listaFuncionariosAchadas){
			alert("Erro ao procurar funcionario")
			tabelaFuncionario(listaFuncionariosAchadas);
		}
	});
}
function tabelaFuncionario(listaFuncionariosAchadas){
	var html = "<div class='teble-reponsive'>" +
			   "<table class='table table-striped table-condensed table-bordered'>";
		html += "<tr>" +
				"<th># ID</th> <th>Nome</th> <th>CPF</th> <th>Cargo</th> <th>Setor</th> <th>Salario</th> <th>Sexo</th> <th>Id Funcionario</th> <th>Ativo</th> <th>Edição</th>" +
				"</tr>"
	for(var i = 0; i < listaFuncionariosAchadas.length; i++){
		html += "<tr>" +
					"<td>" + listaFuncionariosAchadas[i].idfuncionario + "</td>" +
					"<td>" + listaFuncionariosAchadas[i].nome + "</td>" +
					"<td>" + listaFuncionariosAchadas[i].cpf + "</td>" +
					"<td>" + listaFuncionariosAchadas[i].cargo + "</td>" +
					"<td>" + listaFuncionariosAchadas[i].setor + "</td>" +
					"<td>" + listaFuncionariosAchadas[i].salario + "</td>" +
					"<td>" + listaFuncionariosAchadas[i].sexo + "</td>" +
					"<td>" + listaFuncionariosAchadas[i].idfuncionario + "</td>" +
					"<td>" + listaFuncionariosAchadas[i].ativo + "</td>" +
					"<td>" +
						"<div class='btn glyphicon glyphicon-pencil' onclick='exibirEdicaoFuncionario("+listaFuncionariosAchadas[i].idfuncionario+")'></div>" +					
					"</td>" +
				"</tr>"
	}
	html += "</table>" +
			"</div>"
	$("#resultadoFuncionario").html(html);
}
function exibirEdicaoFuncionario(idfuncionario){
	$.ajax({
		type : 'POST',
		url : '/DigitalOS/rest/RestFuncionario/buscarFuncionarioPeloId/'+idfuncionario,
		success: function(funcionario){
			$("#EditIdFuncionario").val(idfuncionario);
			$("#EditNomeFuncionario").val(funcionario.nome);
			$("#EditCpfFuncionario").val(funcionario.cpf);
			$("#EditRgFuncionario").val(funcionario.rg);
			$("#EditDataNascimentoFuncionario").val(funcionario.datanascimento);
			$("#EditSexoFuncionario").val(funcionario.sexo);
			$("#EditTelefoneFuncionario").val(funcionario.telefone);
			$("#EditCelularFuncionario").val(funcionario.celular);
			$("#EditEmailFuncionario").val(funcionario.email);
			$("#EditTipoMoradaFuncionario").val(funcionario.tipomorada);
			$("#EditStatusFuncionario").val(funcionario.ativo);
			$("#EditCepFuncionario").val(funcionario.cep);
			$("#EditNumeroFuncionario").val(funcionario.numero);
			$("#EditEnderecoFuncionario").val(funcionario.endereco);
			$("#EditBairroFuncionario").val(funcionario.bairro);
			$("#EditCidadeFuncionario").val(funcionario.cidade);
			$("#EditEstadoFuncionario").val(funcionario.estado);
			$("#EditNumeroCtFuncionario").val(funcionario.numeroct);
			$("#EditNumeroPisFuncionario").val(funcionario.numeropis);
			$("#EditCargoFuncionario").val(funcionario.cargo);
			$("#EditSetorFuncionario").val(funcionario.setor);
			$("#EditSalarioFuncionario").val(funcionario.salario);
			$("#EditDataAdmissaoFuncionario").val(funcionario.dataadmissao);
			$("#EditDataDemissaoFuncionario").val(funcionario.datademissao);
			$("#EditIdEndereco").val(funcionario.idendereco);
			$("#EditFuncionarioIdFuncionario").val(funcionario.idfuncionario);
			$("#msgEditFuncionario").modal(funcionario);
		},
		error: function(pessoa){
			alert("Erro ao editar Funcionario!");
		}
	});
}
function atualizarFuncionario(){
	var id = $("#EditIdFuncionario").val();
	var nome = $("#EditNomeFuncionario").val();
	var cpf = $("#EditCpfFuncionario").val();
	var rg = $("#EditRgFuncionario").val();
	var datanascimento = $("#EditDataNascimentoFuncionario").val();
	var sexo = $("#EditSexoFuncionario").val();
	var telefone = $("#EditTelefoneFuncionario").val();
	var celular = $("#EditCelularFuncionario").val();
	var email = $("#EditEmailFuncionario").val();
	var tipomorada = $("#EditTipoMoradaFuncionario").val();
	var statusfuncionario = $("#EditStatusFuncionario").val();
	var cep = $("#EditCepFuncionario").val();
	var numero = $("#EditNumeroFuncionario").val();
	var endereco = $("#EditEnderecoFuncionario").val();
	var bairro = $("#EditBairroFuncionario").val();
	var cidade = $("#EditCidadeFuncionario").val();
	var estado = $("#EditEstadoFuncionario").val();
	var numeroct = $("#EditNumeroCtFuncionario").val();
	var numeropis = $("#EditNumeroPisFuncionario").val();
	var cargo = $("#EditCargoFuncionario").val();
	var setor = $("#EditSetorFuncionario").val();
	var salario = $("#EditSalarioFuncionario").val();
	var dataadmissao = $("#EditDataAdmissaoFuncionario").val();
	var datademissao = $("#EditDataDemissaoFuncionario").val();
	var idendereco = $("#EditIdEndereco").val();
	var funcionarioidfuncionario = $("#EditFuncionarioIdFuncionario").val();
	
	var funcionarioEdit = {'id':id, 'nome':nome, 'cpf':cpf, 'rg':rg, 'datanascimento':datanascimento, 'sexo':sexo, 
						   'telefone':telefone, 'celular':celular, 'email':email, 'tipomorada':tipomorada, 'ativo':statusfuncionario, 'cep':cep, 
						   'numero':numero, 'endereco':endereco, 'bairro':bairro, 'cidade':cidade, 'estado':estado, 'numeroct':numeroct, 
						   'numeropis':numeropis, 'cargo':cargo, 'setor':setor, 'salario':salario, 'dataadmissao':dataadmissao, 
						   'datademissao':datademissao, 'idendereco':idendereco, 'idfuncionario':funcionarioidfuncionario};
	var funcionario = JSON.stringify(funcionarioEdit);
	alert(funcionario);
	$.ajax({
		type:'POST',
		url: '/DigitalOS/rest/RestFuncionario/editarFuncionario',
		data: funcionario,
		success: function(resposta){
			$("#msgEditFuncionario").modal('hide');
			buscarFuncionario();
		},
		error: function(){
			alert("Erro ao editar funcionario.");
		}
	});
}
function filtroFuncionariosAtivos(){
	alert($("#filtroAtivoOpc").val());
	$.ajax({
		type:'POST',
		url: '/DigitalOS/rest/RestFuncionario/filtroFuncionarioAtivo',
		data: $("#filtroAtivoOpc").val(),
		success: function(listaFuncionariosAchados){
			tabelaFuncionario(listaFuncionariosAchados);
		},
		error: function(){
			alert("Erro ao Filtrar Servicos");
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
				$("#modalservico").find('form')[0].reset();
				$("#modalservico").modal('hide');
				buscarServico();
			},
			error: function(){
				alert("Erro ao cadastrar nova pessoa.");
				$("#modalservico").modal('hide');
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

/*INICIO CRUD CATERGORIA APARELHO*/
function addCategoriaAparelho(){
	var nome = $("#nomecategoria").val();
	var ativo = $("#statuscategoria").val();
	var categoriaNova = {'nome':nome, 'ativo':ativo};
	var categoria = JSON.stringify(categoriaNova);
	$.ajax({
		type:'POST',
		url: '/DigitalOS/rest/RestCategoriaAparelho/addCategoriaAparelho',
		data: categoria,
		success: function(resposta){
			alert(resposta);
			/*$("#modalcategoriaaparelho").find('form')[0].reset();
			$("#modalcategoriaaparelho").modal('hide');*/
			buscarCatergoria();
		},
		error: function(){
			alert("Erro ao salvar categoria de aparelho");
		}
	});
}

function buscarCatergoria(){
	var valorBusca = $('#buscaCategoria').val();
	$.ajax({
		type: 'POST',
		url: '/DigitalOS/rest/RestCategoriaAparelho/buscarCategoriaAparelhoPorNome',
		data: valorBusca,
		success: function(listaCategoriaAchados){
			tabelaCategoria(listaCategoriaAchados);
		},
		error: function(listaCategoriaAchados){
			tabelaCategoria();
		}
	});
}

function tabelaCategoria(listaCategoriaAchados){
	var html = "<div class='teble-reponsive'>" +
			   "<table class='table table-striped table-condensed table-bordered'>";
		html += "<tr>" +
				"<th># ID</th> <th>Nome Categoria</th> <th>Ativo</th> <th>Edição</th>" +
				"</tr>"
	for(var i = 0; i < listaCategoriaAchados.length; i++){
		html += "<tr>" +
					"<td>" + listaCategoriaAchados[i].id + "</td>" +
					"<td>" + listaCategoriaAchados[i].nome + "</td>" +
					"<td id='td'>" + listaCategoriaAchados[i].ativo + "</td>" +
					"<td>" +
						"<div class='btn glyphicon glyphicon-pencil' onclick='exibirEdicaoCategoria("+listaCategoriaAchados[i].id+")'></div>" +					
					"</td>" +
				"</tr>"
	}
	html += "</table>" +
			"</div>"
	$("#resultadoCategoria").html(html);
}
function exibirEdicaoCategoria(id){
	alert(id);
	$.ajax({
		type : 'POST',
		url : '/DigitalOS/rest/RestCategoriaAparelho/buscarCategoriaAparelhoPeloId/'+id,
		success: function(categoria){
			console.log(categoria);
			$("#EditIdCategoria").val(categoria.id);
			$("#EditNomeCategoria").val(categoria.nome);
			$("#EditStatusCategoria").val(categoria.ativo);
			$("#msgEditCategoria").modal(categoria);
		},
		error: function(servico){
			alert("Erro ao editar Categoria!");
		}
	});
}
function editarCategoria(){
	var id = 	$("#EditIdCategoria").val();
	var nome = 	$("#EditNomeCategoria").val();
	var ativo = $("#EditStatusCategoria").val();
	
	var categoriaEdit = {'id':id, 'nome':nome,'ativo':ativo};
	var categoria = JSON.stringify(categoriaEdit);
	
	$.ajax({
		type : 'POST',
		url : '/DigitalOS/rest/RestCategoriaAparelho/editarCategoriaAparelho',
		data:categoria,
		success: function(resposta){
			alert(resposta);
			$('#msgEditCategoria').modal('hide');
			buscarCatergoria();
		},
		error:function(){
			alert("Erro ao Atualizar Servico");
		}
	});
}
function filtroCategoria(){
	$.ajax({
		type:'POST',
		url: '/DigitalOS/rest/RestCategoriaAparelho/filtroCategoriaAparelhoAtivo',
		data: $("#filtroAtivoOpc").val(),
		success: function(listaCategoriaAchados){
			tabelaCategoria(listaCategoriaAchados);
		},
		error: function(){
			alert("Erro ao Filtrar Categorias");
		}
	});
}
/*FIM CRUD CATEGORIA APARELHO*/





/*INICIO CRUD CATERGORIA APARELHO*/
function addMarca(){
	var nome = $("#nomemarca").val();
	var ativo = $("#statusmarca").val();
	var marcaNova = {'nome':nome, 'ativo':ativo};
	var marca = JSON.stringify(marcaNova);
	$.ajax({
		type:'POST',
		url: '/DigitalOS/rest/RestMarca/addMarca',
		data: marca,
		success: function(resposta){
			alert(resposta);
			$("#modalmarca").modal('hide');
			buscarMarca();
		},
		error: function(){
			alert("Erro ao salvar marca de aparelho");
		}
	});
}
function buscarMarca(){
	var valorBusca = $('#buscaMarca').val();
	$.ajax({
		type: 'POST',
		url: '/DigitalOS/rest/RestMarca/buscarMarcaPorNome',
		data: valorBusca,
		success: function(listaMarcaAchados){
			tabelaMarca(listaMarcaAchados);
		},
		error: function(listaMarcaAchados){
			tabelaMarca();
		}
	});
}
function tabelaMarca(listaMarcaAchados){
	var html = "<div class='teble-reponsive'>" +
			   "<table class='table table-striped table-condensed table-bordered'>";
		html += "<tr>" +
				"<th># ID</th> <th>Nome Marca</th> <th>Ativo</th> <th>Edição</th>" +
				"</tr>"
	for(var i = 0; i < listaMarcaAchados.length; i++){
		html += "<tr>" +
					"<td>" + listaMarcaAchados[i].id + "</td>" +
					"<td>" + listaMarcaAchados[i].nome + "</td>" +
					"<td id='td'>" + listaMarcaAchados[i].ativo + "</td>" +
					"<td>" +
						"<div class='btn glyphicon glyphicon-pencil' onclick='exibirEdicaoMarca("+listaMarcaAchados[i].id+")'></div>" +					
					"</td>" +
				"</tr>"
	}
	html += "</table>" +
			"</div>"
	$("#resultadoMarca").html(html);
}
function exibirEdicaoMarca(id){
	$.ajax({
		type : 'POST',
		url : '/DigitalOS/rest/RestMarca/buscarMarcaPeloId/'+id,
		success: function(marca){
			console.log(marca);
			$("#EditIdMarca").val(id);
			$("#EditNomeMarca").val(marca.nome);
			$("#EditStatusMarca").val(marca.ativo);
			$("#msgEditMarca").modal(marca);
		},
		error: function(servico){
			alert("Erro ao editar marca!");
		}
	});
}
function editarMarca(){
	var id = 	$("#EditIdMarca").val();
	var nome = 	$("#EditNomeMarca").val();
	var ativo = $("#EditStatusMarca").val();	
	var marca = JSON.stringify({'id':id,'nome':nome,'ativo':ativo});
	console.log(marca);
	$.ajax({
		type : 'POST',
		url : '/DigitalOS/rest/RestMarca/editarMarca',
		data: marca,
		success: function(resposta){
			alert(resposta);
			$('#msgEditMarca').modal('hide');
			buscarMarca();
		},
		error:function(){
			alert("Erro ao atualizar marca!");
		}
	});
}
function filtroMarca(){
	$.ajax({
		type:'POST',
		url: '/DigitalOS/rest/RestMarca/filtroMarcaAtivo',
		data: $("#filtroAtivoOpc").val(),
		success: function(listaMarcaAchados){
			tabelaMarca(listaMarcaAchados);
		},
		error: function(){
			alert("Erro ao filtrar marca!");
		}
	});
}
/*FIM CRUD CATEGORIA MARCA*/

/*INICIO ORDEM DE SERVICO*/
function abrirOS(){
}
/*FIM ORDEM DE SERVICO*/
