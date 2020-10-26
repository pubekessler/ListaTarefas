package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ItemNullException;
import com.example.demo.exception.ListaNullException;
import com.example.demo.model.Item;
import com.example.demo.model.Lista;
import com.example.demo.repository.ItemRepository;

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

	public void delete(Long id) throws ItemNullException {
		try {
			Item item = repository.getOne(id);
			repository.delete(item);

		} catch (Exception e) {
			throw new ItemNullException();
		}

	}

	public Item setarItemRealizado(Long id) throws ItemNullException {
		Item item = null;
		try {
			item = repository.getOne(id);
		} catch (Exception e) {
			throw new ItemNullException();
		}

		item.setRealizada(true);
		return atualizar(item);

	}

	public List<Item> buscarPorLista(Long id) throws ListaNullException {
		try {

			Lista lista = new Lista();
			lista.setId(id);
			return repository.findAllByLista(lista);
		} catch (Exception e) {
			throw new ListaNullException();
		}

	}

}
