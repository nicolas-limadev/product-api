package com.api.produtos.Repository;

import org.springframework.data.repository.CrudRepository;

import com.api.produtos.model.ProdutoModel;

public interface ProdutoRepository extends CrudRepository <ProdutoModel, Long>{

}
