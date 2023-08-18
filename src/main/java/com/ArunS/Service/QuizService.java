package com.ArunS.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ArunS.Model.Question;
import com.ArunS.Model.QuestionWrapper;
import com.ArunS.Model.Quiz;
import com.ArunS.Model.Response;
import com.ArunS.Repository.QuestionRepository;
import com.ArunS.Repository.QuizRepository;

@Service
public class QuizService {

	@Autowired
	QuizRepository quizRepository;
	

	
	@Autowired
	QuestionRepository qrepo;
	
	public ResponseEntity<String> createQuiz(String category,String title,int  numQ){
		
		List<Question> questionList = qrepo.findRandomQuestionByCategory(category,numQ);
		
		Quiz quiz = new Quiz();
		quiz.setTitle(title);
	    quiz.setQuestions(questionList);
	    quizRepository.save(quiz);
	    
		return new ResponseEntity<>("created", HttpStatus.OK);
		
		 
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(int quizId) {
		
		            Optional<Quiz> quizData = quizRepository.findById(quizId);
		            System.out.println(quizData);
		            List <Question> QuizQuestionFrmDB =  quizData.get().getQuestions();
		            System.out.println(QuizQuestionFrmDB);
		            List<QuestionWrapper> questionForUser = new ArrayList<>();
		            
		            for(Question q : QuizQuestionFrmDB  ) {
		            	
		            	QuestionWrapper qr = new QuestionWrapper(q.getId(),q.getQuestiontitle(), q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
		            	
		            	questionForUser.add( qr);
		          
		            }
				return new ResponseEntity<>(questionForUser ,HttpStatus.OK);
	}

	public ResponseEntity<Integer> calcuateResult(Integer id, List<Response> response) {

		
		Quiz quiz = quizRepository.findById(id).get();
		List<Question> ForResp = quiz.getQuestions();
		
	
		
		int i=0;
		int right = 0;
		int wrong = 0;
		for(Response resp: response ) {
			
		if(resp.getResponse().equals(ForResp.get(i).getRightAnswer())) {
				
				right++; 
				System.out.println("Right: "+right);
			}
			wrong++;
			i++;
			System.out.println("Result wrong : "+wrong);
		}
		
		return new ResponseEntity<>(right, HttpStatus.OK);
	}
}
