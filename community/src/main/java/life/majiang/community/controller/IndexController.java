package life.majiang.community.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import life.majiang.community.dto.QuestionDTO;
import life.majiang.community.mapper.QuesstionMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.Quesstion;
import life.majiang.community.model.User;
import life.majiang.community.service.QuestionService;

@Controller
public class IndexController {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private QuestionService questionService;
	
	@GetMapping("/")
	public String index(HttpServletRequest request,
						Model model) {
		
		Cookie[] cookies = request.getCookies();
		
		if (cookies !=null && cookies.length !=0) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("token")) {
					String token = cookie.getValue();
					User user = userMapper.findByToken(token);
					if (user != null) {
						request.getSession().setAttribute("user",user);
					}
					break;
				}
			}
		}
		

		List<QuestionDTO> quesstionList = questionService.list();
		model.addAttribute("questions", quesstionList);
		
		return "index";
	}

}
