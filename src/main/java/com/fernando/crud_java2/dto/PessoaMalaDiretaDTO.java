package com.fernando.crud_java2.dto;

public record PessoaMalaDiretaDTO(Long id, String nome, String malaDireta) {
    public PessoaMalaDiretaDTO(Long id, String nome, String endereco, String cep, String cidade, String uf) {
        this(id, nome, String.format("%s – CEP: %s – %s/%s", endereco, cep, cidade, uf));
    }
}