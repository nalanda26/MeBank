package com.mebank.fin.service;

import com.mebank.fin.model.TransactionModel;
import com.mebank.fin.util.MeBankUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class BalanceCalculatorTest {
  private static List<TransactionModel> transactionList = new ArrayList<>();
  private static List<TransactionModel> transactionListWithReturns = new ArrayList<>();

  @BeforeAll
  static void setupTransactionList(){
    transactionList.add(new TransactionModel("TX10001", "ACC998877", "ACC778899", "20/10/2018 18:00:00", "8.50", "PAYMENT"));
    transactionList.add(new TransactionModel("TX10002", "ACC998877", "ACC778899", "20/10/2018 18:30:00", "12.75", "PAYMENT"));
    transactionList.add(new TransactionModel("TX10003", "ACC778899", "ACC998877", "20/10/2018 19:30:00", "6.35", "PAYMENT"));

    transactionListWithReturns.add(new TransactionModel("TX10001", "ACC998877", "ACC778899", "20/10/2018 18:00:00", "8.50", "PAYMENT"));
    transactionListWithReturns.add(new TransactionModel("TX10002", "ACC998877", "ACC778899", "20/10/2018 18:30:00", "12.75", "PAYMENT"));
    transactionListWithReturns.add(new TransactionModel("TX10003", "ACC778899", "ACC998877", "20/10/2018 19:30:00", "6.35", "PAYMENT"));
    transactionListWithReturns.add(new TransactionModel("TX10004", "ACC998877", "ACC778899", "20/10/2018 19:30:00", "12.75", "REVERSAL", "TX10002"));
  }

  @Test
  void testCalculatorForDebits(){
    BalanceCalculator rbc = new BalanceCalculator(transactionList);
    String value = rbc.calulateBalance("ACC998877", MeBankUtil.getLocalDateTimeFromString("20/10/2018 17:50:00"), MeBankUtil.getLocalDateTimeFromString("20/10/2018 18:35:00"));
    Assertions.assertEquals("The Relative Balance for this period is -$21.25 and the Number of Transactions included is: 2", value);
  }

  @Test
  void testCalculatorForCredits(){
    BalanceCalculator rbc = new BalanceCalculator(transactionList);
    String value = rbc.calulateBalance("ACC778899", MeBankUtil.getLocalDateTimeFromString("20/10/2018 17:50:00"), MeBankUtil.getLocalDateTimeFromString("20/10/2018 18:35:00"));
    Assertions.assertEquals("The Relative Balance for this period is $21.25 and the Number of Transactions included is: 2", value);
  }

  @Test
  void testCalculatorForDebitsAndCredits(){
    BalanceCalculator rbc = new BalanceCalculator(transactionList);
    String value = rbc.calulateBalance("ACC998877", MeBankUtil.getLocalDateTimeFromString("20/10/2018 18:25:00"), MeBankUtil.getLocalDateTimeFromString("20/10/2018 19:35:00"));
    Assertions.assertEquals("The Relative Balance for this period is -$6.40 and the Number of Transactions included is: 2", value);
  }

  @Test
  void testCalculatorForCreditAndDebits(){
    BalanceCalculator rbc = new BalanceCalculator(transactionList);
    String value = rbc.calulateBalance("ACC778899", MeBankUtil.getLocalDateTimeFromString("20/10/2018 18:25:00"), MeBankUtil.getLocalDateTimeFromString("20/10/2018 19:35:00"));
    Assertions.assertEquals("The Relative Balance for this period is $6.40 and the Number of Transactions included is: 2", value);
  }


  @DisplayName("Calculator should negate the Debit Calculations With Reversals")
  @Test
  void testCalculatorForDebitsWithReversals(){
    BalanceCalculator rbc = new BalanceCalculator(transactionListWithReturns);
    String value = rbc.calulateBalance("ACC998877", MeBankUtil.getLocalDateTimeFromString("20/10/2018 17:50:00"), MeBankUtil.getLocalDateTimeFromString("20/10/2018 18:35:00"));
    Assertions.assertEquals("The Relative Balance for this period is -$8.50 and the Number of Transactions included is: 1", value);
  }

  @Test
  void testCalculatorForCreditsWithReversals(){
    BalanceCalculator rbc = new BalanceCalculator(transactionListWithReturns);
    String value = rbc.calulateBalance("ACC778899", MeBankUtil.getLocalDateTimeFromString("20/10/2018 17:50:00"), MeBankUtil.getLocalDateTimeFromString("20/10/2018 18:35:00"));
    Assertions.assertEquals("The Relative Balance for this period is $8.50 and the Number of Transactions included is: 1", value);
  }

  @Test
  void testCalculatorForDebitsAndCreditsWithReverSals(){
    BalanceCalculator rbc = new BalanceCalculator(transactionListWithReturns);
    String value = rbc.calulateBalance("ACC998877", MeBankUtil.getLocalDateTimeFromString("20/10/2018 18:25:00"), MeBankUtil.getLocalDateTimeFromString("20/10/2018 19:35:00"));
    Assertions.assertEquals("The Relative Balance for this period is $6.35 and the Number of Transactions included is: 1", value);
  }

  @Test
  void testCalculatorForCreditAndDebitsWithReversals(){
    BalanceCalculator rbc = new BalanceCalculator(transactionListWithReturns);
    String value = rbc.calulateBalance("ACC778899", MeBankUtil.getLocalDateTimeFromString("20/10/2018 18:25:00"), MeBankUtil.getLocalDateTimeFromString("20/10/2018 19:35:00"));
    Assertions.assertEquals("The Relative Balance for this period is -$6.35 and the Number of Transactions included is: 1", value);
  }

  @Test
  void testCalculatorForAllWithReversalsDebit(){
    BalanceCalculator rbc = new BalanceCalculator(transactionListWithReturns);
    String value = rbc.calulateBalance("ACC998877", MeBankUtil.getLocalDateTimeFromString("20/10/2018 17:55:00"), MeBankUtil.getLocalDateTimeFromString("20/10/2018 19:35:00"));
    Assertions.assertEquals("The Relative Balance for this period is -$2.15 and the Number of Transactions included is: 2", value);
  }

  @Test
  void testCalculatorForAllWithReversalsCredit(){
    BalanceCalculator rbc = new BalanceCalculator(transactionListWithReturns);
    String value = rbc.calulateBalance("ACC778899", MeBankUtil.getLocalDateTimeFromString("20/10/2018 17:55:00"), MeBankUtil.getLocalDateTimeFromString("20/10/2018 19:35:00"));
    Assertions.assertEquals("The Relative Balance for this period is $2.15 and the Number of Transactions included is: 2", value);
  }

}
