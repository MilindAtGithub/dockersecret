package com.milind.dockersecret;

import org.springframework.stereotype.Component;

@Component
public class Password {
    @Secret(secret = "PASSWORD", propValue = "app.password")
    String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

