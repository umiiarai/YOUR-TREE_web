package com.fastcampus.ch4.views;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.web.servlet.view.AbstractView;

public class UserUpdateJson extends AbstractView {

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("id", (String)model.get("id"));
		jsonObject.put("pwd", (String)model.get("pwd"));
		jsonObject.put("name", (String)model.get("name"));
		jsonObject.put("bitrh", (String)model.get("birth"));
		jsonObject.put("sns", (String)model.get("sns"));
		
		// String birth = DATE_TIME_FORMAT.format(user.getBirth());
		
		response.setContentType("application/json; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		
		response.getWriter().println(jsonObject.toString());
	}
}
