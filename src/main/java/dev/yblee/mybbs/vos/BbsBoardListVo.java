package dev.yblee.mybbs.vos;

import dev.yblee.mybbs.dtos.BbsBoardListArticleDto;
import dev.yblee.mybbs.entities.BbsBoardEntity;
import dev.yblee.mybbs.enums.BbsBoardListResult;
import dev.yblee.mybbs.interfaces.IResult;

import java.util.List;

public class BbsBoardListVo extends BbsBoardEntity implements IResult<BbsBoardListResult> {
    private BbsBoardListResult result;
    private int articlePerPage;
    private int requestPage;
    private int startPage;
    private int endPage;
    private int maxPage;
    private int minPage;
    private List<BbsBoardListArticleDto> articles;

    @Override
    public BbsBoardListResult getResult() {
        return result;
    }

    @Override
    public void setResult(BbsBoardListResult result) {
        this.result = result;
    }

    public int getArticlePerPage() {
        return articlePerPage;
    }

    public void setArticlePerPage(int articlePerPage) {
        this.articlePerPage = articlePerPage;
    }

    public int getRequestPage() {
        return requestPage;
    }

    public void setRequestPage(int requestPage) {
        this.requestPage = requestPage;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public int getMaxPage() {
        return maxPage;
    }

    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }

    public int getMinPage() {
        return minPage;
    }

    public void setMinPage(int minPage) {
        this.minPage = minPage;
    }

    public List<BbsBoardListArticleDto> getArticles() {
        return this.articles;
    }

    public void setArticles(List<BbsBoardListArticleDto> articles) {
        this.articles = articles;
    }


}
