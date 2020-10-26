package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Lista;
import com.example.demo.service.ListaService;

@RestController
@RequestMapping("lista")
public class ListaController {

	@Autowired
	private ListaService service;

	@CrossOrigin(origins = "*", maxAge = 7200)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public List<Lista> getAll() {
		return service.buscarTodos();

	}

	@CrossOrigin(origins = "*", maxAge = 7200)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Lista create(@RequestBody Lista lista) {
		return service.criarOuAtualizar(lista);

	}
	@CrossOrigin(origins = "*", maxAge = 7200)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
	public void delete(@PathVariable Long id) {
		service.delete(id);

	}

	@CrossOrigin(origins = "*", maxAge = 7200)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
	public Lista update(@RequestBody @Valid Lista lista) {

		return service.criarOuAtualizar(lista);

	}

}
