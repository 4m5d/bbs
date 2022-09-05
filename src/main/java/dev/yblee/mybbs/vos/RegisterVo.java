package dev.yblee.mybbs.vos;

import dev.yblee.mybbs.entities.UserEntity;
import dev.yblee.mybbs.enums.RegisterResult;

public class RegisterVo extends UserEntity {
    private RegisterResult result;

    public RegisterResult getResult() {
        return this.result;
    }

    public void setResult(RegisterResult result) {
        this.result = result;
    }
}
