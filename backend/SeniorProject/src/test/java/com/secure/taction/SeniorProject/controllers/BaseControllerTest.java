package com.secure.taction.SeniorProject.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.secure.taction.SeniorProject.AuthTestConfig;
import com.secure.taction.SeniorProject.auth.AuthHelper;
import com.secure.taction.SeniorProject.auth.SecureTactionAuthenticationProviderTest;
import com.secure.taction.SeniorProject.controllers.UserController;
import com.secure.taction.SeniorProject.repositories.AccountRepository;
import com.secure.taction.SeniorProject.repositories.BudgetRepository;
import com.secure.taction.SeniorProject.repositories.UserRepository;
import com.secure.taction.SeniorProject.services.AccountService;
import com.secure.taction.SeniorProject.services.BudgetService;
import com.secure.taction.SeniorProject.services.UserService;
import com.secure.taction.SeniorProject.tablesetup.controllers.BudgetTableController;
import com.secure.taction.SeniorProject.tablesetup.controllers.AccountTableController;
import com.secure.taction.SeniorProject.tablesetup.controllers.UserTableController;
import com.secure.taction.SeniorProject.tablesetup.services.BudgetTableService;
import com.secure.taction.SeniorProject.tablesetup.services.AccountTableService;
import com.secure.taction.SeniorProject.tablesetup.services.UserTableService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
@ActiveProfiles("test")
public class BaseControllerTest {

    @Autowired
    protected MockMvc mvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    protected AuthHelper authHelper;

    @MockBean
    protected UserController userController;

    @MockBean
    protected UserService userService;

    @MockBean
    protected UserRepository userRepository;

    @MockBean
    protected UserTableController userTableController;

    @MockBean
    protected UserTableService userTableService;

    @MockBean   
    protected BudgetTableController budgetTableController;

    @MockBean
    protected BudgetTableService budgetTableService;

    @MockBean
    protected AccountTableController accountTableController;

    @MockBean
    protected BudgetRepository budgetRepository;

    @MockBean
    protected BudgetController budgetController;

    @MockBean
    protected BudgetService budgetService;

    @MockBean
    protected AccountTableService accountTableService;

    @MockBean
    protected AccountController accountController;

    @MockBean
    protected AccountService accountService;

    @MockBean
    protected AccountRepository accountRepository;

}