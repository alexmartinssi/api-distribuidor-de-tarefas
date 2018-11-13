package com.br.apipadrao.enums;

public enum Profile {
	//ROLE_ OBRIGATORIO
	ADMIN(1, "ROLE_ADMIN"),
	USUARIO(2, "ROLE_USUARIO");

	private Integer cod;
	private String description;
	
	private Profile(Integer cod, String description)  {
		this.cod = cod;
		this.description = description;
	}

	public Integer getCod() {
		return cod;
	}

	public void setCod(Integer cod) {
		this.cod = cod;
	}

	public String getDescription() {
		return description;
	}

	public void setDescricao(String description) {
		this.description = description;
	}
	
	public static Profile toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(Profile e : Profile.values()) {
			if(cod.equals(e.cod)) {
				return e;
			}
		}
		throw new IllegalArgumentException("Não foi encontrado o código informado");
	}
}
