package br.com.digitalOS.objetos;

import java.io.Serializable;

public class FuncionarioObj implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private int numero;
	private int idfuncionario;
	private int idendereco;
	private String nome;
	private String cpf;
	private String rg;
	private String sexo;
	private String cargo;
	private String setor;
	private String numeropis;
	private String numeroct;
	private String salario;
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
	private java.sql.Date datanascimento;
	private java.sql.Date dataadmissao;
	private java.sql.Date datademissao;
	
	
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
	
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
	public String getSetor() {
		return setor;
	}
	public void setSetor(String setor) {
		this.setor = setor;
	}
	
	public String getNumeropis() {
		return numeropis;
	}
	public void setNumeropis(String numeropis) {
		this.numeropis = numeropis;
	}
	
	public String getNumeroct() {
		return numeroct;
	}
	public void setNumeroct(String numeroct) {
		this.numeroct = numeroct;
	}
	
	public String getSalario() {
		return salario;
	}
	public void setSalario(String salario) {
		this.salario = salario;
	}
	
	public java.sql.Date getDataadmissao() {
		return dataadmissao;
	}
	public void setDataadmissao(java.sql.Date dataadmissao) {
		this.dataadmissao = dataadmissao;
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
	
	public java.sql.Date getDatademissao() {
		return datademissao;
	}
	public void setDatademissao(java.sql.Date datademissao) {
		this.datademissao = datademissao;
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
	
}
