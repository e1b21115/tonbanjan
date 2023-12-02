package ipeko.tonbanjan.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface QuestionsMapper {

  @Select("SELECT questionId, roomId, q_content FROM questions;")
  ArrayList<Questions> selectAllQuestions();

  @Select("SELECT questionId,roomId,question,answer from questions where questionId = #{questionId};")
  Questions selectById(int questionId);

  @Insert("INSERT INTO questions (roomId,q_content) VALUES (#{roomId},#{q_content});")
  @Options(useGeneratedKeys = true, keyColumn = "questionId", keyProperty = "questionId")
  void insertQuestions(Questions questions);

  @Select("SELECT * from questions where roomId = #{roomId};")
  ArrayList<Questions> selectByRoomId(int roomId);

  @Select("SELECT count(questionId) from questions where roomId = #{roomId};")
  int selectMaxquestionByRoomId(int roomId);

  @Select("SELECT * from questions where roomId = #{roomId}")
  ArrayList<Questions> selectQuestionByRoomId(int roomId);

  @Select("SELECT * from questions where question = #{question}")
  ArrayList<Questions> selectAllByQuestion(String question);

}
