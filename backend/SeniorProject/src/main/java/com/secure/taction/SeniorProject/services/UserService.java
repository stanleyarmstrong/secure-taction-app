package com.secure.taction.SeniorProject.services;

import java.util.Objects;
import java.util.Optional;

import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.secure.taction.SeniorProject.dtos.user.UserDto;
import com.secure.taction.SeniorProject.dtos.user.UserDtoToUserItem;
import com.secure.taction.SeniorProject.dtos.user.UserItemToUserDto;
import com.secure.taction.SeniorProject.models.User;
import com.secure.taction.SeniorProject.repositories.UserRepository;
import com.secure.taction.SeniorProject.tablesetup.constants.UserTableConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserDtoToUserItem dtoToItem;
    private final UserItemToUserDto itemToDto;

    @Autowired
    public UserService(UserRepository userRepository,
                       UserDtoToUserItem dtoToItem,
                       UserItemToUserDto itemToDto) {
        this.userRepository = userRepository;
        this.dtoToItem = dtoToItem;
        this.itemToDto = itemToDto;
    }



    public Optional<UserDto> findByIdAndName(String id, String username) {
        GetItemSpec spec = new GetItemSpec().withPrimaryKey(UserTableConstants.USER_ID, id,
                                                            UserTableConstants.USER_NAME, username);
        Optional<UserDto> toReturn = Optional.empty();
        try {
            User user = userRepository.findByIdAndName(spec);
            if (Objects.nonNull(user.getItem()))
                toReturn = Optional.of(itemToDto.convert(user));
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return toReturn;
        }
        return toReturn;
        
    }

    public UserDto save(UserDto userDto) {
        User user = dtoToItem.convert(userDto);
        return itemToDto.convert(
                userRepository.save(user));
    }

    public void deleteByIdAndName(String id, String username) {
        DeleteItemSpec spec = new DeleteItemSpec().withPrimaryKey(UserTableConstants.USER_ID, id,
                                                                UserTableConstants.USER_NAME, username);
        try {
            userRepository.deleteByIdAndName(spec);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

}
