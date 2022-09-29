package com.fastcampus.ch4.dao;

import com.fastcampus.ch4.domain.BoardDto;
import com.fastcampus.ch4.domain.SearchCondition;
import com.fastcampus.ch4.domain.User;
import com.fastcampus.ch4.domain.new_Info;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface new_InfoDao {

    List<new_Info> selectAll_new() throws Exception;
    
    List<new_Info> selectsearchAll_new(HashMap<String, String> map) throws Exception;
    
//    List<new_Info> searchSelectPage_new(SearchCondition sc) throws Exception;
}
