package ru.gavk.targsoft;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class RecordTest {
    private final String transaction = "WLMFRDGD, 20/08/2018 12:45:33, 59.99, Kwik-E-Mart, PAYMENT,";
    private final String reversalTransaction = "AKNBVHMN, 20/08/2018 13:14:11, 10.95, Kwik-E-Mart, REVERSAL, YGXKOEIA";

    private final Record record = new Record(transaction);
    private final Record reversalRecord = new Record(reversalTransaction);

    @Test
    public void testGetId() {
        assertEquals("WLMFRDGD", record.getId());
    }

    @Test
    public void testGetDateTime() {
        assertEquals("20/08/2018 12:45:33", record.getDateTime().format(Record.formatter));
    }

    @Test
    public void testGetCostTest() {
        assertEquals(new BigDecimal("59.99"), record.getCost());
    }

    @Test
    public void testGetSellerTest() {
        assertEquals("Kwik-E-Mart", record.getMerchant());
    }

    @Test
    public void testGetTypePaymentTest() {
        assertEquals("PAYMENT", record.getType().toString());
        assertNull(record.getReversal());
    }

    @Test
    public void testGetTypeReversalTest() {
        assertEquals("REVERSAL", reversalRecord.getType().toString());
        assertEquals("YGXKOEIA", reversalRecord.getReversal());
    }
}