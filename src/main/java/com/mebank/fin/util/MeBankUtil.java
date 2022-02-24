package com.mebank.fin.util;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class MeBankUtil {
  public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

  public static LocalDateTime getLocalDateTimeFromString(String input) {
    return LocalDateTime.parse(input, formatter);
  }

  public static long convertStringCurrencyToLongStorageInCents(String inputNumber){
    //Trying to retain as much precision as possible. BigDecimal will be a better option if more precision is needed, but for 2 digit fractional values, meh.
    double interimVal = Double.parseDouble(inputNumber);
    interimVal *= 100;
    return (long) interimVal;
  }

  public static String convertCentValueIntoDollarDisplay(long centValue){
    NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
    return nf.format(centValue / 100.0);
  }
}
