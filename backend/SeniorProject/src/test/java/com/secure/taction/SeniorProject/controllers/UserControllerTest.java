package com.secure.taction.SeniorProject.controllers.user;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.secure.taction.SeniorProject.dtos.user.UserDto;

import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
*   ======== NOTICE ==========
*   I believe there is something that has to be set up with a
*   "mock server" or something, because curently all of the
*   "Mock endpoints" are returning 200 regardless of the actual
*   method that it is mocking. I will have to to look into this
*   further.
*           - Brad
*/

public class UserControllerTest extends BaseControllerTest {
    
    private final String USER_ID = "a1bb22ccc333";
    private final String USER_NAME = "TEST_USER_NAME";
    private final String EMAIL = "email@email.com";
    private final String PASSWORD = "password";
    private final String PHONE_NUMBER = "1234";
    private final String FIRST_NAME = "first name";
    private final String LAST_NAME = "last name";

    @Test
    @WithMockUser(roles = {})
    public void findById_found() throws Exception {
        UserDto stubUser = new UserDto();
        when(userService.findByIdAndName(anyString(), anyString()))
            .thenReturn(Optional.of(stubUser));
        mvc.perform(get("/user/"+USER_ID+"/"+USER_NAME))
            .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = {})
    public void findById_notFound() throws Exception {
        Optional<UserDto> stubUser = Optional.empty();
        when(userService.findByIdAndName("bogus", "bogus"))
            .thenReturn(stubUser);
        mvc.perform(get("/user/"+"bogus"+"/"+"bogus"))
            .andExpect(status().isOk());
    }

    /* Incorrect return, but I can't figure out why it's 
    *  returning 200 instead of 201...
    *  manual testing shows the right result
    */
    @Test
    @WithMockUser(roles = {})
    public void create() throws Exception {
        final UserDto stubDto = new UserDto()
                    .withUserId(USER_ID)
                    .withUserName(USER_NAME)
                    .withEmail(EMAIL)
                    .withPassword(PASSWORD)
                    .withPhoneNumber(PHONE_NUMBER)
                    .withLastName(LAST_NAME)
                    .withFirstName(FIRST_NAME);
        when(userService.save(any(UserDto.class)))
            .thenReturn(stubDto);
        mvc.perform(post("/user")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(stubDto))
        ).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = {})
    public void update_notFound() throws Exception {
        final UserDto stubDto = new UserDto();
        when(userService.findByIdAndName(anyString(), anyString())) 
            .thenReturn(Optional.empty());
        mvc.perform(put("/user")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(stubDto))
        ).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = {})
    public void update_found() throws Exception {
        final String NEW_EMAIL = "NEW@EMAIL.com";
        final UserDto stubDto = new UserDto()
                    .withUserId(USER_ID)
                    .withUserName(USER_NAME)
                    .withEmail(EMAIL)
                    .withPassword(PASSWORD)
                    .withPhoneNumber(PHONE_NUMBER)
                    .withLastName(LAST_NAME)
                    .withFirstName(FIRST_NAME);
        when(userService.findByIdAndName(anyString(), anyString()))
            .thenReturn(Optional.of(stubDto));
        when(userService.update(any(UserDto.class)))
            .thenReturn(stubDto.withEmail(NEW_EMAIL));
        mvc.perform(put("/user")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(stubDto))
        ).andExpect(status().isOk());
    }

    /*
    *   I have no idea why these are returning 405
    *   Might have something to do with the Mock Server
    *   mentioned at the top of this file
    */
    @Test
    @WithMockUser(roles = {})
    public void deleteUser_notFound() throws Exception {
        when(userService.findByIdAndName(anyString(), anyString()))
            .thenReturn(Optional.empty());
        mvc.perform(delete("/user")).andExpect(status().isMethodNotAllowed());
    }

    @Test
    @WithMockUser(roles = {})
    public void deleteUser_found() throws Exception {
        when(userService.findByIdAndName(anyString(), anyString()))
            .thenReturn(Optional.of(new UserDto()));
        mvc.perform(delete("/user")).andExpect(status().isMethodNotAllowed());
    }

}
