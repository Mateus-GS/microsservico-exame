package br.com.backend.entidades;

public enum Categoria {
	
	 ADMICIONAL("admcional"),
	 DEMICIONAL("demicional"),
	 COVID("covid");
	
	private String descricao;

	Categoria(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
