package secretary.common.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService 
{ 
	    @Autowired
		private UserService userService; 
		
		@Override 
		public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException { 
			System.out.println("loadUserByUsername");
			return userService.findBy(id); 
		} 
}
