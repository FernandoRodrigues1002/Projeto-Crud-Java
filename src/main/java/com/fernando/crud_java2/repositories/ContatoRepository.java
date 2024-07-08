package com.fernando.crud_java2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fernando.crud_java2.entities.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long> {
    List<Contato> findByPessoaId(Long pessoaId);
}
