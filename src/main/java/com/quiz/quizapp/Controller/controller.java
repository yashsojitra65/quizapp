package com.quiz.quizapp.Controller;

import com.quiz.quizapp.Service.Services;
import com.quiz.quizapp.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@CrossOrigin("*")
@RequestMapping("question")
public class controller {
    @Autowired
    private Services services;
    @GetMapping("home")
    public String home(){
        return "this is my api";
    }
    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestion(){
        return services.getAllQuestion();
    }
    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category){
        return services.getQuestionByCategory(category);
    }
    @PostMapping("add")
    public ResponseEntity<List<Question>> addQuestion(@RequestBody List<Question> question){
        return services.addQuestion(question);
    }
    @PutMapping("Update")
    public String Update(@RequestBody Question question){
        services.Update(question);
        return "SUCCESS";
    }
    @DeleteMapping("Delete/{id}")
    public String Delete(@PathVariable Integer id){
        services.Delete(id);
        return "SUCCESS";
    }
}