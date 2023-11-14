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

  /**
   * #{userName}などはinsertの引数にあるChamberクラスのフィールドを表しています 引数に直接String
   * userNameなどと書いてもいけるはず
   * 下記のOptionsを指定すると，insert実行時にAuto incrementされたIDの情報を取得できるようになる useGeneratedKeys
   * = true -> Keyは自動生成されることを表す keyColumn : keyになるテーブルのカラム名 keyProperty :
   * keyになるJavaクラスのフィールド名
   *
   * @param chamber
   */
  @Insert("INSERT INTO questions (roomId,q_content) VALUES (#{roomId},#{q_content});")
  @Options(useGeneratedKeys = true, keyColumn = "questionId", keyProperty = "questionId")
  void insertQuestions(Questions questions);

  @Select("SELECT * from questions where question = #{question}")
  ArrayList<Questions> selectAllByQuestion(String question);

  /**
   * DBのカラム名とjavaクラスのフィールド名が同じ場合はそのまま代入してくれる（大文字小文字の違いは無視される）
   * カラム名とフィールド名が異なる場合の対応も可能だが，いきなり複雑になるので，selectで指定するテーブル中のカラム名とクラスのフィールド名は同一になるよう設計することが望ましい
   *
   * @return
   */
  // @Select("SELECT
  // questions.roomId,questions.userId,questions.question,questions.answer,rooms.r_name,users.u_name
  // from questions JOIN rooms ON questions.roomId=rooms.roomId JOIN users ON
  // questions.userId=users.userId;")
  // ArrayList<QuestionsRoomUser> selectAllQuestionsRoomUser();

  // @Insert("INSERT INTO rooms (r_name) VALUES (#{r_name});")
  // void insertRooms(Rooms rooms);
}
