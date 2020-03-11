package life.majiang.community.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import life.majiang.community.dto.PaginationDTO;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import life.majiang.community.service.QuestionService;

@Controller
public class ProfileController {
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private QuestionService questionService;

	@GetMapping("/profile/{action}")
	public String profile(HttpServletRequest request,
						  @PathVariable(name = "action") String action,
						  Model model,
						  @RequestParam(name = "page",defaultValue = "1") Integer page,
						  @RequestParam(name = "size",defaultValue = "5") Integer size) {
		User user = null;
		Cookie[] cookies = request.getCookies();//該方法返回客戶端的所有cookie對象，結果是一個cookie數組。
		
		if (cookies !=null && cookies.length !=0) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("token")) {
					String token = cookie.getValue();
					user = userMapper.findByToken(token);
					if (user != null) {
						request.getSession().setAttribute("user",user);
					}
					break;
				}
			}
		}
		
		if (user == null) {
			return "redirect:/";
		}
		
		if ("questions".equals(action)) {
			model.addAttribute("section","questions");
			model.addAttribute("sessionName","我的留言");
		}
		else if ("replies".equals(action)) {
			model.addAttribute("section","replies");
			model.addAttribute("sessionName","最新回復");
		}
		
		PaginationDTO paginationDTO = questionService.list(user.getId(),page,size);
		model.addAttribute("pagination", paginationDTO);
		
		return "profile";
	}
}
