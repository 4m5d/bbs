package dev.yblee.mybbs.dtos;

import dev.yblee.mybbs.entities.BbsCommentEntity;

public class BbsArticleReadCommentDto extends BbsCommentEntity {
    private String userNickname;

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }
}
