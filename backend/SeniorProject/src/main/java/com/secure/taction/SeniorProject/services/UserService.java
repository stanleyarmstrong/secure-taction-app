package com.secure.taction.SeniorProject.services;

import java.util.Optional;

import com.secure.taction.SeniorProject.dtos.user.UserDto;
import com.secure.taction.SeniorProject.dtos.user.UserDtoToUserItem;
import com.secure.taction.SeniorProject.dtos.user.UserItemToUserDto;
import com.secure.taction.SeniorProject.models.User;
import com.secure.taction.SeniorProject.repositories.UserRepository;

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

    public Optional<UserDto> findById(String id) {

    }

    public UserDto save(UserDto userDto) {
        User user = dtoToItem.convert(userDto);
        return itemToDto.convert(
                userRepository.save(user));
    }

}
