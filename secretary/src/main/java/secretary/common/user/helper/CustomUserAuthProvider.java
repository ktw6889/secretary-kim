package secretary.common.user.helper;


import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * 이걸 사용해도 되고 시큐리티에서 기본적으로 제공해주는 DaoAuthenticationProvider를 사용해도 무방.
 * @author
 *
 */

public class CustomUserAuthProvider extends DaoAuthenticationProvider{
	
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    	System.out.println("=============================>authenticate");
        return super.authenticate(authentication); 
        
    }
 
    
 
}


