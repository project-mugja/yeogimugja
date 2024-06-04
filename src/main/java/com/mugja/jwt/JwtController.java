package com.mugja.jwt;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/jwt")
@Controller
public class JwtController {
    private final JwtUtils jwtUtils;

    public JwtController(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @RequestMapping
    public String test(Authentication authentication) {

//        String token = jwtUtils.createToken(authentication.getName());
//        System.out.println("token ==> " + token);
//
//        boolean validated = jwtUtils.validateToken(token);
//        System.out.println("validated ==> " + validated);
//
//        String secret = jwtUtils.createSecret();
//        System.out.println("secret ==> " + secret);

        return "/view/jwt";
    }
}
