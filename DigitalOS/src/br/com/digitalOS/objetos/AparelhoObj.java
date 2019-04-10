package br.com.digitalOS.objetos;

import java.io.Serializable;

public class AparelhoObj implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int idaparelho;
	private String nome;
	private int nsaparelho;
	private String modelo;
	private String marca;
	private int categoria;
	
	public int getIdaparelho() {
		return idaparelho;
	}
	public void setIdaparelho(int idaparelho) {
		this.idaparelho = idaparelho;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getNsaparelho() {
		return nsaparelho;
	}
	public void setNsaparelho(int nsaparelho) {
		this.nsaparelho = nsaparelho;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public int getCategoria() {
		return categoria;
	}
	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}
	
}
