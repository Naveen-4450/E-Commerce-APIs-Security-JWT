package com.example.repositories;

import com.example.models.dbModels.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CredentialRepository extends JpaRepository<Credentials, Integer>
{
    Credentials findByUsername(String username);
}
