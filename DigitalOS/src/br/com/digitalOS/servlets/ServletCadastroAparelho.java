package br.com.digitalOS.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import br.com.digitalOS.bd.conexao.Conexao;
import br.com.digitalOS.jdbc.JDBCDigitalOSLoginDAO;
import br.com.digitalOS.objetos.AparelhoObj;

public class ServletCadastroAparelho extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletCadastroAparelho() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		process(request, response);
	}

	protected void process(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		AparelhoObj aparelho = new AparelhoObj();
		Map<String, String> msg = new HashMap<String, String>();
		PrintWriter out = response.getWriter();
		String json = null;
		try {
				aparelho.setNome(request.getParameter("nome").toString());
				aparelho.setMarca(Integer.parseInt(request.getParameter("marca")));
				aparelho.setModelo(request.getParameter("modelo").toString());
				aparelho.setNsaparelho(request.getParameter("nsaparelho").toString());
				aparelho.setCategoria(Integer.parseInt(request.getParameter("categoria")));

				Conexao conec = new Conexao();
				Connection conexao = conec.abrirConexao();
				JDBCDigitalOSLoginDAO jdbccadastro = new JDBCDigitalOSLoginDAO(conexao);
				boolean retorno = jdbccadastro.cadastrarAparelho(aparelho);
				conec.fecharConexao();
			if (retorno != false) {
				HttpSession sessao = request.getSession();
				sessao.setAttribute("login", request.getParameter("user"));
				msg.put("resposta", "Aparelho Cadastro");
				json = new Gson().toJson(msg);

				out = response.getWriter();
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				out.print(json);
				out.flush();
			} else {
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
