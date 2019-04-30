package br.com.digitalOS.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.com.digitalOS.bd.conexao.Conexao;
import br.com.digitalOS.jdbc.JDBCDigitalOSLoginDAO;
import br.com.digitalOS.objetos.AparelhoObj;


public class ServletEditarAparelho {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		process(request, response);
	}

	protected void process(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		AparelhoObj aparelho = new AparelhoObj();
		
		aparelho.setIdaparelho(Integer.parseInt(request.getParameter("EditIdAparelho")));
		aparelho.setNome(request.getParameter("EditNomeAparelho"));
		aparelho.setCategoria(Integer.parseInt(request.getParameter("EditCategoriaAparelho")));
		aparelho.setMarca(Integer.parseInt(request.getParameter("EditMarcaAparelho")));
		aparelho.setModelo(request.getParameter("EditModeloAparelho"));
		aparelho.setNsaparelho(request.getParameter("EditNsAparelho"));
		int id = Integer.parseInt(request.getParameter("EditIdAparelho"));
		System.out.println("ID: " + id);
		try {
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			
			JDBCDigitalOSLoginDAO jdbccadastro = new JDBCDigitalOSLoginDAO(conexao);
			
			boolean retorno = jdbccadastro.editarAparelho(aparelho);
			conec.fecharConexao();

			String json = new Gson().toJson(retorno);
			PrintWriter out = response.getWriter();
			out = response.getWriter();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			out.print(json);
			out.flush();
			System.out.println(json);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

