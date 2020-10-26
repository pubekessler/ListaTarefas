package com.example.demo.service;

import java.text.SimpleDateFormat;
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

import com.example.demo.exception.ItemNullException;
import com.example.demo.exception.ListaNullException;
import com.example.demo.model.Item;
import com.example.demo.model.Lista;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@SuppressWarnings("all")
public class ItemServiceTest {

	@Autowired
	ItemService service;

	@Autowired
	ListaService listaService;

	private Lista lista;

	@Before
	@Transactional
	public void inicio() {
		lista = new Lista();
		lista.setNome("Lista 1");
		lista = listaService.criarOuAtualizar(lista);
	}

	@Test
	@Transactional
	public void testbuscarTodos() {

		List<Item> item = service.buscarTodos();

		Assert.assertNotNull(item);

	}

	@Test
	@Transactional
	public void testCriar() {

		Item newItem = new Item();
		newItem.setNome("Item 1");
		newItem.setLista(lista);

		Item listaRetorno = service.criar(newItem);

		Assert.assertNotNull(listaRetorno);
		Assert.assertNotNull(listaRetorno.getId());

	}

	@Test
	@Transactional
	public void testAtualizar() {

		Item newItem = new Item();
		newItem.setNome("Item 1");
		newItem.setLista(lista);

		Item ItemRetorno = service.criar(newItem);

		Assert.assertNotNull(ItemRetorno);
		Assert.assertNotNull(ItemRetorno.getId());
		Assert.assertFalse(ItemRetorno.getRealizada());

		ItemRetorno.setRealizada(true);
		Item ItemAtualizada = service.atualizar(ItemRetorno);

		Assert.assertNotNull(ItemAtualizada);
		Assert.assertNotNull(ItemAtualizada.getId());
		Assert.assertTrue(ItemAtualizada.getRealizada());

	}

	@Test
	@Transactional
	public void testeDelete() throws ItemNullException {

		Item newItem = new Item();
		newItem.setNome("Item 1");
		newItem.setLista(lista);

		Item itemRetorno = service.criar(newItem);

		List<Item> item1 = service.buscarTodos();

		service.delete(itemRetorno.getId());

		List<Item> item2 = service.buscarTodos();

		Assert.assertNotNull(itemRetorno);
		Assert.assertNotNull(itemRetorno.getId());

		Assert.assertEquals(item1.size(), 1);
		Assert.assertEquals(item2.size(), 0);

	}

	@Test
	@Transactional
	public void testSetarItemRealizado() throws ItemNullException {

		Item newItem = new Item();
		newItem.setNome("Item 1");
		newItem.setLista(lista);

		Item ItemRetorno = service.criar(newItem);

		Assert.assertNotNull(ItemRetorno);
		Assert.assertNotNull(ItemRetorno.getId());
		Assert.assertFalse(ItemRetorno.getRealizada());

		Item ItemAtualizada = service.setarItemRealizado(ItemRetorno.getId());

		Assert.assertNotNull(ItemAtualizada);
		Assert.assertNotNull(ItemAtualizada.getId());
		Assert.assertTrue(ItemAtualizada.getRealizada());

	}

	@Test
	@Transactional
	public void testBuscarPorLista() throws ItemNullException, ListaNullException {

		Item item = new Item();
		item.setNome("Item 1");
		item.setRealizada(false);
		item.setLista(lista);

		Item retorno = service.criar(item);

		List<Item> itens = service.buscarPorLista(lista.getId());

		Assert.assertNotNull(itens);
		Assert.assertEquals(itens.size(), 1);

		Lista lista2 = new Lista();
		lista2.setId(new Long(0));
		List<Item> itens2 = service.buscarPorLista(lista2.getId());

		Assert.assertNotNull(itens2);
		Assert.assertEquals(itens2.size(), 0);

	}
}
