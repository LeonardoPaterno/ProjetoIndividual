package br.com.digitalOS.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
			// System.out.println(p);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean consultarLogin(LoginObj login) {
		String comando = "select idlogin, email, senha from login" + " where email like '%" + login.getEmail()
				+ "' and senha like '%" + login.getSenha() + "%'";
		//System.out.println("1 "+comando);
		try {
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			while (rs.next()) {
				login.setId(rs.getInt("idlogin"));
				login.setEmail(rs.getString("email"));
				login.setSenha(rs.getString("senha"));
				
				//String consultou = "Idlogin: "+login.getId()+"\nEmail:"+login.getEmail()+"\nSenha:"+login.getSenha();
				//System.out.println("2 "+consultou);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean cadastrarAparelho(AparelhoObj novoAparelho) {
		String comando = "insert into login (nome, categoria, marca, modelo, nsaparelho) values(?,?,?,?,?)";
		
		try {
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			while(rs.next()) {
				novoAparelho.setNome(rs.getString("nome"));
				novoAparelho.setCategoria(rs.getString("categoria"));
				novoAparelho.setMarca(rs.getString("marca"));
				novoAparelho.setModelo(rs.getString("modelo"));
				novoAparelho.setNsaparelho(rs.getString("nsaparelho"));
			}
		}catch(Exception e) {
			e.printStackTrace(); 
			return false;
		}
		return true;
	}

}
