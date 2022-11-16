package dev.yblee.mybbs.vos;

import dev.yblee.mybbs.entities.BbsArticleEntity;
import dev.yblee.mybbs.enums.BbsWriteResult;
import dev.yblee.mybbs.interfaces.IResult;

public class BbsWriteVo extends BbsArticleEntity implements IResult<BbsWriteResult> {
    private BbsWriteResult result;

    @Override
    public BbsWriteResult getResult() {
        return result;
    }

    @Override
    public void setResult(BbsWriteResult result) {
        this.result = result;
    }
}
