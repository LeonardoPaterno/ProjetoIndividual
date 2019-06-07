package br.com.digitalOS.objetos;

import java.io.Serializable;

public class ServicoObj implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int idservico;
	private String tiposervico;
	private String nomeservico;
	private String descricaoservico;
	private String ativo;
	
	/*TIPO SERVICO*/
	public String getTiposervico() {
		return tiposervico;
	}
	public void setTiposervico(String tiposervico) {
		this.tiposervico = tiposervico;
	}
	
	/*NOME SERVICO*/
	public String getNomeservico() {
		return nomeservico;
	}
	public void setNomeservico(String nomeservico) {
		this.nomeservico = nomeservico;
	}
	
	/*DESCRICAO SERVICO*/
	public String getDescricaoservico() {
		return descricaoservico;
	}
	public void setDescricaoservico(String descricaoservico) {
		this.descricaoservico = descricaoservico;
	}
	
	/*ATIVO*/
	public String getAtivo() {
		return ativo;
	}
	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}
	
	/*ID SERVICO*/
	public int getIdservico() {
		return idservico;
	}
	public void setIdservico(int idservico) {
		this.idservico = idservico;
	}
	
	
	
	
	
	
}
