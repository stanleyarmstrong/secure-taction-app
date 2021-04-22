package com.secure.taction.SeniorProject.tablesetup.controllers;

import static org.mockito.Mockito.when;

import com.secure.taction.SeniorProject.controllers.BaseControllerTest;

import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserTableControllerTest extends BaseControllerTest {
    private String URL = "/table/user"; 

    @Test
    @WithMockUser(roles = {})
    public void createUsersTable() throws Exception {
        when(userTableService.createTable()).thenReturn("Success");
        mvc.perform(get(URL))
            .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = {})
    public void lisTables() throws Exception {
        mvc.perform(get(URL+"/list"))
            .andExpect(status().isOk());

    }

    @Test
    @WithMockUser(roles = {})
    public void deleteTable() throws Exception {
        when(userTableService.deleteTable())
            .thenReturn("Success");
        mvc.perform(delete(URL+"/killer/delete"))
            .andExpect(status().isOk());
    }

}
