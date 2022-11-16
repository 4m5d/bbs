package dev.yblee.mybbs.entities;

import java.util.Date;

public class BbsArticleEntity {
    private int index;
    private String userNickname;
    private String boardId;
    private Date writtenAt;
    private String title;
    private String content;
    private int view;

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

    public String getBoardId() {
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }

    public Date getWrittenAt() {
        return writtenAt;
    }

    public void setWrittenAt(Date writtenAt) {
        this.writtenAt = writtenAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }
}
