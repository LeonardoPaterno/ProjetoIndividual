package br.com.digitalOS.objetos;

import java.io.Serializable;
import java.sql.Date;

public class OrdemServicoObj implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private int numeroos;
	private int pessoa_id;
	private int aparelho_id;
	private int servicos_id;
	private int orcamento_numero;
	
	private float total;
	
	private String obsproblema;
	private String obssolucao;
	private String statusos;
	private String ativo;
	private String nome;
	private String cpf;
	private String rg;
	private String telefone;
	private String celular;
	private String rua;
	private String categoria;
	private String marca;
	private String modelo;
	private String numeroserie;
	private String tiposervico;
	
	private Date abertura;
	private Date prazo;
	private Date fechamento;
	
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
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
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getNumeroserie() {
		return numeroserie;
	}
	public void setNumeroserie(String numeroserie) {
		this.numeroserie = numeroserie;
	}	
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getAbertura() {
		return abertura;
	}
	public void setAbertura(Date abertura) {
		this.abertura = abertura;
	}
	public Date getPrazo() {
		return prazo;
	}
	public void setPrazo(Date prazo) {
		this.prazo = prazo;
	}
	public Date getFechamento() {
		return fechamento;
	}
	public void setFechamento(Date fechamento) {
		this.fechamento = fechamento;
	}
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getTiposervico() {
		return tiposervico;
	}
	public void setTiposervico(String tiposervico) {
		this.tiposervico = tiposervico;
	}
}
