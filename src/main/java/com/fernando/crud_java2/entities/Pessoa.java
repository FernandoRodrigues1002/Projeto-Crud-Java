package com.fernando.crud_java2.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private int matricula;

    @Column(nullable = false)
    private String nome;

    private String endereco;
    private String cep;
    private String cidade;
    private String uf;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Contato> contatos;

    public Pessoa() {
    }

    public Pessoa(int matricula, String nome, String endereco, String cep, String cidade, String uf) {
        this.matricula = matricula;
        this.nome = nome;
        this.endereco = endereco;
        this.cep = cep;
        this.cidade = cidade;
        this.uf = uf;
    }

    // GET e SET
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public List<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return matricula == pessoa.matricula &&
               Objects.equals(id, pessoa.id) &&
               Objects.equals(nome, pessoa.nome) &&
               Objects.equals(endereco, pessoa.endereco) &&
               Objects.equals(cep, pessoa.cep) &&
               Objects.equals(cidade, pessoa.cidade) &&
               Objects.equals(uf, pessoa.uf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, matricula, nome, endereco, cep, cidade, uf);
    }

    @Override
    public String toString() {
        return "Pessoa{" +
               "id=" + id +
               ", matricula=" + matricula +
               ", nome='" + nome + '\'' +
               ", endereco='" + endereco + '\'' +
               ", cep='" + cep + '\'' +
               ", cidade='" + cidade + '\'' +
               ", uf='" + uf + '\'' +
               '}';
    }
}
