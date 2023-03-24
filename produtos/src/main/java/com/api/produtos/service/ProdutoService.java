package com.api.produtos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.produtos.Repository.ProdutoRepository;
import com.api.produtos.model.ProdutoModel;
import com.api.produtos.model.RespostaModel;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private RespostaModel respostaModel;

    // Listar
    public Iterable<ProdutoModel> listar() {
        return produtoRepository.findAll();
    }

    // Método para cadastrar ou alterar produtos
    public ResponseEntity<?> cadastrarAlterar(ProdutoModel produtoModel, String acao) {

        if (produtoModel.getName().equals("")) {
            respostaModel.setMessage("O nome do produto é obrigatório!");
            return new ResponseEntity<RespostaModel>(respostaModel, HttpStatus.BAD_REQUEST);

        } else if (produtoModel.getMarca().equals("")) {
            respostaModel.setMessage("O nome da marca é obrigatório!");
            return new ResponseEntity<RespostaModel>(respostaModel, HttpStatus.BAD_REQUEST);

        } else {
            if (acao.equals("cadastrar")) {
                return new ResponseEntity<ProdutoModel>(
                        produtoRepository.save(produtoModel), HttpStatus.CREATED);

            } else {
                return new ResponseEntity<ProdutoModel>(
                        produtoRepository.save(produtoModel), HttpStatus.OK);
            }
        }
    }

    // Método para remover\
    public ResponseEntity<RespostaModel> remover(long code) {

        produtoRepository.deleteById(code);
        respostaModel.setMessage("O produto foi removido com sucesso!");

        return new ResponseEntity<RespostaModel>(respostaModel, HttpStatus.OK);

    }
}
