package ipeko.tonbanjan.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ipeko.tonbanjan.model.Questions;
import ipeko.tonbanjan.model.QuestionsMapper;

@Controller
public class TonbanjanController {

  @Autowired
  QuestionsMapper qMapper;

  /**
   * sample21というGETリクエストがあったら sample21()を呼び出し，sample21.htmlを返す
   */
  @GetMapping("/waitingRoom")
  public String sample21() {
    return "waitroom.html";
  }

  @GetMapping("/class")
  public String showClass(ModelMap model) {
    ArrayList<Questions> questions = qMapper.selectAllQuestions();
    model.addAttribute("questions", questions);
    return "class.html";
  }

}
