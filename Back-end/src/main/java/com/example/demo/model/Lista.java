package com.example.demo.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
public class Lista {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	//@NotNull(message = "Nome nao pode ser vazio.")
	private String nome;

	//@OneToMany(mappedBy = "lista", fetch = FetchType.LAZY)
	//private List<Item> itens;
}
