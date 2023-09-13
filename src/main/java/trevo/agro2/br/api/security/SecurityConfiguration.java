package trevo.agro2.br.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Autowired
    private JTWAuthenticationFilter authenticationFilter;

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http.csrf()
//                .disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and().authorizeHttpRequests()
//                .requestMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").permitAll()
//
//                .requestMatchers(HttpMethod.POST, "/user/login").permitAll()
//                .requestMatchers(HttpMethod.GET,"/user/list","/find/{id}").hasRole("ADMINISTRADOR")
//                .requestMatchers(HttpMethod.DELETE,"/user/delete/**").hasRole("ADMINISTRADOR")
//                .requestMatchers(HttpMethod.POST,"/user/register").permitAll()
//                .requestMatchers(HttpMethod.PUT,"/user/update/**").hasRole("ADMINISTRADOR")
//
//                .requestMatchers(HttpMethod.POST,"/chamado/criar").permitAll()
//                .requestMatchers(HttpMethod.GET,"/chamado/{id}").permitAll()
//                .requestMatchers(HttpMethod.GET,"/chamado/listar").permitAll()
//
//                .anyRequest().authenticated()
//                .and().addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
//                .build();
//    }

}
