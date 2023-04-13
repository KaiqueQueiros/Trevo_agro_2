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

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, "/user/login").permitAll()
                .requestMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").permitAll()

                .requestMatchers(HttpMethod.GET,"/user/list").hasRole("ADMINISTRADOR")
                .requestMatchers(HttpMethod.DELETE,"/user/delete/**").hasRole("ADMINISTRADOR")
                .requestMatchers(HttpMethod.POST,"/user/register").hasRole("ADMINISTRADOR")

                .requestMatchers(HttpMethod.POST,"/product/register").hasAnyRole("ADMINISTRADOR","COLABORADOR")
                .requestMatchers(HttpMethod.GET,"/product/list").hasAnyRole("ADMINISTRADOR","CLIENTE","COLABORADOR")
                .requestMatchers(HttpMethod.GET,"/find/**").hasAnyRole("ADMINISTRADOR","COLABORADOR","CLIENTE")
                .requestMatchers(HttpMethod.PUT,"/product/update/**").hasAnyRole("ADMINISTRADOR")
                .requestMatchers(HttpMethod.DELETE,"/product/delete/**").hasAnyRole("ADMINISTRADOR")

                .requestMatchers(HttpMethod.POST,"/budget/register").hasAnyRole("ADMINISTRADOR","CLIENTE","COLABORADOR")
                .requestMatchers(HttpMethod.GET,"/budget/list").hasAnyRole("ADMINISTRADOR","COLABORADOR")
                .requestMatchers(HttpMethod.GET,"/budget/find/id/**","/budget/find/email/**").hasAnyRole("ADMINISTRADOR","COLABORADOR")
                .requestMatchers(HttpMethod.PUT,"/budget/update/**").hasAnyRole("ADMINISTRADOR","COLABORADOR")
                .requestMatchers(HttpMethod.DELETE,"/budget/delete/**").hasAnyRole("ADMINISTRADOR","COLABORADOR")

                .anyRequest().authenticated()
                .and().addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}
