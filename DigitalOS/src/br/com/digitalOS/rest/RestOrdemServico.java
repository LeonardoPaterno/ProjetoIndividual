package br.com.digitalOS.rest;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
	@Path("/buscarOS/{num}")
    @PathParam(MediaType.APPLICATION_JSON)
	public Response buscarOS(@PathParam("num") int num) {
		try {
			List<OrdemServicoObj> os = new ArrayList<OrdemServicoObj>();
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCDigitalOSLoginDAO jdbcOrdemServicoObj = new JDBCDigitalOSLoginDAO(conexao);
			os = jdbcOrdemServicoObj.buscarOS(num);
			conec.fecharConexao();
			return Response.ok(this.buildResponse(os), MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}
}
