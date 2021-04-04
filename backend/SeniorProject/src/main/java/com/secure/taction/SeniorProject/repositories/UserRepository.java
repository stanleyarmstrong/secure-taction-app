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
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.secure.taction.SeniorProject.dtos.user.UserDto;
import com.secure.taction.SeniorProject.models.User;
import com.secure.taction.SeniorProject.tablesetup.constants.UserTableConstants;
import com.secure.taction.SeniorProject.utils.DynamoClientUtil;

@Repository
public class UserRepository {
	
    private static Logger LOGGER = LoggerFactory.getLogger(UserRepository.class);

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

	public void deleteByIdAndName(DeleteItemSpec spec) throws Exception {
		table.deleteItem(spec);
	}
}
