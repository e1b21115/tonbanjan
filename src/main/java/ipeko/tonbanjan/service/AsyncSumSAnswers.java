package ipeko.tonbanjan.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

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

  @Async
  public void count(SseEmitter emitter) throws IOException {
    SendCount sc1;
    try {
      while (true) {
        // ArrayList<SendCount> sc = sMapper.selectCountAnsQue();
        ArrayList<SendCount> sc = new ArrayList<>();
        int n = sMapper.selectMaxQue();
        for (int i = 1; i < n + 1; i++) {
          for (int j = 1; j < 5; j++) {
            sc1 = new SendCount(i, j, sMapper.selectCountByAnsQue(j, i));
            sc.add(sc1);
          }
        }
        emitter.send(sc);
        TimeUnit.SECONDS.sleep(1);
      }
    } catch (InterruptedException e) {

    }
  }

}
