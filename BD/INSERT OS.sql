insert into endereco (estado, cidade, bairro, rua, numero, cep) values('SC', 'Joinville', 'Petropolis', 'Manoel de Souza', 100, 89208782);
insert into funcionario (numerocarteiratrabalho, pis, cargo, setor, salario, dataadmissao) values('12345678', '12345678910', 'Suporte', 'Atedimento', 2500.00, '2019-11-12');
insert into pessoa (nome, cpf, rg, datanascimento, sexo, telefone, celular, email, profissao, tipomorada, ativo, funcionario_idfuncionario, endereco_idendereco) values ('Leonardo Paterno', '09612435979', '6655574', '2019-11-12', 'M', '47 3466 4444', '47 9 99455951', 'paternoleo@gmail.com', 'Suporte', 'casa', 'S', 1, 1);
insert into login (email, senha, pessoa_idpessoa, perfil_idperfil, ordemservico_numeroos) values('leo', '123', 1, 1, 0);