package dev.yblee.mybbs.vos;

import dev.yblee.mybbs.dtos.BbsArticleReadCommentDto;
import dev.yblee.mybbs.entities.BbsCommentEntity;
import dev.yblee.mybbs.enums.BbsCommentResult;
import dev.yblee.mybbs.interfaces.IResult;

import java.util.List;

public class BbsCommentVo extends BbsCommentEntity implements IResult<BbsCommentResult> {
    private BbsCommentResult result;

    @Override
    public BbsCommentResult getResult() {
        return result;
    }

    @Override
    public void setResult(BbsCommentResult result) {
        this.result = result;
    }
}
