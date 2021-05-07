package com.secure.taction.SeniorProject.models;

import java.util.Map;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.secure.taction.SeniorProject.tablesetup.constants.TransactionTableConstants;

public class Transaction {
    
    private Item transactionItem;

    public Transaction() {}

    public Item getItem() {return transactionItem;}

    public void setItem(Item transactionItem) {this.transactionItem = transactionItem;}

    public Transaction withItem(Item transactionItem) {
        setItem(transactionItem);
        return this;
    }

    public Map<String, Object> getAttributes() {
        return transactionItem.asMap();
    }

    // Debugging method
    public Transaction withTransactionId(String transactionId) {
        transactionItem.withPrimaryKey(TransactionTableConstants.TRANSACTION_ID, transactionId);
        return this;
    }
}
