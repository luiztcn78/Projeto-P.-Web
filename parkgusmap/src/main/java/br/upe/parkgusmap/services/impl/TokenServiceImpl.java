package br.upe.parkgusmap.services.impl;

import br.upe.parkgusmap.entities.Usuario;
import br.upe.parkgusmap.services.TokenService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenServiceImpl implements TokenService {

    @Value("${api.token.secret}")
    String my_secret;

    @Override
    public String generateToken(Usuario usuario) {
        try{
            Algorithm algorithm = Algorithm.HMAC256(my_secret);
            return JWT.create()
                    .withIssuer("ParkGusMap")
                    .withSubject(usuario.getEmail())
                    .withExpiresAt(
                            LocalDateTime.now().plusHours(2).toInstant(
                                    ZoneOffset.of("-03:00")
                            )
                    )
                    .sign(algorithm);
        }catch (Exception e){
            return "";
        }
    }

    @Override
    public String validateToken(String token) {
        try{
            Algorithm algorithm = Algorithm.HMAC256(my_secret);
            return JWT.require(algorithm)
                    .withIssuer("ParkGusMap")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (Exception e){
            return "";
        }
    }
}

