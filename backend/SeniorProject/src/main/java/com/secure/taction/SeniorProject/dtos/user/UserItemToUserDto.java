package com.secure.taction.SeniorProject.dtos.user;

import java.util.Map;

import com.secure.taction.SeniorProject.models.User;
import com.secure.taction.SeniorProject.tablesetup.constants.UserTableConstants;

import org.springframework.core.convert.converter.Converter;

public class UserItemToUserDto implements Converter<User, UserDto> {

    @Override
    public UserDto convert(User item) {
        Map<String, Object> argItem = item.getAttributes();
        return new UserDto()
            .withUserId((String) argItem.get(UserTableConstants.USER_ID))     
            .withUserName((String) argItem.get(UserTableConstants.USER_NAME))
            .withEmail((String) argItem.get(UserTableConstants.EMAIL))
            .withPassword((String) argItem.get(UserTableConstants.PASSWORD))
            .withPhoneNumber((String) argItem.get(UserTableConstants.PHONE_NUMBER))
            .withFirstName((String) argItem.get(UserTableConstants.FIRST_NAME))
            .withLastName((String) argItem.get(UserTableConstants.LAST_NAME))
        ;
    }
    
}
