package lab1a.productmgmtapp;

import lab1a.productmgmtapp.model.Product;

import java.util.Arrays;
import java.util.Comparator;

public class ProductMgmtApp {

    public static void main(String[] args) {
        Product[] products = {
                new Product(3128874119L, "Banana", java.sql.Date.valueOf("2023-01-24"), 124, 0.55),
                new Product(2927458265L, "Apple", java.sql.Date.valueOf("2022-12-09"), 18, 1.09),
                new Product(9189927460L, "Carrot", java.sql.Date.valueOf("2023-03-31"), 89, 2.99)
        };

        printProducts(products);
    }

    public static void printProducts(Product[] products) {

        Arrays.sort(products, Comparator.comparing(Product::getName));


        System.out.println("JSON-formatted list of all Products:");
        System.out.println("[");
        for (Product product : products) {
            System.out.println(productToJson(product));
        }
        System.out.println("]");
        System.out.println();


        System.out.println("XML-formatted list of all Products:");
        System.out.println("<? xml version='1.0' ?>");
        System.out.println("<products>");
        for (Product product : products) {
            System.out.println(productToXml(product));
        }
        System.out.println("</products>");
        System.out.println();


        System.out.println("and Comma-Separated Values (CSV)-formatted list of all Products:");
        for (Product product : products) {
            System.out.printf("%d,%s,%s,%d,%.2f%n", product.getProductId(), product.getName(), product.getDateSupplied(), product.getQuantityInStock(), product.getUnitPrice());
        }
    }

    private static String productToJson(Product product) {
        return "{\"productId\":" + product.getProductId() +
                ",\"name\":\"" + product.getName() +
                "\",\"dateSupplied\":\"" + product.getDateSupplied() +
                "\",\"quantityInStock\":" + product.getQuantityInStock() +
                ",\"unitPrice\":" + product.getUnitPrice() +
                "}";
    }

    private static String productToXml(Product product) {
        return "<Product>" +
                "<productId>" + product.getProductId() + "</productId>" +
                "<name>" + product.getName() + "</name>" +
                "<dateSupplied>" + product.getDateSupplied() + "</dateSupplied>" +
                "<quantityInStock>" + product.getQuantityInStock() + "</quantityInStock>" +
                "<unitPrice>" + product.getUnitPrice() + "</unitPrice>" +
                "</Product>";
    }
}
