/*INICIO LOGIN*/
	function loginOS() {
		var user = $("#usuario").val();
		var password = $("#senha").val();
		var er = /^[a-z0-9.]+@[a-z0-9]+\.[a-z]+\.([a-z]+)?$/;
		if (!er.exec(user)) {			
			if (user != "" && user != null && password != "" && password != null) {
				var passCoded = btoa(password);
		
				$.ajax({
					type : 'POST',
					url : 'ServletConsultaLogin',
					data : {
						'user' : user,
						'passCoded' : passCoded
					},
		
					success : function(url) {
						location.href = url.url;
						if(url.url == '/DigitalOS/login.html'){
							alert("Usuário ou senha inválido!"+"\n"+"Tente novamente.");
						}else{
							alert("Login realizado com sucesso."+"\n"+"\Seja bem vindo!");
						}
					},
					error : function(url) {
						location.href = url.url;
					}
				});
			} else {
				alert("E-mail ou Senha Inválido!");
				$("#usuario").focus();
			}	
		}else{
			alert("E-mail invalido!");
			$("#usuario").focus();
		}	
	}
 	function carregaPerfil() {
		var id = document.cookie.split('=')[1];
		$.ajax({
			type : 'POST',
			url : '/DigitalOS/rest/RestPerfil/buscarPerfilPeloId/' + id,
			success : function(perfil) {
				$("#nome").val(perfil.nome);
				$("#cep").val(perfil.cep);
				$("#numero").val(perfil.numero);
				$("#endereco").val(perfil.endereco);
				$("#telefone").val(perfil.telefone);
				$("#celular").val(perfil.celular);
				$("#email").val(perfil.email);
				$("#senha").val(perfil.senha);
				$("#id").val(perfil.id);
			},
			error : function(url) {
	
			}
		});
	
	}
	function editarPerfil() {
	var novasenha = $("#novaSenha").val();
	var confirmasenha = $("#confirmaSenha").val();
	if (novasenha == confirmasenha) {
		var id = $("#id").val();
		var nome = $("#nome").val();
		var cep = $("#cep").val();
		var numero = $("#numero").val();
		var endereco = $("#endereco").val();
		var telefone = $("#telefone").val();
		var celular = $("#celular").val();
		var email = $("#email").val();
		var senha = $("#novaSenha").val();

		var perfilNovo = {
			'id' : id,
			'nome' : nome,
			'cep' : cep,
			'numero' : numero,
			'endereco' : endereco,
			'telefone' : telefone,
			'celular' : celular,
			'email' : email,
			'senha' : senha
		};
		var perfil = JSON.stringify(perfilNovo);
		$.ajax({
			type : 'POST',
			url : '/DigitalOS/rest/RestPerfil/editarPerfil',
			data : perfil,
			success : function(resposta) {
				$("#ModalPainelUsuario").find('input').val('');
				alert(resposta);
				carregaPerfil();
			},
			error : function() {
				alert("Erro ao editar perfil");
				$("#ModalPainelUsuario").find('input').val('');
				$("#ModalPainelUsuario").modal('hide');
			}
		});
	} else {
		alert("A nova senha informada difere da senha de confirmação!");
	}

}
/* FIM LOGIN */

/* INICIO LOGOUT */
	function logout(){
		$.ajax({
			type : 'POST',
			url : '../../../ServletLogout',
			// data: session.,
			success : function(logout) { 
					location.href = JSON.parse(logout);				
			},
			error : function(logout) {
				alert("Erro no logout!");
			}
		});
	}
/* FIM LOGOUT */

/* INICIO DELETE COOKIE */
	function deleteACookie(){
	    var cname = window.document.getElementById('cname').value;// Get the
																	// cookie
																	// name from
																	// the cname
																	// input
																	// element
	    deleteCookie(cname);// Call the deleteCookie to delete the cookie
	    window.location.reload();// Reload the page
	}
/* FIM DELETE COOKIE */

	/* INICIO CRUD APARELHO */
	function CadastroAparelho() {
		var nome = $("#nomeaparelho").val();
		var categoria = $("#categoriaaparelho").val();
		var marca = $("#marcaaparelho").val();
		var modelo = $("#modeloaparelho").val();
		var nsaparelho = $("#nsaparelho").val();
		var ativo = $("#statusaparelho").val();
		if(nome != null && categoria != null && marca != null && modelo != null && nsaparelho != null && ativo != null){
			$.ajax({
				type : 'POST',
				url : '../../../ServletCadastroAparelho',
				data : {
					'nome' : nome,
					'marca' : marca,
					'modelo' : modelo,
					'nsaparelho' : nsaparelho,
					'categoria' : categoria,
					'ativo' : ativo
				},
				success : function(resposta) {
					$("#nomeaparelho").val('');
					$("#categoriaaparelho").val('');
					$("#marcaaparelho").val('');
					$("#modeloaparelho").val('');
					$("#nsaparelho").val('');
					$("#modalaparelho").modal('hide');
					buscarAparelho();
				},
				error : function(resposta) {
					alert(resposta);
				}
			});
		}else{
			alert("Preencha os campos corratamente!\n" + "Todos são obrigatorios!");
		}

	}
	function buscarAparelho() {
		var valorBusca = $('#buscaAparelhoInput').val();
		$.ajax({
			type : 'POST',
			url : '../../../ServletBuscarAparelho',
			data : {'valorBusca' : valorBusca},
			success : function(listaAparelhosAchados) {
				carregarTabela(listaAparelhosAchados);
			},
			error : function() {
				alert("Error, nenhum aparelho encontrado");
			}
		});
	};
	function carregarTabela(listaAparelhosAchados) {
		var html = "<div class='teble-reponsive'>"
				+ "<table class='table table-striped table-condensed table-bordered'>";
		html += "<tr>"
				+ "<th># ID</th> <th>Nome</th> <th>Categoria</th> <th>Marca</th> <th>Modelo</th> <th>Número Série</th> <th>Ativo</th> <th>Edição</th>"
				+ "</tr>"
		for (var i = 0; i < listaAparelhosAchados.length; i++) {
			html += "<tr>" + "<td>"
					+ listaAparelhosAchados[i].idaparelho
					+ "</td>"
					+ "<td>"
					+ listaAparelhosAchados[i].nome
					+ "</td>"
					+ "<td>"
					+ listaAparelhosAchados[i].categoria
					+ "</td>"
					+ "<td>"
					+ listaAparelhosAchados[i].marca
					+ "</td>"
					+ "<td>"
					+ listaAparelhosAchados[i].modelo
					+ "</td>"
					+ "<td>"
					+ listaAparelhosAchados[i].nsaparelho
					+ "</td>"
					+ "<td>"
					+ listaAparelhosAchados[i].ativo
					+ "</td>"
					+ "<td>"
					+ "<div class='btn glyphicon glyphicon-pencil' onclick='exibirEdicaoAparelho("
					+ listaAparelhosAchados[i].idaparelho + ")'></div>" + "</td>"
					+ "</tr>"
		}
		html += "</table>" + "</div>"
		$("#resultadoAparelhos").html(html);
	}
	function exibirEdicaoAparelho(idaparelho) {
		$.ajax({
			type : 'POST',
			url : '../../../ServletBuscarPorId',
			data : {
				'idaparelho' : idaparelho
			},
			success : function(aparelho) {
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
			error : function(err) {
				alert("Erro ao editar aparelho!");
			}
		});
	}
	function editarAparelho() {
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
			data : {
				'idaparelho' : idaparelho,
				'nome' : nome,
				'categoria' : categoria,
				'marca' : marca,
				'modelo' : modelo,
				'nsaparelho' : nsaparelho,
				'ativo' : ativo
			},
			success : function(resposta) {
				alert("Atualizado");
				$("#msgEditAparelho").modal('hide');
				buscarAparelho();
			},
			error : function() {
				alert("Erro ao Atualizar");
			}
		});
	}
	function filtroAparelhosAtivos() {
	var ativo = $("#filtroAtivoOpc").val();
	$.ajax({
		type : 'POST',
		url : '../../../ServletFiltroAtivos',
		data : {
			'ativo' : ativo
		},
		success : function(listaAparelhosAchados) {
			carregarTabela(listaAparelhosAchados);
		},
		error : function() {
			alert("Erro ao Filtrar");
		}
	});
}
/* FIM CRUD APARELHO */
	
/* INICIO CRUD CARREGA SELECT */
	function carregaCategoria(){
		$.ajax({
			type : 'POST',
			url : '/DigitalOS/rest/RestSelect/buscarSelectCategoria',
			success : function(lista) {
				listosa = JSON.parse(lista);
				$.each(listosa, function(i, v) {
					$('#categoriaaparelho').append(
							$('<option>').text(v.nome).attr('value', v.id));
				});
			},
			error : function() {
				alert("Erro ao encontrar categoria");
			}
		});
	}
	function carregaMarca(){
		$.ajax({
			type : 'POST',
			url : '/DigitalOS/rest/RestSelect/buscarSelectMarca',
			success : function(lista) {
				listosa = JSON.parse(lista);
				$.each(listosa, function(i, v) {
					$('#marcaaparelho').append(
							$('<option>').text(v.nome).attr('value', v.id));
				});
			},
			error : function() {
				alert("Erro ao encontrar marca");
			}
		});
	}
/* FIM CRUF CARREGA SELECT */

/* INICIO CRUD PESSOA */
	function inserirPessoa() {
		var tipopessoa = $("#tipopessoa").val();
		var nome = $("#nomecliente").val();
		var cpf = $("#cpfcliente").val();
		var rg = $("#rgcliente").val();
		var datanascimento = $("#datanascimento").val();
		var sexo = $("#sexocliente").val();
		var email = $("#emailcliente").val();
		var telefone = $("#telefonecliente").val();
		var celular = $("#celularcliente").val();
		var cep = $("#cepcliente").val();
		var endereco = $("#enderecocliente").val();
		var numero = $("#numerocliente").val();
		var bairro = $("#bairrocliente").val();
		var cidade = $("#cidadecliente").val();
		var estado = $("#estadocliente").val();
		var tipomorada = $("#tipomorada").val();
		var profissao = $("#profissaocliente").val();
		var ativo = $("#statuspessoa").val();
	
		var pessoaNova = {
			'tipopessoa' : tipopessoa,
			'nome' : nome,
			'cpf' : cpf,
			'rg' : rg,
			'datanascimento' : datanascimento,
			'sexo' : sexo,
			'email' : email,
			'telefone' : telefone,
			'celular' : celular,
			'cep' : cep,
			'endereco' : endereco,
			'numero' : numero,
			'bairro' : bairro,
			'cidade' : cidade,
			'estado' : estado,
			'tipomorada' : tipomorada,
			'profissao' : profissao,
			'ativo' : ativo
		};
		var pessoa = JSON.stringify(pessoaNova);
		$.ajax({
			type : 'POST',
			url : '/DigitalOS/rest/RestPessoa/addPessoaObj',
			data : pessoa,
			success : function(resposta) {
				alert(resposta);
				$("#modalcliente").find('form')[0].reset();
				$("#modalcliente").modal('hide');
				buscarPessoa();
			},
			error : function() {
				alert("Erro ao cadastrar nova pessoa.");
			}
		});
	}
	function buscarPessoa() {
		var valorBusca = $('#buscaPessoaInput').val();
		$.ajax({
			type : 'POST',
			url : '/DigitalOS/rest/RestPessoa/buscarPessoaPorNome',
			data : valorBusca,
			success : function(listaPessoasAchadas) {
				tabelaPessoa(listaPessoasAchadas);
			},
			error : function(listaPessoasAchadas) {
				tabelaPessoa(listaPessoasAchadas);
			}
		});
	};
	function tabelaPessoa(listaPessoasAchadas) {
		var html = "<div class='teble-reponsive'>"
				+ "<table class='table table-striped table-condensed table-bordered'>";
		html += "<tr>"
				+ "<th># ID</th> <th>Nome</th> <th>CPF</th> <th>RG</th> <th>Endereco</th> <th>Cidade</th> <th>Estado</th> <th>Telefone</th> <th>Tipo Pessoa</th> <th>Ativo</th> <th>Edição</th>"
				+ "</tr>"
		for (var i = 0; i < listaPessoasAchadas.length; i++) {
			html += "<tr>" + "<td>"
					+ listaPessoasAchadas[i].id
					+ "</td>"
					+ "<td>"
					+ listaPessoasAchadas[i].nome
					+ "</td>"
					+ "<td>"
					+ listaPessoasAchadas[i].cpf
					+ "</td>"
					+ "<td>"
					+ listaPessoasAchadas[i].rg
					+ "</td>"
					+ "<td>"
					+ listaPessoasAchadas[i].endereco
					+ "</td>"
					+ "<td>"
					+ listaPessoasAchadas[i].cidade
					+ "</td>"
					+ "<td>"
					+ listaPessoasAchadas[i].estado
					+ "</td>"
					+ "<td>"
					+ listaPessoasAchadas[i].telefone
					+ "</td>"
					+ "<td>"
					+ listaPessoasAchadas[i].tipopessoa
					+ "</td>"
					+ "<td id='td'>"
					+ listaPessoasAchadas[i].ativo
					+ "</td>"
					+ "<td>"
					+ "<div class='btn glyphicon glyphicon-pencil' onclick='exibirEdicaoPessoa("
					+ listaPessoasAchadas[i].id + ")'></div>" + "</td>" + "</tr>"
		}
		html += "</table>" + "</div>"
		$("#resultadoPessoa").html(html);
	}
	function exibirEdicaoPessoa(id) {
		$.ajax({
			type : 'POST',
			url : '/DigitalOS/rest/RestPessoa/buscarPessoaPeloId/' + id,
			success : function(pessoa) {
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
			error : function(pessoa) {
				alert("Erro ao editar Pessoa!");
			}
		});
	}
	function editarPessoa(id) {
		var id = $("#idpessoaedit").val();
		var tipopessoa = $("#tipopessoaedit").val();
		var nome = $("#nomeclienteedit").val();
		var cpf = $("#cpfclienteedit").val();
		var rg = $("#rgclienteedit").val();
		var datanascimento = $("#datanascimentoedit").val();
		var sexo = $("#sexoclienteedit").val();
		var email = $("#emailclienteedit").val();
		var telefone = $("#telefoneclienteedit").val();
		var celular = $("#celularclienteedit").val();
		var cep = $("#cepclienteedit").val();
		var endereco = $("#enderecoclienteedit").val();
		var numero = $("#numeroclienteedit").val();
		var bairro = $("#bairroclienteedit").val();
		var cidade = $("#cidadeclienteedit").val();
		var estado = $("#estadoclienteedit").val();
		var tipomorada = $("#tipomoradaedit").val();
		var profissao = $("#profissaoclienteedit").val();
		var ativo = $("#statuspessoaedit").val();
		var idendereco = $("#idenderecoedit").val();
	
		var pessoaEdit = {
			'id' : id,
			'tipopessoa' : tipopessoa,
			'nome' : nome,
			'cpf' : cpf,
			'rg' : rg,
			'datanascimento' : datanascimento,
			'sexo' : sexo,
			'email' : email,
			'telefone' : telefone,
			'celular' : celular,
			'cep' : cep,
			'endereco' : endereco,
			'numero' : numero,
			'bairro' : bairro,
			'cidade' : cidade,
			'estado' : estado,
			'tipomorada' : tipomorada,
			'profissao' : profissao,
			'ativo' : ativo,
			'idendereco' : idendereco
		};
		var pessoa = JSON.stringify(pessoaEdit);
	
		$.ajax({
			type : 'POST',
			url : '/DigitalOS/rest/RestPessoa/editarPessoa',
			data : pessoa,
			success : function(resposta) {
				alert(resposta);
				$("#msgEditPessoa").modal('hide');
				buscarPessoa();
			},
			error : function() {
				alert("Erro ao Atualizar");
			}
		});
	}
	function filtroAtivosPessoa() {
	var ativo = JSON.stringify($("#filtroPessoa").val());
	$.ajax({
		type : 'POST',
		url : '/DigitalOS/rest/RestPessoa/filtroAtivo',
		data : $("#filtroPessoa").val(),
		success : function(listaPessoasAchadas) {
			tabelaPessoa(listaPessoasAchadas);
		},
		error : function() {
			alert("Erro ao Filtrar");
		}
	});
}
/* FIM CRUD PESSOA */

/* INICIO CRUD FUNCIONARIO */
	function inserirFuncionario() {
		var nome = $("#nomefuncionario").val();
		var cpf = $("#cpffuncionario").val();
		var rg = $("#rgfuncionario").val();
		var sexo = $("#sexofuncionario").val();
		var datanascimento = $("#datanascimentofuncionario").val();
		var cargo = $("#cargofuncionario").val();
		var setor = $("#setorfuncionario").val();
		var numeropis = $("#numeropis").val();
		var numeroct = $("#numeroct").val();
		var salario = $("#salario").val();
		var dataadmissao = $("#dataadmissaofuncionario").val();
		var email = $("#emailfuncionario").val();
		var endereco = $("#enderecofuncionario").val();
		var cep = $("#cepfuncionario").val();
		var numero = $("#numerofuncionario").val();
		var telefone = $("#telefonefuncionario").val();
		var celular = $("#celularfuncionario").val();
		var estado = $("#estadofuncionario").val();
		var cidade = $("#cidadefuncionario").val();
		var ativo = $("#statusfuncionario").val();
	
		var funcNovo = {
			'nome' : nome,
			'cpf' : cpf,
			'rg' : rg,
			'sexo' : sexo,
			'datanascimento' : datanascimento,
			'cargo' : cargo,
			'setor' : setor,
			'numeropis' : numeropis,
			'numeroct' : numeroct,
			'salario' : salario,
			'dataadmissao' : dataadmissao,
			'email' : email,
			'endereco' : endereco,
			'numero' : numero,
			'telefone' : telefone,
			'celular' : celular,
			'estado' : estado,
			'cidade' : cidade,
			'ativo' : ativo
		};
		var funcionario = JSON.stringify(funcNovo);
		$.ajax({
			type : 'POST',
			url : '/DigitalOS/rest/RestFuncionario/addFuncionario',
			data : funcionario,
			success : function(resposta) {
				alert(resposta);
				$("#modalfuncionario").find('form')[0].reset();
				$("#modalfuncionario").modal('hide');
				buscarFuncionario();
			},
			error : function() {
				alert("Erro ao cadastrar nova pessoa.");
			}
		});
	}
	function buscarFuncionario() {
		var valorBusca = $('#buscaFuncionario').val();
		$.ajax({
			type : 'POST',
			url : '/DigitalOS/rest/RestFuncionario/buscarFuncionarioPorNome',
			data : valorBusca,
			success : function(listaFuncionariosAchadas) {
				tabelaFuncionario(listaFuncionariosAchadas);
			},
			error : function(listaFuncionariosAchadas) {
				alert("Erro ao procurar funcionario")
				tabelaFuncionario(listaFuncionariosAchadas);
			}
		});
	}
	function tabelaFuncionario(listaFuncionariosAchadas) {
		var html = "<div class='teble-reponsive'>"
				+ "<table class='table table-striped table-condensed table-bordered'>";
		html += "<tr>"
				+ "<th># ID</th> <th>Nome</th> <th>CPF</th> <th>Cargo</th> <th>Setor</th> <th>Salario</th> <th>Sexo</th> <th>ID Pessoa</th> <th>Ativo</th> <th>Edição</th>"
			 + "</tr>"
		for (var i = 0; i < listaFuncionariosAchadas.length; i++) {
			html += "<tr>" 
					+ "<td>"+ listaFuncionariosAchadas[i].idfuncionario + "</td>"
					+ "<td>" + listaFuncionariosAchadas[i].nome + "</td>"
					+ "<td>" + listaFuncionariosAchadas[i].cpf + "</td>"
					+ "<td>" + listaFuncionariosAchadas[i].cargo + "</td>"
					+ "<td>" + listaFuncionariosAchadas[i].setor + "</td>"
					+ "<td>" + listaFuncionariosAchadas[i].salario + "</td>"
					+ "<td>" + listaFuncionariosAchadas[i].sexo + "</td>"
					+ "<td>" + listaFuncionariosAchadas[i].id + "</td>"
					+ "<td>" + listaFuncionariosAchadas[i].ativo + "</td>"
					+ "<td>" + "<div class='btn glyphicon glyphicon-pencil' onclick='exibirEdicaoFuncionario("+ listaFuncionariosAchadas[i].idfuncionario + ")'></div>" + "</td>" 
				+ "</tr>"
		}
		html += "</table>" + "</div>"
		$("#resultadoFuncionario").html(html);
	}
	function exibirEdicaoFuncionario(idfuncionario) {
		$.ajax({type : 'POST',
				url : '/DigitalOS/rest/RestFuncionario/buscarFuncionarioPeloId/' + idfuncionario,
				success : function(funcionario) {
					console.log(funcionario);
					$("#EditIdFuncionario").val(funcionario.id);
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
				error : function(pessoa) {
					alert("Erro ao editar Funcionario!");
				}
			});
	}
	function atualizarFuncionario() {
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
	
		var funcionarioEdit = {
			'id' : id,
			'nome' : nome,
			'cpf' : cpf,
			'rg' : rg,
			'datanascimento' : datanascimento,
			'sexo' : sexo,
			'telefone' : telefone,
			'celular' : celular,
			'email' : email,
			'tipomorada' : tipomorada,
			'ativo' : statusfuncionario,
			'cep' : cep,
			'numero' : numero,
			'endereco' : endereco,
			'bairro' : bairro,
			'cidade' : cidade,
			'estado' : estado,
			'numeroct' : numeroct,
			'numeropis' : numeropis,
			'cargo' : cargo,
			'setor' : setor,
			'salario' : salario,
			'dataadmissao' : dataadmissao,
			'datademissao' : datademissao,
			'idendereco' : idendereco,
			'idfuncionario' : funcionarioidfuncionario
		};
		var funcionario = JSON.stringify(funcionarioEdit);
		$.ajax({
			type : 'POST',
			url : '/DigitalOS/rest/RestFuncionario/editarFuncionario',
			data : funcionario,
			success : function(resposta) {
				$("#msgEditFuncionario").modal('hide');
				buscarFuncionario();
			},
			error : function() {
				alert("Erro ao editar funcionario.");
			}
		});
	}
	function filtroFuncionariosAtivos() {
	$.ajax({
		type : 'POST',
		url : '/DigitalOS/rest/RestFuncionario/filtroFuncionarioAtivo',
		data : $("#filtroAtivoOpc").val(),
		success : function(listaFuncionariosAchados) {
			tabelaFuncionario(listaFuncionariosAchados);
		},
		error : function() {
			alert("Erro ao Filtrar Servicos");
		}
	});
}
/* FIM CRUD FUNCIONARIO */

/* INICIO CRUD SERVICO */
	function inserirServico() {
		var tiposervico = $("#tiposervico").val();
		var nomeservico = $("#nomeservico").val();
		var descricaoservico = $("#descricaoservico").val();
		var ativo = $("#statusservico").val();
	
		if (tiposervico == "" || nomeservico == "" || descricaoservico == "") {
			alert("Preencha todos os campos do formulário!");
			return false;
		} else {
			var pessoaNova = {
				'tiposervico' : tiposervico,
				'nomeservico' : nomeservico,
				'descricaoservico' : descricaoservico,
				'ativo' : ativo
			};
			var pessoa = JSON.stringify(pessoaNova);
			$.ajax({
				type : 'POST',
				url : '/DigitalOS/rest/RestServico/addServico',
				data : pessoa,
				success : function(resposta) {
					alert(resposta);
					$("#tiposervico").val('');
					$("#nomeservico").val('');
					$("#descricaoservico").val('');
					$("#modalservico").modal('hide');;
					buscarServico();
				},
				error : function() {
					alert("Erro ao cadastrar nova pessoa.");
					$("#tiposervico").val('');
					$("#nomeservico").val('');
					$("#descricaoservico").val('');
					$("#modalservico").modal('hide');
					buscarServico();
				}
			});
		}
	}
	function buscarServico() {
		var valorBusca = $('#buscarservico').val();
		$.ajax({
			type : 'POST',
			url : '/DigitalOS/rest/RestServico/buscarServicoPorNome',
			data : valorBusca,
			success : function(listaServicoAchados) {
				tabelaServico(listaServicoAchados);
			},
			error : function(listaServicoAchados) {
				tabelaServico(listaServicoAchados);
			}
		});
	}
	function tabelaServico(listaServicoAchados) {
		var html = "<div class='teble-reponsive'>"
				+ "<table class='table table-striped table-condensed table-bordered'>";
		html += "<tr>"
				+ "<th># ID</th> <th>Tipo Servico</th> <th>Nome Servico</th> <th>Descricao</th> <th>Ativo</th> <th>Edição</th>"
				+ "</tr>"
		for (var i = 0; i < listaServicoAchados.length; i++) {
			html += "<tr>"
					+ "<td>"+ listaServicoAchados[i].idservico+ "</td>"
					+ "<td>"+ listaServicoAchados[i].tiposervico+ "</td>"
					+ "<td>"+ listaServicoAchados[i].nomeservico+ "</td>"
					+ "<td>"+ listaServicoAchados[i].descricaoservico+ "</td>"
					+ "<td id='td'>"+ listaServicoAchados[i].ativo+ "</td>"
					+ "<td>"+ "<div class='btn glyphicon glyphicon-pencil' onclick='exibirEdicaoServico("+ listaServicoAchados[i].idservico + ")'></div>" + "</td>"
				 + "</tr>"
		}
		html += "</table>" + "</div>"
		$("#resultadoServico").html(html);
	}
	function exibirEdicaoServico(idservico) {
		$.ajax({
			type : 'POST',
			url : '/DigitalOS/rest/RestServico/buscarServicoPeloId/' + idservico,
			success : function(servico) {
				$("#EditIdServico").val(idservico);
				$("#EditNomeTipoServico").val(servico.tiposervico);
				$("#EditNomeServico").val(servico.nomeservico);
				$("#EditDescricaoServico").val(servico.descricaoservico);
				$("#EditStatusServico").val(servico.ativo);
				$("#msgEditServico").modal(servico);
			},
			error : function(servico) {
				alert("Erro ao editar Servico!");
			}
		});
	}
	function editarServico() {
		var idservico = $("#EditIdServico").val();
		var tiposervico = $("#EditNomeTipoServico").val();
		var nomeservico = $("#EditNomeServico").val();
		var descricaoservico = $("#EditDescricaoServico").val();
		var ativo = $("#EditStatusServico").val();
	
		var servicoEdit = {
			'idservico' : idservico,
			'tiposervico' : tiposervico,
			'nomeservico' : nomeservico,
			'descricaoservico' : descricaoservico,
			'ativo' : ativo
		};
		var servico = JSON.stringify(servicoEdit);
	
		$.ajax({
			type : 'POST',
			url : '/DigitalOS/rest/RestServico/editarServico',
			data : servico,
			success : function(resposta) {
				alert(resposta);
				$('#msgEditServico').modal('hide');
				buscarServico();
			},
			error : function() {
				alert("Erro ao Atualizar Servico");
			}
		});
	}
	function filtroServico() {
		$.ajax({
			type : 'POST',
			url : '/DigitalOS/rest/RestServico/filtroServicoAtivo',
			data : $("#filtroServicoAtivo").val(),
			success : function(listaServicoAchados) {
				tabelaServico(listaServicoAchados);
			},
			error : function() {
				alert("Erro ao Filtrar Servicos");
			}
		});
	}
	function fecharModalServico(){
		$('#modalservico').modal('hide');
		$('body').removeClass('modal-open');
		$('.modal-backdrop').remove();
	}
/* FIM CRUD SERVICO */

/* INICIO CRUD CATERGORIA APARELHO */
	function addCategoriaAparelho() {
		var nome = $("#nomecategoria").val();
		var ativo = $("#statuscategoria").val();
		var categoriaNova = {
			'nome' : nome,
			'ativo' : ativo
		};
		var categoria = JSON.stringify(categoriaNova);
		$.ajax({
			type : 'POST',
			url : '/DigitalOS/rest/RestCategoriaAparelho/addCategoriaAparelho',
			data : categoria,
			success : function(resposta) {
				alert("Categoria cadastrada com sucesso!");
				$("#nomecategoria").val('');
				fechaModalCategoriaAparelho();
				buscarCatergoria();
			},
			error : function() {
				alert("Erro ao salvar categoria de aparelho");
				$("#nomecategoria").val('');
				fechaModalCategoriaAparelho();
				buscarCatergoria();
			}
		});
	}
	function buscarCatergoria() {
		var valorBusca = $('#buscaCategoria').val();
		$
				.ajax({
					type : 'POST',
					url : '/DigitalOS/rest/RestCategoriaAparelho/buscarCategoriaAparelhoPorNome',
					data : valorBusca,
					success : function(listaCategoriaAchados) {
						tabelaCategoria(listaCategoriaAchados);
					},
					error : function(listaCategoriaAchados) {
						tabelaCategoria();
					}
				});
	}
	function tabelaCategoria(listaCategoriaAchados) {
		var html = "<div class='teble-reponsive'>"
				+ "<table class='table table-striped table-condensed table-bordered'>";
		html += "<tr>"
				+ "<th># ID</th> <th>Nome Categoria</th> <th>Ativo</th> <th>Edição</th>"
				+ "</tr>"
		for (var i = 0; i < listaCategoriaAchados.length; i++) {
			html += "<tr>"
					+ "<td>"
					+ listaCategoriaAchados[i].id
					+ "</td>"
					+ "<td>"
					+ listaCategoriaAchados[i].nome
					+ "</td>"
					+ "<td id='td'>"
					+ listaCategoriaAchados[i].ativo
					+ "</td>"
					+ "<td>"
					+ "<div class='btn glyphicon glyphicon-pencil' onclick='exibirEdicaoCategoria("
					+ listaCategoriaAchados[i].id + ")'></div>" + "</td>" + "</tr>"
		}
		html += "</table>" + "</div>"
		$("#resultadoCategoria").html(html);
	}
	function exibirEdicaoCategoria(id) {
		$.ajax({
			type : 'POST',
			url : '/DigitalOS/rest/RestCategoriaAparelho/buscarCategoriaAparelhoPeloId/'
					+ id,
			success : function(categoria) {
				$("#EditIdCategoria").val(categoria.id);
				$("#EditNomeCategoria").val(categoria.nome);
				$("#EditStatusCategoria").val(categoria.ativo);
				$("#msgEditCategoria").modal(categoria);
			},
			error : function(servico) {
				alert("Erro ao editar Categoria!");
			}
		});
	}
	function editarCategoria() {
		var id = $("#EditIdCategoria").val();
		var nome = $("#EditNomeCategoria").val();
		var ativo = $("#EditStatusCategoria").val();
	
		var categoriaEdit = {
			'id' : id,
			'nome' : nome,
			'ativo' : ativo
		};
		var categoria = JSON.stringify(categoriaEdit);
	
		$.ajax({
			type : 'POST',
			url : '/DigitalOS/rest/RestCategoriaAparelho/editarCategoriaAparelho',
			data : categoria,
			success : function(resposta) {
				alert(resposta);
				$("#EditNomeCategoria").val('');
				$('#msgEditCategoria').modal('hide');
				buscarCatergoria();
			},
			error : function() {
				alert("Erro ao Atualizar Servico");
			}
		});
	}
	function filtroCategoria() {
	$
			.ajax({
				type : 'POST',
				url : '/DigitalOS/rest/RestCategoriaAparelho/filtroCategoriaAparelhoAtivo',
				data : $("#filtroAtivoOpc").val(),
				success : function(listaCategoriaAchados) {
					tabelaCategoria(listaCategoriaAchados);
				},
				error : function() {
					alert("Erro ao Filtrar Categorias");
				}
			});
	}
	function fechaModalCategoriaAparelho(){
		$('#modalcategoriaaparelho').modal('hide');
		$('body').removeClass('modal-open');
		$('.modal-backdrop').remove();
	}
/* FIM CRUD CATEGORIA APARELHO */

/* INICIO CRUD MARCA APARELHO */
	function addMarca() {
		var nome = $("#nomemarca").val();
		var ativo = $("#statusmarca").val();
		var marcaNova = {
			'nome' : nome,
			'ativo' : ativo
		};
		var marca = JSON.stringify(marcaNova);
		$.ajax({
			type : 'POST',
			url : '/DigitalOS/rest/RestMarca/addMarca',
			data : marca,
			success : function(resposta) {
				$("#modalmarca").modal('hide');
				$("#nomemarca").val('');
				buscarMarca();
			},
			error : function() {
				alert("Erro ao salvar marca de aparelho");
				$("#modalmarca").modal('hide');
				$("#nomemarca").val('');
				buscarMarca();
			}
		});
	}
	function buscarMarca() {
		var valorBusca = $('#buscaMarca').val();
		$.ajax({
			type : 'POST',
			url : '/DigitalOS/rest/RestMarca/buscarMarcaPorNome',
			data : valorBusca,
			success : function(listaMarcaAchados) {
				tabelaMarca(listaMarcaAchados);
			},
			error : function(listaMarcaAchados) {
				tabelaMarca(listaMarcaAchados);
			}
		});
	}
	function tabelaMarca(listaMarcaAchados) {
		var html = "<div class='teble-reponsive'>"
				+ "<table class='table table-striped table-condensed table-bordered'>";
		html += "<tr>"
				+ "<th># ID</th> <th>Nome Marca</th> <th>Ativo</th> <th>Edição</th>"
				+ "</tr>"
		for (var i = 0; i < listaMarcaAchados.length; i++) {
			html += "<tr>"
					+ "<td>"
					+ listaMarcaAchados[i].id
					+ "</td>"
					+ "<td>"
					+ listaMarcaAchados[i].nome
					+ "</td>"
					+ "<td id='td'>"
					+ listaMarcaAchados[i].ativo
					+ "</td>"
					+ "<td>"
					+ "<div class='btn glyphicon glyphicon-pencil' onclick='exibirEdicaoMarca("
					+ listaMarcaAchados[i].id + ")'></div>" + "</td>" + "</tr>"
		}
		html += "</table>" + "</div>"
		$("#resultadoMarca").html(html);
	}
	function exibirEdicaoMarca(id) {
		$.ajax({
			type : 'POST',
			url : '/DigitalOS/rest/RestMarca/buscarMarcaPeloId/' + id,
			success : function(marca) {
				$("#EditIdMarca").val(id);
				$("#EditNomeMarca").val(marca.nome);
				$("#EditStatusMarca").val(marca.ativo);
				$("#msgEditMarca").modal(marca);
			},
			error : function(servico) {
				alert("Erro ao editar marca!");
			}
		});
	}
	function editarMarca() {
		var id = $("#EditIdMarca").val();
		var nome = $("#EditNomeMarca").val();
		var ativo = $("#EditStatusMarca").val();
		var marca = JSON.stringify({
			'id' : id,
			'nome' : nome,
			'ativo' : ativo
		});
		$.ajax({
			type : 'POST',
			url : '/DigitalOS/rest/RestMarca/editarMarca',
			data : marca,
			success : function(resposta) {
				$('#msgEditMarca').modal('hide');
				buscarMarca();
			},
			error : function() {
				alert("Erro ao atualizar marca!");
			}
		});
	}
	function filtroMarca() {
	$.ajax({
		type : 'POST',
		url : '/DigitalOS/rest/RestMarca/filtroMarcaAtivo',
		data : $("#filtroAtivoOpc").val(),
		success : function(listaMarcaAchados) {
			tabelaMarca(listaMarcaAchados);
		},
		error : function() {
			alert("Erro ao filtrar marca!");
		}
	});
}
/* FIM CRUD MARCA */

/* INICIO FECHA MODAL */
	function fechaModal() {
		$('#ModalPainelUsuario').modal('hide');
		$('body').removeClass('modal-open');
		$('.modal-backdrop').remove();
	}
	function clienteFechaModalOS() {
		$('#modalselecionacliente').modal('hide');
		insereClasse('#modalselecionacliente', '#modalordemservico');
	}
	function aparelhoFechaModalOS() {
		$('#modalselecionaAparelho').modal('hide');
		insereClasse('#modalselecionaAparelho', '#modalordemservico');
	}
	function fechaModalOS(){
		$('#modalordemservico').modal('hide');
	}
/* FIM FECHA MODAL */

/* INICIO INSERE CLASSE MODAL-OPEN*/
	function insereClasse(modalatual, modalbody){
		$(modalatual).on('hidden.bs.modal', function(){
			var visivel = $(modalbody+':visible');
			if(visivel){
				if(!$("body").hasClass('modal-open')){
					$("body").addClass('modal-open')
				}
			}
		});
	}
/* FIM INSERE CLASSE*/
	
/* INICIO ORDEM DE SERVICO */
	/* INICIO BSUCAR CLIENTES */
		function buscarClienteOS() {
			document.getElementById('modalselecionacliente').style.display = "block";
			var nome = $("#buscarClienteS").val();
			$.ajax({
				type : 'POST',
				url : '/DigitalOS/rest/RestPessoaOs/buscarPessoaOs',
				data : nome,
				success : function(resposta) {
					tabelaPessoaOS(resposta);
					$('#modalselecionacliente').modal('show');
				},
				error : function(resposta) {
					alert("Erro ao localizar clientes");
				}
			});
		}
		function Cliente(id, nome, cpf, rg, endereco, telefone, celular) {
			this. id = id;
			this.nome = nome;
			this.cpf = cpf;
			this.rg = rg;
			this.endereco = endereco;
			this.telefone = telefone;
			this.celular = celular;
		}
		clientes = [];
		function tabelaPessoaOS(resposta) {
			var html = "<div class='table-reponsive'>"
					+ "<table class='table table-striped table-condensed table-bordered'>";
			html += "<tr>"
					+ "<th>Seleção</th> <th>ID</th> <th>Nome</th> <th>CPF</th> <th>RG</th> <th>Endereco</th> <th>Telefone</th> <th>Celular</th>"
					+ "</tr>"
		
			for (var i = 0; i < resposta.length; i++) {
				clientes[i] = new Cliente(resposta[i].id, resposta[i].nome, resposta[i].cpf, resposta[i].rg, resposta[i].endereco, resposta[i].telefone, resposta[i].celular);
				html += "<tr>"
							+ "<td>" + "<input type='radio' id='radio' name='capiroto'>"+"</td>" 
							+ "<td>" + resposta[i].id + "</td>" 
							+ "<td>" + resposta[i].nome + "</td>" 
							+ "<td>" + resposta[i].cpf + "</td>" 
							+ "<td>" + resposta[i].rg + "</td>" 
							+ "<td>" + resposta[i].endereco + "</td>" 
							+ "<td>" + resposta[i].telefone + "</td>"
							+ "<td>" + resposta[i].celular + "</td>"
						+ "</tr>"
			}
			html += "</table>" + "</div>"
			$("#tabelaPessoas").html(html);
		}
		function carregaPessoaOS() {
			var radioButtons = $("#tabelaPessoas input:radio[name='capiroto']");
			var selectedIndex = radioButtons.index(radioButtons.filter(':checked'));
			cliente = clientes[selectedIndex];
			for (let name in cliente) {
				$("#"+name).val(cliente[name]);
			}
			clienteFechaModalOS();
		}
	/* FIM BUSCAR CLIENTES */
	
	/* INICIO BUSCAR APARELHOS */
		function buscarAparelhoOS() {
			document.getElementById('modalselecionaAparelho').style.display = "block";
			var nome = $("#buscarAparelho").val();
			$.ajax({
				type : 'POST',
				url : '/DigitalOS/rest/RestSelect/buscarAparelhoOs',
				data : nome,
				success : function(resposta) {
					tabelaAparelhoOS(resposta);
					$('#modalselecionaAparelho').modal('show');
				},
				error : function(resposta) {
					alert("Erro ao localizar aparelhos");
				}
			});
		}
		function AparelhoBuilder(){
			return{
				build : function(resposta){
							let aparelhos=[];
							let respostaArray = JSON.parse(resposta);
							
							for(let i = 0; i <= respostaArray.length; i++){
								if (respostaArray[i] == undefined || respostaArray[i] == null){
									continue;
								}
								aparelhos[i] = new Aparelho(respostaArray[i]);
							}
							return aparelhos;
				}
			}
		}
		function Aparelho({idaparelho, nomeCategoria, nomeMarca, modelo, nsaparelho}) {
			return {
				idaparelho : idaparelho,
				nomeCategoria : nomeCategoria,
				nomeMarca : nomeMarca,
				modelo : modelo,
				nsaparelho : nsaparelho
			};
		}
		aparelhos = [];
		function tabelaAparelhoOS(resposta) {
			var html = "<div class='table-reponsive'> " +
					   "<table class='table table-striped table-condensed table-bordered'>";
			html += "<tr>"
					+ "<th>Seleção</th> <th>ID</th> <th>Modelo</th> <th>Categoria</th> <th>Marca</th> <th>Número Série</th>"
				  + "</tr>"
			aparelhos = AparelhoBuilder().build(resposta);
			for (var i = 0; i < aparelhos.length; i++) {
				let aparelho = aparelhos[i];
				html += "<tr>"
							+ "<td>" + "<input type='checkbox' id='aparelho' name='aparelhos'>"+"</td>" 
							+ "<td>" + aparelho.idaparelho + "</td>" 
							+ "<td>" + aparelho.modelo + "</td>" 
							+ "<td>" + aparelho.nomeCategoria + "</td>" 
							+ "<td>" + aparelho.nomeMarca + "</td>" 
							+ "<td>" + aparelho.nsaparelho + "</td>"  
					 + "</tr>"
			}
			html += "</table>" + "</div>"
			$("#tabelaAparelhos").html(html);
		}
		function carregaAparelhoOS() {
			var inputs = $("#tabelaAparelhos input:checkbox[name='aparelhos']");
			var selectedIndex = inputs.index(inputs.filter(':checked'));
			aparelho = aparelhos[selectedIndex];
			for ( let name in aparelho) {
				$("#"+name).val(aparelho[name]);
			}
			aparelhoFechaModalOS();
		}
	/* FIM BUSCA APARELHOS */
	
	/* INICIO OS */
		function carregarSelect() {
			$.ajax({
				type : 'POST',
				url : '/DigitalOS/rest/RestSelect/buscarSelectCategoria',
				success : function(lista) {
					listosa = JSON.parse(lista);
					$.each(listosa, function(i, v) {
						$('#categoria').append(
								$('<option>').text(v.nome).attr('value', v.id));
					});
				},
				error : function() {
					alert("Erro ao encontrar categoria");
				}
			});
		
			$.ajax({
				type : 'POST',
				url : '/DigitalOS/rest/RestSelect/buscarSelectMarca',
				success : function(lista) {
					listosa = JSON.parse(lista);
					$.each(listosa, function(i, v) {
						$('#marca').append(
								$('<option>').text(v.nome).attr('value', v.id));
					});
				},
				error : function() {
					alert("Erro ao encontrar marca");
				}
			});
		
			$.ajax({
				type : 'POST',
				url : '/DigitalOS/rest/RestSelect/buscarSelectTipoServico',
				success : function(lista) {
					listosa = JSON.parse(lista);
					$.each(listosa, function(i, v) {
						$('#tiposervico').append(
								$('<option>').text(v.tiposervico).attr('value', v.idservico));
					});
				},
				error : function() {
					alert("Erro ao encontrar marca");
				}
			});
		}
		function numeroOS() {
			$.ajax({
				type : 'POST',
				url : '/DigitalOS/rest/RestSelect/buscarSelectNumeroOS',
				success : function(numero) {
					id = (JSON.parse(numero));
		
					numeroOS = id.id + 1;
					$("#idos").val(numeroOS);
				},
				error : function() {
					alert("Erro ao gerar numero da OS");
				}
			});
		}
		function abrirOS() {
			var os = {'numeroos':$("#idos").val(), 'obsproblema':$("#descricaoservico").val(),'statusos':$("#statusos").val(),
					'abertura':$("#dataabertura").val(), 'prazo':$("#dataprazo").val(),
					'pessoa_id':$("#id").val(), 'aparelho_id':$("#idaparelho").val(), 'servicos_id':$("#tiposervico").val()};
			var ordemservico = JSON.stringify(os);
			$.ajax({
				type : 'POST',
				url : '/DigitalOS/rest/RestOrdemServico/addOrdemServico',
				data: ordemservico,
				success : function(resposta) {
					alert(resposta);
					buscarOS();
					fechaModalOS();
					limpaModal();
				},
				error : function(resposta) {
					alert(resposta);
				}
			});
		}
		function limpaModal(){
			$("#idos").val('');
			$("#nome").val('');
			$("#cpf").val('');
			$("#rg").val('');
			$("#endereco").val('');
			$("#telefone").val('');
			$("#celular").val('');
			$("#nomeCategoria").val('');
			$("#nomeMarca").val('');
			$("#modelo").val('');
			$("#nsaparelho").val('');
			$("#descricaoservico").val('');
			$("#statusos").val('');
			$("#dataabertura").val('');
			$("#dataprazo").val('');
			$("#id").val('');
			$("#idaparelho").val('');
			$("#tiposervico").val('');
		}
		function buscarOS(){
			var num;
			if($("#buscaOS").val() == "" || $("#buscaOS").val() == undefined){
				num = 0;
			}else{
				num = $("#buscaOS").val();
			}
			$.ajax({
				type:'POST',
				url:'/DigitalOS/rest/RestOrdemServico/buscarOS/'+ num,
				success:function(resposta){
					tabelaOS(resposta);
				},
				error: function(){
					alert("Erro ao localizar as ordens de servico");
					tabelaOS();
				}
			});
		}
		function tabelaOS(resposta) {
			var html = "<div class='teble-reponsive'>"
						+ "<table class='table table-striped table-condensed table-bordered'>";
					html += "<tr>"
						  	+ "<th>N° OS</th> <th>Cliente</th> <th>Aberta em</th> <th>Prazo até </th> <th>Fechada em</th> <th>Status OS</th> <th>Total OS</th> <th>Edição</th>"
						  + "</tr>"
						  if(resposta!= undefined || resposta != null ){
					for (var i = 0; i < resposta.length; i++) {
						html += "<tr>"
									+ "<td>"+ resposta[i].numeroos+ "</td>"
									+ "<td>"+ resposta[i].nome+ "</td>"
									+ "<td>"+ resposta[i].abertura+ "</td>"
									+ "<td>"+ resposta[i].prazo+ "</td>"
									+ "<td>"+ resposta[i].fechamento+ "</td>"
									+ "<td>"+ resposta[i].statusos+ "</td>"
									+ "<td>"+ resposta[i].total+ "</td>"
									+ "<td>"+ "<div class='btn glyphicon glyphicon-pencil' onclick='exibirEdicaoOS("+ resposta[i].numeroos + ")'></div>" 
									+ "</td>" 
								+"</tr>"
					}
						  }
				 html += "</table>" 
				    + "</div>"
			$("#resultadoOS").html(html);
		}
		function exibirEdicaoOS(numeroos){
			$.ajax({
				type : 'POST',
				url : '/DigitalOS/rest/RestOrdemServico/buscarOSPeloId/' + numeroos,
				success : function(os) {
					$("#idosEdit").val(os.numeroos);
					$("#nomeEdit").val(os.nome);
					$("#cpfEdit").val(os.cpf);
					$("#rgEdit").val(os.rg);
					$("#enderecoEdit").val(os.rua);
					$("#telefoneEdit").val(os.telefone);
					$("#celularEdit").val(os.celular);
					$("#statusosEdit").val(os.statusos);
					$("#categoriaEdit").val(os.categoria);
					$("#marcaEdit").val(os.marca);
					$("#modeloEdit").val(os.modelo);
					$("#nsaparelhoEdit").val(os.numeroserie);
					$("#tiposervicoEdit").val(os.tiposervico);
					$("#dataaberturaEdit").val(os.abertura);
					$("#dataprazoEdit").val(os.prazo);
					$("#datafechamentoEdit").val(os.fechamento);
					$("#totalEdit").val(os.total);
					$("#obsproblemaEdit").val(os.obsproblema);
					$("#obssolucaoEdit").val(os.obssolucao);
					$("#msgEditOS").modal(os);
		
					},
				error : function() {
					alert("Erro ao editar marca!");
				}
			});
		}
		function editarOS(){
			var os = {'numeroos':$("#idosEdit").val(), 'obssolucao':$("#obssolucaoEdit").val(), 'prazo':$("#dataprazoEdit").val(),'statusos':$("#statusosEdit").val(),
					  'total':$("#totalEdit").val(), 'fechamento':$("#datafechamentoEdit").val()};
			var ordem = JSON.stringify(os);
			$.ajax({
				type: 'POST',
				url: '/DigitalOS/rest/RestOrdemServico/editarOS',
				data: ordem,
				success: function(resposta){
					alert(resposta);
					fechaModalEditarOS();
					buscarOS();
				},
				error: function(){
					alert("Erro ao editar OS");
				}
			});
		}
		function fechaModalEditarOS(){
			$('#msgEditOS').modal('hide');
		}
		function filtroOSstatus(){
			var status = $("#filtroOSstatus").val();
			$.ajax({
				type: 'POST',
				url: '/DigitalOS/rest/RestOrdemServico/filtroOsStatus',
				data: status,
				success: function(resposta){
					tabelaOS(resposta);
				},
				error: function(){
					alert("Erro ao editar OS");
				}
			});
		}
		function filtrarOSAtivo(){
			var ativo = $("#filtrarOSAtivo").val();
			$.ajax({
				type: 'POST',
				url: '/DigitalOS/rest/RestOrdemServico/filtroOsAtivo',
				data: ativo,
				success: function(resposta){
					tabelaOS(resposta);
				},
				error: function(){
					alert("Erro ao editar OS");
				}
			});
		}
	/* FIM OS */
/* FIM ORDEM SE SERVIÇO */