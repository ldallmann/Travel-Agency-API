package com.agencia.api.dto;

public class DestinoDTO {
    private String nome;
    private String localizacao;
    private String descricao;

    public DestinoDTO() {}

    public DestinoDTO(String nome, String localizacao, String descricao) {
        this.nome = nome;
        this.localizacao = localizacao;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}