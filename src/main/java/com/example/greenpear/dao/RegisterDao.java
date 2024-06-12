package com.example.greenpear.dao;

import com.example.greenpear.entities.Registration;
import com.example.greenpear.utils.Role;

import javax.security.auth.login.CredentialException;
import java.sql.SQLException;

public interface RegisterDao {

    void registerNewUser(Registration newUser) throws SQLException, CredentialException;

    boolean emailExists(String email) throws SQLException, CredentialException;
}
