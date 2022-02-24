package com.mebank.fin;

import com.mebank.fin.model.TransactionModel;
import com.mebank.fin.service.BalanceCalculator;
import com.mebank.fin.util.MeBankUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class FinancialTransactionApp {
  private static Scanner scanner = new Scanner(System.in);

  public static void main(String args[]) {
    String fileName = args[0];
    Stream<String> streamedFile = null;
    try {
      streamedFile = getStreamFromFile(fileName);
    }catch (IOException ioe){
      ioe.printStackTrace();
      System.exit(1);
    }
    List<TransactionModel> transactionList = transformStreamToTransactions(streamedFile);
    BalanceCalculator bc = new BalanceCalculator(transactionList);
    System.out.println(getOutput(scanner, bc));
    System.exit(0);

  }

  private static Stream<String> getStreamFromFile(String fileNameWithPath) throws IOException {
    Stream<String> transactions = Files.lines(Paths.get(fileNameWithPath));
    return transactions;
  }

  public static List<TransactionModel> transformStreamToTransactions(Stream<String> transactions) {
    List<TransactionModel> transactionList = new ArrayList<>();
    transactions.forEach(transaction ->  transactionList.add(new TransactionModel(transaction.split(","))));
    return transactionList;
  }

  public static String getOutput(Scanner scanner, BalanceCalculator bc){
    System.out.println("Enter the Account ID for Balance Calculation: ");
    String accountID = scanner.nextLine();
    System.out.println("Enter the From Date for Balance Calculation: ");
    LocalDateTime fromDate = MeBankUtil.getLocalDateTimeFromString(scanner.nextLine());
    System.out.println("Enter the To Date for Balance Calculation: ");
    LocalDateTime toDate = MeBankUtil.getLocalDateTimeFromString(scanner.nextLine());
    return bc.calulateBalance(accountID, fromDate, toDate);
  }
}
