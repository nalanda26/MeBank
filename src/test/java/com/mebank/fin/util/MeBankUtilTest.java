package com.mebank.fin.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class MeBankUtilTest {

  @Test
  void testGetLocalDateTimeFromString(){
    Assertions.assertSame(LocalDateTime.class, MeBankUtil.getLocalDateTimeFromString("20/10/2018 19:45:00").getClass());
  }

  @Test
  void testStringToLongCurrencyConversion(){
    ;
    Assertions.assertEquals(2523l, MeBankUtil.convertStringCurrencyToLongStorageInCents("25.23"));
  }

  @Test
  void testLongToDollarDisplayConversion(){
    Assertions.assertEquals("$25.23", MeBankUtil.convertCentValueIntoDollarDisplay(2523));
  }

}