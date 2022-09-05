package dev.yblee.mybbs.entities;

public class UserEntity {
    private String email;
    private String password;
    private String nickname;
    private boolean isDeleted;
    private boolean isSuspended;
    private boolean isAdmin;

    public UserEntity() {
    }

    public UserEntity(String email, String password, String nickname, boolean isDeleted, boolean isSuspended, boolean isAdmin) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.isDeleted = isDeleted;
        this.isSuspended = isSuspended;
        this.isAdmin = isAdmin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public boolean isSuspended() {
        return isSuspended;
    }

    public void setSuspended(boolean suspended) {
        isSuspended = suspended;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
