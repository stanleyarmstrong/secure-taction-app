package com.secure.taction.SeniorProject.repositories;

import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.secure.taction.SeniorProject.dtos.transaction.TransactionDto;
import com.secure.taction.SeniorProject.models.Transaction;

public class TransactionRepository {

    public Transaction findByIdAndUserId(GetItemSpec spec) {
        return null;
    }

    public Transaction save(Transaction transaction) {
        return null;
    }

    public Transaction update(TransactionDto transactionDto) {
        return null;
    }

    public void deleteByIdAndAccountId(DeleteItemSpec spec) {
    }
    
}
