package ipeko.tonbanjan.controller;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ipeko.tonbanjan.model.Class;
import ipeko.tonbanjan.model.Answers;
import ipeko.tonbanjan.model.AnswersMapper;
import ipeko.tonbanjan.model.ClassMapper;
import ipeko.tonbanjan.model.Questions;
import ipeko.tonbanjan.model.QuestionsMapper;

@Controller
public class AddQuestionController {

  @Autowired
  QuestionsMapper qMapper;

  @Autowired
  AnswersMapper aMapper;

  @Autowired
  ClassMapper cMapper;

  @PostMapping("/addQuestion")
  public String addQuestion(@RequestParam int id, @RequestParam String q_content, @RequestParam String a_content1,
      @RequestParam String a_content2,
      @RequestParam String a_content3, @RequestParam String a_content4, ModelMap model,
      Principal prin) {

    Class Class = cMapper.selectByClassId(id);
    model.addAttribute("Class", Class);
    int roomId = Class.getclassId();

    Questions que = new Questions();
    que.setQ_content(q_content);
    que.setRoomId(roomId);

    qMapper.insertQuestions(que);
    int questionId = que.getQuestionId();

    if (!a_content1.equals("")) {
      Answers ans1 = new Answers();
      ans1.setA_content(a_content1);
      ans1.setQuestionId(questionId);
      aMapper.insertAnswers(ans1);
    }
    if (!a_content2.equals("")) {
      Answers ans2 = new Answers();
      ans2.setA_content(a_content2);
      ans2.setQuestionId(questionId);
      aMapper.insertAnswers(ans2);
    }
    if (!a_content3.equals("")) {
      Answers ans3 = new Answers();
      ans3.setA_content(a_content3);
      ans3.setQuestionId(questionId);
      aMapper.insertAnswers(ans3);
    }
    if (!a_content4.equals("")) {
      Answers ans4 = new Answers();
      ans4.setA_content(a_content4);
      ans4.setQuestionId(questionId);
      aMapper.insertAnswers(ans4);
    }

    ArrayList<Questions> questions = qMapper.selectByRoomId(roomId);
    model.addAttribute("questions", questions);

    return "class.html";
  }
}
