package secretary.common.user.helper;


import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import secretary.common.user.model.CustomUserDetails;
import secretary.common.user.service.CustomUserDetailsService;

/**
 * 이걸 사용해도 되고 시큐리티에서 기본적으로 제공해주는 DaoAuthenticationProvider를 사용해도 무방.
 * @author
 *
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{
	
	@Autowired
	private UserDetailsService customUserDetailsService;

	@Autowired
    private PasswordEncoder encoder;
    private org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(CustomAuthenticationProvider.class);
    
    public UserDetailsService getUserDetailsService() {
        return customUserDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.customUserDetailsService = userDetailsService;
    }
    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.debug("CustomUserAuthenticationProvider.authenticate :::: {}",authentication.toString());
        System.out.println("CustomUserAuthenticationProvider================================================");
        
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken)authentication;
        
        String userId = token.getName();
        
        CustomUserDetails user = null;
        
        if(!StringUtils.isEmpty(userId)) {
            user = (CustomUserDetails) customUserDetailsService.loadUserByUsername(userId);
        }
        
        if(ObjectUtils.isEmpty(user)) {
            throw new UsernameNotFoundException("Invalid username");
        }
        
        user.setUsername(user.getUsername());
        user.setPassword(user.getPassword());
        
        String password = user.getPassword();
        
		/*
		 * if(!StringUtils.equals(password,
		 * encoder.encode(String.valueOf(token.getCredentials())))) { throw new
		 * BadCredentialsException("Invalid password"); }
		 */
        
        if(!StringUtils.equals(password, (String)token.getCredentials())) {
            throw new BadCredentialsException("Invalid password");
        }
        
        return new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
        //return super.authenticate(authentication); 
        
    }
 
    @Override
    public boolean supports(Class<?> authentication) {
        // TODO Auto-generated method stub
        log.debug("CustomUserAuthenticationProvider.supports ::::");
        return UsernamePasswordAuthenticationToken
                .class.equals(authentication);
    }
 
}


