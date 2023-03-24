package com.api.produtos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "produtos")
@Getter
@Setter
public class ProdutoModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;
    private String name;
    private String marca;

}
