package ipeko.tonbanjan.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import ipeko.tonbanjan.model.SendCount;
import ipeko.tonbanjan.model.Questions;
import ipeko.tonbanjan.model.QuestionsMapper;
import ipeko.tonbanjan.model.SendMapper;
import ipeko.tonbanjan.model.UsersMapper;

@Service
public class AsyncSumSAnswers {

  @Autowired
  QuestionsMapper qMapper;

  @Autowired
  SendMapper sMapper;

  @Autowired
  UsersMapper uMapper;

  private final Logger logger = LoggerFactory.getLogger(AsyncSumSAnswers.class);

  @Async
  public void count(SseEmitter emitter, String name) {
    SendCount sc1;
    int id = uMapper.selectbyName(name);
    while (true) {
      ArrayList<Questions> que = qMapper.selectQuestionByRoomId(id);
      ArrayList<SendCount> sc = new ArrayList<>();
      for (Questions qu : que) {
        for (int i = 1; i < 5; i++) {
          int qid = qu.getQuestionId();
          sc1 = new SendCount(qid, i, sMapper.selectCountByAnsQue(qid, i));
          sc.add(sc1);
        }
      }
      try {
        emitter.send(sc);
      } catch (IOException e) {
        logger.warn("Exception" + e.getClass().getName() + ":" + e.getMessage());
        emitter.complete();
      }

      try {
        TimeUnit.SECONDS.sleep(1);
      } catch (InterruptedException e) {
        logger.warn("Exception" + e.getClass().getName() + ":" + e.getMessage());
      }

    }
  }

}
