package com.fernando.crud_java2.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "contato")
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private TipoContato tipoContato;

    @Column(nullable = false)
    private String contato;

    @ManyToOne
    @JoinColumn(name = "matricula", referencedColumnName = "matricula", nullable = false)
    @JsonBackReference
    private Pessoa pessoa;

    public Contato() {
    }

    public Contato(TipoContato tipoContato, String contato, Pessoa pessoa) {
        this.tipoContato = tipoContato;
        this.contato = contato;
        this.pessoa = pessoa;
    }

    // GET e SET
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoContato getTipoContato() {
        return tipoContato;
    }

    public void setTipoContato(TipoContato tipoContato) {
        this.tipoContato = tipoContato;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
