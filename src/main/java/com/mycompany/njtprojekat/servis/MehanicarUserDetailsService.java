package com.mycompany.njtprojekat.servis;

import com.mycompany.njtprojekat.entity.impl.Mehanicar;
import com.mycompany.njtprojekat.repository.impl.MehanicarRepository;
import java.util.Collections;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MehanicarUserDetailsService implements UserDetailsService {

    private final MehanicarRepository mehanicarRepository;

    public MehanicarUserDetailsService(MehanicarRepository mehanicarRepository) {
        this.mehanicarRepository = mehanicarRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Mehanicar mehanicar = mehanicarRepository.findByUsername(username);

        if (mehanicar == null) {
            throw new UsernameNotFoundException("Mehanicar sa korisnickim imenom " + username + " nije pronadjen.");
        }

        return new org.springframework.security.core.userdetails.User(
                mehanicar.getUsername(),
                mehanicar.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_MEHANICAR"))
        );
    }
}
