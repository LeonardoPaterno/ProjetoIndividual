package br.com.digitalOS.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

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
		//System.out.println(senhaCod);
		
		/*byte[] arreyBytes = Base64.getDecoder().decode(senhaCod);
		String senhaDecoded = new String(arreyBytes);*/
		
		
		 MessageDigest md = MessageDigest.getInstance("MD5"); 
		 byte[] SenhaMD5 = md.digest(senhaCod.getBytes(StandardCharsets.UTF_8));
		 
		 StringBuilder MD5String = new StringBuilder(); 
		 
		 for (byte b : SenhaMD5) {
			 MD5String.append(String.format("%02x", b)); 
		 }
		 
        /*System.out.println("MD5 = " + new String(MD5String));*/
		String retornoMD5 = MD5String.toString();
		
		login.setSenha(retornoMD5);
		
		try {
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCDigitalOSLoginDAO jdbclogin = new JDBCDigitalOSLoginDAO(conexao);
			boolean retorno = jdbclogin.consultarLogin(login);
			conec.fecharConexao();
			
			String json = null;
			String id = Integer.toString(login.getId());
			
			String context = request.getServletContext().getContextPath();
			PrintWriter out = response.getWriter();
			Map<String, String> msg = new HashMap<String, String>();
			
			if (retorno == true && login.getId() != 0) {
				Cookie ck = new Cookie("id", id);
				response.addCookie(ck);
				
				HttpSession sessao = request.getSession();
				sessao.setAttribute("login", request.getParameter("user"));
				msg.put("url", context + "/resources/mylib/paginas/menu.html");
				json = new Gson().toJson(msg);
				
				out = response.getWriter();
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				out.print(json);
				out.flush();
			} else {
				msg.put("url", context+"/login.html");
				json = new Gson().toJson(msg);
				out = response.getWriter();
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				out.print(json);
				out.flush();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}