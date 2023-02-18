package application;

import entities.Product;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class Main {
    public static void main(String[] args) {
        ArrayList<Product> products = new ArrayList<Product>();
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite o caminho do arquivo: ");
        String path = sc.nextLine();

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
        } catch (IOException e) {
            System.out.println("Erro");
        }


    }
}