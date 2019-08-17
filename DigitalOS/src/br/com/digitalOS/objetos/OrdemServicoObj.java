package br.com.digitalOS.objetos;

import java.io.Serializable;

public class OrdemServicoObj implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int numeroos;
	private int pessoa_id;
	private int aparelho_id;
	private int servicos_id;
	private int orcamento_numero;
	private String obsproblema;
	private String obssolucao;
	
	public int getNumeroos() {
		return numeroos;
	}
	public void setNumeroos(int numeroos) {
		this.numeroos = numeroos;
	}
	public int getPessoa_id() {
		return pessoa_id;
	}
	public void setPessoa_id(int pessoa_id) {
		this.pessoa_id = pessoa_id;
	}
	public int getAparelho_id() {
		return aparelho_id;
	}
	public void setAparelho_id(int aparelho_id) {
		this.aparelho_id = aparelho_id;
	}
	public int getServicos_id() {
		return servicos_id;
	}
	public void setServicos_id(int servicos_id) {
		this.servicos_id = servicos_id;
	}
	public int getOrcamento_numero() {
		return orcamento_numero;
	}
	public void setOrcamento_numero(int orcamento_numero) {
		this.orcamento_numero = orcamento_numero;
	}
	public String getObsproblema() {
		return obsproblema;
	}
	public void setObsproblema(String obsproblema) {
		this.obsproblema = obsproblema;
	}
	public String getObssolucao() {
		return obssolucao;
	}
	public void setObssolucao(String obssolucao) {
		this.obssolucao = obssolucao;
	}
	public String getStatusos() {
		return statusos;
	}
	public void setStatusos(String statusos) {
		this.statusos = statusos;
	}
	public String getAtivo() {
		return ativo;
	}
	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}
	private String statusos;
	private String ativo;

}
