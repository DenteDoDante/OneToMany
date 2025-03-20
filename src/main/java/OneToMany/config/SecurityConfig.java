package OneToMany.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity

public class SecurityConfig implements WebMvcConfigurer {

        @Bean
        public BCryptPasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                // Configuração de CSRF
                                .csrf(csrf -> csrf.disable()) // Desabilita CSRF, caso necessário

                                // Configuração de autorização
                                .authorizeHttpRequests(authz -> authz
                                                .requestMatchers("/login", "/register", "/api/auth/register")
                                                .permitAll() // Permite acesso público às páginas de login e registro
                                                .anyRequest().authenticated() // Exige autenticação para as outras
                                                                              // páginas
                                )

                                // Configuração de login
                                // .formLogin(form -> form
                                // .loginPage("/login") // Página de login personalizada
                                // .loginProcessingUrl("/perform_login") // URL para processar o login
                                // .defaultSuccessUrl("/home", true) // Redireciona para /home após login
                                // bem-sucedido
                                // .permitAll() // Permite acesso público à página de login
                                // )

                                // Configuração de logout
                                .logout(logout -> logout
                                                .logoutUrl("/logout") // URL para logout
                                                .logoutSuccessUrl("/login?logout") // Redireciona para login após logout
                                                .permitAll() // Permite acesso público à página de logout
                                )

                                // Configuração para evitar redirecionamento no login, permitindo uma política
                                // sem estado (stateless)
                                .sessionManagement(session -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

                return http.build(); // Construção da configuração de segurança
        }

        @SuppressWarnings("null")
        @Override
        public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/auth/register")
                                .allowedOrigins("http://localhost:8081") // Origem permitida
                                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos permitidos
                                .allowedHeaders("Authorization", "Content-Type") // Cabeçalhos permitidos
                                .allowCredentials(true); // Permite credenciais (cookies, etc.)

        }

}