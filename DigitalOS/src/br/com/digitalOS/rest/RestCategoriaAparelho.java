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
import br.com.digitalOS.objetos.CategoriaAparelhoObj;

@Path("RestCategoriaAparelho")
public class RestCategoriaAparelho extends UtilRest{
	public RestCategoriaAparelho() {
	}

	@POST
	@Path("/addCategoriaAparelho")
	@Consumes("application/*")

	public Response addCategoriaAparelhoObj(String CategoriaAparelho) {
		try {		
			CategoriaAparelhoObj CategoriaAparelhoNova = new ObjectMapper().readValue(CategoriaAparelho, CategoriaAparelhoObj.class);
			
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCDigitalOSLoginDAO jdbcCategoriaAparelhoObj = new JDBCDigitalOSLoginDAO(conexao);
			jdbcCategoriaAparelhoObj.inserirCategoriaAparelho(CategoriaAparelhoNova);
			conec.fecharConexao();
			
			return Response.ok(this.buildResponse("Categoria Aparelho cadastrada com sucesso!")).build();
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}

	@POST
	@Path("/buscarCategoriaAparelhoPorNome")
	@Produces({MediaType.APPLICATION_JSON })
	public Response buscarCategoriaAparelhoObjsPorNome(String nome) {
		try {
			List<CategoriaAparelhoObj> CategoriaAparelhoObjs = new ArrayList<CategoriaAparelhoObj>();
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCDigitalOSLoginDAO jdbcCategoriaAparelhoObj = new JDBCDigitalOSLoginDAO(conexao);
			CategoriaAparelhoObjs = jdbcCategoriaAparelhoObj.buscarCategoriaAparelhoPorNome(nome);
			conec.fecharConexao();
			return Response.ok(this.buildResponse(CategoriaAparelhoObjs)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}

	@POST
	@Path("/buscarCategoriaAparelhoPeloId/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response buscarCategoriaAparelhoObjPeloId(@PathParam("id") int id) {
		try {
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCDigitalOSLoginDAO jdbcCategoriaAparelhoObj = new JDBCDigitalOSLoginDAO(conexao);
			CategoriaAparelhoObj CategoriaAparelhoObj = jdbcCategoriaAparelhoObj.buscarCategoriaAparelhoPorId(id);
			
			return Response.ok(this.buildResponse(CategoriaAparelhoObj),MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}

	@POST
	@Path("/editarCategoriaAparelho")
	@Consumes("application/*")
	public Response editarCategoriaAparelhoObj(String CategoriaAparelho) {
		try {
			CategoriaAparelhoObj CategoriaAparelhoObj = new ObjectMapper().readValue(CategoriaAparelho, CategoriaAparelhoObj.class);
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCDigitalOSLoginDAO jdbcCategoriaAparelhoObj = new JDBCDigitalOSLoginDAO(conexao);
			jdbcCategoriaAparelhoObj.atualizarCategoriaAparelho(CategoriaAparelhoObj);
			conec.fecharConexao();
			
			return Response.ok(this.buildResponse("CategoriaAparelho editado com sucesso!")).build();
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}

	@POST
	@Path("/filtroCategoriaAparelhoAtivo")
	@Produces("application/*")
	public Response filtrarAparelhosAtivos(String ativo) {
		try {
			CategoriaAparelhoObj CategoriaAparelho = new CategoriaAparelhoObj();
			CategoriaAparelho.setAtivo(ativo);
			
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			
			JDBCDigitalOSLoginDAO jdbcCategoriaAparelhoObj = new JDBCDigitalOSLoginDAO(conexao);
			List<CategoriaAparelhoObj> CategoriaAparelhoObjs = jdbcCategoriaAparelhoObj.filtroCategoriaAparelhoAtivo(CategoriaAparelho);
			conec.fecharConexao();
			
			return Response.ok(this.buildResponse(CategoriaAparelhoObjs), MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}
}
