package com.secure.taction.SeniorProject.repositories;

import java.util.Objects;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;
import com.secure.taction.SeniorProject.dtos.transaction.TransactionDto;
import com.secure.taction.SeniorProject.models.Transaction;
import com.secure.taction.SeniorProject.tablesetup.constants.TransactionTableConstants;
import com.secure.taction.SeniorProject.utils.DynamoClientUtil;
import com.secure.taction.SeniorProject.utils.SnsClientUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class TransactionRepository {

    private static Logger LOGGER = LoggerFactory.getLogger(TransactionRepository.class);
    private static String amount = ":amount";
    private static String address = ":address";
    private static String vendor = ":vendor";
    private static String categories = ":categories";

    private static String updateExpression =
        "set\n" +
        TransactionTableConstants.AMOUNT + " = " + amount + ",\n" +
        TransactionTableConstants.ADDRESS + " = " + address + ",\n" +
        TransactionTableConstants.VENDOR + " = " + vendor + ",\n" +
        TransactionTableConstants.CATEGORIES + " = " + categories; 

    DynamoDB dynamoDB = DynamoClientUtil.getDynamoClient();
    Table table = dynamoDB.getTable(TransactionTableConstants.TRANSACTION_TABLE_NAME);

    public Transaction findByIdAndAccountId(GetItemSpec spec) throws Exception {
        Item outcome = table.getItem(spec);
        return new Transaction().withItem(outcome);
    }

    public Transaction save(Transaction transaction) {
        try {
            PutItemOutcome outcome = table.putItem(transaction.getItem());
            if (Objects.nonNull(outcome)) {
                SnsClientUtil.testCall();
                return transaction;
            }
            else return null;
        } catch (Exception e) {
            LOGGER.error("Exception occured while adding record to the Transactions Table");
            return null;
        }
    }

    public Transaction update(TransactionDto transactionDto) {
        UpdateItemSpec updateItemSpec =
            new UpdateItemSpec()
                .withPrimaryKey(
                    TransactionTableConstants.TRANSACTION_ID, "bogus",
                    TransactionTableConstants.ACCOUNT_ID, "bogus")
                .withUpdateExpression(updateExpression)
                .withValueMap(new ValueMap()
                    .withNumber(amount, transactionDto.getAmount())
                    .withString(vendor, transactionDto.getVendor())
                    //.withString(address, transactionDto.getAddress())
                    .withList(categories, transactionDto.getCategories()))
                .withReturnValues(ReturnValue.UPDATED_NEW);
        try {
            Item outcome = table.updateItem(updateItemSpec).getItem();
            if (Objects.nonNull(outcome)) {
                return new Transaction().withItem(outcome);
            } else {
 //               System.err.println("Unable to update transaction with Id:" + transactionDto.getTransactionId());
                return null;
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return null;
        }
    }

    public void deleteByIdAndAccountId(DeleteItemSpec spec) {
        table.deleteItem(spec);
    }
    
}
