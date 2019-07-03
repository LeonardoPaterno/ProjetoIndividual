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
import br.com.digitalOS.objetos.MarcaObj;

@Path("RestMarca")
public class RestMarca extends UtilRest{
	public RestMarca() {
	}

	@POST
	@Path("/addMarca")
	@Consumes("application/*")
	public Response addMarca(String marca) {
		try {		
			MarcaObj MarcaNova = new ObjectMapper().readValue(marca, MarcaObj.class);
			
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCDigitalOSLoginDAO jdbcMarcaObj = new JDBCDigitalOSLoginDAO(conexao);
			jdbcMarcaObj.inserirMarca(MarcaNova);
			conec.fecharConexao();
			return Response.ok(this.buildResponse("Marca cadastrada com sucesso!")).build();
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}

	@POST
	@Path("/buscarMarcaPorNome")
	@Produces({MediaType.APPLICATION_JSON})
	public Response buscarMarcaPorNome(String nome) {
		try {
			List<MarcaObj> MarcaObjs = new ArrayList<MarcaObj>();
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCDigitalOSLoginDAO jdbcMarcaObj = new JDBCDigitalOSLoginDAO(conexao);
			MarcaObjs = jdbcMarcaObj.buscarMarcaPorNome(nome);
			conec.fecharConexao();
			return Response.ok(this.buildResponse(MarcaObjs)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}

	@POST
	@Path("/buscarMarcaPeloId/{id}")
	@Produces({MediaType.APPLICATION_JSON })
	public Response buscarMarcaPeloId(@PathParam("id") int id) {
		try {
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCDigitalOSLoginDAO jdbcMarcaObj = new JDBCDigitalOSLoginDAO(conexao);
			MarcaObj MarcaObj = jdbcMarcaObj.buscarMarcaPorId(id);
			
			return Response.ok(this.buildResponse(MarcaObj), MediaType.APPLICATION_JSON).build();
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

	@POST
	@Path("/filtroMarcaAtivo")
	@Produces("application/*")
	public Response filtrarAparelhosAtivos(String ativo) {
		System.out.println(ativo);
		try {
			MarcaObj Marca = new MarcaObj();
			Marca.setAtivo(ativo);
			
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			
			JDBCDigitalOSLoginDAO jdbcMarcaObj = new JDBCDigitalOSLoginDAO(conexao);
			List<MarcaObj> MarcaObjs = jdbcMarcaObj.filtroMarcaAtivo(Marca);
			conec.fecharConexao();
			
			return Response.ok(this.buildResponse(MarcaObjs), MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}
}
