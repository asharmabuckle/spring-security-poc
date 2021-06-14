package com.buckle.security.secureapi;

import io.swagger.annotations.Api;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController(value = "/api")
@Api( tags = "Protected APIs")
public class SecuredController {

    @GetMapping("/authenticated")
    public String checkAuthenticated(Principal user) {
        return "You're authenticated, " + user.getName() + "!";
    }

    @PostAuthorize("permitAll()")
    @GetMapping("/check")
    public String check() {
        return "You're authenticated!";
    }

    @GetMapping("/hello")
    @PreAuthorize("hasAuthority('SCOPE_spring-jwt-poc/poc.read')")
    public String hello(Principal user) {
        return "Hello " + user.getName();
    }

    @GetMapping("/user-info")
    @PreAuthorize("hasAuthority('SCOPE_spring-jwt-poc/poc.read')")
    public String userInfo(Principal user) {
        return "Confidential user data";
    }

    @GetMapping("/do-something")
    @PreAuthorize("hasAuthority('SCOPE_spring-jwt-poc/poc.read')")
    public String doingSomething() {
        return "Did something";
    }
}
