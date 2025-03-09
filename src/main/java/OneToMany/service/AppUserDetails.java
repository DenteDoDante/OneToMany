package OneToMany.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import OneToMany.entity.Users;
import lombok.RequiredArgsConstructor;

import java.util.Collection;

@RequiredArgsConstructor
public class AppUserDetails implements UserDetails {

    private final Users users; // Sua entidade Users

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Aqui você pode criar as authorities, por exemplo, roles do usuário
        return AuthorityUtils.createAuthorityList("ROLE_USER");
    }

    @Override
    public String getPassword() {
        return users.getPassword(); // Retorna a senha do usuário
    }

    @Override
    public String getUsername() {
        return users.getEmail(); // Retorna o e-mail do usuário
    }
}
