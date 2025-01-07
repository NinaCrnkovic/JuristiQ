package hr.algebra.juristiq.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/JuristiQ/**",
                                "/JuristiQ/auth/**", // Sve rute za registraciju i prijavu
                                "/JuristiQ/lawfirms/auth_pages/registration-code",
                                "/JuristiQ/lawfirms/auth/register-law-firm",
                                "/JuristiQ/auth_pages/registration-code",
                                "/JuristiQ/lawfirms/**",// Specifična međustranica
                                "/css/**", "/js/**", "/images/**" // Statički resursi
                        ).permitAll()
                        .requestMatchers("/JuristiQ/auth/register-lawyer", "/JuristiQ/lawyers/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/JuristiQ/auth/login") // Ruta za vašu prilagođenu login stranicu
                        .loginProcessingUrl("/login") // URL koji obrađuje zahtjev za prijavu
                        .defaultSuccessUrl("/JuristiQ/home", true) // Gdje preusmjeriti nakon uspješne prijave
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/JuristiQ/logout")
                        .logoutSuccessUrl("/JuristiQ/auth/welcome")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID") // Osigurajte brisanje kolačića sesije
                        .permitAll()
                )

                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/h2-console/**") // Onemogućava CSRF zaštitu za H2 konzolu
                )
                .headers(headers -> headers
                        .frameOptions(frameOptions -> frameOptions.disable()) // Nova metoda za onemogućavanje frameOptions
                );



        return http.build();
    }




    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}