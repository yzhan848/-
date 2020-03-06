package life.majiang.community.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import life.majiang.community.mapper.QuesstionMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.Quesstion;
import life.majiang.community.model.User;

@Controller
public class PublishController {
	
	@Autowired
	private QuesstionMapper quesstionMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	
	@GetMapping("/publish")
	public String publish() {
		return "publish";
	}
	
	@PostMapping("/publish")
	public String doPublish(
	@RequestParam("title") String title,
	@RequestParam("description") String description,
	@RequestParam("tag") String tag,
	HttpServletRequest request,
	Model model) {
		
		User user = null;
		Cookie[] cookies = request.getCookies();
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
		
		if (user == null) {
			model.addAttribute("error","未登錄");
			return "publish";
		}
		
		
		Quesstion quesstion = new Quesstion();
		quesstion.setTitle(title);
		quesstion.setDescription(description);
		quesstion.setTag(tag);
		quesstion.setCreator(user.getId());
		quesstion.setGmtCreate(System.currentTimeMillis());
		quesstion.setGmtModified(quesstion.getGmtCreate());		
		quesstionMapper.create(quesstion);
		return "redirect:/";
		
	}

}
