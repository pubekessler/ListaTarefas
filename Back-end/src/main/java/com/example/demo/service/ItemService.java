package com.example.demo.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Item;
import com.example.demo.model.Lista;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.ListaRepository;

@Service
public class ItemService {

	@Autowired
	private ItemRepository repository;

	public List<Item> buscarTodos() {
		return repository.findAll();

	}

	public Item criar(Item item) {
		item.setRealizada(false);
		return repository.save(item);

	}

	public Item atualizar(Item item) {
		return repository.save(item);

	}

	public void delete(Long id) {
		Item item=repository.getOne(id);
		repository.delete(item);;

	}

	public Item setarItemRealizado(Long id) {

		Item item = repository.getOne(id);
		item.setRealizada(true);
		return atualizar(item);

	}

	public List<Item> buscarPorLista(Long id) {
		Lista lista=new Lista();
		lista.setId(id);
		return repository.findAllByLista(lista);
		
	}

}
