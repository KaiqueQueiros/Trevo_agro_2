package trevo.agro2.br.api.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import trevo.agro2.br.api.model.User;
import java.util.Date;

@Service
public class TokenService {
    public static final int TOKEN_EXPIRATE = 8500_000;
    @Value("${api.security.token.secret}")
    private String secret;

    public String token(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API.Trevo.SA")
                    .withSubject(user.getLogin())
                    .withClaim("id", user.getId().toString())
                    .withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRATE))
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new RuntimeException("Erro ao gerar o token", e);
        }
    }

    public String getSubject(String tokenJWT) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("API.Trevo.SA")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token invalido ou expirado");
        }
    }
}
