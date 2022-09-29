package com.fastcampus.ch4.views;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.servlet.view.AbstractView;

import com.fastcampus.ch4.domain.new_Info;

public class New_InfoSelectJson extends AbstractView {
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<new_Info> list = (List<new_Info>) model.get("list");
		
		JSONArray jsonArray = new JSONArray();
		
		for(new_Info info : list) {
			JSONObject jsonObject = new JSONObject(info);
			
			String old = Integer.toString(info.getOld());
			jsonObject.put("old", old);
			String name = info.getName();
			jsonObject.put("name", name);
			String gender = info.getGender();
			jsonObject.put("gender", gender);
			jsonArray.put(jsonObject);
		}
		
		response.setContentType("application/json; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		
		response.getWriter().println(jsonArray.toString());
	}
}
