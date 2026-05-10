package com.ecommerce.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordHasher {

    // Hashing password
    public static String hashPassword(String password) {

        return BCrypt.hashpw(
                password,
                BCrypt.gensalt()
        );
    }

    // Verifying password
    public static boolean verifyPassword(String password,
                                         String hashedPassword) {

        return BCrypt.checkpw(
                password,
                hashedPassword
        );
    }
}
