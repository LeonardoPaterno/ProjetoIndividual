package br.com.digitalOS.rest;

import java.sql.Connection;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import br.com.digitalOS.bd.conexao.Conexao;
import br.com.digitalOS.jdbc.JDBCDigitalOSLoginDAO;
import br.com.digitalOS.objetos.SelectObj;

@Path("RestSelect")
public class RestSelect extends UtilRest{
	public RestSelect() {
	}

@POST
@Path("/buscarSelectCategoria")
@Consumes("application/*")
public Response buscarSelectCategoria() {
		try {
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCDigitalOSLoginDAO jdbcSelectObj = new JDBCDigitalOSLoginDAO(conexao);
			List<SelectObj> SelectObj = (List<br.com.digitalOS.objetos.SelectObj>) jdbcSelectObj.buscarSelectCategoria();
			
			return Response.ok(this.buildResponse(SelectObj)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}

@POST
@Path("/buscarSelectMarca")
@Consumes("application/*")
public Response buscarSelectMarca() {
		try {
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCDigitalOSLoginDAO jdbcSelectObj = new JDBCDigitalOSLoginDAO(conexao);
			List<SelectObj> SelectObj = (List<br.com.digitalOS.objetos.SelectObj>) jdbcSelectObj.buscarSelectMarca();
			
			return Response.ok(this.buildResponse(SelectObj)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}
}
