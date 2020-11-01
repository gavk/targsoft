package ru.gavk.targsoft;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class TransactionsTest {
    private final String transactionFileName = "transaction.csv";
    private final String fromDate = "20/08/2018 12:00:00";
    private final String toDate = "20/08/2018 13:00:00";
    private final String merchant = "Kwik-E-Mart";
    private Transactions transactions;

    @Before
    public void before() throws IOException {
        transactions = new Transactions();
        transactions.loadFromFile(transactionFileName);
    }

    @Test
    public void ApplyFilterCountTest() {
        transactions.applyFilter(fromDate, toDate, merchant);
        assertEquals(1, transactions.getCount());
    }

    @Test
    public void applyFilterAverageCost() {
        transactions.applyFilter(fromDate, toDate, merchant);
        assertEquals(new BigDecimal("59.99"), transactions.getAverage());
    }
}