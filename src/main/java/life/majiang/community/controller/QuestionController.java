package life.majiang.community.controller;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLNonTransientConnectionException;

import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import life.majiang.community.dto.QuestionDTO;
import life.majiang.community.exception.CustomizeException;
import life.majiang.community.mapper.QuesstionMapper;
import life.majiang.community.service.QuestionService;

@Controller
public class QuestionController {
	
	@Autowired
	private QuestionService questionService;
	
	@GetMapping("/question/{id}")
	public String question(@PathVariable(name = "id") Integer id,
							Model model) {
		
		
			QuestionDTO questionDTO = questionService.getById(id);
			model.addAttribute("question",questionDTO);

		
		
		
		return "question";
	}

}
