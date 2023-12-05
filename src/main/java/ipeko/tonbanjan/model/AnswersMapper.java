package ipeko.tonbanjan.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AnswersMapper {

  @Select("SELECT * from answers where answerId = #{answerId};")
  Answers selectById(int answerId);

  @Select("SELECT * from answers where questionId = #{questionId};")
  ArrayList<Answers> selectByQuestionId(int questionId);
  /**
   * #{userName}などはinsertの引数にあるChamberクラスのフィールドを表しています 引数に直接String
   * userNameなどと書いてもいけるはず
   * 下記のOptionsを指定すると，insert実行時にAuto incrementされたIDの情報を取得できるようになる useGeneratedKeys
   * = true -> Keyは自動生成されることを表す keyColumn : keyになるテーブルのカラム名 keyProperty :
   * keyになるJavaクラスのフィールド名
   *
   * @param chamber
   */
  @Insert("INSERT INTO answers (questionId,a_content) VALUES (#{questionId},#{a_content});")
  @Options(useGeneratedKeys = true, keyColumn = "answerId", keyProperty = "answerId")
  void insertAnswers(Answers answers);

  @Select("SELECT * from answers where a_content = #{a_content}")
  ArrayList<Answers> selectAllByA_content(String a_content);

  @Select("SELECT answers.questionId,answers.a_content,questions.q_content from answers JOIN questions ON answers.questionId=questions.questionId;")
  ArrayList<AnswersQuestions> selectAllAnswersQuestions();
}
