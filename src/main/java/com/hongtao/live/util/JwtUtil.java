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
    private static String key = "WkskpDhSkuBUOP*ITB*123123123";
    public  static  String createJwt(String userId){
        //默认签发有效期24小时的token
        return createJwt(userId,"subject","issure",86400000);
    }
    public static String createJwt(String id, String subject, String issure, long till) {
        JwtBuilder jwtBuilder = Jwts.builder().setId(id)
                .signWith(SignatureAlgorithm.HS256, new SecretKeySpec(DatatypeConverter.parseBase64Binary(key), SignatureAlgorithm.HS256.getJcaName()))
                .setIssuer(issure)
                .setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis() + till));
        return jwtBuilder.compact();
    }

    public static Claims parseJwt(String token) throws Exception {
        Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(key)).parseClaimsJws(token).getBody();
        return claims;


    }
}
