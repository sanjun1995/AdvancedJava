//package com.example.demo.util;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jws;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.security.Keys;
//
//import javax.crypto.SecretKey;
//import java.util.Base64;
//import java.util.Date;
//
//public class JwtUtil {
//    private static final String SECRET_KEY = "S9hIX0IWO5mc+P/syML4MH9ezyTs07HpSaIF3x192zJBgJJLwBjTYB92pyHFk1INQ/aicvFwZt13KqdaOEdbzg==";
//
//    public static String generateToken(String username, long expirationTimeMillis) {
//        return Jwts.builder()
//                .setSubject(username)
//                .setExpiration(new Date(System.currentTimeMillis() + expirationTimeMillis))
//                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
//                .compact();
//    }
//
//    public static boolean validateToken(String token) {
//        try {
//            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    public static String getUsernameFromToken(String token) {
//        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
//        Claims claims = claimsJws.getBody();
//        return claims.getSubject();
//    }
//
//    public static void main(String[] args) {
////        long time = System.currentTimeMillis();
////        System.out.println(time);
////        String token = generateToken("caozhixin", 100000);
////        System.out.println("token: " + token);
//        boolean validate = validateToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjYW96aGl4aW4iLCJleHAiOjE2OTEzNzczMDB9.sHMEU35wFJpAWagnhaXoM-6FxJMOLYqTqneyA8CAlnAwsvA56X8HLPjLYJwZxsoPo5F7k_yyE0GjoKomOszMuQ");
//        System.out.println("validateToken: " + validate);
//        String username = getUsernameFromToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjYW96aGl4aW4iLCJleHAiOjE2OTEzNzczMDB9.sHMEU35wFJpAWagnhaXoM-6FxJMOLYqTqneyA8CAlnAwsvA56X8HLPjLYJwZxsoPo5F7k_yyE0GjoKomOszMuQ");
//        System.out.println("username: " + username);
//    }
//}
//
