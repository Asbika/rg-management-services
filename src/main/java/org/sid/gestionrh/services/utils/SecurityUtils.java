package org.sid.gestionrh.services.utils;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.sid.gestionrh.models.payloads.TokenBody;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
@Component
public class SecurityUtils {

    @Value("${companyservice.tokensecret}")
    private String secret;
    @Value("${companyservice.tokenexpiration}")
    private int tokenExpiration;
    public String generateToken(TokenBody tokenBody){
        Date date = new Date();
        return Jwts.builder()
                .signWith(getSecretKey(),
                        SignatureAlgorithm.HS256)
                .setIssuedAt(date)
                .setExpiration(new Date(date.getTime()+1000*60*tokenExpiration))
                .setSubject(tokenBody.getEmail())
                .compact();
    }
    private Key getSecretKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(Base64.getEncoder().encodeToString(secret.getBytes(StandardCharsets.UTF_8))));
    }
    public boolean validateToken(String token) throws ExpiredJwtException, MalformedJwtException, SignatureException, IllegalArgumentException{
        Jwts.parserBuilder().setSigningKey(getSecretKey()).build().parse(token);
        return true;
    }
    public String getEmail(String token){
        return Jwts.parserBuilder().setSigningKey(getSecretKey()).build().parseClaimsJws(token).getBody().getSubject();
    }
}
