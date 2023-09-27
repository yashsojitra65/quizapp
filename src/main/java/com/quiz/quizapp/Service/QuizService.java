package com.quiz.quizapp.Service;

import com.quiz.quizapp.Repo.QuizRepo;
import com.quiz.quizapp.Repo.Repo;
import com.quiz.quizapp.entity.Question;
import com.quiz.quizapp.entity.QuestionWrapper;
import com.quiz.quizapp.entity.Quiz;
import com.quiz.quizapp.entity.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuizRepo quizRepo;
    @Autowired
    Repo repo;
    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Question> questionList = repo.findRandomQuestionByCategory(category,numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionList(questionList);
        quizRepo.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(Integer id) {
        Optional<Quiz> quiz = quizRepo.findById(id);
        List<Question> questionListFormDB = quiz.get().getQuestionList();
        List<QuestionWrapper> questionForUser = new ArrayList<>();

        for(Question q : questionListFormDB){
            QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(), q.getOption3(), q.getOption4());
            questionForUser.add(qw);
        }

        return new ResponseEntity<>(questionForUser,HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz = quizRepo.findById(id).get();
        List<Question> questionList = quiz.getQuestionList();
        int right = 0;
        int i = 0;
        for(Response response : responses){
            if(response.getResponse().equals(questionList.get(i).getRightAnswer())){
                right++;
            }
            i++;
        }
        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
