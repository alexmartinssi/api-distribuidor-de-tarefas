package com.br.apipadrao.enums;

public enum Status {
	EM_ANDAMENTO("Em andamento"), FINALIZADO("Finalizado");

	private String description;

	private Status(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescricao(String description) {
		this.description = description;
	}
}
