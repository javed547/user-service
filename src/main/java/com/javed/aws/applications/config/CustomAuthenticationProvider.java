package com.javed.aws.applications.config;

import com.javed.aws.applications.model.Login;
import com.javed.aws.applications.model.User;
import com.javed.aws.applications.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName();
        String password = authentication.getCredentials().toString();

        if (authorizedUser(userName, password)) {
            List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
            grantedAuths.add(() -> {
                return "ROLE_ADMIN";
            });
            Authentication auth = new UsernamePasswordAuthenticationToken(userName, password, grantedAuths);
            System.out.println(auth.getAuthorities());
            return auth;
        } else {
            throw new AuthenticationCredentialsNotFoundException("Invalid Credentials!");
        }
    }

    private boolean authorizedUser(String userName, String password) {
        System.out.println("username is :" + userName + " and password is " + password);
        User user = userService.validateUser(new Login(userName, password));
        if (user.getUsername().equals(userName) && user.getPassword().equals(password)){
            return true;
        } else {
            return false;
        }
    }

    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
