package com.secure.taction.SeniorProject.dtos.transaction;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.secure.taction.SeniorProject.models.Transaction;
import com.secure.taction.SeniorProject.tablesetup.constants.TransactionTableConstants;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TransactionDtoToItem implements Converter<TransactionDto, Transaction> {

    @Override
    public Transaction convert(TransactionDto dto) {
        return new Transaction().withItem(
            new Item()
                .withPrimaryKey(TransactionTableConstants.TRANSACTION_ID, dto.getTransactionId())
                .with(TransactionTableConstants.ACCOUNT_ID, dto.getAccountId())
                .with(TransactionTableConstants.AMOUNT, dto.getAmount())
                .with(TransactionTableConstants.ADDRESS, dto.getAddress())
                .with(TransactionTableConstants.VENDOR, dto.getVendor())
                .with(TransactionTableConstants.CATEGORIES, dto.getCategories())
        );
    }
    
}
