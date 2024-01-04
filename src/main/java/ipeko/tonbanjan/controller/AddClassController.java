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

@Controller
public class AddClassController {

  @Autowired
  ClassMapper cMapper;
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
}
