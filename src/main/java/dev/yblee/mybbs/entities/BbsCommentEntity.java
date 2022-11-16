package dev.yblee.mybbs.entities;

import java.util.Date;

public class BbsCommentEntity {
    private int index;
    private String userNickname;
    private int articleIndex;
    private Date writtenAt;
    private String content;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public int getArticleIndex() {
        return articleIndex;
    }

    public void setArticleIndex(int articleIndex) {
        this.articleIndex = articleIndex;
    }

    public Date getWrittenAt() {
        return writtenAt;
    }

    public void setWrittenAt(Date writtenAt) {
        this.writtenAt = writtenAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
