package br.com.digitalOS.jdbcInterface;

import java.util.List;

import br.com.digitalOS.objetos.AparelhoObj;
import br.com.digitalOS.objetos.PessoaObj;

public interface DigitalOSInterface {
	public boolean inserirPessoa(PessoaObj pessoa);
	public boolean atualizar(PessoaObj pessoa);
	public List<PessoaObj> filtroPessoaAtivo(PessoaObj pessoa);
	
	public boolean cadastrarAparelho(AparelhoObj novoAparelho);
	public List<AparelhoObj> buscarAparelho(AparelhoObj aparelho); 	
	public boolean editarAparelho(AparelhoObj aparelho);
	public AparelhoObj buscarPorId(int id);
	public List<AparelhoObj> filtrarAparelhosAtivos(AparelhoObj aparelho);

}

