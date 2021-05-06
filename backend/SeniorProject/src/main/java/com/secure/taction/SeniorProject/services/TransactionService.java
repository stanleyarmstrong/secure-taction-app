package com.secure.taction.SeniorProject.services;
import java.util.Objects;
import java.util.Optional;

import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.secure.taction.SeniorProject.dtos.transaction.TransactionDto;
import com.secure.taction.SeniorProject.dtos.transaction.TransactionDtoToItem;
import com.secure.taction.SeniorProject.dtos.transaction.TransactionItemToDto;
import com.secure.taction.SeniorProject.models.Transaction;
import com.secure.taction.SeniorProject.repositories.AccountRepository;
import com.secure.taction.SeniorProject.repositories.TransactionRepository;
import com.secure.taction.SeniorProject.tablesetup.constants.TransactionTableConstants;
import com.secure.taction.SeniorProject.tablesetup.constants.TransactionTableConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionDtoToItem dtoToItem;
    private final TransactionItemToDto itemToDto;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository,
                              TransactionDtoToItem dtoToItem,
                              TransactionItemToDto itemToDto) {
        this.transactionRepository = transactionRepository;
        this.dtoToItem = dtoToItem;
        this.itemToDto = itemToDto;
    }

    public Optional<TransactionDto> findByIdAndAccountId(@NonNull String id, 
                                                         @NonNull String accountId) {
        GetItemSpec spec = new GetItemSpec().withPrimaryKey(TransactionTableConstants.TRANSACTION_ID, id,
                                                            TransactionTableConstants.ACCOUNT_ID, accountId);
        Optional<TransactionDto> toReturn = Optional.empty();
        try {
            Transaction transaction = transactionRepository.findByIdAndUserId(spec);
            if (Objects.nonNull(transaction.getItem()))
                toReturn = Optional.of(itemToDto.convert(transaction));
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return toReturn;
        }
        return toReturn;
    }

    public TransactionDto save(TransactionDto transactionDto) {
        Transaction transaction = dtoToItem.convert(transactionDto);
        return itemToDto.convert(
                    transactionRepository.save(transaction));
    }

    public TransactionDto update(TransactionDto transactionDto) {
        return itemToDto.convert(
                    transactionRepository.update(transactionDto))
                    .withTransactionId(transactionDto.getTransactionId())
                    .withAccountId(transactionDto.getAccountId());
    }

    public void deleteByIdAndUserId(@NonNull String id, 
                                    @NonNull String accountId) {
        DeleteItemSpec spec = new DeleteItemSpec()
                                .withPrimaryKey(TransactionTableConstants.TRANSACTION_ID, id,
                                                TransactionTableConstants.ACCOUNT_ID, accountId);
        try {
            transactionRepository.deleteByIdAndAccountId(spec);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }



}
