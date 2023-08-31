package org.example;

import java.util.Scanner;

public class ProdutoView {
    Scanner entrada = new Scanner(System.in);

    public void mostrarMenu() {
        menu : while(true){
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

            switch(opcao) {
                case "1" -> mostrarCadastrar();
                case "2" -> mostrarAdicionar();
                case "3" -> mostrarRetirar();
                case "4" -> mostrarConsultar();
                case "0" -> { break menu; }
                default -> System.out.println("Opção inválida");
            }
        }
    }

    public void mostrarCadastrar() {
        String nome;


        do {
            System.out.println("Digite o nome do Produto: ");
            nome = entrada.nextLine();

            if (nome.isEmpty())
                System.out.println("O nome deve ser preenchido");
        } while (nome.isEmpty());

        Produto produto = new Produto(nome);

        //banco.persistirProduto(produto);
    }
    public void mostrarAdicionar() {
        String codigoProduto, quantidadeMovimento;

        System.out.println("Digite o codigo do Produto para inserir: ");
        codigoProduto = entrada.nextLine();

        System.out.println("Digite a quantidade para inserir: ");
        quantidadeMovimento = entrada.nextLine();
    }
    public void mostrarRetirar() {
        System.out.println("Digite o codigo do Produto para remover: ");
        String codigoProduto = entrada.nextLine();

        System.out.println("Digite a quantidade para remover: ");
        String quantidadeMovimento = entrada.nextLine();
    }

    public void mostrarConsultar() {
        System.out.println("Digite o codigo do Produto: ");
        String codigoProduto = entrada.nextLine();

    }
}
