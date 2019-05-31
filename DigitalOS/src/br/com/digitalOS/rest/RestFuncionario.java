package br.com.digitalOS.rest;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;

import br.com.digitalOS.bd.conexao.Conexao;
import br.com.digitalOS.jdbc.JDBCDigitalOSLoginDAO;
import br.com.digitalOS.objetos.PessoaObj;

@Path("RestFuncionario")
public class RestFuncionario extends UtilRest{
	public RestFuncionario() {
	}

	@POST
	@Path("/addPessoaObj")
	@Consumes("application/*")

	public Response addPessoaObj(String Funcionario) {
		try {		
			PessoaObj FuncionarioNova = new ObjectMapper().readValue(Funcionario, PessoaObj.class);
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCDigitalOSLoginDAO jdbcPessoaObj = new JDBCDigitalOSLoginDAO(conexao);
			jdbcPessoaObj.inserirFuncionario(FuncionarioNova);
			conec.fecharConexao();
			return Response.ok(this.buildResponse("Funcionario cadastrada com sucesso!")).build();
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}

	@POST
	@Path("/buscarFuncionarioPorNome")
	@Produces({MediaType.APPLICATION_JSON })
	public Response buscarPessoaObjsPorNome(String nome) {
		try {
			List<PessoaObj> PessoaObjs = new ArrayList<PessoaObj>();
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCDigitalOSLoginDAO jdbcPessoaObj = new JDBCDigitalOSLoginDAO(conexao);
			PessoaObjs = jdbcPessoaObj.buscarFuncionarioPorNome(nome);
			conec.fecharConexao();
			return Response.ok(this.buildResponse(PessoaObjs)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}

	@POST
	@Path("/buscarFuncionarioPeloId/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response buscarPessoaObjPeloId(@PathParam("id") int id) {
		try {
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCDigitalOSLoginDAO jdbcPessoaObj = new JDBCDigitalOSLoginDAO(conexao);
			PessoaObj PessoaObj = jdbcPessoaObj.buscarFuncionarioPorId(id);
			
			return Response.ok(this.buildResponse(PessoaObj),MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}

	@POST
	@Path("/editarFuncionario")
	@Consumes("application/*")
	public Response editarPessoaObj(String Funcionario) {
		try {
			System.out.println("TESTE: \n"+Funcionario);
			PessoaObj PessoaObj = new ObjectMapper().readValue(Funcionario, PessoaObj.class);
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCDigitalOSLoginDAO jdbcPessoaObj = new JDBCDigitalOSLoginDAO(conexao);
			jdbcPessoaObj.atualizar(PessoaObj);
			conec.fecharConexao();
			
			return Response.ok(this.buildResponse("Funcionario editado com sucesso!")).build();
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}

	@POST
	@Path("/filtroAtivo")
	@Produces("application/*")
	public Response filtrarAparelhosAtivos(String ativo) {
		try {
			PessoaObj Funcionario = new PessoaObj();
			Funcionario.setAtivo(ativo);
			
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			
			JDBCDigitalOSLoginDAO jdbcPessoaObj = new JDBCDigitalOSLoginDAO(conexao);
			List<PessoaObj> PessoaObjs = jdbcPessoaObj.filtroFuncionarioAtivo(Funcionario);
			conec.fecharConexao();
			
			return Response.ok(this.buildResponse(PessoaObjs), MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}
}
