package br.com.digitalOS.rest;

import java.sql.Connection;
import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.digitalOS.bd.conexao.Conexao;
import br.com.digitalOS.jdbc.JDBCDigitalOSLoginDAO;
import br.com.digitalOS.objetos.SelectObj;

@Path("RestSelect")
public class RestSelect extends UtilRest{
	public RestSelect() {
	}

@POST
@Path("/buscarSelectCategoria/")
public Response buscarSelectCategoria() {
		try {
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCDigitalOSLoginDAO jdbcSelectObj = new JDBCDigitalOSLoginDAO(conexao);
			List<SelectObj> SelectObj = (List<br.com.digitalOS.objetos.SelectObj>) jdbcSelectObj.buscarSelectCategoria();
			
			return Response.ok(this.buildResponse(SelectObj), MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}
}
