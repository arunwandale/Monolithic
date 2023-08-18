package com.ArunS.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ArunS.Model.Question;
import com.ArunS.Model.Quiz;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer>{

	List<Question> findByCategory(String category);
	
	@Query(value="SELECT * FROM question q where q.category=:category ORDER BY RAND() LIMIT :numQ", nativeQuery=true)
	List<Question> findRandomQuestionByCategory(String category, int numQ);


	@Query(value="SELECT right_answer FROM Question q where q.id =:id" , nativeQuery=true)
	Optional<Quiz> findResponseById(int id);


	
}
