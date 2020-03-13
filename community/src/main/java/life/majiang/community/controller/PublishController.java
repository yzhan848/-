package life.majiang.community.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import life.majiang.community.dto.QuestionDTO;
import life.majiang.community.mapper.QuesstionMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.Quesstion;
import life.majiang.community.model.User;
import life.majiang.community.service.QuestionService;

@Controller
public class PublishController {
	
	@Autowired
	private QuesstionMapper quesstionMapper;
	
	@Autowired
	private QuestionService questionService;
	
	
	@GetMapping("/publish/{id}")
	public String edit(@PathVariable(name = "id") Integer id,
						Model model) {
		QuestionDTO quesstio = questionService.getById(id);
		
		model.addAttribute("title",quesstio.getTitle());
		model.addAttribute("description",quesstio.getDescription());
		model.addAttribute("tag",quesstio.getTag());
		model.addAttribute("id",quesstio.getId());
		return "publish";
	}
	
	
	
	@GetMapping("/publish")
	public String publish() {
		return "publish";
	}
	
	@PostMapping("/publish")
	public String doPublish(
	@RequestParam(value = "title", required = false) String title,
	@RequestParam(value = "description", required = false) String description,
	@RequestParam(value = "tag", required = false) String tag,
	@RequestParam(value = "id", required = false) Integer id,
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
				
		
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			model.addAttribute("error","未登錄");
			return "publish";
		}
		
		
		Quesstion quesstion = new Quesstion();
		quesstion.setTitle(title);
		quesstion.setDescription(description);
		quesstion.setTag(tag);
		quesstion.setCreator(user.getId());

		quesstion.setId(id);
		
		questionService.createOrUpdate(quesstion);
		
		return "redirect:/";
		
	}

}
