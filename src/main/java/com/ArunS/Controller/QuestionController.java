package com.ArunS.Controller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ArunS.Model.Question;
import com.ArunS.Repository.QuestionRepository;
import com.ArunS.Service.QuestionService;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("Question")
public class QuestionController {

	@Autowired
	QuestionService questionService;
	
	@GetMapping("/AllQuestion")
	public ResponseEntity<List<Question>> getAllQuestion() throws JsonProcessingException{
	System.out.print("arun");
	return questionService.getAllQuestion() ;
		
	}
	
	@GetMapping("/BytCategory/{category}")
	public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category){
		System.out.println("Controller: "+questionService.getQuestionByCategory(category));
				 
		return questionService.getQuestionByCategory(category) ;
		
	}
		
	@PostMapping("/add")
	public ResponseEntity<String> addQuestion(@RequestBody Question questionList){
		
		return questionService.addQuestion(questionList);
		
	}
}
