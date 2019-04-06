package br.com.digitalOS.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.digitalOS.bd.conexao.Conexao;
import br.com.digitalOS.jdbc.JDBCDigitalOSLoginDAO;
import br.com.digitalOS.objetos.LoginObj;

public class ServletConsultaLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletConsultaLogin() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			process(request, response);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			process(request, response);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	private void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NoSuchAlgorithmException {

		LoginObj login = new LoginObj();

		login.setEmail(request.getParameter("user").toString());
		String senhaCod = request.getParameter("passCoded").toString();
		byte[] arreyBytes = Base64.getDecoder().decode(senhaCod);
		String senhaDecoded = new String(arreyBytes);
		login.setSenha(senhaDecoded);

		try {
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCDigitalOSLoginDAO jdbclogin = new JDBCDigitalOSLoginDAO(conexao);
			boolean retorno = jdbclogin.consultarLogin(login);
			conec.fecharConexao();

			String context = request.getServletContext().getContextPath();
			PrintWriter out = response.getWriter();
			String resposta;
			if (retorno != false) {
				HttpSession sessao = request.getSession();
				sessao.setAttribute("login", request.getParameter("user"));
				resposta = context + "/paginas/menu.html";

				System.out.println(resposta);
				out.print(resposta);
				out.flush();
			} else {
				resposta = context + "/login.html";
				System.out.println(resposta);
				out.print(resposta);
				out.flush();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}