package com.secure.taction.SeniorProject.repositories;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;
import com.secure.taction.SeniorProject.dtos.accounts.AccountDto;
import com.secure.taction.SeniorProject.models.Account;
import com.secure.taction.SeniorProject.tablesetup.constants.AccountTableConstants;
import com.secure.taction.SeniorProject.utils.DynamoClientUtil;

@Repository
public class AccountRepository {
  private static Logger LOGGER = LoggerFactory.getLogger(AccountRepository.class);
  private static String accountType = ":accountType";
  private static String balance = ":balance";
  private static String accountName = ":accountName";

  private static String updateExpression =
    "set\n" + 
    AccountTableConstants.ACCOUNT_TYPE + " = " + accountType + ",\n" +
		AccountTableConstants.BALANCE + " = " + balance + ",\n" +
		AccountTableConstants.ACCOUNT_NAME + " = " + accountName; 

  DynamoDB dynamoDB = DynamoClientUtil.getClient();
	Table table = dynamoDB.getTable(AccountTableConstants.ACCOUNT_TABLE_NAME);

	public Account findByIdAndName(GetItemSpec spec) throws Exception {
		Item outcome = table.getItem(spec);
		return new Account().withItem(outcome);
	}

	public Account save(Account account) {
		try {
			PutItemOutcome outcome = table.putItem(account.getItem());
			if (Objects.nonNull(outcome))
				return account;
			else
				return null; 
		} catch (Exception e) {
			LOGGER.error("Exception occurred while adding record to the db : ", e);
			return null;
		}
	}

	public Account update(AccountDto accountDto) {
		UpdateItemSpec updateItemSpec = 
			new UpdateItemSpec()
				.withPrimaryKey(
					AccountTableConstants.ACCOUNT_ID, accountDto.getAccountId(),
					AccountTableConstants.USER_ID, accountDto.getUserId())
				.withUpdateExpression(updateExpression)
				.withValueMap(new ValueMap()
					.withString(accountType, accountDto.getAccountType())
					.withNumber(balance, accountDto.getBalance())
					.withString(accountName, accountDto.getAccountName()))
				.withReturnValues(ReturnValue.UPDATED_NEW);
		try {
			Item outcome = table.updateItem(updateItemSpec).getItem();
			if (Objects.nonNull(outcome)) {
				return new Account().withItem(outcome);
			} else {
				System.err.println("Unable to update");
				return null;
			}
		} catch(Exception ex) {
			System.err.println(ex.getMessage());
			return null;
		}
  }

	public void deleteByIdAndName(DeleteItemSpec spec) throws Exception {
		table.deleteItem(spec);
	}
  
	public ItemCollection<QueryOutcome> queryForAccount(QuerySpec querySpec) {
		return table.query(querySpec);
	}
}
