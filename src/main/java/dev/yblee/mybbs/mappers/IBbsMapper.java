package dev.yblee.mybbs.mappers;

import dev.yblee.mybbs.dtos.BbsArticleReadCommentDto;
import dev.yblee.mybbs.dtos.BbsBoardListArticleDto;
import dev.yblee.mybbs.dtos.BbsBoardSearchArticleDto;
import dev.yblee.mybbs.entities.BbsArticleEntity;
import dev.yblee.mybbs.entities.BbsBoardEntity;
import dev.yblee.mybbs.entities.BbsCommentEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IBbsMapper {
    BbsArticleEntity selectArticleByIndex(
            @Param(value = "index") int index);

    BbsCommentEntity selectCommentByIndex(
            @Param(value = "index") int index);

    int selectArticleCountTotal(BbsBoardEntity bbsBoardEntity);
    int selectArticleCountByUser(
            @Param(value = "boardId") String boardId,
            @Param(value = "userNickname") String userNickname);

    int selectArticleCountLikeTitle(
            @Param(value = "boardId") String boardId,
            @Param(value = "title") String title);

    int selectArticleCountLikeAll(
            @Param(value = "boardId") String boardId,
            @Param(value = "keyword") String keyword);

    List<BbsBoardSearchArticleDto> selectArticlesByUser(
            @Param(value = "boardId") String boardId,
            @Param(value = "count") int count,
            @Param(value = "userNickname") String userNickname);

    List<BbsBoardSearchArticleDto> selectArticlesLikeTitle(
            @Param(value = "boardId") String boardId,
            @Param(value = "count") int count,
            @Param(value = "title") String title);

    List<BbsBoardSearchArticleDto> selectArticlesLikeAll(
            @Param(value = "boardId") String boardId,
            @Param(value = "count") int count,
            @Param(value = "keyword") String keyword);

    List<BbsBoardListArticleDto> selectArticlesForBoardList(
            @Param(value = "boardId") String boardId,
            @Param(value = "count") int count);

    BbsBoardEntity selectBoardById(
            @Param(value = "id") String id);

    List<BbsArticleReadCommentDto> selectCommentsForArticleRead(
            @Param(value = "articleIndex") int articleIndex);

    int insertWrite(BbsArticleEntity bbsArticleEntity);

    int insertComment(BbsCommentEntity bbsCommentEntity);

    int deleteComment(BbsCommentEntity bbsCommentEntity);

    BbsBoardEntity[] selectBoards();

    int updateView(int index);

    int deleteArticle(BbsArticleEntity bbsArticleEntity);

    int updateArticle(BbsArticleEntity bbsArticleEntity);
}
