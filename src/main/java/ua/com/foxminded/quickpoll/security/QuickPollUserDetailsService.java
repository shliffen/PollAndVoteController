package ua.com.foxminded.quickpoll.security;

import javax.inject.Inject;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ua.com.foxminded.quickpoll.domain.User;
import ua.com.foxminded.quickpoll.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;


@Component
public class QuickPollUserDetailsService implements UserDetailsService {
    @Inject
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException(String.format("User with the username %s doesn't exist", username));
        }
        // Create a granted authority based on user's role.
        // Can't pass null authorities to user. Hence initialize with an empty arraylist
        List<GrantedAuthority> authorities = new ArrayList<>();
        if(user.isAdmin()) {
            authorities = AuthorityUtils.createAuthorityList("ROLE_ADMIN");
        } else if (user.isStaff()){
            authorities = AuthorityUtils.createAuthorityList("ROLE_STAFF");
        } else if (user.isVisitor()){
            authorities = AuthorityUtils.createAuthorityList("ROLE_VISITOR");
        }
        // Create a UserDetails object from the data
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
        return userDetails;
    }
}