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
import br.com.digitalOS.objetos.ServicoObj;

@Path("RestServico")
public class RestServico extends UtilRest{
	public RestServico() {
	}

	@POST
	@Path("/addServico")
	@Consumes("application/*")

	public Response addServicoObj(String servico) {
		try {		
			ServicoObj servicoNova = new ObjectMapper().readValue(servico, ServicoObj.class);
			
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCDigitalOSLoginDAO jdbcServicoObj = new JDBCDigitalOSLoginDAO(conexao);
			jdbcServicoObj.inserirservico(servicoNova);
			conec.fecharConexao();
			
			return Response.ok(this.buildResponse("servico cadastrada com sucesso!")).build();
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}

	@POST
	@Path("/buscarServicoPorNome")
	@Produces({MediaType.APPLICATION_JSON })
	public Response buscarServicoObjsPorNome(String nome) {
		try {
			List<ServicoObj> ServicoObjs = new ArrayList<ServicoObj>();
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCDigitalOSLoginDAO jdbcServicoObj = new JDBCDigitalOSLoginDAO(conexao);
			ServicoObjs = jdbcServicoObj.buscarservicoPorNome(nome);
			conec.fecharConexao();
			return Response.ok(this.buildResponse(ServicoObjs)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}

	@POST
	@Path("/buscarServicoPeloId/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response buscarServicoObjPeloId(@PathParam("id") int id) {
		try {
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCDigitalOSLoginDAO jdbcServicoObj = new JDBCDigitalOSLoginDAO(conexao);
			ServicoObj ServicoObj = jdbcServicoObj.buscarservicoPorId(id);
			
			return Response.ok(this.buildResponse(ServicoObj),MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}

	@POST
	@Path("/editarServico")
	@Consumes("application/*")
	public Response editarServicoObj(String servico) {
		try {
			ServicoObj ServicoObj = new ObjectMapper().readValue(servico, ServicoObj.class);
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCDigitalOSLoginDAO jdbcServicoObj = new JDBCDigitalOSLoginDAO(conexao);
			jdbcServicoObj.atualizarServico(ServicoObj);
			conec.fecharConexao();
			
			return Response.ok(this.buildResponse("servico editado com sucesso!")).build();
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}

	@POST
	@Path("/filtroServicoAtivo")
	@Produces("application/*")
	public Response filtrarAparelhosAtivos(String ativo) {
		try {
			ServicoObj servico = new ServicoObj();
			servico.setAtivo(ativo);
			
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			
			JDBCDigitalOSLoginDAO jdbcServicoObj = new JDBCDigitalOSLoginDAO(conexao);
			List<ServicoObj> ServicoObjs = jdbcServicoObj.filtroservicoAtivo(servico);
			conec.fecharConexao();
			
			return Response.ok(this.buildResponse(ServicoObjs), MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}
}
