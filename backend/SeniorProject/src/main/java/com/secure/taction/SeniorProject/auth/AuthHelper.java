package com.secure.taction.SeniorProject.auth;

import java.util.Optional;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.secure.taction.SeniorProject.models.User;
import com.secure.taction.SeniorProject.models.UserCredentials;
import com.secure.taction.SeniorProject.services.UserService;
import com.secure.taction.SeniorProject.tablesetup.constants.UserCredentialsTableConstants;
import com.secure.taction.SeniorProject.tablesetup.constants.UserTableConstants;
import com.secure.taction.SeniorProject.utils.DynamoClientUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class AuthHelper {

    private DynamoDB dynamoDB = DynamoClientUtil.getDynamoClient();
    Table table = dynamoDB.getTable(UserCredentialsTableConstants.USER_CREDENTIALS_TABLE_NAME);

    private final UserService userService;

    @Autowired
    public AuthHelper(UserService userService) {
        this.userService = userService;
    }

    public boolean authenticate(String username, String password) {

        // TODO: Create UsersCredentials table service, and verify
        // that a credential exists
        Optional<UserCredentials> credentials = Optional.of(new UserCredentials());
        Optional<User> user = null;
        if (credentials.isPresent()) {
            // TODO: Change this so that it actually gets values from a table
            // Verify by the ID returned for user credentials
            compareUserNameAndPassword();
        }
        return true;
    }

    private void compareUserNameAndPassword() {
        // STUB METHOD
    }
}
