package org.example;
import java.util.Scanner;

public class Main {

    static boolean menu = true;

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);
        Database banco = new Database();

        while(menu){
            System.out.println(
                    "Escolha uma opção do menu \n" +
                            "1 - Cadastrar Produto \n" +
                            "2 - Adicionar Produto \n" +
                            "3 - Excluir Produto \n" +
                            "0 - Sair \n"
            );
            int opcao = entrada.nextInt();

            switch(opcao){
                case 1:

                    String nome;

                    System.out.println("Digite o nome do Produto: ");
                    nome = entrada.nextLine();

                    Produto produto = new Produto(nome);

                    banco.persistirProduto(produto);

                    break;
                case 2:

                    int codigoProduto, quantidadeMovimento;

                    System.out.println("Digite o codigo do Produto para inserir: ");
                    codigoProduto = entrada.nextInt();

                    System.out.println("Digite a quantidade para inserir: ");
                    quantidadeMovimento = entrada.nextInt();


                    break;
                case 3:

                    System.out.println("Digite o codigo do Produto para remover: ");
                    codigoProduto = entrada.nextInt();

                    System.out.println("Digite a quantidade para remover: ");
                    quantidadeMovimento = entrada.nextInt();

                    break;

                case 0:
                    menu = false;
                    break;
                default:
                    System.out.println("Opção Invalida");
                    break;

            }
        }

    }
}