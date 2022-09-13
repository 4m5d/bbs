package dev.yblee.mybbs.vos;

import dev.yblee.mybbs.entities.UserEntity;
import dev.yblee.mybbs.enums.LoginResult;

public class LoginVo extends UserEntity {
    private LoginResult result;

    public LoginResult getResult() {
        return this.result;
    }

    public void setResult(LoginResult result) {
        this.result = result;
    }
}
