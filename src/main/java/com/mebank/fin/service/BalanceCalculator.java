package com.mebank.fin.service;

import com.mebank.fin.model.TransactionModel;
import com.mebank.fin.model.TransactionTypes;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

public class BalanceCalculator {
  private List<TransactionModel> transactionList;

  public BalanceCalculator(List<TransactionModel> transactionList) {
    this.transactionList = transactionList;
  }

  public List<TransactionModel> getTransactionList() {
    return transactionList;
  }


  public String calulateBalance(String accountID, LocalDateTime fromDateTime, LocalDateTime toDateTime){
    long relativeBalance = 0;
    long reversalBalance = 0;
    int transactionCounter = 0;
    for (TransactionModel transaction : transactionList){
      if(transaction.getCreatedAt().isAfter(fromDateTime) && transaction.getCreatedAt().isBefore(toDateTime) && transaction.getTransactionType().equals(TransactionTypes.PAYMENT)){
        if(transaction.getFromAccountID().equals(accountID)){
          relativeBalance -= transaction.getAmount();
          transactionCounter++;
        }else if(transaction.getToAccountID().equals(accountID)){
          relativeBalance += transaction.getAmount();
          transactionCounter++;
        }
      }
      else if(transaction.getTransactionType().equals(TransactionTypes.REVERSAL)){
        if(transaction.getFromAccountID().equals(accountID)){
          reversalBalance += transaction.getAmount();
          transactionCounter--;
        }else if(transaction.getToAccountID().equals(accountID)){
          reversalBalance -= transaction.getAmount();
          transactionCounter--;
        }
      }
    }
    relativeBalance += reversalBalance;
    return "The Relative Balance for this period is " + convertCentValueIntoDollarDisplay(relativeBalance) + " and the Number of Transactions included is: " + transactionCounter;
  }

  public static String convertCentValueIntoDollarDisplay(long centValue){
    NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
    return nf.format(centValue / 100.0);
  }
}
