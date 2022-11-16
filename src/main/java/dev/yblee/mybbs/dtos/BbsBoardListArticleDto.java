package dev.yblee.mybbs.dtos;

import dev.yblee.mybbs.entities.BbsArticleEntity;

public class BbsBoardListArticleDto extends BbsArticleEntity {
    private String userNickname;
    private int commentCount;

    private int viewCount;

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }
}
