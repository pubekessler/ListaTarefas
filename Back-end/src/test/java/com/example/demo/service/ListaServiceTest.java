package com.example.demo.service;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.exception.ListaNullException;
import com.example.demo.model.Lista;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@SuppressWarnings("all")
public class ListaServiceTest {

	@Autowired
	ListaService service;

	@Test
	@Transactional
	public void testbuscarTodos() {

		List<Lista> lista = service.buscarTodos();

		Assert.assertNotNull(lista);

	}

	@Test
	@Transactional
	public void testCriarOuAtualizar() {

		Lista newLista = new Lista();
		newLista.setNome("Lista 1");

		Lista listaRetorno = service.criarOuAtualizar(newLista);

		Assert.assertNotNull(listaRetorno);
		Assert.assertNotNull(listaRetorno.getId());

	}

	@Test
	@Transactional
	public void testeDelete() throws ListaNullException {

		Lista newLista = new Lista();
		newLista.setNome("Lista 1");

		Lista listaRetorno = service.criarOuAtualizar(newLista);
		
		List<Lista> lista1 = service.buscarTodos();
		
		service.delete(listaRetorno.getId());
		
		List<Lista> lista2 = service.buscarTodos();

		Assert.assertNotNull(listaRetorno);
		Assert.assertNotNull(listaRetorno.getId());
		
		Assert.assertEquals(lista1.size(), 1);
		Assert.assertEquals(lista2.size(), 0);
		

	}

}
