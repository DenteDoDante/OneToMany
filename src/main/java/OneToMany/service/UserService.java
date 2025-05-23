package OneToMany.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import OneToMany.entity.Users;
import OneToMany.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public Users registerUser(Users users) {

        users.setPassword(passwordEncoder.encode(users.getPassword()));// Criptografa a senha
        return userRepository.save(users);
    }

    public Users authenticateUser(String email, String password) {
        Users users = userRepository.findByEmail(email);
        if (users != null && passwordEncoder.matches(password, users.getPassword())) {
            return users;
        }
        return null;
    }

    public Users findUserByEmail(String email) 
    {
        Users users = userRepository.findByEmail(email);

        return users;
    }

    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Users users = userRepository.findByName(name);
        if (users == null) {
            throw new UsernameNotFoundException("Usuário não encontrado: " + name);

        }
        return new AppUserDetails(users);
    }

    public Users SaveUser(Users users) {
        return userRepository.save(users);
    }

}
