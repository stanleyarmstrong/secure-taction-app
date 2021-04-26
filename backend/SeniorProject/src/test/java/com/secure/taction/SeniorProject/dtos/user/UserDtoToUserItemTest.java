package com.secure.taction.SeniorProject.dtos.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.secure.taction.SeniorProject.models.User;
import com.secure.taction.SeniorProject.tablesetup.constants.UserTableConstants;

import org.apache.commons.collections4.MapUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserDtoToUserItemTest {
    
    @InjectMocks
    private UserDtoToUserItem converter;

    @Test
    public void convert() {
        final String ID = "expected id";
        final String USERNAME = "expected user name";
        final String EMAIL = "stub@email.com";
        final String PASSWORD = "plain text password";
        final String PHONE_NUMBER = "555-555-5555";
        final String FIRST_NAME = "first name";
        final String LAST_NAME = "last name";
        final List<String> CREDIT_CARDS = Collections.singletonList("credit_card_id");
        final List<String> BUDGETS = Collections.singletonList("budget_id");

        final UserDto argDto = new UserDto()
                                .withUserId(ID)
                                .withUserName(USERNAME)
                                .withEmail(EMAIL)
                                .withPassword(PASSWORD)
                                .withPhoneNumber(PHONE_NUMBER)
                                .withFirstName(FIRST_NAME)
                                .withLastName(LAST_NAME)
                                .withAccounts(CREDIT_CARDS)
                                .withBudgets(BUDGETS);

        User result = converter.convert(argDto);

        assertTrue(result != null);
        Map<String, Object> resMap = result.getAttributes();
        assertTrue(MapUtils.isNotEmpty(resMap));
        assertEquals(USERNAME, resMap.get(UserTableConstants.USER_NAME));
        assertEquals(EMAIL, resMap.get(UserTableConstants.EMAIL));
        assertEquals(PASSWORD, resMap.get(UserTableConstants.PASSWORD));
        assertEquals(PHONE_NUMBER, resMap.get(UserTableConstants.PHONE_NUMBER));
        assertEquals(FIRST_NAME, resMap.get(UserTableConstants.FIRST_NAME));
        assertEquals(LAST_NAME, resMap.get(UserTableConstants.LAST_NAME));
        assertEquals(BUDGETS, resMap.get(UserTableConstants.BUDGETS));
    }
}
