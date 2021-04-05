package com.secure.taction.SeniorProject.dtos.user;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.secure.taction.SeniorProject.models.User;
import com.secure.taction.SeniorProject.tablesetup.constants.UserTableConstants;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserItemToUserDtoTest {
    
    @InjectMocks
    private UserItemToUserDto converter;

    @Test
    public void convert() {
        final String USER_ID = UUID.randomUUID().toString().toUpperCase();
        final String USER_NAME = "user name";
        final String EMAIL = "email";
        final String PASSWORD = "password";
        final String PHONE = "phone";
        final String FIRST_NAME = "first name";
        final String LAST_NAME = "last name";
        User stubUser = new User().withItem(
            new Item()
                .withPrimaryKey(UserTableConstants.USER_ID, USER_ID)
                .with(UserTableConstants.USER_NAME, USER_NAME)
                .with(UserTableConstants.EMAIL, EMAIL)
                .with(UserTableConstants.PASSWORD, PASSWORD)
                .with(UserTableConstants.PHONE_NUMBER, PHONE)
                .with(UserTableConstants.FIRST_NAME, FIRST_NAME)
                .with(UserTableConstants.LAST_NAME, LAST_NAME)
        );

        UserDto result = converter.convert(stubUser);

        assertTrue(result != null);
        assertEquals(result.getUserId(), USER_ID);
        assertEquals(result.getEmail(), EMAIL);
        assertEquals(result.getPassword(), PASSWORD);
        assertEquals(result.getPhoneNumber(), PHONE);
        assertEquals(result.getFirstName(), FIRST_NAME);
        assertEquals(result.getLastName(), LAST_NAME);
    }

}