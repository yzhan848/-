package life.majiang.community.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import life.majiang.community.dto.PaginationDTO;
import life.majiang.community.dto.QuestionDTO;
import life.majiang.community.mapper.QuesstionMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.Quesstion;
import life.majiang.community.model.User;
import life.majiang.community.service.QuestionService;

@Controller
public class IndexController {


	
	@Autowired
	private QuestionService questionService;
	
	//@RequestMapping(method = RequestMethod.GET)的縮寫
	@GetMapping("/")
	public String index(Model model,
						@RequestParam(name = "page",defaultValue = "1") Integer page,
						@RequestParam(name = "size",defaultValue = "5") Integer size) {
		
		
		PaginationDTO pagination = questionService.list(page,size);
		model.addAttribute("pagination", pagination);
		
		return "index";
	}

}
