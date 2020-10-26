package com.example.demo.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import javax.transaction.Transactional;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.demo.exception.ItemNullException;
import com.example.demo.model.Item;
import com.example.demo.model.Lista;
import com.example.demo.service.ItemService;
import com.example.demo.service.ListaService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@SuppressWarnings("all")
public class ItemControllerTest {

	private String URL_FIND_ALL_CLASSES = "http://localhost:8090/classe";

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	ListaService listaService;

	@Autowired
	ItemService service;

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
	public void testGetall() throws Exception {

		mvc.perform(MockMvcRequestBuilders.get("/item").header("Origin", "*").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());

	}

	@Test
	@Transactional
	public void testCreate() throws Exception {

		JSONObject lista = new JSONObject();
		lista.put("id", this.lista.getId());

		JSONObject item = new JSONObject();
		item.put("nome", "Item 1");
		item.put("lista", lista);

		mvc.perform(MockMvcRequestBuilders.post("/item").header("Origin", "*").contentType(MediaType.APPLICATION_JSON)
				.content(item.toString()).accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk())
				.andExpect(content().json(item.toString()));

	}

	@Test
	@Transactional
	public void testDelete() throws Exception {

		Item newItem = new Item();
		newItem.setNome("Item 1");

		Item itemRetorno = service.criar(newItem);

		Assert.assertNotNull(itemRetorno);
		Assert.assertNotNull(itemRetorno.getId());

		mvc.perform(MockMvcRequestBuilders.delete("/item/" + itemRetorno.getId()).header("Origin", "*")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk());

	}

	@Test
	@Transactional
	public void testSetarItemRealizado() throws Exception {

		Item newItem = new Item();
		newItem.setNome("Item 1");
		newItem.setLista(lista);

		Item ItemRetorno = service.criar(newItem);

		Assert.assertNotNull(ItemRetorno);
		Assert.assertNotNull(ItemRetorno.getId());
		Assert.assertFalse(ItemRetorno.getRealizada());

		mvc.perform(MockMvcRequestBuilders.post("/item/concluir/" + ItemRetorno.getId()).header("Origin", "*")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk());

	}

	@Test
	@Transactional
	public void testBuscarPorLista() throws Exception {

		Item newItem = new Item();
		newItem.setNome("Item 1");
		newItem.setLista(lista);

		Item ItemRetorno = service.criar(newItem);

		Assert.assertNotNull(ItemRetorno);
		Assert.assertNotNull(ItemRetorno.getId());
		Assert.assertFalse(ItemRetorno.getRealizada());

		mvc.perform(MockMvcRequestBuilders.get("/item/porLista/" + ItemRetorno.getId()).header("Origin", "*")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk());

		mvc.perform(MockMvcRequestBuilders.get("/item/porLista/0").header("Origin", "*")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andExpect(content().json("[]"));

	}

}
