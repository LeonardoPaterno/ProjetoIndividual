package br.com.digitalOS.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;

import br.com.digitalOS.jdbcInterface.DigitalOSInterface;
import br.com.digitalOS.objetos.PessoaObj;

public class JDBCDigitalOSLoginDAO implements DigitalOSInterface {

	private Connection conexao;

	public JDBCDigitalOSLoginDAO(Connection conexao) {
		this.conexao = conexao;
	}

	@Override
	public boolean cadastrarPessoa(PessoaObj novaPessoa) {
		System.out.println("chegou no metodo cadastrar Pessoa()");
		return false;
	}
	


}
