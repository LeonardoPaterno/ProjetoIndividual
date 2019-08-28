package br.com.digitalOS.filtro;

import java.io.IOException;

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

@WebFilter("/paginas/*")
public class Filtro implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String context = request.getServletContext().getContextPath();

		try {
			HttpSession sessao = ((HttpServletRequest) request).getSession();
			String usuario = (String) sessao.getAttribute("login");

			if (sessao != null) {
				usuario = (String) sessao.getAttribute("login");
			}

			if (usuario == null) {
				sessao.setAttribute("msg", "Você não está logado no sistema!");
				((HttpServletResponse) response).sendRedirect(context + "/login.html");
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
