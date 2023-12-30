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

import ipeko.tonbanjan.model.Class;
import ipeko.tonbanjan.model.ClassMapper;
import ipeko.tonbanjan.model.UsersMapper;
import ipeko.tonbanjan.service.AsyncSumSAnswers;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Controller
public class TonbanjanController {

  @Autowired
  ClassMapper cMapper;

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
