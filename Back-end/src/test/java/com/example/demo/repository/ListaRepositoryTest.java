package com.example.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.model.Lista;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@SuppressWarnings("all")
public class ListaRepositoryTest {

    @Autowired
    ListaRepository repository;

    @Test
    @Transactional
    public void testfindAll() {
        List<Lista> lista = repository.findAll();
        Assert.assertNotNull(lista);
    }

    @Test
    @Transactional
    public void testInsert() {
        Lista lista = new Lista();
        lista.setNome("Lista 1");

        Lista retorno= repository.save(lista);
        
        Assert.assertNotNull(retorno);
        Assert.assertNotNull(retorno.getId());
    }
    
    @Test
    @Transactional
    public void testDelete() {
        Lista lista = new Lista();
        lista.setNome("Lista 1");
        Lista retorno= repository.save(lista);
        
        repository.delete(retorno);
        
        Boolean listaDeletada=repository.findById(retorno.getId()).isPresent();
        
        Assert.assertNotNull(retorno);
        Assert.assertFalse(listaDeletada);
    }

}
