package dev.yblee.mybbs.services;

import dev.yblee.mybbs.dtos.BbsBoardListArticleDto;
import dev.yblee.mybbs.dtos.BbsBoardSearchArticleDto;
import dev.yblee.mybbs.entities.BbsArticleEntity;
import dev.yblee.mybbs.entities.BbsBoardEntity;
import dev.yblee.mybbs.entities.BbsCommentEntity;
import dev.yblee.mybbs.enums.*;
import dev.yblee.mybbs.mappers.IBbsMapper;
import dev.yblee.mybbs.vos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service(value = "dev.yblee.mybbs.services.BbsService")
public class BbsService {
    public static final int[] ARTICLE_PER_PAGE = new int[]{10, 20};

    private final IBbsMapper bbsMapper;

    @Autowired
    public BbsService(IBbsMapper bbsMapper) {
        this.bbsMapper = bbsMapper;
    }

    public void listBoard(BbsBoardListVo bbsBoardListVo) {
        BbsBoardEntity bbsBoardEntity = this.bbsMapper.selectBoardById(bbsBoardListVo.getId());
        if (bbsBoardEntity == null || bbsBoardEntity.getIndex() == 0) {
            bbsBoardListVo.setResult(BbsBoardListResult.NOT_FOUND);
            return;
        }
        final int pageDivFactor = 10;
        int articleCountTotal = this.bbsMapper.selectArticleCountTotal(bbsBoardEntity);
        if (Arrays.stream(ARTICLE_PER_PAGE).noneMatch(x -> x == bbsBoardListVo.getArticlePerPage())) {
            bbsBoardListVo.setArticlePerPage(ARTICLE_PER_PAGE[0]);
        }
        bbsBoardListVo.setMinPage(1);
        bbsBoardListVo.setMaxPage(articleCountTotal / bbsBoardListVo.getArticlePerPage() + (articleCountTotal % bbsBoardListVo.getArticlePerPage() == 0 ? 0 : 1));
        if (bbsBoardListVo.getRequestPage() < bbsBoardListVo.getMinPage()) {
            bbsBoardListVo.setRequestPage(bbsBoardListVo.getMinPage());
        }
        if (bbsBoardListVo.getRequestPage() > bbsBoardListVo.getMaxPage()) {
            bbsBoardListVo.setRequestPage(bbsBoardListVo.getMaxPage());
        }
        bbsBoardListVo.setStartPage(((bbsBoardListVo.getRequestPage() - 1) / pageDivFactor) * pageDivFactor + 1);
        bbsBoardListVo.setEndPage(Math.min(bbsBoardListVo.getMaxPage(), bbsBoardListVo.getStartPage() + pageDivFactor - 1));

        List<BbsBoardListArticleDto> articles = this.bbsMapper.selectArticlesForBoardList(
                bbsBoardListVo.getId(),
                bbsBoardListVo.getArticlePerPage());
        bbsBoardListVo.setArticles(articles);
        bbsBoardListVo.setResult(BbsBoardListResult.SUCCESS);
    }

    public void write(BbsWriteVo bbsWriteVo) {
        if (this.bbsMapper.insertWrite(bbsWriteVo) == 0) {
            bbsWriteVo.setResult(BbsWriteResult.FAILURE);
        } else {
            bbsWriteVo.setResult(BbsWriteResult.SUCCESS);
        }
    }

    public void insertComment(BbsCommentVo bbsCommentVo) {
        if (this.bbsMapper.insertComment(bbsCommentVo) == 0) {
            bbsCommentVo.setResult(BbsCommentResult.FAILURE);
        } else {
            bbsCommentVo.setResult(BbsCommentResult.SUCCESS);
        }
    }

    public BbsBoardEntity[] getBoards() {
        return this.bbsMapper.selectBoards();
    }

    public void readArticle(BbsArticleReadVo bbsArticleReadVo) {
        BbsArticleEntity bbsArticleEntity = this.bbsMapper.selectArticleByIndex(bbsArticleReadVo.getIndex());
        if (bbsArticleEntity == null || bbsArticleEntity.getIndex() == 0) {
            bbsArticleReadVo.setResult(BbsArticleReadResult.NOT_FOUND);
            return;
        }
        bbsArticleReadVo.setIndex(bbsArticleEntity.getIndex());
        bbsArticleReadVo.setUserNickname(bbsArticleEntity.getUserNickname());
        bbsArticleReadVo.setBoardId(bbsArticleEntity.getBoardId());
        bbsArticleReadVo.setWrittenAt(bbsArticleEntity.getWrittenAt());
        bbsArticleReadVo.setTitle(bbsArticleEntity.getTitle());
        bbsArticleReadVo.setContent(bbsArticleEntity.getContent());
        bbsArticleReadVo.setView(bbsArticleEntity.getView());
        bbsArticleReadVo.setComments(this.bbsMapper.selectCommentsForArticleRead(bbsArticleReadVo.getIndex()));
        bbsArticleReadVo.setResult(BbsArticleReadResult.SUCCESS);

    }

    public void articleComment(BbsCommentVo bbsCommentVo) {
        BbsCommentEntity bbsCommentEntity = this.bbsMapper.selectCommentByIndex(bbsCommentVo.getIndex());
        if (bbsCommentEntity == null || bbsCommentEntity.getIndex() == 0) {
            bbsCommentVo.setResult(BbsCommentResult.FAILURE);
            return;
        }
        bbsCommentVo.setIndex(bbsCommentEntity.getIndex());
        bbsCommentEntity.setUserNickname(bbsCommentEntity.getUserNickname());
        bbsCommentVo.setArticleIndex(bbsCommentEntity.getArticleIndex());
        bbsCommentVo.setWrittenAt(bbsCommentEntity.getWrittenAt());
        bbsCommentVo.setContent(bbsCommentEntity.getContent());
        bbsCommentVo.setResult(BbsCommentResult.SUCCESS);
    }

    public void searchBoard(BbsBoardSearchVo bbsBoardSearchVo) {
        BbsBoardEntity boardEntity = this.bbsMapper.selectBoardById(bbsBoardSearchVo.getId());
        if (boardEntity == null || boardEntity.getIndex() == 0) {
            bbsBoardSearchVo.setResult(BbsBoardSearchResult.NOT_FOUND);
            return;
        }
        final int pageDivFactor = 10;
        int articleCountTotal;
        switch (bbsBoardSearchVo.getCriteria()) {
            case "title":
                articleCountTotal = this.bbsMapper.selectArticleCountLikeTitle(
                        bbsBoardSearchVo.getId(),
                        bbsBoardSearchVo.getKeyword()
                );
                break;
            case "writer":
                articleCountTotal = this.bbsMapper.selectArticleCountByUser(
                        bbsBoardSearchVo.getId(),
                        bbsBoardSearchVo.getKeyword()
                );
                break;
            default:
                articleCountTotal = this.bbsMapper.selectArticleCountLikeAll(
                        bbsBoardSearchVo.getId(),
                        bbsBoardSearchVo.getKeyword()
                );
        }
        if (Arrays.stream(ARTICLE_PER_PAGE).noneMatch(x -> x == bbsBoardSearchVo.getArticlePerPage())) {
            bbsBoardSearchVo.setArticlePerPage(ARTICLE_PER_PAGE[0]);
        }
        bbsBoardSearchVo.setMinPage(1);
        bbsBoardSearchVo.setMaxPage(articleCountTotal / bbsBoardSearchVo.getArticlePerPage() + (articleCountTotal % bbsBoardSearchVo.getArticlePerPage() == 0 ? 0 : 1));
        if (bbsBoardSearchVo.getRequestPage() < bbsBoardSearchVo.getMinPage()) {
            bbsBoardSearchVo.setRequestPage(bbsBoardSearchVo.getMinPage());
        }
        if (bbsBoardSearchVo.getRequestPage() > bbsBoardSearchVo.getMaxPage()) {
            bbsBoardSearchVo.setRequestPage(bbsBoardSearchVo.getMaxPage());
        }
        bbsBoardSearchVo.setStartPage(((bbsBoardSearchVo.getRequestPage() - 1) / pageDivFactor) * pageDivFactor + 1);
        bbsBoardSearchVo.setEndPage(Math.min(bbsBoardSearchVo.getMaxPage(), bbsBoardSearchVo.getStartPage() + pageDivFactor - 1));

        List<BbsBoardSearchArticleDto> articles;
        switch (bbsBoardSearchVo.getCriteria()) {
            case "title":
                articles = this.bbsMapper.selectArticlesLikeTitle(
                        bbsBoardSearchVo.getId(),
                        bbsBoardSearchVo.getArticlePerPage(),
                        bbsBoardSearchVo.getKeyword()
                );
                break;
            case "writer":
                articles = this.bbsMapper.selectArticlesByUser(
                        bbsBoardSearchVo.getId(),
                        bbsBoardSearchVo.getArticlePerPage(),
                        bbsBoardSearchVo.getKeyword()
                );
                break;
            default:
                articles = this.bbsMapper.selectArticlesLikeAll(
                        bbsBoardSearchVo.getId(),
                        bbsBoardSearchVo.getArticlePerPage(),
                        bbsBoardSearchVo.getKeyword()
                );
        }
        System.out.println(bbsBoardSearchVo.getRequestPage());
        bbsBoardSearchVo.setArticles(articles);
        bbsBoardSearchVo.setResult(BbsBoardSearchResult.SUCCESS);
    }

    public void updateViewCount(int index) {
        this.bbsMapper.updateView(index);
    }

    public void deleteArticle(BbsArticleEntity bbsArticleEntity) {
        this.bbsMapper.deleteArticle(bbsArticleEntity);
    }

    public void deleteComment(BbsCommentEntity bbsCommentEntity) {
        this.bbsMapper.deleteComment(bbsCommentEntity);
    }

    public void getArticle(BbsWriteModifyVo bbsWriteModifyVo) {
        BbsArticleEntity bbsArticleEntity = this.bbsMapper.selectArticleByIndex(bbsWriteModifyVo.getIndex());
        if (bbsArticleEntity == null || bbsArticleEntity.getTitle() == null) {
            bbsWriteModifyVo.setResult(BbsWriteModifyResult.NOT_FOUND);
            return;
        }
        bbsWriteModifyVo.setIndex(bbsArticleEntity.getIndex());
        bbsWriteModifyVo.setUserNickname(bbsArticleEntity.getUserNickname());
        bbsWriteModifyVo.setBoardId(bbsArticleEntity.getBoardId());
        bbsWriteModifyVo.setWrittenAt(bbsArticleEntity.getWrittenAt());
        bbsWriteModifyVo.setTitle(bbsArticleEntity.getTitle());
        bbsWriteModifyVo.setContent(bbsArticleEntity.getContent());
        bbsWriteModifyVo.setView(bbsArticleEntity.getView());
        bbsWriteModifyVo.setResult(BbsWriteModifyResult.SUCCESS);
    }

    public void modifyArticle(BbsArticleEntity bbsArticleEntity) {
        this.bbsMapper.updateArticle(bbsArticleEntity);
    }
}
