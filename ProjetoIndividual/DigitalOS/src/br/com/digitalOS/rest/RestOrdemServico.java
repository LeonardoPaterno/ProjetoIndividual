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
import br.com.digitalOS.objetos.OrdemServicoObj;

@Path("RestOrdemServico")
public class RestOrdemServico extends UtilRest{
	public RestOrdemServico() {
	}

	@POST
	@Path("/addOrdemServico")
	@Consumes("application/*")
	public Response addOrdemServico(String ordemservico) {
		System.out.println("OS: "+ordemservico);
		try {		
			OrdemServicoObj os = new ObjectMapper().readValue(ordemservico, OrdemServicoObj.class);
			
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCDigitalOSLoginDAO jdbcOrdemServicoObj = new JDBCDigitalOSLoginDAO(conexao);
			jdbcOrdemServicoObj.inserirOrdemServico(os);
			conec.fecharConexao();
			return Response.ok(this.buildResponse("Ordem de serviço cadastrada com sucesso!")).build();
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}

	@POST
	@Path("/buscarOS/numero")
	@Produces({MediaType.APPLICATION_JSON})
	public Response buscarOS(@PathParam("numero") int numero) {
		System.out.println("N° OS: "+numero);
		try {
			List<OrdemServicoObj> os = new ArrayList<OrdemServicoObj>();
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCDigitalOSLoginDAO jdbcOrdemServicoObj = new JDBCDigitalOSLoginDAO(conexao);
			os = jdbcOrdemServicoObj.buscarOS(numero);
			conec.fecharConexao();
			return Response.ok(this.buildResponse(os), MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}

	/*
	 * @POST
	 * 
	 * @Path("/buscarOrdemServicoPeloId/{id}")
	 * 
	 * @Produces({MediaType.APPLICATION_JSON }) public Response
	 * buscarOrdemServicoPeloId(@PathParam("id") int id) { try { Conexao conec = new
	 * Conexao(); Connection conexao = conec.abrirConexao(); JDBCDigitalOSLoginDAO
	 * jdbcOrdemServicoObj = new JDBCDigitalOSLoginDAO(conexao); OrdemServicoObj
	 * OrdemServicoObj = jdbcOrdemServicoObj.buscarOrdemServicoPorId(id);
	 * 
	 * return Response.ok(this.buildResponse(OrdemServicoObj),
	 * MediaType.APPLICATION_JSON).build(); } catch (Exception e) {
	 * e.printStackTrace(); return this.buildErrorResponse(e.getMessage()); } }
	 * 
	 * @POST
	 * 
	 * @Path("/editarOrdemServico")
	 * 
	 * @Consumes("application/*") public Response editarOrdemServicoObj(String
	 * marca) { try { OrdemServicoObj OrdemServicoObj = new
	 * ObjectMapper().readValue(marca, OrdemServicoObj.class); Conexao conec = new
	 * Conexao(); Connection conexao = conec.abrirConexao(); JDBCDigitalOSLoginDAO
	 * jdbcOrdemServicoObj = new JDBCDigitalOSLoginDAO(conexao);
	 * jdbcOrdemServicoObj.atualizarOrdemServico(OrdemServicoObj);
	 * conec.fecharConexao();
	 * 
	 * return
	 * Response.ok(this.buildResponse("Ordem de serviço editada com sucesso!")).
	 * build(); } catch (Exception e) { e.printStackTrace(); return
	 * this.buildErrorResponse(e.getMessage()); } }
	 * 
	 * @POST
	 * 
	 * @Path("/filtroOrdemServicoAtivo")
	 * 
	 * @Produces("application/*") public Response filtrarAparelhosAtivos(String
	 * ativo) { try { OrdemServicoObj OrdemServico = new OrdemServicoObj();
	 * OrdemServico.setAtivo(ativo);
	 * 
	 * Conexao conec = new Conexao(); Connection conexao = conec.abrirConexao();
	 * 
	 * JDBCDigitalOSLoginDAO jdbcOrdemServicoObj = new
	 * JDBCDigitalOSLoginDAO(conexao); List<OrdemServicoObj> OrdemServicoObjs =
	 * jdbcOrdemServicoObj.filtroOrdemServicoAtivo(OrdemServico);
	 * conec.fecharConexao();
	 * 
	 * return Response.ok(this.buildResponse(OrdemServicoObjs),
	 * MediaType.APPLICATION_JSON).build(); } catch (Exception e) {
	 * e.printStackTrace(); return this.buildErrorResponse(e.getMessage()); } }
	 */
}
