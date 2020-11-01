package ru.gavk.targsoft;

import org.eclipse.collections.impl.collector.Collectors2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Transactions {
    private final List<Record> transactions = new ArrayList<>();
    private List<Record> result;

    public void loadFromFile(String fileName) throws IOException {
        try (FileInputStream fis = new FileInputStream(fileName)) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            reader.readLine();
            while (reader.ready()) {
                transactions.add(new Record(reader.readLine()));
            }
        }
    }

    public void clear() {
        transactions.clear();
    }

    public void applyFilter(String fromDateTime, String toDateTime, String merchant) {
        LocalDateTime from = fromDateTime == null || fromDateTime.isEmpty()
                ? LocalDateTime.MIN : LocalDateTime.parse(fromDateTime, Record.formatter);
        LocalDateTime to = fromDateTime == null || fromDateTime.isEmpty()
                ? LocalDateTime.MAX : LocalDateTime.parse(toDateTime, Record.formatter);
        List<String> reversalRecords = transactions.stream()
                .filter(record -> record.getType().equals(Type.REVERSAL))
                .map(Record::getReversal)
                .collect(toList());

        result = transactions.stream()
                .filter(record -> record.getMerchant() == null
                        || record.getMerchant().isEmpty()
                        || !reversalRecords.contains(record.getId())
                        && record.getMerchant().equals(merchant)
                        && !record.getDateTime().isBefore(from)
                        && !record.getDateTime().isAfter(to)
                        && record.getType().equals(Type.PAYMENT))
                .collect(toList());
    }

    public int getCount() {
        return result.size();
    }

    public BigDecimal getAverage() {
        return result.stream()
                .collect(Collectors2.summarizingBigDecimal(Record::getCost)).getAverage();
    }
}
