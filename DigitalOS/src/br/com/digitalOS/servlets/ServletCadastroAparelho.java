package br.com.digitalOS.servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.digitalOS.bd.conexao.Conexao;
import br.com.digitalOS.jdbc.JDBCDigitalOSLoginDAO;
import br.com.digitalOS.objetos.AparelhoObj;

public class ServletCadastroAparelho extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletCadastroAparelho() {

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
	
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AparelhoObj aparelho = new AparelhoObj();
		
		try {
			if(request.getParameter("nsaparelho").toString() != null) {
				aparelho.setNome(request.getParameter("nome").toString());
				aparelho.setMarca(request.getParameter("marca").toString());
				aparelho.setModelo(request.getParameter("modelo").toString());
				aparelho.setNsaparelho(Integer.parseInt(request.getParameter("nsaparelho").toString()));
				request.getParameter(request.getParameter("categoria").toString());
				
				Conexao conec = new Conexao();
				Connection conexao = conec.abrirConexao();
				JDBCDigitalOSLoginDAO jdbccadastro = new JDBCDigitalOSLoginDAO(conexao);
				boolean retorno = jdbccadastro.cadastrarAparelho(aparelho);
				conec.fecharConexao();
				
				System.out.println(retorno);
			}else {
				
			}
		}catch(Exception e) {
			
		}
	}

}
