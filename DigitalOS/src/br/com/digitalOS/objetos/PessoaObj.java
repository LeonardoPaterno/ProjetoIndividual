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
	private String tipoMorada;
	private String estado;
	private String cidade;
	private String ativo;
	private int tipoCliente;
	private String profissao;
	private int funcionario;
	
	/*ID*/
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	/*NOME*/
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/*CPF*/
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	/*RG*/
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	
	/*EMAIL*/
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	/*TELEFONE*/
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	/*CELULAR*/
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	
	/*ENDERE�O*/
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	/*NUMERO ENDERE�O*/
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	/*CIDADE*/
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	} 
	
	/*DATA NASCIMENTO*/
	public java.sql.Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(java.sql.Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	/*ATIVO*/
	public String getAtivo() {
		return ativo;
	}
	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}
	
	/*ESTADO*/
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	/*TIPO MORADA*/
	public String getTipoMorada() {
		return tipoMorada;
	}
	public void setTipoMorada(String tipoMorada) {
		this.tipoMorada = tipoMorada;
	}
	
	/*TIPO CLIENTE*/
	public int getTipoCliente() {
		return tipoCliente;
	}
	public void setTipoCliente(int tipoCliente) {
		this.tipoCliente = tipoCliente;
	}
	
	/*PROFISSAO*/
	public String getProfissao() {
		return profissao;
	}
	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}
	
	/*FUNCIONARIO*/
	public int getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(int funcionario) {
		this.funcionario = funcionario;
	}
}
