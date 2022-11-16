package dev.yblee.mybbs.controllers;

import dev.yblee.mybbs.entities.BbsArticleEntity;
import dev.yblee.mybbs.entities.BbsCommentEntity;
import dev.yblee.mybbs.entities.UserEntity;
import dev.yblee.mybbs.enums.BbsCommentResult;
import dev.yblee.mybbs.enums.BbsWriteModifyResult;
import dev.yblee.mybbs.enums.BbsWriteResult;
import dev.yblee.mybbs.services.BbsService;
import dev.yblee.mybbs.vos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller(value = "dev.yblee.mybbs.controllers.BbsController")
@RequestMapping(value = "/bbs")
public class BbsController {
    private final BbsService bbsService;

    @Autowired
    public BbsController(BbsService bbsService) {
        this.bbsService = bbsService;
    }

    @RequestMapping(value = "board/list/{boardId}", method = RequestMethod.GET)
    public ModelAndView getBoardList(
            @PathVariable(value = "boardId", required = true) String boardId,
            BbsBoardListVo bbsBoardListVo,
            ModelAndView modelAndView

    ) {
        return this.getBoardList(boardId, 1, bbsBoardListVo, modelAndView);
    }

    @RequestMapping(value = "board/list/{boardId}/{page}", method = RequestMethod.GET)
    public ModelAndView getBoardList(
            @PathVariable(value = "boardId", required = true) String boardId,
            @PathVariable(value = "page", required = true) int page,
            BbsBoardListVo bbsBoardListVo,
            ModelAndView modelAndView
    ) {
        bbsBoardListVo.setId(boardId);
        bbsBoardListVo.setRequestPage(page);
        bbsBoardListVo.setResult(null);
        this.bbsService.listBoard(bbsBoardListVo);
        modelAndView.addObject("bbsBoardListVo", bbsBoardListVo);
        modelAndView.addObject("bbsBoardEntities",
                this.bbsService.getBoards());
        modelAndView.setViewName("bbs/board");
        return modelAndView;
    }

    @RequestMapping(value = "write", method = RequestMethod.GET)
    public ModelAndView getWrite(
            ModelAndView modelAndView
    ) {
        modelAndView.addObject("bbsBoardEntities",
                this.bbsService.getBoards());
        modelAndView.setViewName("bbs/write");

        return modelAndView;
    }

    @RequestMapping(value = "write", method = RequestMethod.POST)
    public ModelAndView postWrite(
            BbsWriteVo bbsWriteVo,
            ModelAndView modelAndView,
            @SessionAttribute(value = "userEntity", required = false) UserEntity userEntity
    ) {
        bbsWriteVo.setUserNickname(userEntity.getNickname());
        bbsWriteVo.setResult(null);
        this.bbsService.write(bbsWriteVo);
        if (bbsWriteVo.getResult() == BbsWriteResult.SUCCESS) {
            //글 작성이 성공하면 글 리스트 페이지로 가기
            modelAndView.setViewName("redirect:board/list/" + bbsWriteVo.getBoardId());
        } else {
            modelAndView.addObject("bbsWriteVo", bbsWriteVo);
            modelAndView.setViewName("bbs/write");
        }
        return modelAndView;
    }

    @RequestMapping(value = "article/read/{articleIndex}", method = RequestMethod.GET)
    public ModelAndView getArticleRead(
            @PathVariable(value = "articleIndex", required = true) int articleIndex,
            BbsArticleReadVo bbsArticleReadVo,
            BbsCommentVo bbsCommentVo,
            ModelAndView modelAndView
    ) {
        bbsArticleReadVo.setIndex(articleIndex);
        bbsArticleReadVo.setResult(null);
        this.bbsService.updateViewCount(bbsArticleReadVo.getIndex());
        this.bbsService.readArticle(bbsArticleReadVo);
        this.bbsService.articleComment(bbsCommentVo);
        modelAndView.addObject("bbsArticleReadVo", bbsArticleReadVo);
        modelAndView.addObject("bbsCommentVo", bbsCommentVo);
        modelAndView.setViewName("bbs/read");
        return modelAndView;
    }

    @RequestMapping(value = "article/read/{articleIndex}", method = RequestMethod.POST)
    public ModelAndView PostArticleReadComment(
            @PathVariable(value = "articleIndex", required = true) int articleIndex,
            @SessionAttribute(value = "userEntity", required = false) UserEntity userEntity,
            BbsArticleReadVo bbsArticleReadVo,
            BbsCommentVo bbsCommentVo,
            ModelAndView modelAndView
    ) {
        bbsCommentVo.setUserNickname(userEntity.getNickname());
        bbsArticleReadVo.setIndex(articleIndex);
        bbsArticleReadVo.setResult(null);
        this.bbsService.insertComment(bbsCommentVo);
        this.bbsService.updateViewCount(bbsArticleReadVo.getIndex());
        this.bbsService.readArticle(bbsArticleReadVo);
        modelAndView.addObject("bbsArticleReadVo", bbsArticleReadVo);
        if (bbsCommentVo.getResult() == BbsCommentResult.SUCCESS) {
            modelAndView.setViewName("bbs/read");
        }
        return modelAndView;
    }

    @RequestMapping(value = "deleteComment", method = RequestMethod.GET)
    public ModelAndView getDeleteComment(
            @RequestParam(value = "commentIndex", required = true) int commentIndex,
            ModelAndView modelAndView
    ) {
        BbsCommentEntity bbsCommentEntity = new BbsCommentEntity();
        bbsCommentEntity.setIndex(commentIndex);
        this.bbsService.deleteComment(bbsCommentEntity);
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }

    @RequestMapping(value = "board/search/{boardId}", method = RequestMethod.GET)
    public ModelAndView getBoardSearch(
            @PathVariable(value = "boardId", required = true) String boardId,
            BbsBoardSearchVo bbsBoardSearchVo,
            ModelAndView modelAndView
    ) {
        return this.getBoardSearch(boardId, 1, bbsBoardSearchVo, modelAndView);
    }

    @RequestMapping(value = "board/search/{boardId}/{page}", method = RequestMethod.GET)
    public ModelAndView getBoardSearch(
            @PathVariable(value = "boardId", required = true) String boardId,
            @PathVariable(value = "page", required = true) int page,
            BbsBoardSearchVo bbsBoardSearchVo,
            ModelAndView modelAndView
    ) {
        bbsBoardSearchVo.setId(boardId);
        bbsBoardSearchVo.setRequestPage(page);
        bbsBoardSearchVo.setResult(null);
        this.bbsService.searchBoard(bbsBoardSearchVo);
        modelAndView.addObject("bbsBoardEntities",
                this.bbsService.getBoards());
        modelAndView.addObject("bbsBoardSearchVo", bbsBoardSearchVo);
        modelAndView.setViewName("bbs/search");
        return modelAndView;
    }

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public ModelAndView getDelete(
            @RequestParam(value = "articleIndex", required = true) int articleIndex,
            ModelAndView modelAndView
    ) {
        BbsArticleEntity bbsArticleEntity = new BbsArticleEntity();
        bbsArticleEntity.setIndex(articleIndex);
        this.bbsService.deleteArticle(bbsArticleEntity);
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }



    @RequestMapping(value = "modify", method = RequestMethod.GET)
    public ModelAndView getModify(
            @RequestParam(value = "articleIndex", required = true) int articleIndex,
            ModelAndView modelAndView
    ) {
        BbsWriteModifyVo bbsWriteModifyVo = new BbsWriteModifyVo();
        bbsWriteModifyVo.setIndex(articleIndex);
        this.bbsService.getArticle(bbsWriteModifyVo);
        modelAndView.addObject("bbsBoardEntities",
                this.bbsService.getBoards());
        modelAndView.addObject("bbsWriteModifyVo", bbsWriteModifyVo);
        modelAndView.setViewName("bbs/modify");
        return modelAndView;
    }

    @RequestMapping(value = "modify", method = RequestMethod.POST)
    public ModelAndView postModify(
            BbsArticleEntity bbsArticleEntity,
            @RequestParam(value = "articleIndex", required = true) int articleIndex,
            @SessionAttribute(value = "userEntity", required = false) UserEntity userEntity,
            ModelAndView modelAndView
    ) {
        bbsArticleEntity.setUserNickname(userEntity.getNickname());
        bbsArticleEntity.setIndex(articleIndex);
        this.bbsService.modifyArticle(bbsArticleEntity);
        modelAndView.setViewName("redirect:article/read/" + articleIndex);
        return modelAndView;
    }
}
