package com.secure.taction.SeniorProject.auth;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;

public class InMemoryAuthenticationProviderTest {
    
    private InMemoryAuthenticationProvider authProvider = new InMemoryAuthenticationProvider();

    @Test
    public void valid() {
        final Authentication auth = mock(Authentication.class);
        when(auth.getName()).thenReturn("goodUser");
        when(auth.getCredentials()).thenReturn("goodPassword");
        Authentication result = authProvider.authenticate(auth);
        assertNotNull(result);
        assertTrue(result.getAuthorities().size() == 0);
    }

    @Test
    public void invalid() {

        final Authentication auth = mock(Authentication.class);
        when(auth.getName()).thenReturn("");
        when(auth.getPrincipal()).thenReturn("");
        when(auth.getCredentials()).thenReturn("");

        assertThrows(BadCredentialsException.class, () -> authProvider.authenticate(auth));
    }
}
