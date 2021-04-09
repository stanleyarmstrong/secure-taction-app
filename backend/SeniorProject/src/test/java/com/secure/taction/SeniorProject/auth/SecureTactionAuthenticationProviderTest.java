package com.secure.taction.SeniorProject.auth;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;

@ExtendWith(MockitoExtension.class)
public class SecureTactionAuthenticationProviderTest {

    @InjectMocks
    private SecureTactionAuthenticationProvider authProvider;
    @Mock
    private AuthHelper authHelper;

    @Test
    public void authenticate_blankStrings() {
        final Authentication auth = mock(Authentication.class);
        when(auth.getName()).thenReturn("");
        when(auth.getCredentials()).thenReturn("");
        assertThrows(BadCredentialsException.class, 
            () -> authProvider.authenticate(auth));
    }

    @Test
    public void authenticate_validFields() {
        // TODO Update this test once the methods are updated
        final Authentication auth = mock(Authentication.class);
        when(auth.getName()).thenReturn("validUserName");
        when(auth.getCredentials()).thenReturn("validPassword");
        when(authHelper.authenticate(anyString(), anyString()))
            .thenReturn(true);
        Authentication result = authProvider.authenticate(auth);
        assertNotNull(result);
        assertTrue(result.getAuthorities().size() == 0);

    }

    @Test
    public void invalid() {
        final Authentication auth = mock(Authentication.class);
        when(auth.getName()).thenReturn("invalidUserName");
        when(auth.getCredentials()).thenReturn("invalidPassword");
        when(authHelper.authenticate(anyString(), anyString()))
            .thenReturn(false);
        Authentication result = authProvider.authenticate(auth);
        assertNull(null);
    }
}
