package com.ArunS.Service;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ArunS.Controller.QuestionController;
import com.ArunS.Model.Question;
import com.ArunS.Repository.QuestionRepository;

@Service
public class QuestionService {

	
	@Autowired
	QuestionRepository Qrepo; 
	
	public ResponseEntity<List<Question>> getAllQuestion() {	
		
	try{	
	 List<Question> questionList=  Qrepo.findAll();
		 return new ResponseEntity<>( questionList,HttpStatus.OK);
	}catch(Exception e) {
		e.printStackTrace();
	}
	return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST); 
	}
	

	public ResponseEntity<String> addQuestion(Question questionList) {
		try {
			Qrepo.save(questionList);
			return new ResponseEntity<>("The Question is saved",HttpStatus.CREATED);
		}catch(Exception E){
			E.printStackTrace();
		}
		return new ResponseEntity<>("The Question is saved",HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
		try {
			return new ResponseEntity<>(Qrepo.findByCategory(category),HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
			return new ResponseEntity<>(Qrepo.findByCategory(category),HttpStatus.BAD_REQUEST);
	}
}
