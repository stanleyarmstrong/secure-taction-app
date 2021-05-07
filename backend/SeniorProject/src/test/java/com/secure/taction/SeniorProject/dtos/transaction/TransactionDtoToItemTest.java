package com.secure.taction.SeniorProject.dtos.transaction;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.secure.taction.SeniorProject.models.Transaction;
import com.secure.taction.SeniorProject.tablesetup.constants.TransactionTableConstants;

import org.apache.commons.collections4.MapUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TransactionDtoToItemTest {
    
    @InjectMocks
    private TransactionDtoToItem converter;

    @Test
    public void convert() {
        final String ID = "expected Id";
        final String ACCOUNT_ID = "expected account id";
        final BigDecimal AMOUNT = new BigDecimal("1233.56");
        final String ADDRESS = "122 Address Pl.";
        final String VENDOR = "Bapple Store";
        final List<String> CATEGORIES = new LinkedList<>();

        final TransactionDto argDto = new TransactionDto()
            .withTransactionId(ID)
            .withAccountId(ACCOUNT_ID)
            .withAmount(AMOUNT)
            .withAddress(ADDRESS)
            .withVendor(VENDOR)
            .withCategories(CATEGORIES);

        Transaction result = converter.convert(argDto);

        assertTrue(result != null);
        Map<String, Object> resMap = result.getAttributes();
        assertTrue(MapUtils.isNotEmpty(resMap));
        assertEquals(ID, resMap.get(TransactionTableConstants.TRANSACTION_ID));
        assertEquals(ACCOUNT_ID, resMap.get(TransactionTableConstants.ACCOUNT_ID));
        assertEquals(AMOUNT, new BigDecimal(((Number) resMap.get(TransactionTableConstants.AMOUNT)).toString()));
        assertEquals(ADDRESS, resMap.get(TransactionTableConstants.ADDRESS));
        assertEquals(VENDOR, resMap.get(TransactionTableConstants.VENDOR));
        assertEquals(CATEGORIES, resMap.get(TransactionTableConstants.CATEGORIES));
    }
}