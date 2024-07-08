package com.fernando.crud_java2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fernando.crud_java2.entities.Pessoa;

public interface PessoaRepository extends JpaRepository< Pessoa, Long> {

}
