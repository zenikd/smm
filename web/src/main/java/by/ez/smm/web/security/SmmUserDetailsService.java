package by.ez.smm.web.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import by.ez.smm.dao.orm.entity.Users;
import by.ez.smm.dao.orm.filter.UserFilter;
import by.ez.smm.service.UserService;

@Service("userDetailsService")
public class SmmUserDetailsService implements UserDetailsService
{
	@Autowired
	UserService userService;

	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException
	{
		UserFilter userFilter = new UserFilter();
		userFilter.setName(name);
		List<Users> users = userService.find(userFilter);
		Users user = users.get(0);

		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		grantedAuthorities.add(new SimpleGrantedAuthority("Admin"));

		return new User(user.getName(), user.getPass(), grantedAuthorities);
	}
}
