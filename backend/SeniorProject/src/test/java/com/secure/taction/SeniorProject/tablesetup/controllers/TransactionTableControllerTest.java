package com.secure.taction.SeniorProject.tablesetup.controllers;

import com.secure.taction.SeniorProject.controllers.BaseControllerTest;

import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TransactionTableControllerTest extends BaseControllerTest {
    
    private String URL = "/table/transaction";

    @Test
    @WithMockUser(roles = {})
    public void createUsersTable() throws Exception {
        when(transactionTableService.createTable()).thenReturn("Success");
        mvc.perform(get(URL))
            .andExpect(status().isOk());
    }


    @Test
    @WithMockUser(roles = {})
    public void deleteTable() throws Exception {
        when(transactionTableService.deleteTable())
            .thenReturn("Success");
        mvc.perform(delete(URL+"/killer/delete"))
            .andExpect(status().isOk());
    }
}
