package br.com.digitalOS.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.digitalOS.jdbcInterface.DigitalOSInterface;
import br.com.digitalOS.objetos.AparelhoObj;
import br.com.digitalOS.objetos.CategoriaAparelhoObj;
import br.com.digitalOS.objetos.FuncionarioObj;
import br.com.digitalOS.objetos.GraficoOS;
import br.com.digitalOS.objetos.LoginObj;
import br.com.digitalOS.objetos.MarcaObj;
import br.com.digitalOS.objetos.OrdemServicoObj;
import br.com.digitalOS.objetos.PessoaObj;
import br.com.digitalOS.objetos.PessoaOsObj;
import br.com.digitalOS.objetos.SelectObj;
import br.com.digitalOS.objetos.ServicoObj;

public class JDBCDigitalOSLoginDAO implements DigitalOSInterface {

	private Connection conexao;

	public JDBCDigitalOSLoginDAO(Connection conexao) {
		this.conexao = conexao;
	}
	
	/*INICIO LOGIN*/
	public boolean consultarLogin(LoginObj login) {
		String comando = "SELECT login.id, login.email, login.senha from login where login.email = '"+login.getEmail()+"' and senha = '"+login.getSenha()+"';";
		try {
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			while (rs.next()) {
				int id = rs.getInt("login.id");
				String email = rs.getString("login.email");
				String senha = rs.getString("login.senha");
				
				login.setId(id);
				login.setEmail(email);
				login.setSenha(senha);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		if(login.getId() != 0){
			return true;
		}else{
			return false;
		}
	}
	/*FIM LOGIN*/
	
	/*INICIO APARELHO*/
	@Override
	public boolean cadastrarAparelho(AparelhoObj novoAparelho) {
		String comando = "INSERT INTO aparelho (nome, numeroserie, modelo, ativo, marca_id, categoriaaparelho_id)"
					   + "values(?,?,?,?,(select id from marca where marca.id = "+novoAparelho.getMarca()+"), (select id from categoriaaparelho where id = "+novoAparelho.getCategoria()+"))";
		PreparedStatement p;
		try {
			p = this.conexao.prepareStatement(comando);
			p.setString(1, novoAparelho.getNome());
			p.setString(2, novoAparelho.getNsaparelho());
			p.setString(3, novoAparelho.getModelo());
			p.setString(4, novoAparelho.getAtivo());
			p.execute();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public List<AparelhoObj> buscarAparelho(AparelhoObj aparelho) {

		String nome = aparelho.getNome();
		String comando = "select id, nome, numeroserie, modelo, ativo, marca_id, categoriaaparelho_id from aparelho";
		if (nome != "") {
			comando += " where nome like '" + nome + "%';";
		}

		List<AparelhoObj> ListaAparelho = new ArrayList<AparelhoObj>();
		try {
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			while (rs.next()) {
				AparelhoObj aparelhoAux = new AparelhoObj();
				int idaparelho = rs.getInt("id");
				String nomeaparelho = rs.getString("nome");
				String nsaparelho = rs.getString("numeroserie");
				String modelo = rs.getString("modelo");
				String ativo = rs.getString("ativo");
				int marca = Integer.parseInt(rs.getString("marca_id"));
				int categoria = Integer.parseInt(rs.getString("categoriaaparelho_id"));

				aparelhoAux.setIdaparelho(idaparelho);
				aparelhoAux.setNome(nomeaparelho);
				aparelhoAux.setNsaparelho(nsaparelho);
				aparelhoAux.setModelo(modelo);
				aparelhoAux.setAtivo((ativo));
				aparelhoAux.setMarca(marca);
				aparelhoAux.setCategoria(categoria);

				ListaAparelho.add(aparelhoAux);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ListaAparelho;
	}
		@Override
	public AparelhoObj buscarPorId(int id){
		String comando = "SELECT * from aparelho WHERE id = " + id;
		AparelhoObj aparelho = new AparelhoObj();
		
		try{
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			while(rs.next()){
				int idaparelho = rs.getInt("id");
				String nome = rs.getString("nome");
				String nsaparelho = rs.getString("numeroserie");
				String modelo = rs.getString("modelo");
				String ativo = rs.getString("ativo");
				String marca = rs.getString("marca_id");
				String categoria = rs.getString("categoriaaparelho_id");
				
				aparelho.setIdaparelho(idaparelho);
				aparelho.setNome(nome);
				aparelho.setCategoria(Integer.parseInt(categoria));
				aparelho.setMarca(Integer.parseInt(marca));
				aparelho.setAtivo(ativo);
				aparelho.setModelo(modelo);
				aparelho.setNsaparelho(nsaparelho);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return aparelho;
	}
	@Override
	public boolean editarAparelho(AparelhoObj aparelho) {
	String comando = "update aparelho set nome=?, numeroserie=?, modelo=?, ativo=?, marca_id=?, categoriaaparelho_id=? ";
	comando += "WHERE id = " + aparelho.getIdaparelho();
	PreparedStatement p;
	try{
		p = this.conexao.prepareStatement(comando);
		p.setString(1, aparelho.getNome());
		p.setString(2, aparelho.getNsaparelho());
		p.setString(3, aparelho.getModelo());
		p.setString(4, aparelho.getAtivo());
		p.setInt(5, aparelho.getMarca());
		p.setInt(6, aparelho.getCategoria());
		p.executeUpdate();
	}catch(SQLException e){
		e.printStackTrace();
		return false;
	}
	return true;
	}
	@Override
	public List<AparelhoObj> filtrarAparelhosAtivos(AparelhoObj aparelho) {
		List<AparelhoObj> ListaAparelho = new ArrayList<AparelhoObj>();
		String comando = "";
		
		if(aparelho.getAtivo().equals("N")) {
			comando = "select * from aparelho  where ativo = 'N';";
		}
		else if(aparelho.getAtivo().equals("S")) {
			comando = "select * from aparelho  where ativo = 'S';";
		}
		else{
			comando = "select * from aparelho;";
		}
		try {
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			while (rs.next()) {
				AparelhoObj aparelhofiltrado = new AparelhoObj();
				int idaparelho = rs.getInt("id");
				String nomeaparelho = rs.getString("nome");
				String nsaparelho = rs.getString("numeroserie");
				String modelo = rs.getString("modelo");
				String ativo = rs.getString("ativo");
				int marca = Integer.parseInt(rs.getString("marca_id"));
				int categoria = Integer.parseInt(rs.getString("categoriaaparelho_id"));

				aparelhofiltrado.setIdaparelho(idaparelho);
				aparelhofiltrado.setNome(nomeaparelho);
				aparelhofiltrado.setNsaparelho(nsaparelho);
				aparelhofiltrado.setModelo(modelo);
				aparelhofiltrado.setAtivo((ativo));
				aparelhofiltrado.setMarca(marca);
				aparelhofiltrado.setCategoria(categoria);

				ListaAparelho.add(aparelhofiltrado);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ListaAparelho;
	}
	/*FIM APARELHO*/
	
	/*INICIO PESSOA*/
	@Override
	public boolean inserirPessoa(PessoaObj pessoa) {
		String comando1 = "INSERT INTO endereco (estado, cidade, bairro, rua, numero, cep) VALUES(?,?,?,?,?,?);";				
		String comando2 = "INSERT INTO pessoa (nome, cpf, rg, datanascimento, sexo, telefone, celular, email, profissao, tipomorada, tipopessoa, ativo, endereco_id) "
						 + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?, (select max(id) from endereco));";
		String comando3 = "INSERT INTO login (email, senha, pessoa_id, ordemservico_id) values((select email from pessoa where id in (select max(id) from pessoa)), '1234', (select max(id) from pessoa), (select max(id) from ordemservico));";
		PreparedStatement p1;
		PreparedStatement p2;
		PreparedStatement p3;
		try {
			p1 = this.conexao.prepareStatement(comando1);
				p1.setString(1, pessoa.getEstado());
				p1.setString(2, pessoa.getCidade());
				p1.setString(3, pessoa.getBairro());
				p1.setString(4, pessoa.getEndereco());
				p1.setInt(5, pessoa.getNumero());
				p1.setString(6, pessoa.getCep());
			p1.execute();
			
			p2 = this.conexao.prepareStatement(comando2);
				p2.setString(1, pessoa.getNome());
				p2.setString(2, pessoa.getCpf());
				p2.setString(3, pessoa.getRg());
				p2.setDate(4, pessoa.getDatanascimento());
				p2.setString(5, pessoa.getSexo());
				p2.setString(6, pessoa.getTelefone());
				p2.setString(7, pessoa.getCelular());
				p2.setString(8, pessoa.getEmail());
				p2.setString(9, pessoa.getProfissao());
				p2.setString(10, pessoa.getTipomorada());
				p2.setString(11, pessoa.getTipopessoa());
				p2.setString(12, pessoa.getAtivo());
			p2.execute();
			
			p3 = this.conexao.prepareStatement(comando3);
			p3.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public boolean atualizar(PessoaObj pessoa) {
		String comando1 = "UPDATE endereco SET estado=?, cidade=?, bairro=?, rua=?, numero=?, cep=? WHERE id = "+pessoa.getIdendereco()+";";
		String comando2 = "UPDATE pessoa SET nome=?, cpf=?, rg=?, datanascimento=?, sexo=?, telefone=?, celular=?, email=?, profissao=?, tipomorada=?, tipopessoa=?, ativo=? WHERE id = "+pessoa.getId()+";";
		PreparedStatement p1;
		PreparedStatement p2;
		try{p1 = this.conexao.prepareStatement(comando1);
				p1.setString(1, pessoa.getEstado());
				p1.setString(2, pessoa.getCidade());
				p1.setString(3, pessoa.getBairro());
				p1.setString(4, pessoa.getEndereco());
				p1.setInt(5, pessoa.getNumero());
				p1.setString(6, pessoa.getCep());
			p1.executeUpdate(); System.out.println(p1);
			
			p2 = this.conexao.prepareStatement(comando2);
				p2.setString(1, pessoa.getNome());
				p2.setString(2, pessoa.getCpf());
				p2.setString(3, pessoa.getRg());
				p2.setDate(4, pessoa.getDatanascimento());
				p2.setString(5, pessoa.getSexo());
				p2.setString(6, pessoa.getTelefone());
				p2.setString(7, pessoa.getCelular());
				p2.setString(8, pessoa.getEmail());
				p2.setString(9, pessoa.getProfissao());
				p2.setString(10, pessoa.getTipomorada());
				p2.setString(11, pessoa.getTipopessoa());
				p2.setString(12, pessoa.getAtivo());
			p2.executeUpdate();System.out.println(p2);
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public PessoaObj buscarPessoaPorId(int id) {
		String comando = "SELECT pessoa.id, nome, cpf, rg, datanascimento, profissao, telefone, celular, email, tipopessoa, tipomorada, ativo, "
				   + "sexo, funcionario_id, endereco.id, endereco.cep, endereco.numero, endereco.rua, endereco.bairro, endereco.cidade, endereco.estado "
				   +"FROM pessoa INNER JOIN endereco ON endereco.id = pessoa.endereco_id WHERE pessoa.id = "+id+";";
		PessoaObj pessoa = new PessoaObj();
		try{
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			while(rs.next()){
				int idpessoa = rs.getInt("id");
				int idfuncionario = rs.getInt("funcionario_id");
				int numero = rs.getInt("endereco.numero");
				int idendereco = rs.getInt("endereco.id");
				String nome = rs.getString("nome");
				String cpf = rs.getString("cpf");
				String rg = rs.getString("rg");
				String profissao = rs.getString("profissao");
				String telefone = rs.getString("telefone");
				String celular = rs.getString("celular");
				String email = rs.getString("email");
				String tipopessoa = rs.getString("tipopessoa");
				String ativo = rs.getString("ativo");
				String endereco = rs.getString("endereco.rua");
				String cidade = rs.getString("endereco.cidade");
				String estado = rs.getString("endereco.estado");
				String cep = rs.getString("endereco.cep");
				String bairro = rs.getString("endereco.bairro");
				String tipomorada = rs.getString("tipomorada");
				String sexo = rs.getString("sexo");
				Date datanascimento = rs.getDate("datanascimento");
				
				pessoa.setId(idpessoa);
				pessoa.setNome(nome);
				pessoa.setCpf(cpf);
				pessoa.setRg(rg);
				pessoa.setDatanascimento(datanascimento);
				pessoa.setProfissao(profissao);
				pessoa.setTelefone(telefone);
				pessoa.setEmail(email);
				pessoa.setAtivo(ativo);
				pessoa.setEndereco(endereco);
				pessoa.setTipopessoa(tipopessoa);
				pessoa.setIdfuncionario(idfuncionario);
				pessoa.setEstado(estado);
				pessoa.setCidade(cidade);
				pessoa.setCep(cep);
				pessoa.setBairro(bairro);
				pessoa.setNumero(numero);
				pessoa.setCelular(celular);
				pessoa.setTipomorada(tipomorada);
				pessoa.setSexo(sexo);
				pessoa.setIdendereco(idendereco);
			}
		}catch(Exception e){
			e.printStackTrace();
			return pessoa;
		}
		return pessoa;
	}
	public List<PessoaObj> buscarPessoaPorNome(String nome) {
		String comando = "SELECT pessoa.id, nome, cpf, rg, datanascimento, profissao, telefone, celular, email, tipopessoa, ativo, "
					   + "endereco.rua, endereco.cidade, endereco.estado FROM pessoa INNER JOIN endereco ON endereco.id = pessoa.endereco_id ";
		if (nome != "") {
			comando += "WHERE nome LIKE '" + nome + "%' "
					+ "AND pessoa.id IN (SELECT pessoa.id FROM pessoa WHERE pessoa.funcionario_id IS NULL);";
			}
		List<PessoaObj> ListaPessoa = new ArrayList<PessoaObj>();
		try {
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			while (rs.next()) {
				PessoaObj pessoa = new PessoaObj();
					int idpessoa = rs.getInt("id");
					String nomepessoa = rs.getString("nome");
					String cpf = rs.getString("cpf");
					String rg = rs.getString("rg");
					Date dataNascimento = rs.getDate("datanascimento");
					String profissao = rs.getString("profissao");
					String telefone = rs.getString("telefone");
					String celular = rs.getString("celular");
					String email = rs.getString("email");
					String tipopessoa = rs.getString("tipopessoa");
					String ativo = rs.getString("ativo");
					String endereco = rs.getString("endereco.rua");
					String cidade = rs.getString("endereco.cidade");
					String estado = rs.getString("endereco.estado");
					
				pessoa.setId(idpessoa);
				pessoa.setNome(nomepessoa);
				pessoa.setCpf(cpf);
				pessoa.setRg(rg);
				pessoa.setDatanascimento(dataNascimento);
				pessoa.setProfissao(profissao);
				pessoa.setEndereco(endereco);
				pessoa.setTelefone(telefone);
				pessoa.setCelular(celular);
				pessoa.setEmail(email);
				pessoa.setCidade(cidade); 
				pessoa.setEstado(estado);
				pessoa.setTipopessoa(tipopessoa);
				pessoa.setAtivo(ativo);

				ListaPessoa.add(pessoa);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ListaPessoa;
	}
	public List<PessoaObj> filtroPessoaAtivo(PessoaObj pessoaAtivo) {
		List<PessoaObj> ListaAtivos = new ArrayList<PessoaObj>();
		String comando = "";
		String filtro = pessoaAtivo.getAtivo();
		if(filtro.equalsIgnoreCase("N")) {
			comando += "SELECT pessoa.id, nome, cpf, rg, rua, cidade, estado, telefone, tipopessoa, ativo FROM pessoa "
					 + "INNER JOIN endereco ON endereco.id = pessoa.endereco_id "
					 + "WHERE ativo = 'N' " 
					 + "AND pessoa.id IN (SELECT pessoa.id FROM pessoa WHERE pessoa.funcionario_id IS NULL);";
		}
		else if(filtro.equalsIgnoreCase("S")) {
			comando += "SELECT pessoa.id, nome, cpf, rg, rua, cidade, estado, telefone, tipopessoa, ativo FROM pessoa "
					 + "INNER JOIN endereco ON endereco.id = pessoa.endereco_id "
					 + "WHERE ativo = 'S' " 
					 + "AND pessoa.id IN (SELECT pessoa.id FROM pessoa WHERE pessoa.funcionario_id IS NULL);";
		}
		else{
			comando += "SELECT pessoa.id, nome, cpf, rg, rua, cidade, estado, telefone, tipopessoa, ativo FROM pessoa "
					 + "INNER JOIN endereco ON endereco.id = pessoa.endereco_id "
					 + "AND pessoa.id IN (SELECT pessoa.id FROM pessoa WHERE pessoa.funcionario_id IS NULL);";
		}
		int vezes = 0;
		try {
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);			
			while (rs.next()) {
				vezes = vezes + 1;
				PessoaObj pessoa = new PessoaObj();
				int idpessoa = rs.getInt("id");
				String nomepessoa = rs.getString("nome");
				String cpf = rs.getString("cpf");
				String rg = rs.getString("rg");
				String endereco = rs.getString("rua");
				String telefone = rs.getString("telefone");
				String cidade = rs.getString("cidade");
				String estado = rs.getString("estado");
				String tipopessoa = rs.getString("tipopessoa");
				String ativo = rs.getString("ativo");
				
				pessoa.setId(idpessoa);
				pessoa.setNome(nomepessoa);
				pessoa.setCpf(cpf);
				pessoa.setRg(rg);
				pessoa.setEndereco(endereco);
				pessoa.setTelefone(telefone);
				pessoa.setCidade(cidade);
				pessoa.setEstado(estado);
				pessoa.setTipopessoa(tipopessoa);
				pessoa.setAtivo(ativo);

				ListaAtivos.add(pessoa);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ListaAtivos;
	}
	/*FIM PESSOA*/
	
	/*INICIO FUNCIONARIO*/
	public boolean inserirFuncionario(FuncionarioObj funcionario) {
		String comando1 = "INSERT INTO endereco (estado, cidade, bairro, rua, numero, cep) VALUES(?,?,?,?,?,?);";
		String comando2 = "INSERT INTO funcionario (cargo, setor, salario, dataadmissao, numeroct, pis) VALUES (?,?,?,?,?,?);";				
		String comando3 = "INSERT INTO pessoa (nome, cpf, rg, datanascimento, sexo, telefone, celular, email, profissao, tipomorada, ativo, funcionario_id, endereco_id) "
						 + "VALUES(?,?,?,?,?,?,?,?,?,?,?, (select max(id) from funcionario), (select max(id) from endereco));";
		String comando4 = "INSERT INTO login (email, senha, pessoa_id, ordemservico_id) values((select email from pessoa where id in (select max(id) from pessoa)), '1234', (select max(id) from pessoa), (select max(id) from ordemservico));";
		PreparedStatement p1;
		PreparedStatement p2;
		PreparedStatement p3;
		PreparedStatement p4;
				try {
					p1 = this.conexao.prepareStatement(comando1);
					p1.setString(1, funcionario.getEstado());
					p1.setString(2, funcionario.getCidade());
					p1.setString(3, funcionario.getBairro());
					p1.setString(4, funcionario.getEndereco());
					p1.setInt(5, funcionario.getNumero());
					p1.setString(6, funcionario.getCep());
					p1.execute();
					
					p2 = this.conexao.prepareStatement(comando2);
					p2.setString(1, funcionario.getCargo());
					p2.setString(2, funcionario.getSetor());
					p2.setString(3, funcionario.getSalario());
					p2.setDate(4, funcionario.getDataadmissao());
					p2.setString(5, funcionario.getNumeroct());
					p2.setString(6, funcionario.getNumeropis());
					p2.execute();
					
					p3 = this.conexao.prepareStatement(comando3);
					p3.setString(1, funcionario.getNome());
					p3.setString(2, funcionario.getCpf());
					p3.setString(3, funcionario.getRg());
					p3.setDate(4, funcionario.getDatanascimento());
					p3.setString(5, funcionario.getSexo());
					p3.setString(6, funcionario.getTelefone());
					p3.setString(7, funcionario.getCelular());
					p3.setString(8, funcionario.getEmail());
					p3.setString(9, funcionario.getProfissao());
					p3.setString(10, funcionario.getTipomorada());
					p3.setString(11, funcionario.getAtivo());
					p3.execute();
					
					p4 = this.conexao.prepareStatement(comando4);
					p4.execute();
				} catch (SQLException e) {
					e.printStackTrace();
					return false;
				}
				return true;
			}
	public List<FuncionarioObj> buscarFuncionarioPorNome(String nome) {
		String comando = "SELECT pessoa.id, nome, cpf, cargo, setor, salario, sexo, ativo, funcionario_id, endereco_id FROM pessoa "
					   + "INNER JOIN funcionario ON funcionario.id = pessoa.funcionario_id ";
		if (nome != "" && nome != null) {
			comando += "AND pessoa.id NOT IN (SELECT pessoa.id FROM pessoa WHERE funcionario_id IS NULL);";
			}
		List<FuncionarioObj> ListaFuncionario = new ArrayList<FuncionarioObj>();
		try {
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			while (rs.next()) {
				FuncionarioObj funcionario = new FuncionarioObj();
				int idpessoa = rs.getInt("pessoa.id");
				String nomepessoa = rs.getString("nome");
				String cpf = rs.getString("cpf");
				String cargo = rs.getString("cargo");
				String setor = rs.getString("setor");
				String salario = rs.getString("salario");
				String sexo = rs.getString("sexo");
				String ativo = rs.getString("ativo");
				int idfuncionario = rs.getInt("funcionario_id");
				
				funcionario.setId(idpessoa);
				funcionario.setNome(nomepessoa);
				funcionario.setCpf(cpf);
				funcionario.setCargo(cargo);
				funcionario.setSetor(setor);
				funcionario.setSalario(salario);
				funcionario.setSexo(sexo);
				funcionario.setAtivo(ativo);
				funcionario.setIdfuncionario(idfuncionario);

				ListaFuncionario.add(funcionario);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ListaFuncionario;
	}
	public FuncionarioObj buscarFuncionarioPorId(int id) {
		String comando = "SELECT pessoa.id, nome, cpf, rg, datanascimento, sexo, telefone, celular, email, profissao, tipomorada, ativo, "
					   + "funcionario_id, endereco_id, endereco.cep, endereco.numero, endereco.rua, endereco.bairro, endereco.cidade, endereco.estado, "
					   + "funcionario.numeroct, funcionario.pis, funcionario.cargo, funcionario.setor, funcionario.salario, funcionario.dataadmissao, "
					   + "funcionario.datademissao "
					   + "FROM pessoa "
					   + "INNER JOIN endereco on endereco.id = pessoa.endereco_id "
					   + "INNER JOIN funcionario on funcionario.id = pessoa.funcionario_id "
					   + "WHERE pessoa.id = 1;";
		FuncionarioObj funcionario = new FuncionarioObj();
		try{
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			while(rs.next()){
				int idpessoa = rs.getInt("pessoa.id");
				String nome = rs.getString("nome");
				String cpf = rs.getString("cpf");
				String rg = rs.getString("rg");
				Date datanascimento = rs.getDate("datanascimento");
				String sexo = rs.getString("sexo");
				String telefone = rs.getString("telefone");
				String celular = rs.getString("celular");
				String email = rs.getString("email");
				String profissao = rs.getString("profissao");
				String tipomorada = rs.getString("tipomorada");
				String ativo = rs.getString("ativo");
				int idfuncionario = rs.getInt("funcionario_id");
				int idendereco = rs.getInt("endereco_id");
				String cep = rs.getString("cep");
				int numero = rs.getInt("numero");
				String endereco = rs.getString("rua");
				String bairro = rs.getString("bairro");
				String cidade = rs.getString("cidade");
				String estado = rs.getString("estado");
				String numeroct = rs.getString("numeroct");
				String numeropis = rs.getString("pis");
				String cargo = rs.getString("cargo");
				String setor = rs.getString("setor"); 
				String salario = rs.getString("salario"); 
				Date dataadmissao = rs.getDate("dataadmissao"); 
				Date datademissao = rs.getDate("datademissao");	
				
				funcionario.setId(idpessoa);
				funcionario.setNome(nome);
				funcionario.setCpf(cpf);
				funcionario.setRg(rg);
				funcionario.setDatanascimento(datanascimento);
				funcionario.setProfissao(profissao);
				funcionario.setSexo(sexo);
				funcionario.setTelefone(telefone);
				funcionario.setCelular(celular);
				funcionario.setEmail(email);
				funcionario.setProfissao(profissao);
				funcionario.setTipomorada(tipomorada);
				funcionario.setAtivo(ativo);
				funcionario.setIdfuncionario(idfuncionario);
				funcionario.setIdendereco(idendereco);
				funcionario.setCep(cep);
				funcionario.setNumero(numero);
				funcionario.setEndereco(endereco);
				funcionario.setBairro(bairro);
				funcionario.setCidade(cidade);
				funcionario.setEstado(estado);
				funcionario.setNumeroct(numeroct);
				funcionario.setNumeropis(numeropis);
				funcionario.setCargo(cargo);
				funcionario.setSetor(setor);
				funcionario.setSalario(salario);
				funcionario.setDataadmissao(dataadmissao);
				funcionario.setDatademissao(datademissao);
			}
		}catch(Exception e){
			e.printStackTrace();
			return funcionario;
		}
		return funcionario;
	}
	public boolean atualizarFuncionario(FuncionarioObj funcionario) {
		String comando1 = "UPDATE endereco SET estado=?, cidade=?, bairro=?, rua=?, numero=?, cep=? WHERE endereco.id = "+funcionario.getIdendereco()+";";
		String comando2 = "UPDATE funcionario SET numeroct=?, pis=?, cargo=?, setor=?, salario=?, dataadmissao=? WHERE funcionario.id = "+funcionario.getIdfuncionario()+";";				
		String comando3 = "UPDATE pessoa SET nome=?, cpf=?, rg=?, datanascimento=?, sexo=?, telefone=?, celular=?, email=?, ativo=? WHERE pessoa.id = "+funcionario.getId()+";";
		
		PreparedStatement p1;
		PreparedStatement p2;
		PreparedStatement p3;
		try{
			p1 = this.conexao.prepareStatement(comando1);
			p1.setString(1, funcionario.getEstado());
			p1.setString(2, funcionario.getCidade());
			p1.setString(3, funcionario.getBairro());
			p1.setString(4, funcionario.getEndereco());
			p1.setInt(5, funcionario.getNumero());
			p1.setString(6, funcionario.getCep());
			p1.executeUpdate();
			
			p2 = this.conexao.prepareStatement(comando2);
			p2.setString(1, funcionario.getNumeroct());
			p2.setString(2, funcionario.getNumeropis());
			p2.setString(3, funcionario.getCargo());
			p2.setString(4, funcionario.getSetor());
			p2.setString(5, funcionario.getSalario());
			p2.setDate(6, funcionario.getDataadmissao());
			p2.executeUpdate();
			
			p3 = this.conexao.prepareStatement(comando3);
			p3.setString(1, funcionario.getNome());
			p3.setString(2, funcionario.getCpf());
			p3.setString(3, funcionario.getRg());
			p3.setDate(4, funcionario.getDatanascimento());
			p3.setString(5, funcionario.getSexo());
			p3.setString(6, funcionario.getTelefone());
			p3.setString(7, funcionario.getCelular());
			p3.setString(8, funcionario.getEmail());
			p3.setString(9, funcionario.getAtivo());
			p3.execute();
			p3.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public List<FuncionarioObj> filtroFuncionarioAtivo(FuncionarioObj funcionario) {
		
		List<FuncionarioObj> ListaAtivos = new ArrayList<FuncionarioObj>();
		String comando = "";
		String filtro = funcionario.getAtivo();
		
		if(filtro.equalsIgnoreCase("N")) {
			comando += "SELECT pessoa.id, nome, cpf, cargo, setor, salario, sexo, ativo, funcionario_id, endereco_id FROM pessoa "
					+ "INNER JOIN funcionario ON funcionario.id = pessoa.funcionario_id "
					+ "WHERE ativo = 'N' AND pessoa.id NOT IN (SELECT pessoa.id FROM pessoa WHERE funcionario_id IS NULL);";
		}
		else if(filtro.equalsIgnoreCase("S")) {
			comando += "SELECT pessoa.id, nome, cpf, cargo, setor, salario, sexo, ativo, funcionario_id, endereco_id FROM pessoa "
					+ "INNER JOIN funcionario ON funcionario.id = pessoa.funcionario_id "
					+ "WHERE ativo = 'S' AND pessoa.id NOT IN (SELECT pessoa.id FROM pessoa WHERE funcionario_id IS NULL);";
		}
		else{
			comando += "SELECT pessoa.id, nome, cpf, cargo, setor, salario, sexo, ativo, funcionario_id, endereco_id FROM pessoa "
					+ "INNER JOIN funcionario ON funcionario.id = pessoa.funcionario_id "
					+ "WHERE pessoa.id NOT IN (SELECT pessoa.id FROM pessoa WHERE funcionario_id IS NULL);";
		}
		try {
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);			
			while (rs.next()) {
				FuncionarioObj funcionarioobj = new FuncionarioObj();
				int idpessoa = rs.getInt("pessoa.id");
				String nomepessoa = rs.getString("nome");
				String cpf = rs.getString("cpf");
				String cargo = rs.getString("cargo");
				String setor = rs.getString("setor");
				String salario = rs.getString("salario");
				String sexo = rs.getString("sexo");
				String ativo = rs.getString("ativo");
				int idfuncionario = rs.getInt("funcionario_id");
				
				funcionarioobj.setId(idpessoa);
				funcionarioobj.setNome(nomepessoa);
				funcionarioobj.setCpf(cpf);
				funcionarioobj.setCargo(cargo);
				funcionarioobj.setSetor(setor);
				funcionarioobj.setSalario(salario);
				funcionarioobj.setSexo(sexo);
				funcionarioobj.setAtivo(ativo);
				funcionarioobj.setIdfuncionario(idfuncionario);

				ListaAtivos.add(funcionarioobj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ListaAtivos;
	}
		
	/*INICIO SERVICO*/
	public boolean inserirservico(ServicoObj servico) {
		String comando = "INSERT INTO servicos (tiposervico, nome, descricao, ativo) VALUES(?,?,?,?);";
				PreparedStatement p;
				try {
					p = this.conexao.prepareStatement(comando);
					p.setString(1, servico.getTiposervico());
					p.setString(2, servico.getNomeservico());
					p.setString(3, servico.getDescricaoservico());
					p.setString(4, servico.getAtivo());
					p.execute();
				} catch (SQLException e) {
					e.printStackTrace();
					return false;
				}
				return true;
	}
	public List<ServicoObj> buscarservicoPorNome(String nome) {
		String comando = "SELECT id, tiposervico, nome, descricao, ativo FROM servicos";
		if (nome != "" && nome != null) {
			comando += " WHERE nome LIKE '" + nome + "%';";
			}
		List<ServicoObj> ListaServico = new ArrayList<ServicoObj>();
		try {
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			while (rs.next()) {
				ServicoObj servico = new ServicoObj();
				int idservico = rs.getInt("id");
				String tiposervico = rs.getString("tiposervico");
				String nomeservico = rs.getString("nome");
				String descricaoservico = rs.getString("descricao");
				String ativo = rs.getString("ativo");
				
				servico.setIdservico(idservico);
				servico.setTiposervico(tiposervico);
				servico.setNomeservico(nomeservico);
				servico.setDescricaoservico(descricaoservico);
				servico.setAtivo(ativo);
				ListaServico.add(servico);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ListaServico;
	}
	public ServicoObj buscarservicoPorId(int id) {
		String comando = "SELECT id, tiposervico, nome, descricao, ativo FROM servicos WHERE id = "+id+";";
		ServicoObj servico = new ServicoObj();
		try{
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			while(rs.next()){
				int idservico = rs.getInt("id");
				String tiposervico = rs.getString("tiposervico");
				String nomeservico = rs.getString("nome");
				String descricaoservico = rs.getString("descricao");
				String ativo = rs.getString("ativo");
				
				servico.setIdservico(idservico);
				servico.setTiposervico(tiposervico);
				servico.setNomeservico(nomeservico);;
				servico.setDescricaoservico(descricaoservico);
				servico.setAtivo(ativo);
			}
		}catch(Exception e){
			e.printStackTrace();
			return servico;
		}
		return servico;
	}
	public boolean atualizarServico(ServicoObj servico) {
		String comando = "UPDATE servicos SET tiposervico=?, nome=?, descricao=?, ativo=? WHERE id = "+servico.getIdservico()+";";
				PreparedStatement p;
				try{p = this.conexao.prepareStatement(comando);
					p.setString(1, servico.getTiposervico());
					p.setString(2, servico.getNomeservico());
					p.setString(3, servico.getDescricaoservico());
					p.setString(4, servico.getAtivo());
					p.executeUpdate();
				}catch(SQLException e){
					e.printStackTrace();
					return false;
				}
				return true;
	}
	public List<ServicoObj> filtroservicoAtivo(ServicoObj servico) {
		List<ServicoObj> ListaServicosAtivos = new ArrayList<ServicoObj>();
		String comando = "";
		String filtro = servico.getAtivo();
		if(filtro.equalsIgnoreCase("N")) {
			comando += "SELECT id, tiposervico, nome, descricao, ativo FROM servicos WHERE ativo = 'N';";
		}
		else if(filtro.equalsIgnoreCase("S")) {
			comando += "SELECT id, tiposervico, nome, descricao, ativo FROM servicos WHERE ativo = 'S';";
		}
		else{
			comando += "SELECT id, tiposervico, nome, descricao, ativo FROM servicos;";
		}
		int vezes = 0;
		try {
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			while (rs.next()) {
				vezes = vezes + 1;
				ServicoObj servicos = new ServicoObj();
				int idservico = rs.getInt("id");
				String tiposervico = rs.getString("tiposervico");
				String nomeservico = rs.getString("nome");
				String descricaoservico = rs.getString("descricao");
				String ativo = rs.getString("ativo");
				
				servicos.setIdservico(idservico);
				servicos.setTiposervico(tiposervico);
				servicos.setNomeservico(nomeservico);
				servicos.setDescricaoservico(descricaoservico);
				servicos.setAtivo(ativo);

				ListaServicosAtivos.add(servicos);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ListaServicosAtivos;
	}
	/*FIM SERVICO*/
	
	/*INICIO CATEGORIA APARELHO*/
	public boolean inserirCategoriaAparelho(CategoriaAparelhoObj categoria) {
		String comando = "INSERT INTO categoriaaparelho (nome, ativo) values(?,?);";
		PreparedStatement p;
		try {
			p = this.conexao.prepareStatement(comando);
			p.setString(1, categoria.getNome());
			p.setString(2, categoria.getAtivo());
			p.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public List<CategoriaAparelhoObj> buscarCategoriaAparelhoPorNome(String nome) {
		String comando = "SELECT * FROM categoriaaparelho ";
		if(nome != "" && nome != null) {
			comando += "WHERE nome like '"+nome+"%'";
		}
		
		List<CategoriaAparelhoObj> ListaCategoria = new ArrayList<CategoriaAparelhoObj>();
		try {
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			while (rs.next()) {
				CategoriaAparelhoObj categoria = new CategoriaAparelhoObj();
				int id = rs.getInt("id");
				String nomecategoria = rs.getString("nome");
				String ativo = rs.getString("ativo");
				
				categoria.setId(id);
				categoria.setNome(nomecategoria);
				categoria.setAtivo(ativo);
				ListaCategoria.add(categoria);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ListaCategoria;
	}
	public CategoriaAparelhoObj buscarCategoriaAparelhoPorId(int id) {
		String comando = "SELECT id, nome, ativo FROM categoriaaparelho WHERE id = "+id+";";
		CategoriaAparelhoObj categoria = new CategoriaAparelhoObj();
		try{
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			while(rs.next()){
				int idservico = rs.getInt("id");
				String nome = rs.getString("nome");
				String ativo = rs.getString("ativo");
				
				categoria.setId(idservico);
				categoria.setNome(nome);;
				categoria.setAtivo(ativo);
			}
		}catch(Exception e){
			e.printStackTrace();
			return categoria;
		}
		return categoria;
	}
	public boolean atualizarCategoriaAparelho(CategoriaAparelhoObj categoria) {
		String comando = "UPDATE categoriaaparelho SET nome = ?, ativo = ? WHERE id = "+categoria.getId()+";";
		PreparedStatement p;
		try{p = this.conexao.prepareStatement(comando);
			p.setString(1, categoria.getNome());
			p.setString(2, categoria.getAtivo());
			p.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
		return true;		
	}
	public List<CategoriaAparelhoObj> filtroCategoriaAparelhoAtivo(CategoriaAparelhoObj categoria) {
		List<CategoriaAparelhoObj> ListaCategoriasAtivos = new ArrayList<CategoriaAparelhoObj>();
		String comando = "";
		String filtro = categoria.getAtivo();
		if(filtro.equalsIgnoreCase("N")) {
			comando += "SELECT id, nome, ativo FROM categoriaaparelho WHERE ativo = 'N';";
		}
		else if(filtro.equalsIgnoreCase("S")) {
			comando += "SELECT id, nome, ativo FROM categoriaaparelho WHERE ativo = 'S';";
		}
		else{
			comando += "SELECT id, nome, ativo FROM categoriaaparelho;";
		}
		int vezes = 0;
		try {
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			while (rs.next()) {
				vezes = vezes + 1;
				CategoriaAparelhoObj categorias = new CategoriaAparelhoObj();
				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				String ativo = rs.getString("ativo");
				
				categorias.setId(id);
				categorias.setNome(nome);
				categorias.setAtivo(ativo);

				ListaCategoriasAtivos.add(categorias);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ListaCategoriasAtivos;
	}
	/*FIM CATEGORIA APARELHO*/
	
	/*INICIO MARCA*/
	public boolean inserirMarca(MarcaObj marca) {
		String comando = "insert into marca (nome, ativo) values(?, ?);";
		PreparedStatement p;
		try {
			p = this.conexao.prepareStatement(comando);
			p.setString(1, marca.getNome());
			p.setString(2, marca.getAtivo());
			p.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public List<MarcaObj> buscarMarcaPorNome(String nome) {
		String comando = "SELECT * FROM marca ";
		if(nome != "" || nome != null) {
			comando += "WHERE nome like '"+ nome +"%'";
		}
		List<MarcaObj> ListaMarca = new ArrayList<MarcaObj>();
		try {
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			while (rs.next()) {
				MarcaObj marca = new MarcaObj();
				int id = rs.getInt("id");
				String nomemarca = rs.getString("nome");
				String ativo = rs.getString("ativo");
				marca.setId(id);
				marca.setNome(nomemarca);
				marca.setAtivo(ativo);
				ListaMarca.add(marca);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ListaMarca;
	}
	public MarcaObj buscarMarcaPorId(int id) {
		String comando = "SELECT id, nome, ativo FROM marca WHERE id = "+id+";";
		MarcaObj marca = new MarcaObj();
		try{
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			while(rs.next()){
				int idservico = rs.getInt("id");
				String nomemarca = rs.getString("nome");
				String ativo = rs.getString("ativo");
				
				marca.setId(idservico);
				marca.setNome(nomemarca);;
				marca.setAtivo(ativo);
			}
		}catch(Exception e){
			e.printStackTrace();
			return marca;
		}
		return marca;
	}
	public boolean atualizarMarca(MarcaObj marca) {
		String comando = "UPDATE marca SET nome = ?, ativo = ? WHERE id = "+marca.getId()+";";
		PreparedStatement p;
		try{p = this.conexao.prepareStatement(comando);
			p.setString(1, marca.getNome());
			p.setString(2, marca.getAtivo());
			p.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
		return true;		
	}
	public List<MarcaObj> filtroMarcaAtivo(MarcaObj marca) {
		List<MarcaObj> ListaMarcaAtivos = new ArrayList<MarcaObj>();
		String comando = "";
		String filtro = marca.getAtivo();
		if(filtro.equalsIgnoreCase("N")) {
			comando += "SELECT id, nome, ativo FROM marca WHERE ativo = 'N';";
		}
		else if(filtro.equalsIgnoreCase("S")) {
			comando += "SELECT id, nome, ativo FROM marca WHERE ativo = 'S';";
		}
		else{
			comando += "SELECT id, nome, ativo FROM marca;";
		}
		int vezes = 0;
		try {
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			while (rs.next()) {
				vezes = vezes + 1;
				MarcaObj marcas = new MarcaObj();
				int id = rs.getInt("id");
				String nomemarca = rs.getString("nome");
				String ativo = rs.getString("ativo");
				
				marcas.setId(id);
				marcas.setNome(nomemarca);
				marcas.setAtivo(ativo);

				ListaMarcaAtivos.add(marcas);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ListaMarcaAtivos;
	}
	/*FIM MARCA*/
	
	/*INICIO PERFIL*/
	public LoginObj perfil(int id) {
		String comando = "SELECT login.id, nome, telefone, celular, rua, endereco.numero, cep, login.email, senha from login "
				       + "inner join pessoa on pessoa.id = login.pessoa_id "
				       + "inner join endereco on endereco.id = pessoa.endereco_id "
				       + "where login.id = "+id+";";
		
		LoginObj login = new LoginObj();
		try {
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			while (rs.next()) {
				login.setId(rs.getInt("id"));
				login.setNome(rs.getString("nome"));
				login.setTelefone(rs.getString("telefone"));
				login.setCelular(rs.getString("celular"));	
				login.setEndereco(rs.getString("rua"));
				login.setNumero(rs.getString("numero"));
				login.setCep(rs.getString("cep"));
				login.setEmail(rs.getString("email"));
				login.setSenha(rs.getString("senha"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return login;
	}
	public boolean atualizarPerfil(LoginObj login) {
		String senha = login.getSenha();
		if(login.getSenha().isEmpty() || senha == null || senha == "") {
			String comando = " UPDATE endereco SET rua=?, numero=?, cep=? WHERE endereco.id in (select endereco_id from pessoa where pessoa.id in (select pessoa_id from login where login.id = "+login.getId()+"));";
			String comando2 = "UPDATE pessoa SET nome=?, telefone=?, celular=?, email=? WHERE pessoa.id in (select pessoa_id from login where login.id = "+login.getId()+");";
			String comando3 = "UPDATE login SET email =? WHERE id = "+login.getId()+";";
			PreparedStatement p;
			PreparedStatement p2;
			PreparedStatement p3;
			try{
				p = this.conexao.prepareStatement(comando);
				p.setString(1, login.getEndereco());
				p.setString(2, login.getNumero());
				p.setString(3, login.getCep());
				p.executeUpdate();
				
				p2 = this.conexao.prepareStatement(comando2);
				p2.setString(1, login.getNome());
				p2.setString(2, login.getTelefone());
				p2.setString(3, login.getCelular());
				p2.setString(4, login.getEmail());
				p2.executeUpdate();
				
				p3 = this.conexao.prepareStatement(comando3);
				p3.setString(1, login.getEmail());
				p3.executeUpdate();
				
			}catch(SQLException e){
				e.printStackTrace();
				return false;
			} 
			return true;
		}else {
			String comando =  " UPDATE endereco SET rua=?, numero=?, cep=? WHERE endereco.id in (select endereco_id from pessoa where pessoa.id in (select pessoa_id from login where login.id = "+login.getId()+"));";
			String comando2 = " UPDATE pessoa SET nome=?, telefone=?, celular=?, email=? WHERE pessoa.id in (select pessoa_id from login where login.id = "+login.getId()+");";
			String comando3 = " UPDATE login SET email=?, senha=? WHERE id = "+login.getId()+";";
			PreparedStatement p;
			PreparedStatement p2;
			PreparedStatement p3;
			try{
				p = this.conexao.prepareStatement(comando);
				p.setString(1, login.getEndereco());
				p.setString(2, login.getNumero());
				p.setString(3, login.getCep());
				p.executeUpdate();
				
				p2 = this.conexao.prepareStatement(comando2);
				p2.setString(1, login.getNome());
				p2.setString(2, login.getTelefone());
				p2.setString(3, login.getCelular());
				p2.setString(4, login.getEmail());
				p2.executeUpdate();
				
				p3 = this.conexao.prepareStatement(comando3);
				p3.setString(1, login.getEmail());
				p3.setString(2, login.getSenha());
				p3.executeUpdate();
			}catch(SQLException e){
				e.printStackTrace();
				return false;
			}
			return true;
		}
	}
	/*FIM PERFIL*/
	/*INICIO SELECTs OS*/
	public List<SelectObj> buscarSelectCategoria() {
		String comando = "SELECT id, nome FROM categoriaaparelho;";

		List<SelectObj> ListaSelect = new ArrayList<SelectObj>();
		try {
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			while (rs.next()) {
				SelectObj lista = new SelectObj();
				int id = rs.getInt("id");
				String nome = (rs.getString("nome"));
				
				lista.setId(id);
				lista.setNome(nome);
				ListaSelect.add(lista);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ListaSelect;
	}
	public List<SelectObj> buscarSelectMarca() {
		String comando = "SELECT id, nome FROM marca;";

		List<SelectObj> ListaSelect = new ArrayList<SelectObj>();
		try {
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			while (rs.next()) {
				SelectObj lista = new SelectObj();
				int id = rs.getInt("id");
				String nome = (rs.getString("nome"));
				
				lista.setId(id);
				lista.setNome(nome);
				ListaSelect.add(lista);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ListaSelect;
	}
	public List<ServicoObj> buscarSelectTipoServico() {
		String comando = "SELECT id, tiposervico FROM servicos;";
		List<ServicoObj> ListaSelect = new ArrayList<ServicoObj>();
		try {
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			while (rs.next()) {
				ServicoObj lista = new ServicoObj();
				int id = rs.getInt("id");
				String tiposervico = (rs.getString("tiposervico"));
				
				lista.setIdservico(id);
				lista.setTiposervico(tiposervico);
				ListaSelect.add(lista);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ListaSelect;
	}
	public SelectObj buscarSelectNumeroOS() {
		String comando = "SELECT id FROM ordemservico WHERE id IN(SELECT MAX(id) FROM ordemservico);";
		SelectObj numero = null;
		try {
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			while(rs.next()) {
				numero = new SelectObj();
				int id = rs.getInt("id");
				numero.setId(id);
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		return numero;
	}
	public List<PessoaOsObj> buscarPessoaOs(String nome) {
		List<PessoaOsObj> ListaPessoaOs = new ArrayList<PessoaOsObj>();
		String comando = "SELECT pessoa.id, nome, cpf, rg, telefone, celular, "
				       + "endereco.rua FROM pessoa INNER JOIN endereco ON endereco.id = pessoa.endereco_id ";
	if (nome != "") {
		comando += "WHERE nome LIKE '" + nome + "%' "
				 + "AND pessoa.ativo = 'S' AND pessoa.id IN (SELECT pessoa.id FROM pessoa WHERE pessoa.funcionario_id IS NULL);";
		}
	try {
		java.sql.Statement stmt = conexao.createStatement();
		ResultSet rs = stmt.executeQuery(comando);
		while (rs.next()) {
			PessoaOsObj pessoa = new PessoaOsObj();
				int id = rs.getInt("pessoa.id");
				String nomepessoa = rs.getString("nome");
				String cpf = rs.getString("cpf");
				String rg = rs.getString("rg");
				String telefone = rs.getString("telefone");
				String celular = rs.getString("celular");
				String endereco = rs.getString("endereco.rua");
			
			pessoa.setId(id);
			pessoa.setNome(nomepessoa);
			pessoa.setCpf(cpf);
			pessoa.setRg(rg);
			pessoa.setEndereco(endereco);
			pessoa.setTelefone(telefone);
			pessoa.setCelular(celular);

			ListaPessoaOs.add(pessoa);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return ListaPessoaOs;
	}
	public List<AparelhoObj> buscarAparelhoOs() {
		String comando = "select ap.id, ap.nome, numeroserie, modelo, marca.nome as marca, categoriaaparelho.nome as categoria from aparelho ap "
				+ "inner join categoriaaparelho on categoriaaparelho.id = ap.categoriaaparelho_id "
				+ "inner join marca on marca.id = ap.marca_id "
				+ "where ap.ativo = 'S';";
		List<AparelhoObj> listaAparelho = new ArrayList<AparelhoObj>();
		try {
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			while (rs.next()) {
				AparelhoObj aparelho = new AparelhoObj();
				int idaparelho = rs.getInt("ap.id");
				String nome = rs.getString("ap.nome");
				String categoria = rs.getString("categoria");
				String marca = rs.getString("marca");
				String modelo = rs.getString("modelo");
				String nsaparelho = rs.getString("numeroserie");
				
				aparelho.setIdaparelho(idaparelho);
				aparelho.setNome(nome);
				aparelho.setNomeCategoria(categoria);
				aparelho.setNomeMarca(marca);
				aparelho.setModelo(modelo);
				aparelho.setNsaparelho(nsaparelho);
				listaAparelho.add(aparelho);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaAparelho;
	}
	/*FIM SELECTs OS*/

	public boolean inserirOrdemServico(OrdemServicoObj os) {
		String comando = "insert into ordemservico (numeroos, descproblema, dataabertura, dataprazo, statusos, pessoa_id, ativo, aparelho_id, servicos_id) "
					   + "values(?, ?, ?, ?, ?, ?, 'S', ?, ?);";
		PreparedStatement p;
		try {
			p = this.conexao.prepareStatement(comando);
			p.setInt(1, os.getNumeroos());
			p.setString(2, os.getObsproblema());
			p.setDate(3, os.getAbertura());
			p.setDate(4, os.getPrazo());
			p.setString(5, os.getStatusos());
			p.setInt(6, os.getPessoa_id());
			p.setInt(7, os.getAparelho_id());
			p.setInt(8, os.getServicos_id());			
			p.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public List<OrdemServicoObj> buscarOS(int numero) {
		List<OrdemServicoObj> listaOS = new ArrayList<OrdemServicoObj>();
		String comando = "";
		if(numero != 0) {
			comando += "select ordemservico.id, numeroos, pessoa.nome, dataabertura, dataprazo, datafechamento, statusos, total from ordemservico "
				       + "inner join pessoa on pessoa.id = ordemservico.pessoa_id "
				       + "where numeroos = "+numero+";";
		}else {
			comando += "select ordemservico.id, numeroos, pessoa.nome, dataabertura, dataprazo, datafechamento, statusos, total from ordemservico "
				       + "inner join pessoa on pessoa.id = ordemservico.pessoa_id ";
		};
		
		try {
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			while (rs.next()) {
				OrdemServicoObj os = new OrdemServicoObj();
				
				int id = rs.getInt("ordemservico.id");
				int numeroos = rs.getInt("numeroos");
				String nome = rs.getString("pessoa.nome");
				Date abertura = rs.getDate("dataabertura");
				Date prazo = rs.getDate("dataprazo");
				Date fechamento = rs.getDate("datafechamento");
				String statusos = rs.getString("statusos");
				float total = rs.getFloat("total");
				
				os.setId(id);
				os.setNumeroos(numeroos);
				os.setNome(nome);
				os.setAbertura(abertura);
				os.setPrazo(prazo);
				os.setFechamento(fechamento);
				os.setStatusos(statusos);
				os.setTotal(total);
				
				listaOS.add(os);
				
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		return listaOS;
	}
	public OrdemServicoObj buscarPorNumeroOs(int numeroos) {
		String comando = "select numeroos, descproblema, descsolucao, dataabertura, dataprazo, datafechamento, statusos, ordemservico.ativo, "
				+ "total, pessoa.nome as cliente, pessoa.cpf, pessoa.rg, pessoa.telefone, pessoa.celular, endereco.rua, "
				+ "categoriaaparelho.nome as categoria, marca.nome as marca, aparelho.modelo, aparelho.numeroserie, servicos.tiposervico "
				+ "from ordemservico "
				+ "inner join aparelho on aparelho.id = ordemservico.aparelho_id "
				+ "inner join categoriaaparelho on categoriaaparelho.id = aparelho.categoriaaparelho_id "
				+ "inner join marca on marca.id = aparelho.marca_id "
				+ "inner join pessoa on pessoa.id = ordemservico.pessoa_id "
				+ "inner join endereco on endereco.id = pessoa.endereco_id "
				+ "inner join servicos on servicos.id = ordemservico.servicos_id "
				+ "where numeroos = "+numeroos+";";
			OrdemServicoObj os = new OrdemServicoObj();
		
		try {
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			while(rs.next()) {
				
				int numero = rs.getInt("numeroos");
				String obsproblema = rs.getString("descproblema");
				String obssolucao = rs.getString("descsolucao");
				Date abertura = rs.getDate("dataabertura");
				Date prazo = rs.getDate("dataprazo");
				Date fechamento = rs.getDate("datafechamento");
				String statusos = rs.getString("statusos");
				String ativo = rs.getString("ordemservico.ativo");
				float total = rs.getFloat("total");
				String nome = rs.getString("cliente");
				String cpf = rs.getString("pessoa.cpf");
				String rg = rs.getString("pessoa.rg");
				String telefone = rs.getString("pessoa.telefone");
				String celular = rs.getString("pessoa.celular");
				String rua = rs.getString("endereco.rua");
				String marca = rs.getString("marca");
				String categoria = rs.getString("categoria");
				String modelo = rs.getString("aparelho.modelo");
				String numeroserie = rs.getString("aparelho.numeroserie");
				String tiposervico = rs.getString("servicos.tiposervico");
				
				os.setNumeroos(numero);
				os.setObsproblema(obsproblema);
				os.setObssolucao(obssolucao);
				os.setAbertura(abertura);
				os.setPrazo(prazo);
				os.setFechamento(fechamento);
				os.setStatusos(statusos);
				os.setAtivo(ativo);
				os.setTotal(total);
				os.setNome(nome);
				os.setCpf(cpf);
				os.setRg(rg);
				os.setTelefone(telefone);
				os.setCelular(celular);
				os.setRua(rua);
				os.setMarca(marca);
				os.setCategoria(categoria);
				os.setModelo(modelo);
				os.setNumeroserie(numeroserie);
				os.setTiposervico(tiposervico);
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return os;
	}
	public boolean editarOS(OrdemServicoObj os) {
		String comando = "";
		String status = os.getStatusos();
		if(status.equalsIgnoreCase("CA") || status.equalsIgnoreCase("CO")) {
			comando += "update ordemservico "
					+ "set descsolucao = ?, dataprazo = ?, datafechamento = ?,  statusos= ?, total = ?, ativo = 'N' "
					+ "where id = "+os.getNumeroos()+";";
		}else {
			comando +="update ordemservico "
					+ "set descsolucao = ?, dataprazo = ?, datafechamento = ?,  statusos= ?, total = ?, ativo = 'S' "
					+ "where id = "+os.getNumeroos()+";";
		}
		
		PreparedStatement p;
		try {
			p = this.conexao.prepareStatement(comando);
			System.out.println(comando);
			p.setString(1, os.getObssolucao());
			p.setDate(2, os.getPrazo());
			p.setDate(3, os.getFechamento());
			p.setString(4, os.getStatusos());
			p.setDouble(5, os.getTotal());			
			p.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public List<OrdemServicoObj> filtroOsStatus(OrdemServicoObj os) {
		List<OrdemServicoObj> listaOS = new ArrayList<OrdemServicoObj>();
		String comando = "";
		String status = os.getAtivo();
		if(status.equalsIgnoreCase("CO")) {
			comando += "select ordemservico.id, numeroos, pessoa.nome, dataabertura, dataprazo, datafechamento, statusos, total from ordemservico "
					+ "inner join pessoa on pessoa.id = ordemservico.pessoa_id where ordemservico.statusos like 'CO%';";
		}
		else if(status.equalsIgnoreCase("CA")) {
			comando += "select ordemservico.id, numeroos, pessoa.nome, dataabertura, dataprazo, datafechamento, statusos, total from ordemservico "
					+ "inner join pessoa on pessoa.id = ordemservico.pessoa_id where ordemservico.statusos like 'CA%';";
		}
		else if(status.equalsIgnoreCase("AB")) {
			comando += "select ordemservico.id, numeroos, pessoa.nome, dataabertura, dataprazo, datafechamento, statusos, total from ordemservico "
					+ "inner join pessoa on pessoa.id = ordemservico.pessoa_id where ordemservico.statusos like 'AB%';";
		}
		else if(status.equalsIgnoreCase("EA")) {
			comando += "select ordemservico.id, numeroos, pessoa.nome, dataabertura, dataprazo, datafechamento, statusos, total from ordemservico "
					+ "inner join pessoa on pessoa.id = ordemservico.pessoa_id where ordemservico.statusos like 'EA%';";
		}
		else{
			comando += "select ordemservico.id, numeroos, pessoa.nome, dataabertura, dataprazo, datafechamento, statusos, total from ordemservico "
					+ "inner join pessoa on pessoa.id = ordemservico.pessoa_id;";
		}	
		try {
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			while (rs.next()) {
				OrdemServicoObj ordem = new OrdemServicoObj();
				
				int id = rs.getInt("ordemservico.id");
				int numeroos = rs.getInt("numeroos");
				String nome = rs.getString("pessoa.nome");
				Date abertura = rs.getDate("dataabertura");
				Date prazo = rs.getDate("dataprazo");
				Date fechamento = rs.getDate("datafechamento");
				String statusos = rs.getString("statusos");
				float total = rs.getFloat("total");
				
				ordem.setId(id);
				ordem.setNumeroos(numeroos);
				ordem.setNome(nome);
				ordem.setAbertura(abertura);
				ordem.setPrazo(prazo);
				ordem.setFechamento(fechamento);
				ordem.setStatusos(statusos);
				ordem.setTotal(total);
				
				listaOS.add(ordem);
				
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		return listaOS;
	}
	public List<OrdemServicoObj> filtroOsAtivo(OrdemServicoObj os) {
		List<OrdemServicoObj> listaOS = new ArrayList<OrdemServicoObj>();
		String comando = "";
		String ativo = os.getAtivo();
		if(ativo.equalsIgnoreCase("N")) {
			comando += "select ordemservico.id, numeroos, pessoa.nome, dataabertura, dataprazo, datafechamento, statusos, total from ordemservico "
					+ "inner join pessoa on pessoa.id = ordemservico.pessoa_id where ordemservico.ativo = 'N';";
		}
		else if(ativo.equalsIgnoreCase("S")) {
			comando += "select ordemservico.id, numeroos, pessoa.nome, dataabertura, dataprazo, datafechamento, statusos, total from ordemservico "
					+ "inner join pessoa on pessoa.id = ordemservico.pessoa_id where ordemservico.ativo = 'S';";
		}
		else{
			comando += "select ordemservico.id, numeroos, pessoa.nome, dataabertura, dataprazo, datafechamento, statusos, total from ordemservico "
					+ "inner join pessoa on pessoa.id = ordemservico.pessoa_id;";
		}	
		try {
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			while (rs.next()) {
				OrdemServicoObj ordem = new OrdemServicoObj();
				
				int id = rs.getInt("ordemservico.id");
				int numeroos = rs.getInt("numeroos");
				String nome = rs.getString("pessoa.nome");
				Date abertura = rs.getDate("dataabertura");
				Date prazo = rs.getDate("dataprazo");
				Date fechamento = rs.getDate("datafechamento");
				String statusos = rs.getString("statusos");
				float total = rs.getFloat("total");
				
				ordem.setId(id);
				ordem.setNumeroos(numeroos);
				ordem.setNome(nome);
				ordem.setAbertura(abertura);
				ordem.setPrazo(prazo);
				ordem.setFechamento(fechamento);
				ordem.setStatusos(statusos);
				ordem.setTotal(total);
				
				listaOS.add(ordem);
				
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		return listaOS;
	}

	public List<GraficoOS> OSmensal(GraficoOS os) {
		List<GraficoOS> lista = new ArrayList<GraficoOS>();
		try {
			String comando = "select distinct month(dataabertura) as mes, year(dataabertura) as ano, count(numeroos) as qtde from ordemservico group by mes;";
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			while(rs.next()) {
				GraficoOS graficos = new GraficoOS();
				
				String mes = rs.getString("mes");
				int qtde = rs.getInt("qtde");
				int ano = rs.getInt("ano");
				
				graficos.setMes(mes);;
				graficos.setValor(qtde);
				graficos.setAno(ano);
				
				lista.add(graficos);
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		return lista;
	}
}




