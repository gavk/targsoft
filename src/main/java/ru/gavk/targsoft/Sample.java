package ru.gavk.targsoft;

import java.io.IOException;

public class Sample {
    public static void main(String[] args) throws IOException {
        String transactionFileName = "transaction.csv";
        String fromDate = "20/08/2018 12:00:00";
        String toDate = "20/08/2018 13:00:00";
        String merchant = "Kwik-E-Mart";
        Transactions transactions = new Transactions();
        transactions.loadFromFile(transactionFileName);
        transactions.applyFilter(fromDate, toDate, merchant).forEach(System.out::println);
    }
}
