package com.app.jagarv.outil;

import java.util.UUID;

public class GenerateResetPasswordToken {
   public static String generate() {
       return UUID.randomUUID().toString();
    }
}
