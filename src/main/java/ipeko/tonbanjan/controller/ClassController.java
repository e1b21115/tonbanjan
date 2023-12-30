package ipeko.tonbanjan.controller;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ipeko.tonbanjan.model.Answers;
import ipeko.tonbanjan.model.AnswersMapper;
import ipeko.tonbanjan.model.Class;
import ipeko.tonbanjan.model.ClassMapper;
import ipeko.tonbanjan.model.Questions;
import ipeko.tonbanjan.model.QuestionsMapper;
import ipeko.tonbanjan.model.UsersMapper;

@Controller
public class ClassController {
  @Autowired
  UsersMapper uMapper;

  @Autowired
  QuestionsMapper qMapper;

  @Autowired
  AnswersMapper aMapper;

  @Autowired
  ClassMapper cMapper;

  @GetMapping("/class")
  public String showClass(@RequestParam int id, ModelMap model, Principal prin) {
    String loginName = prin.getName();
    if (id != uMapper.selectbyName(loginName)) {
      uMapper.UpdateUser(id, loginName);
    }
    Class Class = cMapper.selectByClassId(id);
    model.addAttribute("Class", Class);

    int roomId = Class.getclassId();

    ArrayList<Questions> questions = qMapper.selectByRoomId(roomId);
    model.addAttribute("questions", questions);

    ArrayList<Answers> answers = new ArrayList<Answers>();
    for (Questions q : questions) {
      ArrayList<Answers> hoge = aMapper.selectByQuestionId(q.getQuestionId());
      for (Answers a : hoge) {
        answers.add(a);
      }
    }
    model.addAttribute("answers", answers);
    model.addAttribute("loginName", loginName);
    return "class.html";
  }

}
