package com.example.demo.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import javax.transaction.Transactional;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.demo.model.Lista;
import com.example.demo.service.ListaService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@SuppressWarnings("all")
public class ListaControllerTest {

	private String URL_FIND_ALL_CLASSES = "http://localhost:8090/classe";

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	ListaService service;

	@Test
	@Transactional
	public void testGetall() throws Exception {

		mvc.perform(MockMvcRequestBuilders.get("/lista").header("Origin", "*").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());

	}

	@Test
	@Transactional
	public void testCreate() throws Exception {

		JSONObject lista = new JSONObject();
		lista.put("nome", "Lista 1");

		mvc.perform(MockMvcRequestBuilders.post("/lista").header("Origin", "*").contentType(MediaType.APPLICATION_JSON)
				.content(lista.toString()).accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk())
				.andExpect(content().json(lista.toString()));

		Assert.assertTrue(true);
	}

	@Test
	@Transactional
	public void testDelete() throws Exception {

		Lista newLista = new Lista();
		newLista.setNome("Lista 1");

		Lista listaRetorno = service.criarOuAtualizar(newLista);

		Assert.assertNotNull(listaRetorno);
		Assert.assertNotNull(listaRetorno.getId());

		mvc.perform(MockMvcRequestBuilders.delete("/lista/" + listaRetorno.getId()).header("Origin", "*")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk());

	}

}
