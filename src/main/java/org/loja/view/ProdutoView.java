package org.loja.view;

import org.apache.commons.lang3.StringUtils;
import org.loja.classes.MovimentoProduto;
import org.loja.classes.Produto;
import org.loja.logic.ProdutoLogic;

import java.util.List;
import java.util.Scanner;

public class ProdutoView {
    Scanner entrada = new Scanner(System.in);
    ProdutoLogic logic = new ProdutoLogic();

    public void mostrarMenu() {
        Menu : while (true) {
            System.out.println(
                    """
                    Escolha uma opção do menu
                        1 - Cadastrar produto
                        2 - Movimentar produto
                        3 - Consultar produto
                        4 - Listar todos os produtos
                        0 - Sair
                    """
            );
            String opcao = entrada.nextLine();

            switch (opcao) {
                case "1" -> mostrarCadastrar();
                case "2" -> mostrarMovimentar();
                case "3" -> mostrarConsultar();
                case "4" -> mostrarTodosProdutosComQntd();
                case "0" -> { break Menu; }
                default -> System.out.println("Opção inválida");
            }
        }
    }

    public void mostrarCadastrar() {
        String nome;

        do {
            System.out.print("Digite o nome do produto: ");
            nome = entrada.nextLine();

            if (nome.isEmpty() || nome.isBlank())
                System.out.println("O nome deve ser preenchido");
        } while (nome.isEmpty() || nome.isBlank());

        Produto produto = new Produto(nome);

        if (logic.cadastrarProduto(produto)) {
            mostrarTodosProdutos();
            System.out.println("Produto cadastrado com sucesso");
        } else {
            System.out.println("Erro, transação mal-sucedida");
        }
    }

    public void mostrarMovimentar() {
        Produto prod = procurarProduto();

        if (prod == null) {
            System.out.println("Produto não encontrado");
            return;
        }

        MovimentoProduto.Movimentos tipoMovimento = receberTipoMovimento();

        int quantidade = receberQuantidadeMovimento();

        MovimentoProduto movimento = new MovimentoProduto(prod, quantidade, tipoMovimento);

        if (logic.movimentarProduto(movimento))
            System.out.println("Produto movimentado!");
        else
            System.out.println("Erro, movimento deixará o produto com um valor negativo");
    }

    public Produto procurarProduto() {
        String codigo;
        Produto prod = null;

        do {
            System.out.print("Digite o codigo do produto: ");
            codigo = entrada.nextLine();

            if (numeroEstaInvalido(codigo)) {
                System.out.println("Entrada inválida");
                continue;
            }

            prod = logic.consultarProduto(Integer.parseInt(codigo));
        } while (numeroEstaInvalido(codigo));

        return prod;
    }

    public MovimentoProduto.Movimentos receberTipoMovimento() {
        String tipoRecebido;
        MovimentoProduto.Movimentos tipoMovimento = null;

        do {
            System.out.println("Digite 0 para remover e 1 para adicionar:");
            tipoRecebido = entrada.nextLine();

            switch (tipoRecebido) {
                case "0" -> tipoMovimento = MovimentoProduto.Movimentos.SAIDA;
                case "1" -> tipoMovimento = MovimentoProduto.Movimentos.ENTRADA;
                default -> System.out.println("Entrada inválida");
            }
        } while (!(tipoRecebido.equals("0") || tipoRecebido.equals("1")));

        return tipoMovimento;
    }

    public int receberQuantidadeMovimento() {
        String quantidade;

        do {
            System.out.print("Digite a quantidade a ser movimentada: ");
            quantidade = entrada.nextLine();

            if (numeroEstaInvalido(quantidade))
                System.out.println("Entrada inválida");
        } while (numeroEstaInvalido(quantidade));

        return Integer.parseInt(quantidade);
    }

    public void mostrarConsultar() {
        Produto prod = procurarProduto();

        if (prod == null)
            System.out.println("Produto não encontrado");
        else {
            int qntd = logic.calcularQntdProduto(prod.getIdProduto());
            System.out.println("Produto: " + prod.getNomeProduto() + " | Quantidade: " + qntd);
        }
    }

    public void mostrarTodosProdutos() {
        List<Produto> produtos = logic.listarProdutos();

        for (Produto p : produtos) {
            System.out.println(p);
        }
    }

    public void mostrarTodosProdutosComQntd() {
        List<Produto> produtos = logic.listarProdutos();

        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado");
            return;
        }

        for (Produto m : produtos) {
            System.out.println( m.toString() +
                    " | Qtde: " +
                    logic.calcularQntdProduto(m.getIdProduto()));
        }
    }

    public boolean numeroEstaInvalido(String num) {
        if (num.isEmpty() || !StringUtils.isNumeric(num) || Integer.parseInt(num) < 1)
            return true;

        return false;
    }
}
