package com.quiz.quizapp.Service;

import com.quiz.quizapp.Repo.Repo;
import com.quiz.quizapp.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class Services {
    @Autowired
    private Repo repo;

    public ResponseEntity<List<Question>> getAllQuestion(){
        try {
            return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(repo.findAll(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
        try {
            return new ResponseEntity<>(repo.findByCategory(category),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(repo.findByCategory(category),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> addQuestion(List<Question> question) {
        try {
            return new ResponseEntity<>(repo.saveAll(question),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(repo.saveAll(question),HttpStatus.BAD_REQUEST);

    }
    public ResponseEntity<String> Update(Question question) {
        repo.save(question);
        return new ResponseEntity<>("SUCCESS",HttpStatus.CREATED);
    }
    public void Delete(Integer parseLong) {
        repo.deleteById(parseLong);
    }
}