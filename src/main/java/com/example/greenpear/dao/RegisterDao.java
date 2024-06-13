package com.example.greenpear.dao;

import com.example.greenpear.entities.UserProfile;

import javax.security.auth.login.CredentialException;
import java.sql.SQLException;

public interface RegisterDao {

    void registerNewUser(UserProfile newUser) throws SQLException, CredentialException;

    boolean emailExists(String email) throws SQLException, CredentialException;
}
