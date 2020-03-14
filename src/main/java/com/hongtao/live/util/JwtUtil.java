package com.hongtao.live.util;

import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Created 2020/3/13.
 *
 * @author HongTao
 */
public class JwtUtil {
    private static final String KEY = "WkskpDhSkuBUOP*ITB*935245421";
    private static final String SUBJECT = "LoginAndLogout";
    private static final String ISSURE = "LiveServer";

    public static final String CLAIMS_USER_ID = "userId";

    private static final long EXPIRATION = 86400000 * 7;

    public static String createJwt(String userId) {
        //默认签发有效期7天的token
        return createJwt(userId, SUBJECT, ISSURE, EXPIRATION);
    }

    public static String createJwt(String userId, String subject, String issure, long till) {
        JwtBuilder jwtBuilder = Jwts.builder()
                .claim(CLAIMS_USER_ID, userId)
                .signWith(SignatureAlgorithm.HS256, new SecretKeySpec(DatatypeConverter.parseBase64Binary(KEY), SignatureAlgorithm.HS256.getJcaName()))
                .setIssuer(issure)
                .setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis() + till));
        return jwtBuilder.compact();
    }

    public static Claims parseJwt(String token) throws Exception {
        Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(KEY)).parseClaimsJws(token).getBody();
        return claims;


    }
}
