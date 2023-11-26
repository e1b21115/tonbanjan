package ipeko.tonbanjan.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import ipeko.tonbanjan.model.SendCount;

@Mapper
public interface SendMapper {
  @Select("SELECT * form send_answer")
  Answers selectAllSend();

  @Insert("INSERT INTO send_answer (answerId, questionId) VALUES (#{answerId},#{questionId});")
  @Options(useGeneratedKeys = true, keyColumn = "sAnswerId", keyProperty = "sAnswerId")
  void insertQuestions(Send send);

  @Select("SELECT questionId, answerId, count(answerid) as amount FROM SEND_ANSWER group by (questionid, answerid);")
  ArrayList<SendCount> selectCountAnsQue();

  @Select("SELECT count(answerid) FROM SEND_ANSWER WHERE questionid = #{questionid} and answerid = #{answerid};")
  int selectCountByAnsQue(int answerid, int questionid);

  @Select("SELECT max(questionid) from send_answer")
  int selectMaxQue();
}
