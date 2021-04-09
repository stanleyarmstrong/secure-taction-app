package com.secure.taction.SeniorProject.controllers.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.secure.taction.SeniorProject.AuthTestConfig;
import com.secure.taction.SeniorProject.auth.AuthHelper;
import com.secure.taction.SeniorProject.auth.SecureTactionAuthenticationProviderTest;
import com.secure.taction.SeniorProject.controllers.UserController;
import com.secure.taction.SeniorProject.repositories.UserRepository;
import com.secure.taction.SeniorProject.services.UserService;
import com.secure.taction.SeniorProject.tablesetup.controllers.CreditCardTableController;
import com.secure.taction.SeniorProject.tablesetup.controllers.UserTableController;
import com.secure.taction.SeniorProject.tablesetup.services.CreditCardTableService;
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
    protected CreditCardTableController creditCardTableController;

    @MockBean
    protected CreditCardTableService creditCardTableService;

}
