insert into endereco (estado, cidade, bairro, rua, numero, cep) values('SC', 'Joinville', 'Petropolis', 'Manoel de Souza', 100, 89208782);
insert into endereco (estado, cidade, bairro, rua, numero, cep) values('PR', 'Curitiba', 'Guarujá', 'Afonso Padilha', 1508, 87408569);
insert into marca (nome, ativo) values('Motorola', 'S');
insert into marca (nome, ativo) values('Samsung', 'S');
insert into marca (nome, ativo) values('Nokia', 'N');
insert into categoriaaparelho (nome, ativo) values('Smartphone', 'S');
insert into categoriaaparelho (nome, ativo) values('Televisor', 'S');
insert into categoriaaparelho (nome, ativo) values('Radio', 'N');
insert into aparelho (nome, numeroserie, modelo, ativo, marca_id, categoriaaparelho_id) values('Moto E', 'XT589', 'Moto E 5 Plus', 'S', 1, 1);
insert into aparelho (nome, numeroserie, modelo, ativo, marca_id, categoriaaparelho_id) values('TV Samsung', '85964756', 'LED 4k SM589', 'N', 2, 2);
insert into servicos (tiposervico, nome, descricao, ativo) values('Manutencao', 'Troca de tela', 'Desmontagem e troca da tela, remontagem e teste do aparelho.', 'S');
insert into servicos (tiposervico, nome, descricao, ativo) values('Limpeza', 'Limpeza do aparelho', 'Desmontagem e limpeza de componentes, remontagem e teste do aparelho.', 'N');
insert into funcionario (cargo, setor, salario, dataadmissao, numeroct, pis, datademissao) values('Suporte', 'Atedimento', 2500.00, '2019-05-02', '58635964', '14529687369', '2019-12-20');
insert into pessoa (nome, cpf, rg, datanascimento, sexo, telefone, celular, email, profissao, tipomorada, tipopessoa, ativo, funcionario_id, endereco_id) values ('Leonardo Paterno', '09612435979', '6655574', '2019-11-12', 'M', '47 3466 4444', '47 9 99455951', 'leo', 'Suporte', 'casa', 'PF', 'S', 1, 1);
insert into pessoa (nome, cpf, rg, datanascimento, sexo, telefone, celular, email, profissao, tipomorada, tipopessoa, ativo, endereco_id) values ('Paterno Leo', '09612435979', '6655574', '2019-11-12', 'M', '47 3466 4444', '47 9 99455951', 'paterno@gmail.com', 'Suporte', 'casa', 'PF', 'S', 1);
insert into ordemservico (numeroos, descproblema, descsolucao, dataabertura, dataprazo, datafechamento, statusos, pessoa_id, ativo, aparelho_id, servicos_id, total) values(1,'teste de insert', '', '2019-08-21', '2019-08-27', null, 'AB', 1, 'S', 1, 1, 150);
insert into ordemservico (numeroos, descproblema, descsolucao, dataabertura, dataprazo, datafechamento, statusos, pessoa_id, ativo, aparelho_id, servicos_id, total ) values(2,'teste de insert', '', '2019-08-21', '2019-08-27', null, 'AB', 1, 'S', 1, 1, null);
insert into login (email, senha, pessoa_id, ordemservico_id) values('leo', '123', 1, (select max(id) from ordemservico));
insert into login (email, senha, pessoa_id, ordemservico_id) values('paternoleo@gmail.com', '123', 2, (select max(id) from ordemservico));




