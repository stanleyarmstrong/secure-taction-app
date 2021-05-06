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
    private TransactionDtoToItem dtoToITem;
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
}
