package org.example;

import javax.persistence.*;

@Entity
@Table(name = "MovimentoProduto")
public class MovimentoProduto {
    public enum Movimentos {
        ENTRADA,
        SAIDA
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_movimento", nullable = false)
    private int idMovimento;

    @ManyToOne(targetEntity = Produto.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "id_produto", referencedColumnName = "codigo_produto", nullable = false)
    private int produto;

    @Column(name = "quantidade_movimento", nullable = false)
    private int quantidadeMovimento;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_movimento", nullable = false)
    private Movimentos tipoMovimento;

    public MovimentoProduto() {
    }

    public MovimentoProduto(int codigoProduto, int quantidadeMovimento, Movimentos tipoMovimento) {
        this.produto = codigoProduto;
        this.quantidadeMovimento = quantidadeMovimento;
        this.tipoMovimento = tipoMovimento;
    }
}
