package org.loja.classes;

import javax.persistence.*;

@Entity
@Table(name = "produtos")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produto")
    private int idProduto;

    @Column(name = "nome_produto", nullable = false)
    private String nomeProduto;

    public Produto() {
    }

    public Produto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    @Override
    public String toString() {
        return "ID: " + idProduto + " | Nome: " + nomeProduto;
    }
}
