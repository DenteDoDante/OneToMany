package OneToMany.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Configuração de CSRF
                .csrf(csrf -> csrf.disable()) // Desabilita CSRF, caso necessário

                // Configuração de autorização
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/login", "/register").permitAll() // Permite acesso público às páginas de
                                                                            // login e registro
                        .anyRequest().authenticated() // Exige autenticação para as outras páginas
                )

                // Configuração de login
                .formLogin(form -> form
                        .loginPage("/login") // Página de login personalizada
                        .loginProcessingUrl("/perform_login") // URL para processar o login
                        .defaultSuccessUrl("/home", true) // Redireciona para /home após login bem-sucedido
                        .permitAll() // Permite acesso público à página de login
                )

                // Configuração de logout
                .logout(logout -> logout
                        .logoutUrl("/logout") // URL para logout
                        .logoutSuccessUrl("/login?logout") // Redireciona para login após logout
                        .permitAll() // Permite acesso público à página de logout

                );

        return http.build(); // Construção da configuração de segurança
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Encoder para senha
    }

}
