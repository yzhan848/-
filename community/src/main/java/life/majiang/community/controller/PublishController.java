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
	@RequestParam(value = "title", required = false) String title,
	@RequestParam(value = "description", required = false) String description,
	@RequestParam(value = "tag", required = false) String tag,
	HttpServletRequest request,
	Model model) {
		
		model.addAttribute("title",title);
		model.addAttribute("description",description);
		model.addAttribute("tag",tag);
		
		if ("".equals(title)) {
			model.addAttribute("error","標題要填寫");
			return "publish";
		}
		
		if ("".equals(description)) {
			model.addAttribute("error","內容要填寫");
			return "publish";
		}
		
		if ("".equals(tag)) {
			model.addAttribute("error","標籤要填寫");
			return "publish";
		}
		
		

		
		User user = null;
		Cookie[] cookies = request.getCookies();
		
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
