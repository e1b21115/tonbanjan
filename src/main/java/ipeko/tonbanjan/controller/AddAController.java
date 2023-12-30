package ipeko.tonbanjan.controller;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ipeko.tonbanjan.model.Class;
import ipeko.tonbanjan.model.ClassMapper;
import ipeko.tonbanjan.model.Answers;
import ipeko.tonbanjan.model.AnswersMapper;
import ipeko.tonbanjan.model.Questions;
import ipeko.tonbanjan.model.QuestionsMapper;
import ipeko.tonbanjan.model.Send;
import ipeko.tonbanjan.model.SendMapper;
import ipeko.tonbanjan.model.UsersMapper;

@Controller
public class AddAController {

  @Autowired
  QuestionsMapper qMapper;

  @Autowired
  ClassMapper cMapper;

  @Autowired
  AnswersMapper aMapper;

  @Autowired
  UsersMapper uMapper;

  @Autowired
  SendMapper sMapper;

  @PostMapping("/addAnswer")
  public String addAnswer(@RequestParam int id, @RequestParam int q_id, @RequestParam int send_answer, ModelMap model,
      Principal prin) {
    Class Class = cMapper.selectByClassId(id);
    model.addAttribute("Class", Class);
    int roomId = Class.getclassId();
    Send send = new Send();
    send.setQuestionId(q_id);
    send.setsendId(send_answer);
    sMapper.insertQuestions(send);

    ArrayList<Class> classlist = cMapper.selectAllclass();
    model.addAttribute("classlist", classlist);

    ArrayList<Questions> questions = qMapper.selectByRoomId(roomId);
    model.addAttribute("questions", questions);

    uMapper.UpdateUser(0, prin.getName());

    String loginName = prin.getName();
    if (id != uMapper.selectbyName(loginName)) {
      uMapper.UpdateUser(id, loginName);
    }

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
