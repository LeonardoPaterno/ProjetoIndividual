package br.com.digitalOS.rest;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.Response;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.ObjectMapper;

import br.com.digitalOS.bd.conexao.Conexao;
import br.com.digitalOS.jdbc.JDBCDigitalOSLoginDAO;
import br.com.digitalOS.objetos.PessoaObj;
import br.com.digitalOS.rest.UtilRest;

@Path("RestPessoa")
public class RestPessoa extends UtilRest{
	public RestPessoa() {
	}

	@POST
	@Path("/addPessoaObj")
	@Consumes("application/*")

	public Response addPessoaObj(String pessoa) {
		System.out.println(pessoa);
		try {		
			PessoaObj pessoaNova = new ObjectMapper().readValue(pessoa, PessoaObj.class);
			System.out.println(pessoaNova.getNome());
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCDigitalOSLoginDAO jdbcPessoaObj = new JDBCDigitalOSLoginDAO(conexao);
			jdbcPessoaObj.inserirPessoa(pessoaNova);
			conec.fecharConexao();
			return this.buildResponse("Pessoa cadastrada com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}

	@POST
	@Path("/buscarPessoaPorNome/{nome}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response buscarPessoaObjsPorNome(@PathParam("nome") String nome) {
		try {
			List<PessoaObj> PessoaObjs = new ArrayList<PessoaObj>();

			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCDigitalOSLoginDAO jdbcPessoaObj = new JDBCDigitalOSLoginDAO(conexao);
			PessoaObjs = jdbcPessoaObj.buscarPessoaPorNome(nome);
			conec.fecharConexao();

			return this.buildResponse(PessoaObjs);
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}

	@POST
	@Path("/deletarPessoa/{id}")
	@Produces("application/*")
	public Response deletarPessoaObj(@PathParam("id") int id) {
		try {
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCDigitalOSLoginDAO jdbcPessoaObj = new JDBCDigitalOSLoginDAO(conexao);
			jdbcPessoaObj.deletarPessoaObj(id);
			conec.fecharConexao();
			return this.buildResponse("PessoaObj deletado com sucesso.");
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}

	@POST
	@Path("/buscarPessoaPeloId/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response buscarPessoaObjPeloId(@PathParam("id") int id) {
		try {
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCDigitalOSLoginDAO jdbcPessoaObj = new JDBCDigitalOSLoginDAO(conexao);
			PessoaObj PessoaObj = jdbcPessoaObj.buscarPessoaPorId(id);
			return this.buildResponse(PessoaObj);
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}

	@POST
	@Path("/editarPessoa")
	@Consumes("application/*")
	public Response editarPessoaObj(String PessoaObjParam) {
		try {
			PessoaObj PessoaObj = new ObjectMapper().readValue(PessoaObjParam, PessoaObj.class);
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCDigitalOSLoginDAO jdbcPessoaObj = new JDBCDigitalOSLoginDAO(conexao);
			jdbcPessoaObj.atualizar(PessoaObj);
			conec.fecharConexao();
			return this.buildResponse("Pessoa editado com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}
}
