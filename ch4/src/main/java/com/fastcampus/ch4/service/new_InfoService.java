package com.fastcampus.ch4.service;

import com.fastcampus.ch4.domain.new_Info;
import com.fastcampus.ch4.domain.BoardDto;
import com.fastcampus.ch4.domain.SearchCondition;
import com.fastcampus.ch4.domain.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface new_InfoService {

    List<new_Info> getList() throws Exception;
    List<new_Info> getsearchList(HashMap<String, String> map) throws Exception;
//    List<new_Info> getSearchResultPage(SearchCondition sc) throws Exception;
}
