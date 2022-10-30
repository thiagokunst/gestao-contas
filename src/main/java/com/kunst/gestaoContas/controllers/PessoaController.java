package com.kunst.gestaoContas.controllers;

import com.kunst.gestaoContas.entities.Pessoa;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/pessoa")
public class PessoaController {

    public List<Pessoa> findAll(){

        return null;
    }
}
