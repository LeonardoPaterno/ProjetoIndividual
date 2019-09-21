package br.com.digitalOS.objetos;

import java.io.Serializable;

public class GraficoOS implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int qtde;
	private String mes;
	
	public int getValor() {
		return qtde;
	}
	public void setValor(int qtde) {
		this.qtde = qtde;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	
	
	
}
