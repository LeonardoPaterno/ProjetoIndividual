package br.com.digitalOS.objetos;

import java.io.Serializable;

public class SelectObj implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String nome;
	private String ativo;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getAtivo() {
		return ativo;
	}
	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}
	
	
	
	
	
	
	
	
}
