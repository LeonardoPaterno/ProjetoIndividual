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
import br.com.digitalOS.objetos.FuncionarioObj;
import br.com.digitalOS.objetos.LoginObj;
import br.com.digitalOS.objetos.PessoaObj;
import br.com.digitalOS.objetos.ServicoObj;

public class JDBCDigitalOSLoginDAO implements DigitalOSInterface {

	private Connection conexao;

	public JDBCDigitalOSLoginDAO(Connection conexao) {
		this.conexao = conexao;
	}
	/*INICIO LOGIN*/
	public boolean consultarLogin(LoginObj login) {
		String comando = "select id, email, senha from login" + " where email like '%" + login.getEmail() + "' and senha like '%" + login.getSenha() + "%'";
		try {
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			while (rs.next()) {
				login.setId(rs.getInt("id"));
				login.setEmail(rs.getString("email"));
				login.setSenha(rs.getString("senha"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	/*FIM LOGIN*/
	
	/*INICIO APARELHO*/
	@Override
	public boolean cadastrarAparelho(AparelhoObj novoAparelho) {
		String comando = "INSERT INTO ordemservico.registroaparelho "
				+ "(nomeaparelho, numerodeserie, modelo, ativo, marca_marca, categoriaaparelho_categoriaaparelho)"
				+ "values(?,?,?,?,?,?)";

		PreparedStatement p;
		try {
			p = this.conexao.prepareStatement(comando);
			p.setString(1, novoAparelho.getNome());
			p.setString(2, novoAparelho.getNsaparelho());
			p.setString(3, novoAparelho.getModelo());
			p.setString(4, novoAparelho.getAtivo());
			p.setInt(5, novoAparelho.getMarca());
			p.setInt(6, novoAparelho.getCategoria());
			p.execute();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public List<AparelhoObj> buscarAparelho(AparelhoObj aparelho) {

		String nome = aparelho.getNome();
		String comando = "select idregistroaparelho, nomeaparelho, numerodeserie, modelo,  ativo,marca_marca, categoriaaparelho_categoriaaparelho "
				+ "from registroaparelho";
		if (nome != "") {
			comando += " where nomeaparelho like '" + nome + "%';";
		}

		List<AparelhoObj> ListaAparelho = new ArrayList<AparelhoObj>();
		try {
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			while (rs.next()) {
				AparelhoObj aparelhoAux = new AparelhoObj();
				int idaparelho = rs.getInt("idregistroaparelho");
				String nomeaparelho = rs.getString("nomeaparelho");
				String nsaparelho = rs.getString("numerodeserie");
				String modelo = rs.getString("modelo");
				String ativo = rs.getString("ativo");
				int marca = Integer.parseInt(rs.getString("marca_marca"));
				int categoria = Integer.parseInt(rs.getString("categoriaaparelho_categoriaaparelho"));

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
		String comando = "SELECT * from registroaparelho WHERE idregistroaparelho= " + id;
		AparelhoObj aparelho = new AparelhoObj();
		
		try{
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			while(rs.next()){
				int idaparelho = rs.getInt("idregistroaparelho");
				String nome = rs.getString("nomeaparelho");
				String nsaparelho = rs.getString("numerodeserie");
				String modelo = rs.getString("modelo");
				String ativo = rs.getString("ativo");
				String marca = rs.getString("marca_marca");
				String categoria = rs.getString("categoriaaparelho_categoriaaparelho");
				
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
	String comando = "update registroaparelho set nomeaparelho=?, numerodeserie=?, modelo=?, ativo=?, marca_marca=?, categoriaaparelho_categoriaaparelho=? ";
	comando += "WHERE idregistroaparelho= " + aparelho.getIdaparelho();
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
			comando = "select * from registroaparelho  where ativo = 'N';";
		}
		else if(aparelho.getAtivo().equals("S")) {
			comando = "select * from registroaparelho  where ativo = 'S';";
		}
		else{
			comando = "select * from registroaparelho;";
		}
		try {
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			while (rs.next()) {
				AparelhoObj aparelhofiltrado = new AparelhoObj();
				int idaparelho = rs.getInt("idregistroaparelho");
				String nomeaparelho = rs.getString("nomeaparelho");
				String nsaparelho = rs.getString("numerodeserie");
				String modelo = rs.getString("modelo");
				String ativo = rs.getString("ativo");
				int marca = Integer.parseInt(rs.getString("marca_marca"));
				int categoria = Integer.parseInt(rs.getString("categoriaaparelho_categoriaaparelho"));

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
		String comando2 = "INSERT INTO pessoa (nome, cpf, rg, datanascimento, sexo, telefone, celular, email, profissao, tipomorada, tipopessoa, ativo, funcionario_id, endereco_id) "
						 + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?, (select max(id) from funcionario), (select max(id) from endereco));";
		PreparedStatement p1;
		PreparedStatement p2;		
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
				System.out.println(p2);
			p2.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public boolean atualizar(PessoaObj pessoa) {
		String comando1 = "UPDATE endereco SET estado=?, cidade=?, bairro=?, rua=?, numero=?, cep=? WHERE id = "+pessoa.getIdendereco()+";";
		String comando2 = "UPDATE pessoa SET nome=?, cpf=?, rg=?, datanascimento=?, sexo=?, telefone=?, celular=?, "
					   + "email=?, profissao=?, tipomorada=?, tipopessoa=?, ativo=?"
					   + "WHERE id = "+pessoa.getId()+";";
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
					   + "funcionario_id, endereco.rua, endereco.cidade, endereco.estado "
					   +"FROM pessoa INNER JOIN endereco ON endereco.id = pessoa.endereco_id ";
		if (nome != "") {
			comando += "WHERE nome LIKE '" + nome + "%';";
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
					int funcionario = rs.getInt("funcionario_id");
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
				pessoa.setIdfuncionario(funcionario);

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
			comando += "SELECT idpessoa, nome, cpf, rg, endereco, cidade, estado, telefone, tipopessoa, ativo FROM pessoa where ativo = 'N';";
		}
		else if(filtro.equalsIgnoreCase("S")) {
			comando += "SELECT idpessoa, nome, cpf, rg, endereco, cidade, estado, telefone, tipopessoa, ativo FROM pessoa where ativo = 'S';";
		}
		else{
			comando += "SELECT idpessoa, nome, cpf, rg, endereco, cidade, estado, telefone, tipopessoa, ativo FROM pessoa;";
		}
		int vezes = 0;
		try {
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);			
			while (rs.next()) {
				vezes = vezes + 1;
				PessoaObj pessoa = new PessoaObj();
				int idpessoa = rs.getInt("idpessoa");
				String nomepessoa = rs.getString("nome");
				String cpf = rs.getString("cpf");
				String rg = rs.getString("rg");
				String endereco = rs.getString("endereco");
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
		String comando2 = "INSERT INTO funcionario (cargo, setor, salario, dataadmissao, numeroct, pis, datademissao) VALUES (?,?,?,?,?,?,?);";				
		String comando3 = "INSERT INTO pessoa (nome, cpf, rg, datanascimento, sexo, telefone, celular, email, profissao, tipomorada, tipopessoa, ativo, funcionario_id, endereco_id) "
						 + "VALUES(?,?,?,?,?,?,?,?,?,?,?, (select max(id) from funcionario), (select max(id) from endereco));";
		
		PreparedStatement p1;
		PreparedStatement p2;
		PreparedStatement p3;		
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
					p2.setString(1, funcionario.getNumeroct());
					p2.setString(2, funcionario.getNumeropis());
					p2.setString(3, funcionario.getCargo());
					p2.setString(4, funcionario.getSetor());
					p2.setString(5, funcionario.getSalario());
					p2.setDate(6, funcionario.getDataadmissao());
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
				} catch (SQLException e) {
					e.printStackTrace();
					return false;
				}
				return true;
			}
	public List<FuncionarioObj> buscarFuncionarioPorNome(String nome) {
		String comando = "SELECT idpessoa, nome, cpf, cargo, setor, salario, sexo, ativo, funcionario_idfuncionario, endereco_idendereco FROM pessoa";
		if (nome != "" && nome != null) {
			comando += " INNER JOIN funcionario ON funcionario.idfuncionario = pessoa.funcionario_idfuncionario "
					+ "WHERE nome LIKE '" + nome + "%' AND funcionario_idfuncionario <> null OR funcionario_idfuncionario <> '"+"';";
			}
		List<FuncionarioObj> ListaFuncionario = new ArrayList<FuncionarioObj>();
		try {
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			while (rs.next()) {
				FuncionarioObj funcionario = new FuncionarioObj();
				int idpessoa = rs.getInt("idpessoa");
				String nomepessoa = rs.getString("nome");
				String cpf = rs.getString("cpf");
				String cargo = rs.getString("cargo");
				String setor = rs.getString("setor");
				String salario = rs.getString("salario");
				String sexo = rs.getString("sexo");
				String ativo = rs.getString("ativo");
				int idfuncionario = rs.getInt("funcionario_idfuncionario");
				
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
		String comando = "SELECT idpessoa, nome, cpf, rg, datanascimento, sexo, telefone, celular, email, profissao, tipomorada, ativo, "
					   + "funcionario_idfuncionario, endereco_idendereco, endereco.cep, endereco.numero, endereco.rua, endereco.bairro, endereco.cidade, "
					   + "endereco.estado, funcionario.numerocarteiratrabalho, funcionario.pis, funcionario.cargo, funcionario.setor, "
					   + "funcionario.salario, funcionario.dataadmissao, funcionario.datademissao FROM pessoa "
					   + "INNER JOIN endereco on endereco.idendereco = pessoa.endereco_idendereco "
					   + "INNER JOIN funcionario on funcionario.idfuncionario = pessoa.funcionario_idfuncionario "
					   + "WHERE idpessoa = "+id+";";
		FuncionarioObj funcionario = new FuncionarioObj();
		try{
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			while(rs.next()){
				int idpessoa = rs.getInt("idpessoa");
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
				int idfuncionario = rs.getInt("funcionario_idfuncionario");
				int idendereco = rs.getInt("endereco_idendereco");
				String cep = rs.getString("cep");
				int numero = rs.getInt("numero");
				String endereco = rs.getString("rua");
				String bairro = rs.getString("bairro");
				String cidade = rs.getString("cidade");
				String estado = rs.getString("estado");
				String numeroct = rs.getString("numerocarteiratrabalho");
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
		String comando1 = "UPDATE endereco SET estado=?, cidade=?, bairro=?, rua=?, numero=?, cep=? WHERE idendereco = "+funcionario.getIdendereco()+";";
		String comando2 = "UPDATE funcionario SET numerocarteiratrabalho=?, pis=?, cargo=?, setor=?, salario=?, dataadmissao=? WHERE idfuncionario = "+funcionario.getIdfuncionario()+";";				
		String comando3 = "UPDATE pessoa SET nome=?, cpf=?, rg=?, datanascimento=?, sexo=?, telefone=?, celular=?, email=?, profissao=?, tipomorada=?, ativo=? WHERE idpessoa = "+funcionario.getId()+";";
		
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
			p3.setString(9, funcionario.getProfissao());
			p3.setString(10, funcionario.getTipomorada());
			p3.setString(11, funcionario.getAtivo());
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
			comando += "SELECT idpessoa, nome, cpf, cargo, setor, salario, sexo, ativo, funcionario_idfuncionario, endereco_idendereco FROM pessoa "
					+ "INNER JOIN funcionario ON funcionario.idfuncionario = pessoa.funcionario_idfuncionario "
					+ "WHERE ativo = 'N' AND funcionario_idfuncionario <> '"+"';";
		}
		else if(filtro.equalsIgnoreCase("S")) {
			comando += "SELECT idpessoa, nome, cpf, cargo, setor, salario, sexo, ativo, funcionario_idfuncionario, endereco_idendereco FROM pessoa "
					+ "INNER JOIN funcionario ON funcionario.idfuncionario = pessoa.funcionario_idfuncionario "
					+ "WHERE ativo = 'S' AND funcionario_idfuncionario <> '"+"';";
		}
		else{
			comando += "SELECT idpessoa, nome, cpf, cargo, setor, salario, sexo, ativo, funcionario_idfuncionario, endereco_idendereco FROM pessoa "
					+ "INNER JOIN funcionario ON funcionario.idfuncionario = pessoa.funcionario_idfuncionario "
					+ "WHERE funcionario_idfuncionario <> null OR funcionario_idfuncionario <> '"+"';";
		}
		try {
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);			
			while (rs.next()) {
				FuncionarioObj funcionarioobj = new FuncionarioObj();
				int idpessoa = rs.getInt("idpessoa");
				String nomepessoa = rs.getString("nome");
				String cpf = rs.getString("cpf");
				String cargo = rs.getString("cargo");
				String setor = rs.getString("setor");
				String salario = rs.getString("salario");
				String sexo = rs.getString("sexo");
				String ativo = rs.getString("ativo");
				int idfuncionario = rs.getInt("funcionario_idfuncionario");
				
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
	/*FIM FUNCIONARIO*/
	
	/*INICIO SERVICO*/
	public boolean inserirservico(ServicoObj servico) {
		String comando = "INSERT INTO servicos (nometiposervico, nomeservico, descricaoservico, ativo) VALUES(?,?,?,?);";
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
		String comando = "SELECT idservico, nometiposervico, nomeservico, descricaoservico, ativo FROM servicos";
		if (nome != "" && nome != null) {
			comando += " WHERE nomeservico LIKE '" + nome + "%';";
			}
		List<ServicoObj> ListaServico = new ArrayList<ServicoObj>();
		try {
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			while (rs.next()) {
				ServicoObj servico = new ServicoObj();
				int idservico = rs.getInt("idservico");
				String tiposervico = rs.getString("nometiposervico");
				String nomeservico = rs.getString("nomeservico");
				String descricaoservico = rs.getString("descricaoservico");
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
		String comando = "SELECT idservico, nometiposervico, nomeservico, descricaoservico, ativo FROM servicos WHERE idservico = "+id+";";
		ServicoObj servico = new ServicoObj();
		try{
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			while(rs.next()){
				int idservico = rs.getInt("idservico");
				String tiposervico = rs.getString("nometiposervico");
				String nomeservico = rs.getString("nomeservico");
				String descricaoservico = rs.getString("descricaoservico");
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
		String comando = "UPDATE servicos SET nometiposervico=?, nomeservico=?, descricaoservico=?, ativo=? WHERE idservico = "+servico.getIdservico()+";";
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
			comando += "SELECT idservico, nometiposervico, nomeservico, descricaoservico, ativo FROM servicos WHERE ativo = 'N';";
		}
		else if(filtro.equalsIgnoreCase("S")) {
			comando += "SELECT idservico, nometiposervico, nomeservico, descricaoservico, ativo FROM servicos WHERE ativo = 'S';";
		}
		else{
			comando += "SELECT idservico, nometiposervico, nomeservico, descricaoservico, ativo FROM servicos;";
		}
		int vezes = 0;
		try {
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			while (rs.next()) {
				vezes = vezes + 1;
				ServicoObj servicos = new ServicoObj();
				int idservico = rs.getInt("idservico");
				String tiposervico = rs.getString("nometiposervico");
				String nomeservico = rs.getString("nomeservico");
				String descricaoservico = rs.getString("descricaoservico");
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
}


