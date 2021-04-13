package com.secure.taction.SeniorProject.services.user;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.secure.taction.SeniorProject.dtos.user.UserDto;
import com.secure.taction.SeniorProject.dtos.user.UserDtoToUserItem;
import com.secure.taction.SeniorProject.dtos.user.UserItemToUserDto;
import com.secure.taction.SeniorProject.models.User;
import com.secure.taction.SeniorProject.repositories.UserRepository;
import com.secure.taction.SeniorProject.services.UserService;
import com.secure.taction.SeniorProject.tablesetup.constants.UserTableConstants;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    
    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserDtoToUserItem dtoToItem;
    @Mock
    private UserItemToUserDto itemToDto;

    @Test
    public void findByIdAndName_empty() throws Exception {
        final String ID = "bogus";
        final String NAME = "bogus";
        GetItemSpec argSpec = new GetItemSpec()
                                .withPrimaryKey(
                                    UserTableConstants.USER_ID, ID,
                                    UserTableConstants.USER_NAME, NAME);

        Optional<UserDto> result = userService.findByIdAndName(ID, NAME);
        assertEquals(Optional.empty(), result);
    }

    @Test
    public void findById_found() throws Exception {
        final String USER_ID = "a1bb22ccc333";
        final String USER_NAME = "TEST_USER_NAME";
        final String EMAIL = "email@email.com";
        final String PASSWORD = "password";
        final String PHONE_NUMBER = "1234";
        final String FIRST_NAME = "first name";
        final String LAST_NAME = "last name";
        final List<String> CARDS = Collections.singletonList("STRING");
        final List<String> BUDGETS = Collections.singletonList("STRING");

        final UserDto stubDto = new UserDto()
                    .withUserId(USER_ID)
                    .withUserName(USER_NAME)
                    .withEmail(EMAIL)
                    .withPassword(PASSWORD)
                    .withPhoneNumber(PHONE_NUMBER)
                    .withLastName(LAST_NAME)
                    .withFirstName(FIRST_NAME)
                    .withCreditCards(CARDS)
                    .withBudgets(BUDGETS);
        
        final User stubUser = new User()
            .withItem(new Item()
                .withPrimaryKey(UserTableConstants.USER_ID, USER_ID)
                .with(UserTableConstants.USER_NAME, USER_NAME)
                .with(UserTableConstants.EMAIL, EMAIL)
                .with(UserTableConstants.PASSWORD, PASSWORD)
                .with(UserTableConstants.PHONE_NUMBER, PHONE_NUMBER)
                .with(UserTableConstants.FIRST_NAME, FIRST_NAME)
                .with(UserTableConstants.LAST_NAME, LAST_NAME)
                .with(UserTableConstants.CARDS, CARDS)
                .with(UserTableConstants.BUDGETS, BUDGETS)
            );
        when(userRepository.findByIdAndName(any(GetItemSpec.class)))
            .thenReturn(stubUser);
        when(itemToDto.convert(any(User.class)))
            .thenReturn(stubDto);
        
        Optional<UserDto> result = userService.findByIdAndName(USER_ID, USER_NAME);
        assertTrue(result.isPresent());
        assertEquals(USER_ID, result.get().getUserId());
        assertEquals(USER_NAME, result.get().getUserName());
        assertEquals(PASSWORD, result.get().getPassword());
        assertEquals(PHONE_NUMBER, result.get().getPhoneNumber());
        assertEquals(EMAIL, result.get().getEmail());
        assertEquals(FIRST_NAME, result.get().getFirstName());
        assertEquals(LAST_NAME, result.get().getLastName());
        assertEquals(CARDS, result.get().getCreditCards());
        assertEquals(BUDGETS, result.get().getBudgets());
    }

    @Test
    public void save() {
        final String USER_ID = "a1bb22ccc333";
        final String USER_NAME = "TEST_USER_NAME";
        final String EMAIL = "email@email.com";
        final String PASSWORD = "password";
        final String PHONE_NUMBER = "1234";
        final String FIRST_NAME = "first name";
        final String LAST_NAME = "last name";
        final List<String> CARDS = Collections.singletonList("STRING");
        final List<String> BUDGETS = Collections.singletonList("STRING");

        final UserDto stubDto = new UserDto()
                    .withUserId(USER_ID)
                    .withUserName(USER_NAME)
                    .withEmail(EMAIL)
                    .withPassword(PASSWORD)
                    .withPhoneNumber(PHONE_NUMBER)
                    .withLastName(LAST_NAME)
                    .withFirstName(FIRST_NAME)
                    .withCreditCards(CARDS)
                    .withBudgets(BUDGETS);

        final User stubUser = new User()
            .withItem(new Item()
                .withPrimaryKey(UserTableConstants.USER_ID, USER_ID)
                .with(UserTableConstants.USER_NAME, USER_NAME)
                .with(UserTableConstants.EMAIL, EMAIL)
                .with(UserTableConstants.PASSWORD, PASSWORD)
                .with(UserTableConstants.PHONE_NUMBER, PHONE_NUMBER)
                .with(UserTableConstants.FIRST_NAME, FIRST_NAME)
                .with(UserTableConstants.LAST_NAME, LAST_NAME)
                .with(UserTableConstants.CARDS, CARDS)
                .with(UserTableConstants.BUDGETS, BUDGETS)
            );

        when(dtoToItem.convert(any(UserDto.class)))
            .thenReturn(stubUser);
        when(itemToDto.convert(any(User.class)))
            .thenReturn(stubDto);
        when(userRepository.save(any(User.class)))
            .thenReturn(stubUser);

        final UserDto result = userService.save(stubDto);
        assertEquals(USER_ID, result.getUserId());
        assertEquals(USER_NAME, result.getUserName());
        assertEquals(PASSWORD, result.getPassword());
        assertEquals(PHONE_NUMBER, result.getPhoneNumber());
        assertEquals(EMAIL, result.getEmail());
        assertEquals(FIRST_NAME, result.getFirstName());
        assertEquals(LAST_NAME, result.getLastName());
        assertEquals(CARDS, result.getCreditCards());
        assertEquals(BUDGETS, result.getBudgets());
    }

    @Test
    public void update() {
        final String USER_ID = "a1bb22ccc333";
        final String USER_NAME = "TEST_USER_NAME";
        final String EMAIL = "email@email.com";
        final String PASSWORD = "password";
        final String PHONE_NUMBER = "1234";
        final String FIRST_NAME = "first name";
        final String LAST_NAME = "last name";
        final List<String> CARDS = Collections.singletonList("String");
        final List<String> BUDGETS = Collections.singletonList("String");

        final UserDto stubDto = new UserDto()
                    .withUserId(USER_ID)
                    .withUserName(USER_NAME)
                    .withEmail(EMAIL)
                    .withPassword(PASSWORD)
                    .withPhoneNumber(PHONE_NUMBER)
                    .withLastName(LAST_NAME)
                    .withFirstName(FIRST_NAME)
                    .withCreditCards(CARDS)
                    .withBudgets(BUDGETS);

        final User stubUser = new User()
            .withItem(new Item()
                .withPrimaryKey(UserTableConstants.USER_ID, USER_ID)
                .with(UserTableConstants.USER_NAME, USER_NAME)
                .with(UserTableConstants.EMAIL, EMAIL)
                .with(UserTableConstants.PASSWORD, PASSWORD)
                .with(UserTableConstants.PHONE_NUMBER, PHONE_NUMBER)
                .with(UserTableConstants.FIRST_NAME, FIRST_NAME)
                .with(UserTableConstants.LAST_NAME, LAST_NAME)
                .with(UserTableConstants.CARDS, CARDS)
                .with(UserTableConstants.BUDGETS, BUDGETS)
            );

        when(userRepository.update(stubDto))
            .thenReturn(stubUser);
        when(itemToDto.convert(any(User.class)))
            .thenReturn(stubDto);

        final UserDto result = userService.update(stubDto);
        assertEquals(USER_ID, result.getUserId());
        assertEquals(USER_NAME, result.getUserName());
        assertEquals(PASSWORD, result.getPassword());
        assertEquals(PHONE_NUMBER, result.getPhoneNumber());
        assertEquals(EMAIL, result.getEmail());
        assertEquals(FIRST_NAME, result.getFirstName());
        assertEquals(LAST_NAME, result.getLastName());
        assertEquals(BUDGETS, result.getBudgets());
    }

    @Test
    public void deleteByIdAndName() throws Exception {
        final String USER_ID = UUID.randomUUID().toString().toUpperCase();
        final String NAME = UUID.randomUUID().toString().toUpperCase();
        userService.deleteByIdAndName(USER_ID, NAME);
        verify(userRepository, times(1)).deleteByIdAndName(any(DeleteItemSpec.class));
    }
}
