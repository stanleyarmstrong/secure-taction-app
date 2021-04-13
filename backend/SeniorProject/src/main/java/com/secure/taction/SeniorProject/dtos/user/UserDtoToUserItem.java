package com.secure.taction.SeniorProject.dtos.user;

import java.util.UUID;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.secure.taction.SeniorProject.models.User;
import com.secure.taction.SeniorProject.tablesetup.constants.UserTableConstants;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserDtoToUserItem implements Converter<UserDto, User> {

    @Override
    public User convert(UserDto dto) {
        return new User().withItem(
            new Item()
                .withPrimaryKey(UserTableConstants.USER_ID, UUID.randomUUID().toString().toUpperCase())
                .with(UserTableConstants.USER_NAME, dto.getUserName())
                .with(UserTableConstants.EMAIL, dto.getEmail())
                .with(UserTableConstants.PASSWORD, dto.getPassword())
                .with(UserTableConstants.PHONE_NUMBER, dto.getPhoneNumber())
                .with(UserTableConstants.FIRST_NAME, dto.getFirstName())
                .with(UserTableConstants.LAST_NAME, dto.getLastName())
                .with(UserTableConstants.CARDS, dto.getCreditCards())
                .with(UserTableConstants.BUDGETS, dto.getBudgets())
        );
    }
    
}
