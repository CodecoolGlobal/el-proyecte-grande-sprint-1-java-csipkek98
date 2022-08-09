package com.WizardsOfTheCoast.magic.controller;

import com.WizardsOfTheCoast.magic.entity.Role;
import com.WizardsOfTheCoast.magic.entity.User;
import com.WizardsOfTheCoast.magic.service.UserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

//@CrossOrigin(origins = {"http://localhost:3000","https://lemon-stone-05afd8203.1.azurestaticapps.net"},
@CrossOrigin(origins = {"*"},
        methods = {RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}
        , allowedHeaders = "*")
@RestController
@Slf4j
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;

    }

    @PostMapping("/api/usercheck")
    public boolean getUsersWithName(@RequestBody Map<String, String> payload) {
        System.out.println(payload);
        return userService.checkIfUserIsAlreadyExist(payload.get("username"), payload.get("email"));
    }

    @PostMapping("/register")
    public void saveNewUserToDB(@RequestBody User newUser) {
        userService.createNewUser(newUser);
    }

    @PostMapping("/login")
    public User loginWithUserData(@RequestBody Map<String, String> payload) {
        return userService.findUserByNameOrEmail(payload.get("username"), payload.get("email"), payload.get("password"));
    }

    @GetMapping("/api/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok().body(userService.getAllUser());
    }

    @PostMapping("/api/user/save")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body(userService.createNewUser(user));
    }

    @PostMapping("/api/role/save")
    public ResponseEntity<Role> saveUser(@RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping("/api/role/addroletouser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form) {
        userService.addRoleToUser(form.getUsername(), form.getRoleName());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("Refreshing JWT token..");
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            try{
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes(StandardCharsets.UTF_8));
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                User user = userService.getUser(username);
                String access_token = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis()+10*60*100))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles",user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                        .sign(algorithm);
                Map<String,String> tokens=new HashMap<>();
                tokens.put("access_token",access_token);
                tokens.put("refresh_token",refresh_token);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            } catch (Exception exception){
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
//                    response.sendError(FORBIDDEN.value());
                Map<String,String> error =new HashMap<>();
                error.put("error_message",exception.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        }else{
            throw new RuntimeException("Refresh token is missing!");
        }
        log.info("JWT Token refresh complete!");
    }
}

@Data
class RoleToUserForm{
    private String username;
    private String roleName;
}
