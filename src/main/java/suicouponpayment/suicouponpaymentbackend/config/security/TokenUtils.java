package suicouponpayment.suicouponpaymentbackend.config.security;


import suicouponpayment.suicouponpaymentbackend.model.dto.response.Token;
import suicouponpayment.suicouponpaymentbackend.model.dto.response.UserDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class TokenUtils {

    @Value("{security.token.access.secret-key}")
    private String accessTokenSecretKey;

    @Value("${security.token.access.expiry-length}")
    private long accessTokenExpiryInMilliseconds;

    @Value("{security.token.refresh.secret-key}")
    private String refreshTokenSecretKey;

    @Value("${security.token.refresh.expiry-length}")
    private long refreshTokenExpiryInMilliseconds;

    public String generateAccessToken(UserDto user) {

        Claims claims = Jwts.claims()
                .setSubject(user.getEmailAddress());
        claims.put("email", String.valueOf(user.getEmailAddress()));
        claims.put("role", "ROLE_"+user.getRole());

        Date now = new Date();
        Date accessTokenExpiration = new Date(now.getTime() + accessTokenExpiryInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(accessTokenExpiration)
                .signWith(SignatureAlgorithm.HS512, accessTokenSecretKey)
                .compact();

    }

    public String generateRefreshToken(UserDto user) {

        Claims claims = Jwts.claims()
                .setSubject(user.getEmailAddress());
        claims.put("role", "ROLE_CUSTOMER");

        Date now = new Date();
        Date refreshTokenExpiration = new Date(now.getTime() + refreshTokenExpiryInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(refreshTokenExpiration)
                .signWith(SignatureAlgorithm.HS512, refreshTokenSecretKey)
                .compact();

    }

    public Token generateAccessAndRefreshToken(UserDto user){
        return new Token(generateAccessToken(user), generateRefreshToken(user));
    }

    public Claims validateAccessToken(String token) {

        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(accessTokenSecretKey)
                    .parseClaimsJws(token)
                    .getBody();
        }
        catch (Exception e) {
            throw new BadCredentialsException("Invalid access token: "+e.getMessage());
        }
        return claims;
    }


    public Claims validateRefreshToken(String token) {

        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(refreshTokenSecretKey)
                    .parseClaimsJws(token)
                    .getBody();
        }
        catch (Exception e) {
            throw new BadCredentialsException("Invalid refresh token: "+e.getMessage());
        }
        return claims;
    }
}
