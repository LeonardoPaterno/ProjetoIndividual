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

public class ServletBuscarPorId extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletBuscarPorId() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	protected void process(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("idaparelho"));
		PrintWriter out = response.getWriter();
		String json = null;
		try {	Conexao conec = new Conexao();
				Connection conexao = conec.abrirConexao();
				JDBCDigitalOSLoginDAO jdbccadastro = new JDBCDigitalOSLoginDAO(conexao);
				AparelhoObj aparelho = jdbccadastro.buscarPorId(id);
				System.out.println(aparelho.getIdaparelho());
				conec.fecharConexao();
				
			json = new Gson().toJson(aparelho);
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
