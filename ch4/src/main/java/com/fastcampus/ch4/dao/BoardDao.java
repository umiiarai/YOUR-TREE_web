package com.fastcampus.ch4.dao;

import com.fastcampus.ch4.domain.BoardDto;
import com.fastcampus.ch4.domain.SearchCondition;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface BoardDao {
    int count() throws Exception;

    int deleteAll() throws Exception;

    int delete(String bno, String writer) throws Exception;

    int insert(BoardDto boardDto) throws Exception;

    List<BoardDto> selectAll() throws Exception;

    BoardDto select(Integer bno) throws Exception;

    List<BoardDto> selectPage(Map map) throws Exception;

    int update(BoardDto boardDto) throws Exception;

    int increaseViewCnt(Integer bno) throws Exception;

    List<BoardDto> searchSelectPage(SearchCondition sc) throws Exception;

    int searchResultCnt(SearchCondition sc) throws Exception;
}
