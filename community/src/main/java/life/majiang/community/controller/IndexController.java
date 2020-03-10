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

	//注入
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private QuestionService questionService;
	
	//@RequestMapping(method = RequestMethod.GET)的縮寫
	@GetMapping("/")
	public String index(HttpServletRequest request,
						Model model,
						@RequestParam(name = "page",defaultValue = "1") Integer page,
						@RequestParam(name = "size",defaultValue = "5") Integer size) {
		
		//HttpServletRequest對象代表客戶端的請求
		
		
		Cookie[] cookies = request.getCookies();//該方法返回客戶端的所有cookie對象，結果是一個cookie數組。
		
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
		

		PaginationDTO pagination = questionService.list(page,size);
		model.addAttribute("pagination", pagination);
		
		return "index";
	}

}
