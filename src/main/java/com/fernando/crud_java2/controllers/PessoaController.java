package com.fernando.crud_java2.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fernando.crud_java2.dto.PessoaMalaDiretaDTO;
import com.fernando.crud_java2.entities.Pessoa;
import com.fernando.crud_java2.repositories.PessoaRepository;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(value = "/api/pessoas")
public class PessoaController {

	@Autowired
	private PessoaRepository repository;
	
	@Operation(summary = "Listagem de todos os objetos Pessoa e contatos", method ="GET")
	@GetMapping
	public List<Pessoa> findAll() {
		List<Pessoa> result = repository.findAll();
		return result;
	}
	
	@Operation(summary = "Busca de um objeto Pessoa por seu id", method ="GET")
	@GetMapping(value = "/{id}")
	public ResponseEntity<Pessoa> buscaId(@PathVariable Long id) {
		Optional<Pessoa> pessoa = repository.findById(id);
		return pessoa.map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@Operation(summary = "Busca de um objeto Pessoa por seu id, utilizando mala direta", method ="GET")
	@GetMapping("/maladireta/{id}")
	public ResponseEntity<PessoaMalaDiretaDTO> getPessoaMalaDiretaById(@PathVariable Long id) {
		Optional<Pessoa> pessoa = repository.findById(id);
		return pessoa.map(p -> {
			PessoaMalaDiretaDTO dto = new PessoaMalaDiretaDTO(p.getId(), p.getNome(), p.getEndereco(), p.getCep(),
					p.getCidade(), p.getUf());
			return ResponseEntity.ok(dto);
		}).orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@Operation(summary = "Adiciona uma nova pessoa no banco de dados", method ="POST")
	@PostMapping
	public ResponseEntity<Pessoa> insert(@RequestBody Pessoa pessoa) {
		Pessoa newPessoa = repository.save(pessoa);
		return new ResponseEntity<>(newPessoa, HttpStatus.CREATED);
	}
	
	@Operation(summary = "Atualiza as informações de uma pessoa através do seu id", method ="PUT")
	@PutMapping("/{id}")
	public ResponseEntity<Pessoa> editPessoa(@PathVariable Long id, @RequestBody Pessoa pessoaDetails) {
		Optional<Pessoa> pessoa = repository.findById(id);
		if (pessoa.isPresent()) {
			Pessoa p = pessoa.get();
			p.setNome(pessoaDetails.getNome());
			p.setEndereco(pessoaDetails.getEndereco());
			p.setCep(pessoaDetails.getCep());
			p.setCidade(pessoaDetails.getCidade());
			p.setUf(pessoaDetails.getUf());
			repository.save(p);
			return ResponseEntity.ok(p);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@Operation(summary = "Exlui uma pessoa através do seu id", method ="DELETE")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePessoa(@PathVariable Long id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
