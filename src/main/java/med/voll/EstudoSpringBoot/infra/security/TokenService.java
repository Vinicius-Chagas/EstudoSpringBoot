package med.voll.EstudoSpringBoot.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import med.voll.EstudoSpringBoot.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    //Secret declarado como propriedade da API em application.config
    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken(Usuario usuario){
        try {
            var algoritmo = Algorithm.HMAC256(secret); // Tipo de criptografia e senha de criptografia
            return JWT.create()
                    .withIssuer("API Voll.med")
                    .withSubject(usuario.getLogin()) // Vincular o usuário
                    .withExpiresAt(dataExpiracao()) // Data de expiração para o token
                    .sign(algoritmo); // Algoritmo de criptografia
        } catch (JWTCreationException exception){
            throw new RuntimeException("erro ao gerrar token jwt", exception);
        }
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}
