package br.com.digitalOS.filtro;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebFilter("/recursos/paginas/*")
public class Filtro implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String context = request.getServletContext().getContextPath();

		try {
			HttpSession session = ((HttpServletRequest) request).getSession();
			String usuario = null;

			if (session != null) {
				usuario = (String) session.getAttribute("login");
			}

			if (usuario == null) {
				session.setAttribute("retorno", "Acesso Negado!");
				((HttpServletResponse) response).sendRedirect(context + "/index.html");
			} else {
				chain.doFilter(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
