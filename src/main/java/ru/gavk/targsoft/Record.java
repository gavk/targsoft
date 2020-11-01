package ru.gavk.targsoft;


import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class Record {

    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy H:mm:ss");
    private final String id;
    private final LocalDateTime dateTime;
    private final BigDecimal cost;
    private final String merchant;
    private final Type type;
    private final String reversal;

    public Record(String record) {
        String[] array = record.split(",");
        id = array[0];
        dateTime = LocalDateTime.parse(array[1].trim(), formatter);
        cost = new BigDecimal(array[2].trim());
        merchant = array[3].trim();
        type = Type.valueOf(array[4].trim());
        reversal = array.length == 5 ? null : array[5].trim();
    }

    public String toString() {
        return String.format("%s, %s, %s, %s, %s, %s",
                id, dateTime.format(formatter), cost, merchant, type,
                reversal == null || reversal.isEmpty() ? "" : reversal).trim();
    }
}
