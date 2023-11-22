package ipeko.tonbanjan.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SendMapper {
    @Select("SELECT * form send_answer" )
    Answers selectAllSend();

    @Insert("INSERT INTO send_answer (answerId, questionId) VALUES (#{answerId},#{questionId});")
  @Options(useGeneratedKeys = true, keyColumn = "sAnswerId", keyProperty = "sAnswerId")
  void insertQuestions(Send send);
}
