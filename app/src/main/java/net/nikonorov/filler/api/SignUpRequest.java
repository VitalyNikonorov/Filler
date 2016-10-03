package net.nikonorov.filler.api;

/**
 * Created by vitaly on 03.10.16.
 */

public class SignUpRequest {

    String name;
    String email;
    String password;

    public SignUpRequest(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }
}
