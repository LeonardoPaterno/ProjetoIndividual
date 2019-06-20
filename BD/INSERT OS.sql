insert into endereco (estado, cidade, bairro, rua, numero, cep) values('SC', 'Joinville', 'Petropolis', 'Manoel de Souza', 100, 89208782);
insert into marca (nomemarca, ativo) values('Motorola', 'S');
insert into categoriaaparelho (nome) values('Smartphone');
insert into aparelho (nome, numeroserie, modelo, ativo, marca_id, categoriaaparelho_id) values('Moto E', 'XT589', 'Moto E 5 Plus', 'S', 1, 1);
insert into servicos (tiposervico, nomeservico, descricao, ativo) values('Manutencao', 'Troca de tela', 'Desmontagem e troca da tela, remontagem e teste do aparelho.', 'S');
insert into orcamento (total) values(526.85);
insert into funcionario (cargo, setor, salario, dataadmissao, numeroct, pis, datademissao) values('Suporte', 'Atedimento', 2500.00, '2019-05-02', '58635964', '14529687369', '2019-12-20');
insert into pessoa (nome, cpf, rg, datanascimento, sexo, telefone, celular, email, profissao, tipomorada, tipopessoa, ativo, funcionario_id, endereco_id) values ('Leonardo Paterno', '09612435979', '6655574', '2019-11-12', 'M', '47 3466 4444', '47 9 99455951', 'paternoleo@gmail.com', 'Suporte', 'casa', 'PF', 'S', 1, 1);
insert into ordemservico (observacoes, statusos, login_id, ativo, aparelho_id, servicos_id, orcamento_numero ) values('teste de insert', 'A', 1, 'S', 1, 1, 1);
insert into login (email, senha, pessoa_id, ordemservico_numeroos) values('leo', '123', 1, (select max(numeroos) from ordemservico));




