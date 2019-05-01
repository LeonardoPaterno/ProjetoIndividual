package br.com.digitalOS.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.com.digitalOS.bd.conexao.Conexao;
import br.com.digitalOS.jdbc.JDBCDigitalOSLoginDAO;
import br.com.digitalOS.objetos.AparelhoObj;


public class ServletEditarAparelho extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		process(request, response);
	}

	protected void process(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		AparelhoObj aparelho = new AparelhoObj();
		
		aparelho.setIdaparelho(Integer.parseInt(request.getParameter("idaparelho")));
		aparelho.setNome(request.getParameter("nome"));
		aparelho.setCategoria(Integer.parseInt(request.getParameter("categoria")));
		aparelho.setMarca(Integer.parseInt(request.getParameter("marca")));
		aparelho.setModelo(request.getParameter("modelo"));
		aparelho.setNsaparelho(request.getParameter("nsaparelho"));
		aparelho.setStatus(Integer.parseInt(request.getParameter("status")));
		int id = Integer.parseInt(request.getParameter("idaparelho"));
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

