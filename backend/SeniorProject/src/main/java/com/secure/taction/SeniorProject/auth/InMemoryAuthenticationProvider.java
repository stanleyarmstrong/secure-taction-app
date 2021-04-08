package com.secure.taction.SeniorProject.auth;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Profile("test")
@Component("inMemoryAuthenticationProvider")
public class InMemoryAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        DummyUserCredentials user = new DummyUserCredentials(auth.getName(), (String) auth.getCredentials());

        if (StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())) {
            throw new BadCredentialsException("Invalid Credentials: Should not happen");
        }
        return new UsernamePasswordAuthenticationToken(user.getUsername(),user.password, new ArrayList<>());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    private class DummyUserCredentials {

        private String username;
        private String password;

        public DummyUserCredentials() {}

        public DummyUserCredentials(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + getEnclosingInstance().hashCode();
            result = prime * result + ((password == null) ? 0 : password.hashCode());
            result = prime * result + ((username == null) ? 0 : username.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            DummyUserCredentials other = (DummyUserCredentials) obj;
            if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
                return false;
            if (password == null) {
                if (other.password != null)
                    return false;
            } else if (!password.equals(other.password))
                return false;
            if (username == null) {
                if (other.username != null)
                    return false;
            } else if (!username.equals(other.username))
                return false;
            return true;
        }

        private InMemoryAuthenticationProvider getEnclosingInstance() {
            return InMemoryAuthenticationProvider.this;
        }

        
    }
}
