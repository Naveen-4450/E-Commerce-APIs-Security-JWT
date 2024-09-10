package com.example.services;

import com.example.config.CustomUserDetails;
import com.example.models.dbModels.Credentials;
import com.example.models.dtoModels.UserDto;
import com.example.repositories.CredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CredentialService implements UserDetailsService
{

    @Autowired
    private PasswordEncoder encode;

    @Autowired
    private CredentialRepository credRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Credentials credentials = credRepo.findByUsername(username);
        if(credentials == null){
            throw new UsernameNotFoundException("User Not Found");
        }

        return new CustomUserDetails(credentials);
    }


//    ******************************* JWT Token *******************************

    public boolean creatingToken(UserDto userDto)
    {
        Credentials cr = credRepo.findByUsername(userDto.getUsername());
        if(cr != null && encode.matches(userDto.getPassword(), cr.getPassword())){
            return true;
        }else {
            return false;
        }
    }

}
