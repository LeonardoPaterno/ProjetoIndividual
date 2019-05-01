package br.com.digitalOS.jdbc;

import java.sql.Connection;
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

	@Override
	public boolean cadastrarPessoa(PessoaObj novaPessoa) {

		String comando = "insert into novaPessoa " + "(nome, cpf, datanascimento, rg)" + "values(?,?,?,?,?)";
		PreparedStatement p;
		try {
			p = this.conexao.prepareStatement(comando);
			p.setString(1, novaPessoa.getNome());
			p.setString(2, novaPessoa.getCpf());
			p.setString(3, novaPessoa.getDataNascimento());
			p.setString(4, novaPessoa.getRg());
			p.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
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
		String comando = "INSERT INTO `ordemservico`.`registroaparelho` "
				+ "(`nomeaparelho`, `numerodeserie`, `modelo`, `statusaparelho`, `marca_marca`, `categoriaaparelho_categoriaaparelho`)"
				+ "values(?,?,?,?,?,?)";

		PreparedStatement p;
		try {
			p = this.conexao.prepareStatement(comando);
			p.setString(1, novoAparelho.getNome());
			p.setString(2, novoAparelho.getNsaparelho());
			p.setString(3, novoAparelho.getModelo());
			p.setInt(4, novoAparelho.getStatus());
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
		String comando = "select idregistroaparelho, nomeaparelho, numerodeserie, modelo, statusaparelho, marca_marca, categoriaaparelho_categoriaaparelho "
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
				String status = rs.getString("statusaparelho");
				int marca = Integer.parseInt(rs.getString("marca_marca"));
				int categoria = Integer.parseInt(rs.getString("categoriaaparelho_categoriaaparelho"));

				aparelhoAux.setIdaparelho(idaparelho);
				aparelhoAux.setNome(nomeaparelho);
				aparelhoAux.setNsaparelho(nsaparelho);
				aparelhoAux.setModelo(modelo);
				aparelhoAux.setStatus(Integer.parseInt((status)));
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
		System.out.println(comando);
		AparelhoObj aparelho = new AparelhoObj();
		
		try{
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			while(rs.next()){
				int idaparelho = rs.getInt("idregistroaparelho");
				String nome = rs.getString("nomeaparelho");
				String nsaparelho = rs.getString("numerodeserie");
				String modelo = rs.getString("modelo");
				int status = rs.getInt(Integer.parseInt("statusaparelho"));
				String marca = rs.getString("marca_marca");
				String categoria = rs.getString("categoriaaparelho_categoriaaparelho");
				
				aparelho.setIdaparelho(idaparelho);
				aparelho.setNome(nome);
				aparelho.setCategoria(Integer.parseInt(categoria));
				aparelho.setMarca(Integer.parseInt(marca));
				aparelho.setStatus(status);
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
	String comando = "UPDATE registroaparelho SET nomeaparelho=?, numerodeserie=?, modelo=?, statusaparelho=?, marca_marca=?, categoriaaparelho_categoriaaparelho=? ";
	comando += "WHERE idregistroaparelho= " + aparelho.getIdaparelho();
	System.out.println(comando);
	PreparedStatement p;
	try{
		p = this.conexao.prepareStatement(comando);
		p.setString(1, aparelho.getNome());
		p.setString(2, aparelho.getNsaparelho());
		p.setString(3, aparelho.getModelo());
		p.setInt(4, aparelho.getCategoria());
		p.setInt(5, aparelho.getStatus());
		p.setInt(6, aparelho.getMarca());
		p.executeUpdate();
	}catch(SQLException e){
		e.printStackTrace();
		return false;
	}
	return true;
	}
}
