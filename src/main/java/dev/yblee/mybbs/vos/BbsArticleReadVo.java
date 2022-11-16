package dev.yblee.mybbs.vos;

import dev.yblee.mybbs.dtos.BbsArticleReadCommentDto;
import dev.yblee.mybbs.entities.BbsArticleEntity;
import dev.yblee.mybbs.enums.BbsArticleReadResult;
import dev.yblee.mybbs.interfaces.IResult;

import java.util.List;

public class BbsArticleReadVo extends BbsArticleEntity implements IResult<BbsArticleReadResult> {
    private BbsArticleReadResult result;
    private String userNickname;

    private int viewCount;

    private List<BbsArticleReadCommentDto> comments;

    @Override
    public BbsArticleReadResult getResult() {
        return result;
    }

    @Override
    public void setResult(BbsArticleReadResult result) {
        this.result = result;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public List<BbsArticleReadCommentDto> getComments() {
        return comments;
    }

    public void setComments(List<BbsArticleReadCommentDto> comments) {
        this.comments = comments;
    }
}
