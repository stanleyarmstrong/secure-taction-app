package com.secure.taction.SeniorProject.dtos.transaction;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.secure.taction.SeniorProject.models.Transaction;
import com.secure.taction.SeniorProject.tablesetup.constants.TransactionTableConstants;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("unchecked")
public class TransactionItemToDto implements Converter<Transaction, TransactionDto> {

    @Override
    public TransactionDto convert(Transaction source) {
        Map<String, Object> item = source.getAttributes();
        return new TransactionDto()
            .withTransactionId((String) item.get(TransactionTableConstants.TRANSACTION_ID))
            .withAccountId((String) item.get(TransactionTableConstants.ACCOUNT_ID))
            .withAmount(new BigDecimal(((Number)item.get(TransactionTableConstants.AMOUNT)).toString()))
            .withAddress((String) item.get(TransactionTableConstants.ADDRESS))
            .withVendor((String) item.get(TransactionTableConstants.VENDOR))
            .withCategories((List<String>) item.get(TransactionTableConstants.CATEGORIES))
        ;
    }
    
}
