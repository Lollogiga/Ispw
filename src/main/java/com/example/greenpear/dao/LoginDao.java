package com.example.greenpear.dao;

import com.example.greenpear.entities.UserProfile;

import javax.security.auth.login.CredentialException;
import java.sql.SQLException;

public interface LoginDao {
    UserProfile loginUser(UserProfile user) throws SQLException, CredentialException;
}
