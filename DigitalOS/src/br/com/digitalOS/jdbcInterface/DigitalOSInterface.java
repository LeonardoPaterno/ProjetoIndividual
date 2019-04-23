package br.com.digitalOS.jdbcInterface;

import java.util.List;

import br.com.digitalOS.objetos.AparelhoObj;
import br.com.digitalOS.objetos.PessoaObj;

public interface DigitalOSInterface {
	public boolean cadastrarPessoa(PessoaObj novaPessoa);
	
	public boolean cadastrarAparelho(AparelhoObj novoAparelho);
	
	public List<AparelhoObj> buscarAparelho(List<AparelhoObj> Aparelho); 

}

