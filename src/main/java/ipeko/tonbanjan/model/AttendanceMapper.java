package ipeko.tonbanjan.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AttendanceMapper {

  @Select("SELECT attendId,roomId,userId,attend_times from attendance where attendId = #{attendId};")
  Attendance selectById(int attendId);

  /**
   * #{userName}などはinsertの引数にあるChamberクラスのフィールドを表しています 引数に直接String
   * userNameなどと書いてもいけるはず
   * 下記のOptionsを指定すると，insert実行時にAuto incrementされたIDの情報を取得できるようになる useGeneratedKeys
   * = true -> Keyは自動生成されることを表す keyColumn : keyになるテーブルのカラム名 keyProperty :
   * keyになるJavaクラスのフィールド名
   *
   * @param chamber
   */
  @Insert("INSERT INTO attendance (roomId,userId,attend_times) VALUES (#{roomId},#{userId},#{attend_times});")
  @Options(useGeneratedKeys = true, keyColumn = "attendId", keyProperty = "attendId")
  void insertAttendance(Attendance attendance);

  @Select("SELECT * from attendance where attend_times = #{attend_times}")
  ArrayList<Attendance> selectAllByAttend_times(int attend_times);

  /**
   * DBのカラム名とjavaクラスのフィールド名が同じ場合はそのまま代入してくれる（大文字小文字の違いは無視される）
   * カラム名とフィールド名が異なる場合の対応も可能だが，いきなり複雑になるので，selectで指定するテーブル中のカラム名とクラスのフィールド名は同一になるよう設計することが望ましい
   *
   * @return
   */
  //@Select("SELECT attendance.roomId,attendance.userId,attendance.attend_times,rooms.r_name,users.u_name from attendance JOIN rooms ON attendance.roomId=rooms.roomId JOIN users ON attendance.userId=users.userId;")
  //ArrayList<AttendanceRoomUser> selectAllAttendanceRoomUser();

  //@Insert("INSERT INTO rooms (r_name) VALUES (#{r_name});")
  //void insertRooms(Rooms rooms);
}
