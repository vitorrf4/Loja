package org.example;

import jdk.jfr.Name;

import javax.persistence.*;
import javax.persistence.GeneratedValue;

@Entity
@Table(name="produtos")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="codigo_produto", nullable = false)
    private int codigoProduto;
    @Column(name="nome_produto", nullable = false)
    private String nomeProduto;

    public Produto() {
    }

    public Produto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public int getCodigoProduto() {
        return codigoProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }
}
