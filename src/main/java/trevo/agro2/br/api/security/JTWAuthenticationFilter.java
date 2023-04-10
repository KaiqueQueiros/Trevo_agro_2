package trevo.agro2.br.api.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import trevo.agro2.br.api.model.User;
import trevo.agro2.br.api.repository.UserRepository;
import java.io.IOException;

@Component
public class JTWAuthenticationFilter extends OncePerRequestFilter {
    public static final String HEADER_ATRIBUTE = "Authorization";
    public static final String ATRIBUTE_PREFIX = "Bearer ";
    @Autowired
    private TokenService service;
    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = retrieveToken(request);
        if (token != null) {
            String subject = service.getSubject(token);
            User user = userRepository.findByLogin(subject);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private String retrieveToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(HEADER_ATRIBUTE);
        if (authorizationHeader != null) {
            return authorizationHeader.replace(ATRIBUTE_PREFIX, "");
        }
        return null;
    }
}
