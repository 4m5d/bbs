package dev.yblee.mybbs.vos;

import dev.yblee.mybbs.entities.BbsArticleEntity;
import dev.yblee.mybbs.enums.BbsWriteModifyResult;
import dev.yblee.mybbs.interfaces.IResult;

public class BbsWriteModifyVo extends BbsArticleEntity implements IResult<BbsWriteModifyResult> {
    private BbsWriteModifyResult result;

    @Override
    public BbsWriteModifyResult getResult() {
        return result;
    }

    @Override
    public void setResult(BbsWriteModifyResult result) {
        this.result = result;
    }
}
