package org.example;

import org.apache.commons.lang3.StringUtils;
import java.util.Scanner;

public class ProdutoView {
    Scanner entrada = new Scanner(System.in);

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

        //banco.persistirProduto(produto);
    }

    public void mostrarAdicionar() {
        String codigo, quantidade;

        do {
            System.out.println("Digite o codigo do Produto para inserir: ");
            codigo = entrada.nextLine();

            if (verificarNumeroInvalido(codigo))
                System.out.println("Entrada inválida");
        } while (verificarNumeroInvalido(codigo));

        do {
            System.out.println("Digite a quantidade para inserir: ");
            quantidade = entrada.nextLine();

            if (verificarNumeroInvalido(quantidade))
                System.out.println("Entrada inválida");
        } while (verificarNumeroInvalido(quantidade));

    }

    public void mostrarRetirar() {
        String codigo, quantidade;

        do {
            System.out.println("Digite o codigo do Produto para remover: ");
            codigo = entrada.nextLine();


            if (verificarNumeroInvalido(codigo))
                System.out.println("Entrada inválida");
        } while (verificarNumeroInvalido(codigo));

        do {
            System.out.println("Digite a quantidade para remover: ");
            quantidade = entrada.nextLine();

            if (verificarNumeroInvalido(quantidade))
                System.out.println("Entrada inválida");
        } while (verificarNumeroInvalido(quantidade));
    }

    public void mostrarConsultar() {
        String codigo;

        do {
            System.out.println("Digite o codigo do Produto: ");
            codigo = entrada.nextLine();

            if (verificarNumeroInvalido(codigo))
                System.out.println("Entrada inválida");
        } while (verificarNumeroInvalido(codigo));

    }

    public boolean verificarNumeroInvalido(String num) {
        if (num.isEmpty() || !StringUtils.isNumeric(num) || Integer.parseInt(num) < 1)
            return true;

        return false;
    }
}
