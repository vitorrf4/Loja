package org.loja.logic;

import org.loja.classes.MovimentoProduto;
import org.loja.classes.Produto;
import org.loja.repos.Database;

import java.util.List;

public class ProdutoLogic {
    Database db = new Database();

    public void cadastrarProduto (Produto produto) {
        db.persistirProduto(produto);
    }

    public boolean movimentarProduto (MovimentoProduto movimento) {
        if (db.consultarProduto(movimento.getProduto().getIdProduto()) == null ){
            return false;
        }
        db.persistirMovimento(movimento);
        return true;
    }

    public Produto consultarProduto (int id) {
        Produto prod = db.consultarProduto(id);

        return prod;
    }

    public int calcularQntdProduto(int id) {
//        Produto prod = db.consultarProduto(id);
        List<MovimentoProduto> movimentos = db.consultarTodosMovimentos();
        int qntdTotal = 0;

        for (MovimentoProduto m : movimentos) {
            if (m.getProduto().getIdProduto() == id) {
                if (m.getTipoMovimento() == MovimentoProduto.Movimentos.ENTRADA) {
                    qntdTotal += m.getQuantidadeMovimento();
                } else {
                    qntdTotal -= m.getQuantidadeMovimento();
                }
            }
        }
        return qntdTotal;

    }
}
