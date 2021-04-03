package com.secure.taction.SeniorProject.repositories;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.secure.taction.SeniorProject.dtos.user.UserDto;
import com.secure.taction.SeniorProject.models.User;
import com.secure.taction.SeniorProject.tablesetup.constants.UserTableConstants;

@Repository
public class UserRepository {
	
    private static Logger LOGGER = LoggerFactory.getLogger(UserRepository.class);

	AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
	DynamoDB dynamoDb = new DynamoDB(client);

	public UserDto getUser(String key) {
		Table table = dynamoDb.getTable("user");
		GetItemSpec spec = new GetItemSpec().withPrimaryKey("user_id", key);
		try {
			System.out.println("Attempting to read item");
			Item outcome = table.getItem(spec);
			if (Objects.nonNull(outcome)) {
				UserDto user = new UserDto();
				user.setUserId(outcome.get("user_id").toString());
				user.setFirstName(outcome.get("first_name").toString());
				user.setLastName(outcome.get("last_name").toString());
				return user;
			}
		} catch (Exception e) {
			LOGGER.error("Exception occurred during getUser : ", e);
		}
		return null;
	}

	public User save(User user) {
		Table table = dynamoDb.getTable(UserTableConstants.USER_TABLE_NAME);
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
}
