package com.quiz.quizapp.Repo;

import com.quiz.quizapp.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface Repo extends JpaRepository<Question,Integer> {
    List<Question> findByCategory(String category);

    @Query(value = "SELECT * FROM question q Where q.category=:category ORDER BY RAND() LIMIT :numQ",nativeQuery = true)
    List<Question> findRandomQuestionByCategory(@Param("category") String category,@Param("numQ") int numQ);
}
