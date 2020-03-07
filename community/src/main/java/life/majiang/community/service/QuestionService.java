package life.majiang.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import life.majiang.community.dto.QuestionDTO;
import life.majiang.community.mapper.QuesstionMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.Quesstion;
import life.majiang.community.model.User;

@Service
public class QuestionService {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private QuesstionMapper quesstionMapper;
	
	public List<QuestionDTO> list() {
		
		List <Quesstion> quesstions = quesstionMapper.list();
		List <QuestionDTO> questionDTOList = new ArrayList<>();
		for (Quesstion quesstion : quesstions) {
			User user = userMapper.findById(quesstion.getCreator());
			QuestionDTO questionDTO = new QuestionDTO();
			BeanUtils.copyProperties(quesstion, questionDTO);
			questionDTO.setUser(user);
			questionDTOList.add(questionDTO);
			
		}
		
		return questionDTOList;
	}

}
