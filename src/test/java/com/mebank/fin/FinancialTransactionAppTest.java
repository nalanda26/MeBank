package com.mebank.fin;

import com.mebank.fin.model.TransactionModel;
import com.mebank.fin.service.BalanceCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class FinancialTransactionAppTest {

  @Test
  void testTransformation() {
    Stream<String> stringStream = Mockito.mock(Stream.class);
    Assertions.assertSame(ArrayList.class, FinancialTransactionApp.transformStreamToTransactions(stringStream).getClass());
  }

  @Test
  void testCreateTransaction() {
    String input = "TX10001,ACC998877,ACC778899,20/10/2018 18:00:00,8.50,PAYMENT";
    TransactionModel transactionModel = new TransactionModel(input.split(","));
    Assertions.assertEquals(850l, transactionModel.getAmount());
  }

  @Test
  void testUserInputToCalculator() {
    List<TransactionModel> transactionListWithReturns = new ArrayList<>();
    transactionListWithReturns.add(new TransactionModel("TX10001", "ACC998877", "ACC778899", "20/10/2018 18:00:00", "8.50", "PAYMENT"));
    transactionListWithReturns.add(new TransactionModel("TX10002", "ACC998877", "ACC778899", "20/10/2018 18:30:00", "12.75", "PAYMENT"));
    transactionListWithReturns.add(new TransactionModel("TX10003", "ACC778899", "ACC998877", "20/10/2018 19:30:00", "6.35", "PAYMENT"));
    transactionListWithReturns.add(new TransactionModel("TX10004", "ACC998877", "ACC778899", "20/10/2018 19:30:00", "12.75", "REVERSAL", "TX10002"));

    ByteArrayInputStream in = new ByteArrayInputStream("ACC778899\n20/10/2018 17:55:00\n20/10/2018 19:35:00".getBytes());
    Scanner consoleInput = new Scanner(in);
    BalanceCalculator rbc = new BalanceCalculator(transactionListWithReturns);
    Assertions.assertEquals("The Relative Balance for this period is $2.15 and the Number of Transactions included is: 2", FinancialTransactionApp.getOutput(consoleInput, rbc));
  }
}
