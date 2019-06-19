package br.com.digitalOS.objetos;

import java.io.Serializable;

public class PessoaObj implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private int numero;
	private int idfuncionario;
	private int idendereco;
	private String nome;
	private String cpf;
	private String rg;
	private String sexo;
	private String email;
	private String endereco;
	private String bairro;
	private String cep;
	private String telefone;
	private String celular;
	private String estado;
	private String cidade;
	private String ativo;
	private String profissao;
	private String Tipomorada;
	private String Tipopessoa;
	private java.sql.Date datanascimento;
	
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
	
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	public java.sql.Date getDatanascimento() {
		return datanascimento;
	}
	public void setDatanascimento(java.sql.Date datanascimento) {
		this.datanascimento = datanascimento;
	}
		
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	public String getAtivo() {
		return ativo;
	}
	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}
	
	public int getIdfuncionario() {
		return idfuncionario;
	}
	public void setIdfuncionario(int idfuncionario) {
		this.idfuncionario = idfuncionario;
	}
	
	public int getIdendereco() {
		return idendereco;
	}
	public void setIdendereco(int idendereco) {
		this.idendereco = idendereco;
	}
	
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	
	public String getProfissao() {
		return profissao;
	}
	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}
	
	public String getTipomorada() {
		return Tipomorada;
	}
	public void setTipomorada(String tipomorada) {
		Tipomorada = tipomorada;
	}
	public String getTipopessoa() {
		return Tipopessoa;
	}
	public void setTipopessoa(String tipopessoa) {
		Tipopessoa = tipopessoa;
	}
}
