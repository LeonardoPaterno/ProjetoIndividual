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
import br.com.digitalOS.objetos.LoginObj;
import br.com.digitalOS.objetos.PessoaObj;

public class JDBCDigitalOSLoginDAO implements DigitalOSInterface {

	private Connection conexao;

	public JDBCDigitalOSLoginDAO(Connection conexao) {
		this.conexao = conexao;
	}

	public boolean consultarLogin(LoginObj login) {
		String comando = "select idlogin, email, senha from login" + " where email like '%" + login.getEmail() + "' and senha like '%" + login.getSenha() + "%'";
		try {
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			while (rs.next()) {
				login.setId(rs.getInt("idlogin"));
				login.setEmail(rs.getString("email"));
				login.setSenha(rs.getString("senha"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

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

	@Override
	public boolean inserirPessoa(PessoaObj pessoa) {
		String comando = "INSERT INTO pessoa " + 
		"(nome, cpf, rg, datanascimento, profissao, endereco, numeroendereco, telefone, celular, email, cidade, estado, "
		+ "tipomorada, tipopessoa, ativo, funcionario_idfuncionario)" 
		+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement p;
		try {
			p = this.conexao.prepareStatement(comando);
			p.setString(1, pessoa.getNome());
			p.setString(2, pessoa.getCpf());
			p.setString(3, pessoa.getRg());
			p.setDate(4, pessoa.getDataNascimento());
			p.setString(5, pessoa.getProfissao());
			p.setString(6, pessoa.getEndereco());
			p.setInt(7, pessoa.getNumero());
			p.setString(8, pessoa.getTelefone());
			p.setString(9, pessoa.getCelular());
			p.setString(10, pessoa.getEmail());
			p.setString(11, pessoa.getCidade());
			p.setString(12, pessoa.getEstado());
			p.setString(13, pessoa.gettipomorada());
			p.setString(14, pessoa.gettipopessoa());
			p.setString(15, pessoa.getAtivo());
			p.setInt(16, pessoa.getFuncionario());
			p.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	
	public boolean atualizar(PessoaObj pessoa) {
		String comando = "UPDATE pessoa SET nome=?, cpf=?, rg=?, datanascimento=?, profissao=?, endereco=?, numeroendereco=?,"
		+ " telefone=?, celular=?, email=?, cidade=?, estado=?, tipomorada=?, tipopessoa=?, ativo=?, funcionario_idfuncionario=? "
		+ "WHERE idpessoa ="+pessoa.getId()+";";
		PreparedStatement p;
		try{
			p = this.conexao.prepareStatement(comando);
			p.setString(1, pessoa.getNome());
			p.setString(2, pessoa.getCpf());
			p.setString(3, pessoa.getRg());
			p.setDate(4, pessoa.getDataNascimento());
			p.setString(5, pessoa.getProfissao());
			p.setString(6, pessoa.getEndereco());
			p.setInt(7, pessoa.getNumero());
			p.setString(8, pessoa.getTelefone());
			p.setString(9, pessoa.getCelular());
			p.setString(10, pessoa.getEmail());
			p.setString(11, pessoa.getCidade());
			p.setString(12, pessoa.getEstado());
			p.setString(13, pessoa.gettipomorada());
			p.setString(14, pessoa.gettipopessoa());
			p.setString(15, pessoa.getAtivo());
			p.setInt(16, pessoa.getFuncionario());
			p.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public PessoaObj buscarPessoaPorId(int id) {
		String comando = "SELECT idpessoa, nome, cpf, rg, datanascimento, profissao, endereco, numeroendereco, telefone, celular, email,"
				+ " cidade, estado, tipomorada, tipopessoa, ativo, funcionario_idfuncionario FROM pessoa WHERE idpessoa = "+id+";";
		PessoaObj pessoa = new PessoaObj();
		try{
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			while(rs.next()){
				int idpessoa = rs.getInt("idpessoa");
				String nome = rs.getString("nome");
				String cpf = rs.getString("cpf");
				String rg = rs.getString("rg");
				Date dataNascimento = rs.getDate("datanascimento");
				String profissao = rs.getString("profissao");
				String endereco = rs.getString("endereco");
				int numero = rs.getInt("numeroendereco");
				String telefone = rs.getString("telefone");
				String celular = rs.getString("celular");
				String email = rs.getString("email");
				String cidade = rs.getString("cidade");
				String estado = rs.getString("estado");
				String tipomorada = rs.getString("tipomorada");
				String tipopessoa = rs.getString("tipopessoa");
				String ativo = rs.getString("ativo");
				int funcionario = rs.getInt("funcionario_idfuncionario");
				
				pessoa.setId(idpessoa);
				pessoa.setNome(nome);
				pessoa.setCpf(cpf);
				pessoa.setRg(rg);
				pessoa.setDataNascimento(dataNascimento);
				pessoa.setProfissao(profissao);
				pessoa.setEndereco(endereco);
				pessoa.setNumero(numero);
				pessoa.setTelefone(telefone);
				pessoa.setCelular(celular);
				pessoa.setEmail(email);
				pessoa.setCidade(cidade);
				pessoa.setEstado(estado);
				pessoa.settipomorada(tipomorada);
				pessoa.settipopessoa(tipopessoa);
				pessoa.setAtivo(ativo);
				pessoa.setFuncionario(funcionario);
			}
		}catch(Exception e){
			e.printStackTrace();
			return pessoa;
		}
		return pessoa;
	}

	public List<PessoaObj> buscarPessoaPorNome(String nome) {
		String comando = "SELECT idpessoa, nome, cpf, rg, datanascimento, profissao, endereco, numeroendereco, telefone, celular, email,"
				+ " cidade, estado, tipomorada, tipopessoa, ativo, funcionario_idfuncionario FROM pessoa";
		if (nome != "" && nome != null) {
			comando += " WHERE nome LIKE '" + nome + "%';";
			}
		List<PessoaObj> ListaPessoa = new ArrayList<PessoaObj>();
		try {
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			while (rs.next()) {
				PessoaObj pessoa = new PessoaObj();
				int idpessoa = rs.getInt("idpessoa");
				String nomepessoa = rs.getString("nome");
				String cpf = rs.getString("cpf");
				String rg = rs.getString("rg");
				Date dataNascimento = rs.getDate("datanascimento");
				String profissao = rs.getString("profissao");
				String endereco = rs.getString("endereco");
				int numero = rs.getInt("numeroendereco");
				String telefone = rs.getString("telefone");
				String celular = rs.getString("celular");
				String email = rs.getString("email");
				String cidade = rs.getString("cidade");
				String estado = rs.getString("estado");
				String tipomorada = rs.getString("tipomorada");
				String tipopessoa = rs.getString("tipopessoa");
				String ativo = rs.getString("ativo");
				int funcionario = rs.getInt("funcionario_idfuncionario");
				
				pessoa.setId(idpessoa);
				pessoa.setNome(nomepessoa);
				pessoa.setCpf(cpf);
				pessoa.setRg(rg);
				pessoa.setDataNascimento(dataNascimento);
				pessoa.setProfissao(profissao);
				pessoa.setEndereco(endereco);
				pessoa.setNumero(numero);
				pessoa.setTelefone(telefone);
				pessoa.setCelular(celular);
				pessoa.setEmail(email);
				pessoa.setCidade(cidade);
				pessoa.setEstado(estado);
				pessoa.settipomorada(tipomorada);
				pessoa.settipopessoa(tipopessoa);
				pessoa.setAtivo(ativo);
				pessoa.setFuncionario(funcionario);

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
			System.out.println(filtro);
			System.out.println(comando);
		}
		else if(filtro.equalsIgnoreCase("S")) {
			comando += "SELECT idpessoa, nome, cpf, rg, endereco, cidade, estado, telefone, tipopessoa, ativo FROM pessoa where ativo = 'S';";
			System.out.println(filtro);
			System.out.println(comando);
		}
		else{
			comando += "SELECT idpessoa, nome, cpf, rg, endereco, cidade, estado, telefone, tipopessoa, ativo FROM pessoa;";
			System.out.println(filtro);
			System.out.println(comando);
		}
		int vezes = 0;
		try {
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			System.out.println("RS: "+comando);
			
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
				System.out.println(idpessoa+","+nomepessoa+","+ativo);
				
				pessoa.setId(idpessoa);
				pessoa.setNome(nomepessoa);
				pessoa.setCpf(cpf);
				pessoa.setRg(rg);
				pessoa.setEndereco(endereco);
				pessoa.setTelefone(telefone);
				pessoa.setCidade(cidade);
				pessoa.setEstado(estado);
				pessoa.settipopessoa(tipopessoa);
				pessoa.setAtivo(ativo);

				ListaAtivos.add(pessoa);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ListaAtivos;
	}
}
