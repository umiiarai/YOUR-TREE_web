package com.fastcampus.ch4.controller;

import com.fastcampus.ch4.domain.*;
import com.fastcampus.ch4.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.*;

import javax.servlet.http.*;
import java.time.*;
import java.util.*;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    BoardService boardService;

    @PostMapping("/modify")
    public String modify(BoardDto boardDto, SearchCondition sc, RedirectAttributes rattr, Model m, HttpSession session) {
        String writer = (String)session.getAttribute("id");
        boardDto.setWriter(writer);

        try {
            if (boardService.modify(boardDto)!= 1)
                throw new Exception("Modify failed.");

            rattr.addFlashAttribute("msg", "MOD_OK");
            return "redirect:/board/list"+sc.getQueryString();
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute(boardDto);
            m.addAttribute("msg", "MOD_ERR");
            return "board";
        }
    }

    @GetMapping("/write")
    public String write(Model m) {
        m.addAttribute("mode", "new");

        return "board";
    }

    @PostMapping("/write")
    public String write(BoardDto boardDto, RedirectAttributes rattr, Model m, HttpSession session) {
        String writer = (String)session.getAttribute("id");
        boardDto.setWriter(writer);

        try {
            if (boardService.write(boardDto) != 1)
                throw new Exception("Write failed.");

            rattr.addFlashAttribute("msg", "WRT_OK");
            return "redirect:/board/list";
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute(boardDto);
            m.addAttribute("mode", "new");
            m.addAttribute("msg", "WRT_ERR");
            return "board";
        }
    }

    @GetMapping("/read")
    public String read(Integer bno, SearchCondition sc, RedirectAttributes rattr, Model m) {
        try {
            BoardDto boardDto = boardService.read(bno);
            m.addAttribute(boardDto);
        } catch (Exception e) {
            e.printStackTrace();
            rattr.addFlashAttribute("msg", "READ_ERR");
            return "redirect:/board/list"+sc.getQueryString();
        }

        return "board";
    }

    @PostMapping("/remove")
    public String remove(String bno, SearchCondition sc, RedirectAttributes rattr, HttpSession session) {
        String writer = (String)session.getAttribute("id");
        String msg = "DEL_OK";

        try {
            if(boardService.remove(bno, writer)!=1)
                throw new Exception("Delete failed.");
        } catch (Exception e) {
            e.printStackTrace();
            msg = "DEL_ERR";
        }

        rattr.addFlashAttribute("msg", msg);
        return "redirect:/board/list"+sc.getQueryString();
    }
    
  //게시물 선택삭제
    @RequestMapping("/selectremove")
    public String selectremove(String bno, SearchCondition sc, RedirectAttributes rattr, HttpSession session, HttpServletRequest request) {
    	String writer = (String)session.getAttribute("id");
        String msg = "DEL_OK";
        
        String[] ajaxMsg = request.getParameterValues("valueArr");
        int size = ajaxMsg.length;
        for(int i=0; i<size; i++) {
        	try {
                if(boardService.remove(ajaxMsg[i], writer)!=1)
                    throw new Exception("Delete failed.");
            } catch (Exception e) {
                e.printStackTrace();
                msg = "DEL_ERR";
            }
        }
        rattr.addFlashAttribute("msg", msg);
        System.out.println("sc:dicc::"+sc.getQueryString());
        return "redirect:/board/list"+sc.getQueryString();
    }

    @GetMapping("/list")
    public String list(Model m, SearchCondition sc, HttpServletRequest request) {
    	if(!loginCheck(request))
            return "redirect:/login/login?toURL="+request.getRequestURL();  // 로그인을 안했으면 로그인 화면으로 이동

        try {
            int totalCnt = boardService.getSearchResultCnt(sc);
            m.addAttribute("totalCnt", totalCnt);
            System.out.println("totalCnt : "+totalCnt);

            PageHandler pageHandler = new PageHandler(totalCnt, sc);

            List<BoardDto> list = boardService.getSearchResultPage(sc);
            m.addAttribute("list", list);
            m.addAttribute("ph", pageHandler);

            Instant startOfToday = LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant();
            m.addAttribute("startOfToday", startOfToday.toEpochMilli());
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute("msg", "LIST_ERR");
            m.addAttribute("totalCnt", 0);
        }
    	return "boardList";
    }

    private boolean loginCheck(HttpServletRequest request) {
        // 1. 세션을 얻어서(false는 session이 없어도 새로 생성하지 않는다. 반환값 null)
        HttpSession session = request.getSession(false);
        // 2. 세션에 id가 있는지 확인, 있으면 true를 반환
        return session!=null && session.getAttribute("id")!=null;
    }
    
    // 즐겨찾기 게시판
    @GetMapping("/lovelist")
    public String lovelist(Model m, SearchCondition sc, HttpServletRequest request) {
    	if(!loginCheck(request))
            return "redirect:/login/login?toURL="+request.getRequestURL();  // 로그인을 안했으면 로그인 화면으로 이동

        try {
            int totalCnt = boardService.getSearchResultCnt(sc);
            m.addAttribute("totalCnt", totalCnt);
            System.out.println("totalCnt : "+totalCnt);

            PageHandler pageHandler = new PageHandler(totalCnt, sc);

            List<BoardDto> list = boardService.getSearchResultPage_love(sc);
            m.addAttribute("list", list);
            m.addAttribute("ph", pageHandler);

            Instant startOfToday = LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant();
            m.addAttribute("startOfToday", startOfToday.toEpochMilli());
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute("msg", "LIST_ERR");
            m.addAttribute("totalCnt", 0);
        }
    	return "lovelist";
    }

    
 /*   
 // 빈하트 클릭시 하트 저장
    @ResponseBody
    @RequestMapping(value = "/saveHeart.do")
    public BoardDto save_heart(@RequestParam int no, HttpSession session) {

    	BoardDto to = new BoardDto();

        // 게시물 번호 세팅
        to.setBno(no);

        // 좋아요 누른 사람 nick을 userid로 세팅
        to.setUserid((String) session.getAttribute("nick"));

        // +1된 하트 갯수를 담아오기위함
        BoardDto pto = heartDao.pictureSaveHeart(to);

        return pto;
    }

    // 꽉찬하트 클릭시 하트 해제
    @ResponseBody
    @RequestMapping(value = "/removeHeart.do")
    public BoardDto remove_heart(@RequestParam int no, HttpSession session) {
    	BoardDto to = new BoardDto();

        // 게시물 번호 세팅
        to.setBno(no);

        // 좋아요 누른 사람 nick을 userid로 세팅
        to.setUserid((String) session.getAttribute("nick"));

        // -1된 하트 갯수를 담아오기위함
        BoardDto pto = BoardDto.pictureRemoveHeart(to);

        return pto;
    }
    */
}