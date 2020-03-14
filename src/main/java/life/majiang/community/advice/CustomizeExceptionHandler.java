package life.majiang.community.advice;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import life.majiang.community.exception.CustomizeException;

@ControllerAdvice
public class CustomizeExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	
	ModelAndView handle(Throwable e, Model model) {
		
		
		if (e instanceof CustomizeException) {
			model.addAttribute("message",e.getMessage());
		}
		else {
			model.addAttribute("message","服務器異常");
		}
		
		
		
		return new ModelAndView("error");
	}



}
