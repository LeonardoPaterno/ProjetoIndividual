package br.com.digitalOS.rest;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.digitalOS.bd.conexao.Conexao;
import br.com.digitalOS.jdbc.JDBCDigitalOSLoginDAO;
import br.com.digitalOS.objetos.PessoaOsObj;

@Path("RestPessoaOs")
public class RestPessoaOs extends UtilRest{
	public RestPessoaOs() {
	}

	@POST
	@Path("/buscarPessoaOs")
	@Produces({MediaType.APPLICATION_JSON })
	public Response buscarPessoaOs(String nome) {
		try {
			List<PessoaOsObj> PessoaOsObjs = new ArrayList<PessoaOsObj>();
			
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCDigitalOSLoginDAO jdbcPessoaOsObj = new JDBCDigitalOSLoginDAO(conexao);
			
			PessoaOsObjs = jdbcPessoaOsObj.buscarPessoaOs(nome);
			conec.fecharConexao();
			
			return Response.ok(this.buildResponse(PessoaOsObjs)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}
}
