package com.projedata_informatica.demo.util;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FormatadorUtil {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DecimalFormat MONEY_FORMAT = new DecimalFormat("#,##0.00");

    public static String formatarData(LocalDate data) {
        return data.format(DATE_FORMAT);
    }

    public static String formatarMoeda(Number valor) {
        return MONEY_FORMAT.format(valor);
    }
}
