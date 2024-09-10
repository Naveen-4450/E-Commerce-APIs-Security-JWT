package com.example.controllers;

import com.example.config.JwtUtil;
import com.example.models.dtoModels.UserDto;
import com.example.services.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@Slf4j
public class CredentialController
{
    @Autowired
    private CredentialService credSer;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/denied")
    public ResponseEntity<String> deniedPage()
    {
        return new ResponseEntity<>("Access Denied", HttpStatus.FORBIDDEN);
    }

    @PostMapping("/TokenGenerating")
    public ResponseEntity<String> creatingToken(@RequestBody UserDto userDto)
    {
        boolean validUser = credSer.creatingToken(userDto);

        if (validUser){
            String token = jwtUtil.generateToken(userDto.getUsername());
            return new ResponseEntity<>(token, HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>("Invalid Credentials", HttpStatus.NOT_FOUND);
        }
    }
}
