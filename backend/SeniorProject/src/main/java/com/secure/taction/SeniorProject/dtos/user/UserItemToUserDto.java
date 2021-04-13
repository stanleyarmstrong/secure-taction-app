package com.secure.taction.SeniorProject.dtos.user;

import java.util.List;
import java.util.Map;

import com.secure.taction.SeniorProject.models.User;
import com.secure.taction.SeniorProject.tablesetup.constants.UserTableConstants;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("unchecked")
public class UserItemToUserDto implements Converter<User, UserDto> {

    @Override
    public UserDto convert(User user) {
        Map<String, Object> argItem = user.getAttributes();
        return new UserDto()
            .withUserId((String) argItem.get(UserTableConstants.USER_ID))     
            .withUserName((String) argItem.get(UserTableConstants.USER_NAME))
            .withEmail((String) argItem.get(UserTableConstants.EMAIL))
            .withPassword((String) argItem.get(UserTableConstants.PASSWORD))
            .withPhoneNumber((String) argItem.get(UserTableConstants.PHONE_NUMBER))
            .withFirstName((String) argItem.get(UserTableConstants.FIRST_NAME))
            .withLastName((String) argItem.get(UserTableConstants.LAST_NAME))
            .withCreditCards((List<String>) argItem.get(UserTableConstants.CARDS))
            .withBudgets((List<String>) argItem.get(UserTableConstants.BUDGETS))
        ;
    }
    
}
