package com.example.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.model.Item;
import com.example.demo.model.Lista;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@SuppressWarnings("all")
public class ItemRepositoryTest {

	@Autowired
	ListaRepository listaRepository;

	@Autowired
	ItemRepository repository;

	private Lista lista;

	@Before
	@Transactional
	public void inicio() {
		lista = new Lista();
		lista.setNome("Lista 1");
		lista = listaRepository.save(lista);
	}

	@Test
	 @Transactional
	public void testfindAll() {
		List<Item> item = repository.findAll();
		Assert.assertNotNull(item);
	}

	@Test
	 @Transactional
	public void testfindByLista() {

		Item item = new Item();
		item.setNome("Item 1");
		item.setRealizada(false);
		item.setLista(lista);

		Item retorno = repository.save(item);

		List<Item> itens = repository.findAllByLista(lista);
		
		Assert.assertNotNull(itens);
		Assert.assertEquals(itens.size(), 1);
		
		
		
		Lista lista2=new Lista();
		lista2.setId(new Long(0));
		List<Item> itens2 = repository.findAllByLista(lista2);
		
		Assert.assertNotNull(itens2);
		Assert.assertEquals(itens2.size(), 0);
	}

	@Test
	@Transactional
	public void testInsert() {

		Item item = new Item();
		item.setNome("Item 1");
		item.setRealizada(false);
		item.setLista(lista);

		Item retorno = repository.save(item);

		Assert.assertNotNull(retorno);
		Assert.assertNotNull(retorno.getId());
	}

	@Test
	@Transactional
	public void testDelete() {
		Item item = new Item();
		item.setNome("Item 1");
		item.setRealizada(false);
		item.setLista(lista);
		Item retorno = repository.save(item);

		repository.delete(retorno);

		Boolean itemDeletada = repository.findById(retorno.getId()).isPresent();

		Assert.assertNotNull(retorno);
		Assert.assertFalse(itemDeletada);
	}

}
