package com.app.jagarv.exception.exceptions.users;

public class InvalidResetTokenOrEmail extends RuntimeException {
    public InvalidResetTokenOrEmail(String error) {
        super(error);
    }
}

// will be used on auth service, in the reset password.
// if the email or reset token are expired,
// this exception will be thrown