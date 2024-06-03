package com.mugja.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;

@Service
public class JwtUtils {
    private static final String secret = "kHqw7vweVp2ZJ07CRjEqMfAldY1VuXn/FWVw2OEGzus="; // JWT secret key
    private static final long validityInMilliseconds = 3600000; // 1 hour

    /** token 만들기 */
    public String createToken(String user) {

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        String compact = Jwts.builder()
                .setSubject("mugjatk")   // 토큰 제목
                .setIssuer(user)    // 발급자
                .setIssuedAt(now)   // 발급 시간
                .setExpiration(validity)    // 만료 시간
                .signWith(new SecretKeySpec(secret.getBytes(), SignatureAlgorithm.HS256.getJcaName()))  // HS256 알고리즘 사용 secret key 생성
                .compact(); // 생성

        return compact;
    }

    /** token 검증 */
    public boolean validateToken(String token) {

        try {
            // 토큰 검증
            Key key = Keys.hmacShaKeyFor(secret.getBytes());
            Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
            System.out.println("claims = " + claims);

            // 여기에서 필요한 추가 검증 로직을 수행
            // 예: 만료 시간 확인, 특정 클레임 값 확인 등

            return true; // 검증 성공
        } catch (Exception e) {
            System.out.println("e = " + e);
            return false; // 검증 실패
        }
    }

    /** secret 를 상수로 설정하거나 직접 만드는게 가능하다. */
    public String createSecret() {

        String key = "";

        try {
            // KeyGenerator를 사용하여 HMAC-SHA256에 적합한 secret key 생성
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
            SecretKey secretKey = keyGen.generateKey();

            // secret key를 Base64로 인코딩하여 출력
            key = java.util.Base64.getEncoder().encodeToString(secretKey.getEncoded());

        } catch (Exception e) {
            System.out.println("e = " + e);
        }

        return key;
    }

    /** token에서 클레임 추출 */
    public Claims getClaimsFromToken(String token) {
        Key key = Keys.hmacShaKeyFor(secret.getBytes());
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

}
