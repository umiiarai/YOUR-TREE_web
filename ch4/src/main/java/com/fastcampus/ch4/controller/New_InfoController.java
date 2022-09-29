package com.fastcampus.ch4.controller;

import com.fastcampus.ch4.domain.*;
import com.fastcampus.ch4.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.*;

import javax.servlet.http.*;
import java.time.*;
import java.util.*;

@Controller
@RequestMapping("/new_Info")
public class New_InfoController {
    @Autowired
    new_InfoService new_infoservice;

    // ajax 이용하기
    @GetMapping("/new")
    public String newpage(Model m, SearchCondition sc, HttpServletRequest request) {
        try {
        	List<new_Info> list = new_infoservice.getList();
            m.addAttribute("infolist", list);
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute("msg", "LIST_ERR");
            m.addAttribute("totalCnt", 0);
        }

        return "new"; // new.jsp로 이동
    }
	
    @PostMapping("/new")
	public String registerAllSelectSave(HttpServletRequest request, HttpServletResponse respone, Model m, HttpSession session) throws Exception {
		
		List<new_Info> list = new_infoservice.getList();
		if(list != null && list.size() > 0) {
			m.addAttribute("list", list);
			return "new_infoselectJson";
		}
        return "new_infoselectJson";
	}
    
    
    @GetMapping("/search")
	public String new_InfoSelect() {
		return "new";
	}
	
	@PostMapping("/search")
	public String new_InfoSelectSave(HttpServletRequest request, HttpServletResponse respone, Model m, HttpSession session) throws Exception {
		String option = request.getParameter("option");
		String keyword = request.getParameter("name");
		if(keyword != null && option != null) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("option", option);
			map.put("keyword", keyword);
			List<new_Info> list = new_infoservice.getsearchList(map);
			if(list != null && list.size() > 0) {
				m.addAttribute("list", list);
				return "new_infoselectJson";
			}
		}
		
        return "new_infoselectJson";
	}
	
	@GetMapping("/form")
	public String formSelect() {
		return "new";
	}
	
	@PostMapping("/form")
	public String formSelectSave(HttpServletRequest request, HttpServletResponse respone, Model m, HttpSession session) throws Exception {
		String option2 = request.getParameter("option2");
		String keyword2 = request.getParameter("keyword2");
		if(keyword2 != null && option2 != null) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("option", option2);
			map.put("keyword", keyword2);
			List<new_Info> list = new_infoservice.getsearchList(map);
			if(list != null && list.size() > 0) {
				m.addAttribute("list", list);
				return "new_infoselectJson";
			}
		}
		
        return "new_infoselectJson";
	}
}