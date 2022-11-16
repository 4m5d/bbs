package dev.yblee.mybbs.vos;

import dev.yblee.mybbs.dtos.BbsBoardSearchArticleDto;
import dev.yblee.mybbs.entities.BbsBoardEntity;
import dev.yblee.mybbs.enums.BbsBoardSearchResult;
import dev.yblee.mybbs.interfaces.IResult;

import java.util.List;

public class BbsBoardSearchVo extends BbsBoardEntity implements IResult<BbsBoardSearchResult> {
    private BbsBoardSearchResult result;
    private String criteria;
    private String keyword;
    private int articlePerPage;
    private int requestPage;
    private int startPage;
    private int endPage;
    private int maxPage;
    private int minPage;

    private List<BbsBoardSearchArticleDto> articles;

    @Override
    public BbsBoardSearchResult getResult() {
        return result;
    }

    @Override
    public void setResult(BbsBoardSearchResult result) {
        this.result = result;
    }

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
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

    public List<BbsBoardSearchArticleDto> getArticles() {
        return articles;
    }

    public void setArticles(List<BbsBoardSearchArticleDto> articles) {
        this.articles = articles;
    }
}
