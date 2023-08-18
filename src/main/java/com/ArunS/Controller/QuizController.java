 package com.ArunS.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ArunS.Model.Question;
import com.ArunS.Model.QuestionWrapper;
import com.ArunS.Model.Response;
import com.ArunS.Service.QuizService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/quiz")
public class QuizController {

	@Autowired
	QuizService quizservice;
	
	@PostMapping("/create")
	public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam String title, @RequestParam int numQ) {
		
		return quizservice.createQuiz(category,title,numQ);
	}
	
	@GetMapping("/getQuizQuestion/{id}")
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(@PathVariable Integer id){
		
		return quizservice.getQuizQuestion(id);
	}
	
	@GetMapping("/result/{id}")
	public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> response){
		
		return quizservice.calcuateResult(id,response);
		
	}
}
