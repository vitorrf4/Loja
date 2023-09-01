package org.example;

import org.apache.commons.lang3.StringUtils;
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
                        2 - Adicionar produtos
                        3 - Retirar produtos
                        4 - Consultar produto
                        0 - Sair
                    """
            );
            String opcao = entrada.nextLine();

            switch (opcao) {
                case "1" -> mostrarCadastrar();
                case "2" -> mostrarAdicionar();
                case "3" -> mostrarRetirar();
                case "4" -> mostrarConsultar();
                case "0" -> { break Menu; }
                default -> System.out.println("Opção inválida");
            }
        }
    }

    public void mostrarCadastrar() {
        String nome;

        do {
            System.out.println("Digite o nome do Produto: ");
            nome = entrada.nextLine();

            if (nome.isEmpty() || nome.isBlank())
                System.out.println("O nome deve ser preenchido");
        } while (nome.isEmpty() || nome.isBlank());

        Produto produto = new Produto(nome);

        logic.cadastrarProduto(produto);
    }

    public void mostrarAdicionar() {
        String codigo, quantidade;
        Produto prod;

        do {
            System.out.println("Digite o codigo do produto: ");
            codigo = entrada.nextLine();

            prod = logic.consultarProduto(Integer.parseInt(codigo));

            if (verificarNumeroInvalido(codigo))
                System.out.println("Entrada inválida");
        } while (verificarNumeroInvalido(codigo));

        do {
            System.out.println("Digite a quantidade a ser adicionado: ");
            quantidade = entrada.nextLine();

            if (verificarNumeroInvalido(quantidade))
                System.out.println("Entrada inválida");
        } while (verificarNumeroInvalido(quantidade));

        MovimentoProduto movimento = new MovimentoProduto(
                prod, Integer.parseInt(quantidade), MovimentoProduto.Movimentos.ENTRADA);

        if (logic.movimentarProduto(movimento)) {
            System.out.println("Produto movimentado!");
        } else {
            System.out.println("Produto não encontrado");
        }

    }

    public void mostrarRetirar() {
        String codigo, quantidade;
        Produto prod;

        do {
            System.out.println("Digite o codigo do Produto para remover: ");
            codigo = entrada.nextLine();

            prod = logic.consultarProduto(Integer.parseInt(codigo));

            if (verificarNumeroInvalido(codigo))
                System.out.println("Entrada inválida");
        } while (verificarNumeroInvalido(codigo));

        do {
            System.out.println("Digite a quantidade para remover: ");
            quantidade = entrada.nextLine();

            if (verificarNumeroInvalido(quantidade))
                System.out.println("Entrada inválida");
        } while (verificarNumeroInvalido(quantidade));

        MovimentoProduto movimento = new MovimentoProduto
                (prod, Integer.parseInt(quantidade), MovimentoProduto.Movimentos.SAIDA);

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

            if (verificarNumeroInvalido(codigo))
                System.out.println("Entrada inválida");
        } while (verificarNumeroInvalido(codigo));

        Produto prod = logic.consultarProduto(Integer.parseInt(codigo));

        if (prod == null)
            System.out.println("Produto não encontrado");
        else {
            int qntd = logic.calcularQntdProduto(prod.getIdProduto());
            System.out.println("Produto: " + prod.getNomeProduto() + " | Quantidade: " + qntd);
        }
    }

    public boolean verificarNumeroInvalido(String num) {
        if (num.isEmpty() || !StringUtils.isNumeric(num) || Integer.parseInt(num) < 1)
            return true;

        return false;
    }
}
