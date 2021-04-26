package com.secure.taction.SeniorProject.repositories;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;
import com.secure.taction.SeniorProject.dtos.user.UserDto;
import com.secure.taction.SeniorProject.models.User;
import com.secure.taction.SeniorProject.tablesetup.constants.UserTableConstants;
import com.secure.taction.SeniorProject.utils.DynamoClientUtil;

@Repository
public class UserRepository {
	
    private static Logger LOGGER = LoggerFactory.getLogger(UserRepository.class);
	private static String password = ":password";
	private static String phoneNumber = ":phoneNumber";
	private static String firstName = ":firstName";
	private static String lastName = ":lastName";
	private static String email = ":email";
	private static String accounts = ":accounts";
	private static String budgets = ":budgets";

	private static String updateExpression = 
		"set\n" +
		UserTableConstants.PASSWORD + " = " + password + ",\n" +
		UserTableConstants.PHONE_NUMBER + " = " + phoneNumber + ",\n" +
		UserTableConstants.FIRST_NAME + " = " + firstName + ",\n" +
		UserTableConstants.LAST_NAME + " = " + lastName + ",\n" +
		UserTableConstants.EMAIL + " = " + email + ",\n" +
		UserTableConstants.ACCOUNTS + " = " + accounts + ",\n" +
		UserTableConstants.BUDGETS + " = " + budgets;

    DynamoDB dynamoDB = DynamoClientUtil.getClient();
	Table table = dynamoDB.getTable(UserTableConstants.USER_TABLE_NAME);

	public User findByIdAndName(GetItemSpec spec) throws Exception {
		Item outcome = table.getItem(spec);
		return new User().withItem(outcome);
	}

	public User save(User user) {
		try {
			PutItemOutcome outcome = table.putItem(user.getItem());
			if (Objects.nonNull(outcome))
				return user;
			else
				return null; 
		} catch (Exception e) {
			LOGGER.error("Exception occurred while adding record to the db : ", e);
			return null;
		}
	}

	public User update(UserDto userDto) {
		UpdateItemSpec updateItemSpec = 
			new UpdateItemSpec()
				.withPrimaryKey(
					UserTableConstants.USER_ID, userDto.getUserId(),
					UserTableConstants.USER_NAME, userDto.getUserName())
				.withUpdateExpression(updateExpression)
				.withValueMap(new ValueMap()
					.withString(password, userDto.getPassword())
					.withString(phoneNumber, userDto.getPhoneNumber())
					.withString(firstName, userDto.getFirstName())
					.withString(lastName, userDto.getLastName())
					.withString(email, userDto.getEmail())
					.withList(accounts, userDto.getAccounts())
					.withList(budgets, userDto.getBudgets()))
				.withReturnValues(ReturnValue.UPDATED_NEW);
		try {
			Item outcome = table.updateItem(updateItemSpec).getItem();
			if (Objects.nonNull(outcome)) {
				return new User().withItem(outcome);
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
}
