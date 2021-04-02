package com.antaadama.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class TokenUtil {

    public static final String SECRET = "asdfrtyuafqwvq9704'pki;";
    public static final int EXPIRE = 5;

    /**
     * @param userId
     * @param userName
     * @return
     * @throws Exception
     */
    public static String createToken(String userId, String userName) throws Exception {
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.SECOND, EXPIRE);
        Date expireDate = nowTime.getTime();

        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");

        String token = JWT.create()
                .withHeader(map)
                .withClaim("userId", userId)
                .withClaim("userName", userName)
                .withSubject("token")
                .withIssuedAt(new Date())
                .withExpiresAt(expireDate)
                .sign(Algorithm.HMAC256(SECRET));
        return token;
    }

    /**

     * @param token
     * @return
     * @throws Exception
     */
    public static Map<String, Claim> verifyToken(String token)throws Exception{
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        DecodedJWT jwt = null;
        try {
            System.out.println(token);
            jwt = verifier.verify(token);

        }catch (Exception e){
            throw new RuntimeException("time out , please re-login");
        }
        return jwt.getClaims();
    }

    /**
     * @param token
     * @return
     */
    public static Map<String, Claim> parseToken(String token){
        DecodedJWT decodedJWT = JWT.decode(token);
        return decodedJWT.getClaims();
    }


    public static void main(String[] args) throws Exception {
        String token = TokenUtil.createToken("111", "admin");
        System.out.println(token);
        Map<String, Claim> tokenResult = TokenUtil.verifyToken(token);
        System.out.println(tokenResult.get("userId").asString());
        System.out.println(tokenResult.get("userName").asString());
    }
}
