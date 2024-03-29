package br.com.digitalOS.objetos;

import java.io.Serializable;

public class AparelhoObj implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int idaparelho;
	private int categoria;
	private int marca;
	private String nome;
	private String modelo;
	private String nsaparelho;
	private String ativo;
	private String NomeCategoria;
	private String NomeMarca;
	
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
	public String getNsaparelho() {
		return nsaparelho;
	}
	public void setNsaparelho(String nsaparelho) {
		this.nsaparelho = nsaparelho;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public int getMarca() {
		return marca;
	}
	public void setMarca(int marca) {
		this.marca = marca;
	}
	public int getCategoria() {
		return categoria;
	}
	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}
	public String getAtivo() {
		return ativo;
	}
	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}
	public String getNomeCategoria() {
		return NomeCategoria;
	}
	public void setNomeCategoria(String nomeCategoria) {
		NomeCategoria = nomeCategoria;
	}
	public String getNomeMarca() {
		return NomeMarca;
	}
	public void setNomeMarca(String nomeMarca) {
		NomeMarca = nomeMarca;
	}
	
}
