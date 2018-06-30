package br.com.teste.spring_angular_back.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ClienteInsertDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Preenchimento obrigatório.")
	private String nome;

	@NotNull(message = "Preenchimento obrigatório.")
	private Double limiteCredito;

	@NotBlank(message = "Preenchimento obrigatório.")
	private String risco;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getLimiteCredito() {
		return limiteCredito;
	}

	public void setLimiteCredito(Double limiteCredito) {
		this.limiteCredito = limiteCredito;
	}

	public String getRisco() {
		return risco;
	}

	public void setRisco(String risco) {
		this.risco = risco;
	}

}