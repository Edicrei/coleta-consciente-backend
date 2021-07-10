package com.coletaconsciente.location.domain.model;

import lombok.Getter;

@Getter
public enum Material {
	
	BATERIA("Bateria"),
	PILHA("Pilha"),
	AMBOS("Ambos");
	
	private String descricao;
	
	Material(String descricao) {
		this.descricao = descricao;
	}
}