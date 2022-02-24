package com.mebank.fin.model;

import com.mebank.fin.util.MeBankUtil;
import java.time.LocalDateTime;

public class TransactionModel {

  private String transactionID;
  private String fromAccountID;

  public String getTransactionID() {
    return transactionID;
  }

  public void setTransactionID(String transactionID) {
    this.transactionID = transactionID;
  }

  public String getFromAccountID() {
    return fromAccountID;
  }

  public void setFromAccountID(String fromAccountID) {
    this.fromAccountID = fromAccountID;
  }

  public String getToAccountID() {
    return toAccountID;
  }

  public void setToAccountID(String toAccountID) {
    this.toAccountID = toAccountID;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public long getAmount() {
    return amount;
  }

  public void setAmount(long amount) {
    this.amount = amount;
  }

  public TransactionTypes getTransactionType() {
    return transactionType;
  }

  public void setTransactionType(TransactionTypes transactionType) {
    this.transactionType = transactionType;
  }

  public String getRelatedTransaction() {
    return relatedTransaction;
  }

  public void setRelatedTransaction(String relatedTransaction) {
    this.relatedTransaction = relatedTransaction;
  }

  private String toAccountID;
  private LocalDateTime createdAt;
  private long amount;
  private TransactionTypes transactionType;
  private String relatedTransaction;

  public TransactionModel(String... transactionInfo) {
    this.transactionID = transactionInfo[0].strip();
    this.fromAccountID = transactionInfo[1].strip();
    this.toAccountID = transactionInfo[2].strip();
    this.createdAt = MeBankUtil.getLocalDateTimeFromString(transactionInfo[3].strip());
    this.amount = MeBankUtil.convertStringCurrencyToLongStorageInCents(transactionInfo[4].strip());
    this.transactionType = TransactionTypes.valueOf(transactionInfo[5].strip());
    if(this.transactionType.equals(TransactionTypes.REVERSAL)) {
      this.relatedTransaction = transactionInfo[6].strip();
    }
  }
}
