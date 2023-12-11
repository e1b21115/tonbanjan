package ipeko.tonbanjan.controller;

import java.security.Principal;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ipeko.tonbanjan.model.ClassMapper;
import ipeko.tonbanjan.model.Class;
import ipeko.tonbanjan.model.Answers;
import ipeko.tonbanjan.model.AnswersMapper;
import ipeko.tonbanjan.model.Questions;
import ipeko.tonbanjan.model.QuestionsMapper;
import ipeko.tonbanjan.model.Send;
import ipeko.tonbanjan.model.SendMapper;
import ipeko.tonbanjan.model.UsersMapper;
import ipeko.tonbanjan.service.AsyncSumSAnswers;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Controller
public class TonbanjanController {

  @Autowired
  QuestionsMapper qMapper;

  @Autowired
  AnswersMapper aMapper;

  @Autowired
  ClassMapper cMapper;

  @Autowired
  SendMapper sMapper;

  @Autowired
  UsersMapper uMapper;

  @Autowired
  AsyncSumSAnswers ssa;

  private final Logger logger = LoggerFactory.getLogger(TonbanjanController.class);

  @GetMapping("/waitingRoom")
  public String go_waitroom(ModelMap model, Principal prin) {
    String loginName = prin.getName();
    if (uMapper.selectcountbyName(loginName) > 0) {
      uMapper.UpdateUser(0, loginName);
    } else {
      uMapper.InsertUser(0, loginName);
    }
    ArrayList<Class> classlist = cMapper.selectAllclass();
    model.addAttribute("classlist", classlist);
    model.addAttribute("loginName", loginName);

    return "waitroom.html";
  }

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

  @PostMapping("/addAnswer")
  public String addAnswer(@RequestParam int id, @RequestParam int q_id, @RequestParam int send_answer, ModelMap model,
      Principal prin) {
    Class Class = cMapper.selectByClassId(id);
    model.addAttribute("Class", Class);
    Send send = new Send();
    send.setQuestionId(q_id);
    send.setsendId(send_answer);
    sMapper.insertQuestions(send);

    ArrayList<Class> classlist = cMapper.selectAllclass();
    model.addAttribute("classlist", classlist);

    uMapper.UpdateUser(0, prin.getName());
    return "waitroom.html";
  }

  @PostMapping("/addClass")
  public String addClass(@RequestParam String className, ModelMap model, Principal prin) {
    String loginName = prin.getName();
    Class addClass = new Class();
    addClass.setclassName(className);
    cMapper.InsertClass(addClass);

    ArrayList<Class> classlist = cMapper.selectAllclass();
    model.addAttribute("classlist", classlist);
     model.addAttribute("loginName", loginName);
    return "waitroom.html";
  }

  @GetMapping("SumSAns")
  public SseEmitter pushConut(Principal prin) {
    String loginUser = prin.getName();
    logger.info("SumSAn");
    final SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);

    this.ssa.count(emitter, loginUser);
    return emitter;
  }
}
