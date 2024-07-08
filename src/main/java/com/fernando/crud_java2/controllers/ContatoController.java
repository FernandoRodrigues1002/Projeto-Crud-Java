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

import com.fernando.crud_java2.entities.Contato;
import com.fernando.crud_java2.entities.Pessoa;
import com.fernando.crud_java2.repositories.ContatoRepository;
import com.fernando.crud_java2.repositories.PessoaRepository;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(value = "/api/contatos")
public class ContatoController {

	@Autowired
	private ContatoRepository contatoRepository;

	@Autowired
	private PessoaRepository pessoaRepository;

	@Operation(summary = "Retorna as informações de u contato com seu id", method ="GET")
	@GetMapping(value = "/{id}")
	public ResponseEntity<Contato> getContatoById(@PathVariable Long id) {
		Optional<Contato> contato = contatoRepository.findById(id);
		return contato.map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@Operation(summary = "Retorna todos os contatos de uma pessoa com o Id da pessoa", method ="GET")
	@GetMapping(value = "/pessoas/{idPessoa}")
	public ResponseEntity<List<Contato>> getContatosByPessoaId(@PathVariable Long idPessoa) {
		List<Contato> contatos = contatoRepository.findByPessoaId(idPessoa);
		return ResponseEntity.ok(contatos);
	}

	@Operation(summary = "Adiciona um novo contato a um pessoa", method ="POST")
	@PostMapping("/{idPessoa}")
	public ResponseEntity<Contato> addContato(@PathVariable Long idPessoa, @RequestBody Contato contato) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(idPessoa);
		if (pessoa.isPresent()) {
			contato.setPessoa(pessoa.get());
			Contato newContato = contatoRepository.save(contato);
			return new ResponseEntity<>(newContato, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@Operation(summary = "Atualiza o contato de uma pessoa existente", method ="PUT")
	@PutMapping("/{id}")
	public ResponseEntity<Contato> updateContato(@PathVariable Long id, @RequestBody Contato contatoDetails) {
		Optional<Contato> contato = contatoRepository.findById(id);
		if (contato.isPresent()) {
			Contato c = contato.get();
			c.setTipoContato(contatoDetails.getTipoContato());
			c.setContato(contatoDetails.getContato());
			contatoRepository.save(c);
			return ResponseEntity.ok(c);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@Operation(summary = "Deleta um contato através do seu id", method ="DELETE")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteContato(@PathVariable Long id) {
		if (contatoRepository.existsById(id)) {
			contatoRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
