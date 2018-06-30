package br.com.teste.spring_angular_back.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import br.com.teste.spring_angular_back.domain.enums.TipoRisco;

@Entity
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	private Double limiteCredito;

	@Enumerated(EnumType.STRING)
	private TipoRisco risco;

	private Double taxa;

	public Cliente() {
		super();
	}

	public Cliente(String nome, Double limiteCredito, TipoRisco risco) {
		this.nome = nome;
		this.limiteCredito = limiteCredito;
		this.risco = risco;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public TipoRisco getRisco() {
		return risco;
	}

	public void setRisco(TipoRisco risco) {
		this.risco = risco;
	}

	public Double getTaxa() {
		return taxa;
	}

	public static class Builder {

		private String nome;
		private Double limiteCredito;
		private TipoRisco risco;

		public Builder nome(String nome) {
			this.nome = nome;
			return this;
		}

		public Builder limite(Double limite) {
			this.limiteCredito = limite;
			return this;
		}

		public Builder risco(String risco) {
			this.risco = TipoRisco.valueOf(risco);
			return this;
		}

		public Cliente build() {
			return new Cliente(this.nome, this.limiteCredito, this.risco);
		}

	}

	@PrePersist
	@PreUpdate
	public void prePersist() {
		this.taxa = this.risco.getTaxaJuros();
	}

	@PostLoad
	public void postLoad() {
		this.taxa = this.risco.getTaxaJuros();
	}
}