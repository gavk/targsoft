package ru.gavk.targsoft;

import org.eclipse.collections.impl.collector.Collectors2;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

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
        List<Record> result = transactions.applyFilter(fromDate, toDate, merchant);
        assertEquals(1, result.size());
    }

    @Test
    public void applyFilterAverageCost() {
        List<Record> filterRecords = transactions.applyFilter(fromDate, toDate, merchant);
        BigDecimal average = filterRecords.stream()
                .collect(Collectors2.summarizingBigDecimal(Record::getCost)).getAverage();
        assertEquals(new BigDecimal("59.99"), average);
    }
}