package com.secure.taction.SeniorProject.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.secure.taction.SeniorProject.dtos.transaction.TransactionDto;
import com.secure.taction.SeniorProject.dtos.transaction.TransactionDtoToItem;
import com.secure.taction.SeniorProject.dtos.transaction.TransactionItemToDto;
import com.secure.taction.SeniorProject.models.Transaction;
import com.secure.taction.SeniorProject.repositories.TransactionRepository;
import com.secure.taction.SeniorProject.tablesetup.constants.TransactionTableConstants;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {
    
    @InjectMocks
    private TransactionService transactionService;
    @Mock
    private TransactionRepository transactionRepository;
    @Mock
    private TransactionDtoToItem dtoToItem;
    @Mock
    private TransactionItemToDto itemToDto;

    @Test
    public void findByIdAndAccountId_empty() throws Exception {
        final String ID = "bogus";
        final String ACCOUNT_ID = "bogus";
        GetItemSpec argSpec = new GetItemSpec()
            .withPrimaryKey(TransactionTableConstants.TRANSACTION_ID, ID,
                            TransactionTableConstants.ACCOUNT_ID, ACCOUNT_ID);
        Optional<TransactionDto> result = transactionService.findByIdAndAccountId(anyString(), anyString());
        assertEquals(Optional.empty(), result);
    } 

    @Test
    public void findByIdAndAccountId_found() throws Exception {
        final String transactionId = UUID.randomUUID().toString().toUpperCase();
        final String accountId = UUID.randomUUID().toString().toUpperCase();
        final BigDecimal amount = new BigDecimal("1234.56");
        final String address = "1234 Location Pl.";
        final String vendor = "Congazon";
        final List<String> categories = Collections.singletonList("Singular Category");

        final TransactionDto dto = new TransactionDto()
                .withTransactionId(transactionId)
                .withAccountId(accountId)
                .withAmount(amount)
                .withAddress(address)
                .withVendor(vendor)
                .withCategories(categories);
        final Transaction transaction = new Transaction()
            .withItem(
                new Item()
                    .with(TransactionTableConstants.TRANSACTION_ID, transactionId)
                    .with(TransactionTableConstants.ACCOUNT_ID, accountId)
                    .with(TransactionTableConstants.AMOUNT, amount)
                    .with(TransactionTableConstants.ADDRESS, address)
                    .with(TransactionTableConstants.VENDOR, vendor)
                    .with(TransactionTableConstants.CATEGORIES, categories)
            );
        when(transactionRepository.findByIdAndAccountId(any(GetItemSpec.class)))
            .thenReturn(transaction);
        when(itemToDto.convert(any(Transaction.class)))
            .thenReturn(dto);
        
        Optional<TransactionDto> result = transactionService.findByIdAndAccountId(transactionId, accountId);
        assertTrue(result.isPresent());
        assertEquals(transactionId, result.get().getTransactionId());
        assertEquals(accountId, result.get().getAccountId());
        assertEquals(amount, result.get().getAmount());
        assertEquals(address, result.get().getAddress());
        assertEquals(vendor, result.get().getVendor());
        assertEquals(categories, result.get().getCategories());
    }

    @Test
    public void save() throws Exception {
        final String transactionId = UUID.randomUUID().toString().toUpperCase();
        final String accountId = UUID.randomUUID().toString().toUpperCase();
        final BigDecimal amount = new BigDecimal("1234.56");
        final String address = "1234 Location Pl.";
        final String vendor = "Congazon";
        final List<String> categories = Collections.singletonList("Singular Category");

        final TransactionDto dto = new TransactionDto()
                .withTransactionId(transactionId)
                .withAccountId(accountId)
                .withAmount(amount)
                .withAddress(address)
                .withVendor(vendor)
                .withCategories(categories);
        final Transaction transaction = new Transaction()
            .withItem(
                new Item()
                    .with(TransactionTableConstants.TRANSACTION_ID, transactionId)
                    .with(TransactionTableConstants.ACCOUNT_ID, accountId)
                    .with(TransactionTableConstants.AMOUNT, amount)
                    .with(TransactionTableConstants.ADDRESS, address)
                    .with(TransactionTableConstants.VENDOR, vendor)
                    .with(TransactionTableConstants.CATEGORIES, categories)
            );
        when(itemToDto.convert(transaction))
            .thenReturn(dto);
        when(dtoToItem.convert(dto))
            .thenReturn(transaction);
        when(transactionRepository.save(any(Transaction.class)))
            .thenReturn(transaction);
        
        TransactionDto result = transactionService.save(dto);
        assertNotNull(result);
        assertEquals(transactionId, result.getTransactionId());
        assertEquals(accountId, result.getAccountId());
        assertEquals(amount, result.getAmount());
        assertEquals(address, result.getAddress());
        assertEquals(vendor, result.getVendor());
        assertEquals(categories, result.getCategories());
    }

    @Test
    public void update() throws Exception {
        final String transactionId = UUID.randomUUID().toString().toUpperCase();
        final String accountId = UUID.randomUUID().toString().toUpperCase();
        final BigDecimal amount = new BigDecimal("1234.56");
        final String address = "1234 Location Pl.";
        final String vendor = "Congazon";
        final List<String> categories = Collections.singletonList("Singular Category");

        final String NEW_VENDOR = "Windoors";

        final TransactionDto dto = new TransactionDto()
                .withTransactionId(transactionId)
                .withAccountId(accountId)
                .withAmount(amount)
                .withAddress(address)
                .withVendor(NEW_VENDOR)
                .withCategories(categories);
        final Transaction transaction = new Transaction()
            .withItem(
                new Item()
                    .with(TransactionTableConstants.TRANSACTION_ID, transactionId)
                    .with(TransactionTableConstants.ACCOUNT_ID, accountId)
                    .with(TransactionTableConstants.AMOUNT, amount)
                    .with(TransactionTableConstants.ADDRESS, address)
                    .with(TransactionTableConstants.VENDOR, vendor)
                    .with(TransactionTableConstants.CATEGORIES, categories)
            );
        when(itemToDto.convert(transaction))
            .thenReturn(dto);
        when(dtoToItem.convert(dto))
            .thenReturn(transaction);
        when(transactionRepository.save(any(Transaction.class)))
            .thenReturn(transaction);
        
        TransactionDto result = transactionService.save(dto);
        assertNotNull(result);
        assertEquals(transactionId, result.getTransactionId());
        assertEquals(accountId, result.getAccountId());
        assertEquals(amount, result.getAmount());
        assertEquals(address, result.getAddress());
        assertEquals(NEW_VENDOR, result.getVendor());
        assertEquals(categories, result.getCategories());
    }

    @Test
    public void deleteByIdandAccountId() throws Exception {
        final String transactionId = UUID.randomUUID().toString().toUpperCase();
        final String userId = UUID.randomUUID().toString().toUpperCase();
        transactionService.deleteByIdAndUserId(transactionId, userId);
        verify(transactionRepository, times(1)).deleteByIdAndAccountId(any(DeleteItemSpec.class));
    }
}
