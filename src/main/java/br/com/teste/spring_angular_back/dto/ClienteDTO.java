package br.com.teste.spring_angular_back.dto;

import java.io.Serializable;

import br.com.teste.spring_angular_back.domain.Cliente;

public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nome;
	private String risco;
	private Double limiteCredito;
	private Double taxa;

	public ClienteDTO() {
		super();
	}

	public ClienteDTO(Cliente cliente) {
		this.nome = cliente.getNome();
		this.risco = cliente.getRisco().name();
		this.taxa = cliente.getRisco().getTaxaJuros();
		this.limiteCredito = cliente.getLimiteCredito();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRisco() {
		return risco;
	}

	public void setRisco(String risco) {
		this.risco = risco;
	}

	public Double getLimiteCredito() {
		return limiteCredito;
	}

	public void setLimiteCredito(Double limiteCredito) {
		this.limiteCredito = limiteCredito;
	}

	public Double getTaxa() {
		return taxa;
	}

	public void setTaxa(Double taxa) {
		this.taxa = taxa;
	}

}
