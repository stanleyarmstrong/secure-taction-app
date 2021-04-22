package com.secure.taction.SeniorProject.tablesetup.controllers;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.secure.taction.SeniorProject.controllers.BaseControllerTest;

public class AccountTableControllerTest extends BaseControllerTest{
    private String URL = "/table/account"; 

    @Test
    @WithMockUser(roles = {})
    public void createUsersTable() throws Exception {
        when(accountTableService.createTable()).thenReturn("Success");
        mvc.perform(get(URL))
            .andExpect(status().isOk());
    }


    @Test
    @WithMockUser(roles = {})
    public void deleteTable() throws Exception {
        when(accountTableService.deleteTable())
            .thenReturn("Success");
        mvc.perform(delete(URL+"/killer/delete"))
            .andExpect(status().isOk());
    }


}
