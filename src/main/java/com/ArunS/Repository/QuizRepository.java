package com.ArunS.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ArunS.Model.Question;
import com.ArunS.Model.QuestionWrapper;
import com.ArunS.Model.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Integer> {

	Optional<Quiz> findById(int quizId);
	
	
}
