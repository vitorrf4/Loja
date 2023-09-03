package org.loja.view;

import org.apache.commons.lang3.StringUtils;
import org.loja.classes.MovimentoProduto;
import org.loja.classes.Produto;
import org.loja.logic.ProdutoLogic;

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
                        0 - Sair
                    """
            );
            String opcao = entrada.nextLine();

            switch (opcao) {
                case "1" -> mostrarCadastrar();
                case "2" -> mostrarMovimentar();
                case "3" -> mostrarConsultar();
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

        if (logic.cadastrarProduto(produto))
            System.out.println("Produto cadastrado com sucesso");
        else
            System.out.println("Erro, transação mal-sucedida");
    }

    public void mostrarMovimentar() {
        String codigo, quantidade, tipoRecebido;
        Produto prod = null;
        MovimentoProduto.Movimentos tipoMovimento = null;

        do {
            System.out.print("Digite o codigo do produto: ");
            codigo = entrada.nextLine();

            if (numeroEstaInvalido(codigo)) {
                System.out.println("Entrada inválida");
                continue;
            }

            prod = logic.consultarProduto(Integer.parseInt(codigo));

            if (prod == null) {
                System.out.println("Produto não encontrado");
                return;
            }
        } while (numeroEstaInvalido(codigo));

        do {
            System.out.println("Digite 0 para remover e 1 para adicionar:");
            tipoRecebido = entrada.nextLine();

            switch (tipoRecebido) {
                case "0" -> { tipoMovimento = MovimentoProduto.Movimentos.SAIDA; }
                case "1" -> { tipoMovimento = MovimentoProduto.Movimentos.ENTRADA; }
                default -> { System.out.println("Entrada inválida"); }
            }
        } while (!(tipoRecebido.equals("0") || tipoRecebido.equals("1")));

        do {
            System.out.print("Digite a quantidade a ser movimentada: ");
            quantidade = entrada.nextLine();

            if (numeroEstaInvalido(quantidade))
                System.out.println("Entrada inválida");
        } while (numeroEstaInvalido(quantidade));

        MovimentoProduto movimento = new MovimentoProduto(prod, Integer.parseInt(quantidade), tipoMovimento);

        if (logic.movimentarProduto(movimento))
            System.out.println("Produto movimentado!");
        else
            System.out.println("Produto não encontrado");
    }

    public void mostrarConsultar() {
        String codigo;

        do {
            System.out.println("Digite o codigo do Produto: ");
            codigo = entrada.nextLine();

            if (numeroEstaInvalido(codigo))
                System.out.println("Entrada inválida");
        } while (numeroEstaInvalido(codigo));

        Produto prod = logic.consultarProduto(Integer.parseInt(codigo));

        if (prod == null)
            System.out.println("Produto não encontrado");
        else {
            int qntd = logic.calcularQntdProduto(prod.getIdProduto());
            System.out.println("Produto: " + prod.getNomeProduto() + " | Quantidade: " + qntd);
        }
    }

    public boolean numeroEstaInvalido(String num) {
        if (num.isEmpty() || !StringUtils.isNumeric(num) || Integer.parseInt(num) < 1)
            return true;

        return false;
    }
}
