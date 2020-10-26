package com.example.demo.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Lista;
import com.example.demo.repository.ListaRepository;

@Service
public class ListaService {

	@Autowired
	private ListaRepository repository;

	public List<Lista> buscarTodos() {
		return repository.findAll();

	}

	public Lista criarOuAtualizar(Lista lista) {
		return repository.save(lista);
		
	}

	public void delete(Long id) {
		Lista lista=repository.getOne(id);
		repository.delete(lista);
		
	}

	

}
