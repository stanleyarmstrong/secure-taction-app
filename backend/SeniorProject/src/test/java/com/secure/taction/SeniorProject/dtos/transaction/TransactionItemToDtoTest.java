package com.secure.taction.SeniorProject.dtos.transaction;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.secure.taction.SeniorProject.models.Transaction;
import com.secure.taction.SeniorProject.tablesetup.constants.TransactionTableConstants;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TransactionItemToDtoTest {
    
    @InjectMocks
    private TransactionItemToDto converter;

    @Test
    public void convert() {
        final String transactionId = UUID.randomUUID().toString().toUpperCase();
        final String accountId = UUID.randomUUID().toString().toUpperCase();
        final BigDecimal amount = new BigDecimal("1234.56");
        final String location = "123 Localtion Pl.";
        final String vendor = "Windors Store";
        final List<String> categories = Collections.singletonList("Only String");

        final Transaction argItem = new Transaction()
            .withItem(
                new Item()
                    .withPrimaryKey(TransactionTableConstants.TRANSACTION_ID, transactionId)
                    .with(TransactionTableConstants.ACCOUNT_ID, accountId)
                    .with(TransactionTableConstants.AMOUNT, amount)
                    .with(TransactionTableConstants.LOCATION, location)
                    .with(TransactionTableConstants.VENDOR, vendor)
                    .with(TransactionTableConstants.CATEGORIES, categories)
            );

        TransactionDto result = converter.convert(argItem);
        assertTrue(result != null);
        assertEquals(transactionId, result.getTransactionId());
        assertEquals(accountId, result.getAccountId());
        assertEquals(amount, result.getAmount());
        assertEquals(location, result.getLocation());
        assertEquals(vendor, result.getVendor());
        assertEquals(categories, result.getCategories());
    }

}
