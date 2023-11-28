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
import ipeko.tonbanjan.model.QuestionsMapper;
import ipeko.tonbanjan.model.SendMapper;

@Service
public class AsyncSumSAnswers {

  @Autowired
  QuestionsMapper qMapper;

  @Autowired
  SendMapper sMapper;

  private final Logger logger = LoggerFactory.getLogger(AsyncSumSAnswers.class);

  @Async
  public void count(SseEmitter emitter) {
    SendCount sc1;
    while (true) {
      ArrayList<SendCount> sc = new ArrayList<>();
      int n = sMapper.selectMaxQue();
      for (int i = 1; i < n + 1; i++) {
        for (int j = 1; j < 5; j++) {
          sc1 = new SendCount(i, j, sMapper.selectCountByAnsQue(j, i));
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
