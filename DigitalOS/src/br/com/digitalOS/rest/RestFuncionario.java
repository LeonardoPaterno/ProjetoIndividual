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
import br.com.digitalOS.objetos.FuncionarioObj;

@Path("RestFuncionario")
public class RestFuncionario extends UtilRest{
	public RestFuncionario() {
	}

	@POST
	@Path("/addFuncionario")
	@Consumes("application/*")

	public Response addFuncionario(String funcionario) {
		System.out.println(funcionario);
		try {		
			FuncionarioObj FuncionarioNovo = new ObjectMapper().readValue(funcionario, FuncionarioObj.class);
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCDigitalOSLoginDAO jdbcFuncionario = new JDBCDigitalOSLoginDAO(conexao);
			jdbcFuncionario.inserirFuncionario(FuncionarioNovo);
			conec.fecharConexao();
			return Response.ok(this.buildResponse("Funcionario cadastrado com sucesso!")).build();
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}

	@POST
	@Path("/buscarFuncionarioPorNome")
	@Produces({MediaType.APPLICATION_JSON})
	public Response buscarFuncionariosPorNome(String nome) {
		//System.out.println("Nome: "+nome);
		try {
			List<FuncionarioObj> Funcionarios = new ArrayList<FuncionarioObj>();
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCDigitalOSLoginDAO jdbcFuncionario = new JDBCDigitalOSLoginDAO(conexao);
			Funcionarios = jdbcFuncionario.buscarFuncionarioPorNome(nome);
			conec.fecharConexao();
			return Response.ok(this.buildResponse(Funcionarios), MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}

	@POST
	@Path("/buscarFuncionarioPeloId/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response buscarFuncionarioPeloId(@PathParam("id") int id) {
		try {
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCDigitalOSLoginDAO jdbcFuncionario = new JDBCDigitalOSLoginDAO(conexao);
			FuncionarioObj Funcionario = jdbcFuncionario.buscarFuncionarioPorId(id);
			
			return Response.ok(this.buildResponse(Funcionario)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}

	@POST
	@Path("/editarFuncionario")
	@Consumes("application/*")
	public Response editarFuncionario(String funcionario) {
		try {
			FuncionarioObj Funcionario = new ObjectMapper().readValue(funcionario, FuncionarioObj.class);
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCDigitalOSLoginDAO jdbcFuncionario = new JDBCDigitalOSLoginDAO(conexao);
			jdbcFuncionario.atualizarFuncionario(Funcionario);
			conec.fecharConexao();
			
			return Response.ok(this.buildResponse("Funcionario editado com sucesso!")).build();
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}

	@POST
	@Path("/filtroFuncionarioAtivo")
	@Produces("application/*")
	public Response filtrarAparelhosAtivos(String ativo) {
		try {
			FuncionarioObj Funcionario = new FuncionarioObj();
			Funcionario.setAtivo(ativo);
			
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			
			JDBCDigitalOSLoginDAO jdbcFuncionario = new JDBCDigitalOSLoginDAO(conexao);
			List<FuncionarioObj> Funcionarios = jdbcFuncionario.filtroFuncionarioAtivo(Funcionario);
			conec.fecharConexao();
			
			return Response.ok(this.buildResponse(Funcionarios), MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}
}
