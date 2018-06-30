package br.com.teste.spring_angular_back.domain.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum TipoRisco {

	A(Double.valueOf(0)), B(Double.valueOf(10)), C(Double.valueOf(20));

	private TipoRisco(Double taxaJuros) {
		this.taxaJuros = taxaJuros;
	}

	private Double taxaJuros;

	public Double getTaxaJuros() {
		return taxaJuros;
	}

	public void setTaxaJuros(Double taxaJuros) {
		this.taxaJuros = taxaJuros;
	}

	public static List<String> getAll() {
		return Arrays.asList(TipoRisco.values()).stream().map(TipoRisco::name).collect(Collectors.toList());
	}
}