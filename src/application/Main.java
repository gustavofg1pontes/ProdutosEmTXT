package application;

import entities.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class Main {
    public static void main(String[] args) {
        ArrayList<Product> products = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite o caminho do arquivo: ");
        String path = sc.nextLine();

        while (true) {
            //try with resources (leitor)
            try (BufferedReader br = new BufferedReader(new FileReader(path))) {
                String linha = br.readLine();
                //adiciona na lista de produtos e passa pra proxima linha
                while (linha != null) {
                    System.out.println(linha);
                    String[] fields = linha.split(",");
                    String name = fields[0];
                    double price = parseDouble(fields[1]);
                    int quantity = parseInt(fields[2]);
                    products.add(new Product(name, price, quantity));
                    linha = br.readLine();
                }

                System.out.println("Adicionar produto:");
                System.out.print("NOME: ");
                String nome = sc.nextLine();
                System.out.print("PREÇO: ");
                double preco = sc.nextDouble();
                System.out.print("QUANTIDADE: ");
                int quant = sc.nextInt();

                products.add(new Product(nome, preco, quant));

                try(BufferedWriter bw = new BufferedWriter(new FileWriter(path))){
                    for(Product p : products){
                        bw.write(p.getName() + "," +p.getPrice() + "," + p.getQuantity() );
                        bw.newLine();
                    }
                }catch(IOException e){
                    System.out.println("ERRO: " + e.getMessage());
                }

            } catch (IOException e) {
                System.out.println("ERRO: " + e.getMessage());
            }
        }

    }
}