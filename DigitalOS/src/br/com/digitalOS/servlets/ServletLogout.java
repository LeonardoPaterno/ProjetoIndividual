package br.com.digitalOS.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

public class ServletLogout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletLogout() {
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
			
		String json = null;
		String recarrega = "http://localhost:8080/DigitalOS/login.html"; 
		
		response.setContentType("text/html");  
        PrintWriter out = response.getWriter();  
                  
        json = new Gson().toJson(recarrega);  
          
        HttpSession session = request.getSession();
        //System.out.println(request.getParameter("login"));
        //System.out.println(session.getAttribute("login"));
        session.invalidate();
        //System.out.println(session);
        //session.setAttribute("user","false");
        
        out.print(json);  
          
        out.close();  
	}
}