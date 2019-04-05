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
import com.google.gson.Gson;

@WebFilter("/paginas/*")
public class Filtro implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String context = request.getServletContext().getContextPath();

		try {
			HttpSession sessao = ((HttpServletRequest) request).getSession();
			String usuario = (String) sessao.getAttribute("login");
			Map<String, String> resposta = new HashMap<String, String>();
			if (usuario == null || usuario == "") {
				resposta.put("resposta", context + "/index.html");
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
