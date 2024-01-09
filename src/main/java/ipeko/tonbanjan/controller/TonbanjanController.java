package ipeko.tonbanjan.controller;

import java.security.Principal;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ipeko.tonbanjan.model.ClassMapper;
import ipeko.tonbanjan.model.Class;
import ipeko.tonbanjan.model.Answers;
import ipeko.tonbanjan.model.AnswersMapper;
import ipeko.tonbanjan.model.Questions;
import ipeko.tonbanjan.model.QuestionsMapper;
import ipeko.tonbanjan.model.UsersMapper;
import ipeko.tonbanjan.service.AsyncSumSAnswers;
import ipeko.tonbanjan.model.SendMapper;
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
  UsersMapper uMapper;

  @Autowired
  SendMapper sMapper;

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

  @GetMapping("/delete")
  public String delete(@RequestParam int id, ModelMap model, Principal prin) {

    String loginName = prin.getName();
    int roomId = qMapper.selectRoomIdByQuestionId(id);
    Class Class = cMapper.selectByClassId(roomId);
    model.addAttribute("Class", Class);

    qMapper.deleteByQuestionId(id);
    aMapper.deleteByQuestinoId(id);
    sMapper.deleteByQuestionId(id);

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

  @GetMapping("SumSAns")
  public SseEmitter pushConut(Principal prin) {
    String loginUser = prin.getName();
    logger.info("SumSAn");
    final SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);

    this.ssa.count(emitter, loginUser);
    return emitter;
  }
}
