package com.api.produtos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.produtos.model.ProdutoModel;
import com.api.produtos.model.RespostaModel;
import com.api.produtos.service.ProdutoService;

@RestController
@CrossOrigin(origins = "*")
public class ProdutoController {
    
    @Autowired
    private ProdutoService produtoService;

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody ProdutoModel produtoModel){

        return produtoService.cadastrarAlterar(produtoModel, "cadastrar");
    }

    @PutMapping("/alterar")
    public ResponseEntity<?> alterar(@RequestBody ProdutoModel produtoModel){

        return produtoService.cadastrarAlterar(produtoModel, "alterar");
    }

    @GetMapping("/listar")
    public Iterable<ProdutoModel> listar(){
        return produtoService.listar();
    }

    @GetMapping("/")
    public String rota(){

        return "API de produtos funcionando!";
    }

    @DeleteMapping("/remover/{code}")
    public ResponseEntity<RespostaModel> excluir(@PathVariable long code){

        return produtoService.remover(code);
    }
}
