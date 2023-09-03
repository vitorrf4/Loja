package org.loja.classes;

import javax.persistence.*;

@Entity
@Table(name = "movimentos_produto")
public class MovimentoProduto {
    public enum Movimentos {
        ENTRADA,
        SAIDA
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movimento", nullable = false)
    private int idMovimento;

    @ManyToOne(targetEntity = Produto.class)
    @JoinColumn(name = "id_produto", nullable = false)
    private Produto produto;

    @Column(name = "quantidade_movimento", nullable = false)
    private int quantidadeMovimento;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_movimento",  nullable = false)
    private Movimentos tipoMovimento;

    public MovimentoProduto() {
    }

    public MovimentoProduto(Produto produto, int quantidadeMovimento, Movimentos tipoMovimento) {
        this.produto = produto;
        this.quantidadeMovimento = quantidadeMovimento;
        this.tipoMovimento = tipoMovimento;
    }

    public int getIdMovimento() {
        return idMovimento;
    }

    public void setIdMovimento(int idMovimento) {
        this.idMovimento = idMovimento;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidadeMovimento() {
        return quantidadeMovimento;
    }

    public void setQuantidadeMovimento(int quantidadeMovimento) {
        this.quantidadeMovimento = quantidadeMovimento;
    }

    public Movimentos getTipoMovimento() {
        return tipoMovimento;
    }

    public void setTipoMovimento(Movimentos tipoMovimento) {
        this.tipoMovimento = tipoMovimento;
    }

    @Override
    public String toString() {
        return "ID Movimento: " + idMovimento +
                " | Produto: " + produto.getIdProduto() +
                " | Quantidade: " + quantidadeMovimento +
                " | Tipo: " + tipoMovimento;
    }
}
