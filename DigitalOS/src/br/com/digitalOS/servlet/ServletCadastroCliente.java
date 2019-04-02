package br.com.digitalOS.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.digitalOS.cadastros.CadastroCliente;


@WebServlet("/ServletCadastroCliente")
public class ServletCadastroCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServletCadastroCliente() {
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doGet(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
		
		CadastroCliente novoCliente = null;
		
		
		
		
	}

}
 