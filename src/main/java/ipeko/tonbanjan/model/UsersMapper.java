package ipeko.tonbanjan.model;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UsersMapper {
  @Select("SELECT roomid from users where username= #{name};")
  int selectbyName(String name);

  @Select("SELECT count(username) from users where username= #{name};")
  int selectcountbyName(String name);

  @Insert("Insert INTO users (roomId, userName) Values (#{roomId}, #{userName})")
  void InsertUser(int roomId, String userName);

  @Update("UPDATE Users SET roomId=#{id} WHERE userName = #{name}")
  void UpdateUser(int id, String name);
}
