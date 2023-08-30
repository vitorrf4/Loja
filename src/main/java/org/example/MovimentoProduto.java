package org.example;

import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;

@Entity
public class MovimentoProduto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_movimento", nullable = false)
    private int id;

    @Column(name = "codigo_produto", nullable = false)
    private int codigoProduto;

    @Column(name = "quantidade_movimento", nullable = false)
    private int quantidadeMovimento;

    @Column(name = "tipo_movimento", nullable = false)
    private String tipoMovimento;

    public MovimentoProduto() {
    }

    public MovimentoProduto(int codigoProduto, int quantidadeMovimento, String tipoMovimento) {
        this.codigoProduto = codigoProduto;
        this.quantidadeMovimento = quantidadeMovimento;
        this.tipoMovimento = tipoMovimento;
    }
}
