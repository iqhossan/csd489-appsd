package com.gateway.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.security.Key;

@Component
public class JwtUtil {

    public static final String SECRET="NkDg4Ck5/TXwU3zYbGGIZuF0R9qudDdPFbnFD3vycC1V9c9BGgewo0ZiesT0QORhg6NkIv5wiGkGjeTLAVqh3w==";

    public void validateToken(final String token){
        //System.out.println("token: "+token);
        Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
    }

    private Key getSignKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
