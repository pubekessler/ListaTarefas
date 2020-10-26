package com.example.demo.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Lista;

@Repository
public interface ListaRepository extends JpaRepository<Lista, Long> {

}
