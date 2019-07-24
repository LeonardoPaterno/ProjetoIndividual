package br.com.digitalOS.rest;

import java.sql.Connection;

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
import br.com.digitalOS.objetos.LoginObj;
import br.com.digitalOS.objetos.MarcaObj;

@Path("RestPerfil")
public class RestPerfil extends UtilRest{
	public RestPerfil() {
	}

	@POST
	@Path("/buscarPerfilPeloId/{id}")
	@Produces({MediaType.APPLICATION_JSON })
	public Response buscarMarcaPeloId(@PathParam("id") int id) {
		try {
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCDigitalOSLoginDAO jdbcMarcaObj = new JDBCDigitalOSLoginDAO(conexao);
			LoginObj perfil = jdbcMarcaObj.perfil(id);
			
			return Response.ok(this.buildResponse(perfil), MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}

	@POST
	@Path("/editarMarca")
	@Consumes("application/*")
	public Response editarMarcaObj(String marca) {
		System.out.println("Mara Editar: "+marca);
		try {
			MarcaObj MarcaObj = new ObjectMapper().readValue(marca, MarcaObj.class);
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCDigitalOSLoginDAO jdbcMarcaObj = new JDBCDigitalOSLoginDAO(conexao);
			jdbcMarcaObj.atualizarMarca(MarcaObj);
			conec.fecharConexao();
			
			return Response.ok(this.buildResponse("Marca editado com sucesso!")).build();
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}
}
