package br.com.digitalOS.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.com.digitalOS.bd.conexao.Conexao;
import br.com.digitalOS.jdbc.JDBCDigitalOSLoginDAO;
import br.com.digitalOS.objetos.AparelhoObj;

public class ServletFiltroAtivos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		process(request, response);
	}

	protected void process(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		AparelhoObj aparelhos = new AparelhoObj();
		aparelhos.setAtivo(request.getParameter("ativo"));

		List<AparelhoObj> ListaAparelhosFiltrados = new ArrayList<AparelhoObj>();

		Conexao conec = new Conexao();
		Connection conexao = conec.abrirConexao();

		JDBCDigitalOSLoginDAO jdbcAparelhoObj = new JDBCDigitalOSLoginDAO(conexao);
		ListaAparelhosFiltrados = jdbcAparelhoObj.filtrarAparelhosAtivos(aparelhos);
		conec.fecharConexao();

		String json = new Gson().toJson(ListaAparelhosFiltrados);
		PrintWriter out = response.getWriter();

		try {
			out = response.getWriter();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.setStatus(HttpServletResponse.SC_OK);
			response.getWriter();
			out.print(json);
			out.flush();

		} catch (IOException e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}
}