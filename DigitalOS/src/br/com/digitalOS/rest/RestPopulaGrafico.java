package br.com.digitalOS.rest;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.digitalOS.bd.conexao.Conexao;
import br.com.digitalOS.jdbc.JDBCDigitalOSLoginDAO;
import br.com.digitalOS.objetos.GraficoOS;

@Path("RestPopulaGrafico")
public class RestPopulaGrafico extends UtilRest{
	public RestPopulaGrafico() {
	}

	@POST
	@Path("/NumeroOSMensal")
	@Produces({MediaType.APPLICATION_JSON })
	@Consumes("application/*")
	public Response addMarca() {
		try {		
			GraficoOS os = new GraficoOS();	
			List<GraficoOS> grafico = new ArrayList<GraficoOS>();
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCDigitalOSLoginDAO jdbcMarcaObj = new JDBCDigitalOSLoginDAO(conexao);
			grafico = jdbcMarcaObj.OSmensal(os);
			conec.fecharConexao();
			return Response.ok(this.buildResponse(grafico)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}
}
