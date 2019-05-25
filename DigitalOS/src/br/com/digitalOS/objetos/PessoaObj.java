package br.com.digitalOS.objetos;

import java.io.Serializable;

public class PessoaObj implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String nome;
	private java.sql.Date dataNascimento;
	private String cpf;
	private String rg;
	private String email;
	private String telefone;
	private String celular;
	private String endereco;
	private int numero;
	private String complemento;
	private String estado;
	private String cidade;
	private String ativo;
	/* private int tipocliente; */
	
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
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	} 
	
	public java.sql.Date getDataNascimento() {
		return dataNascimento;
	}
	
	public void setDataNascimento(java.sql.Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getAtivo() {
		return ativo;
	}
	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	/*
	 * public int getTipocliente() { return tipocliente; } public void
	 * setTipocliente(int tipocliente) { this.tipocliente = tipocliente; }
	 */
}
