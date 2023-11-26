package ipeko.tonbanjan.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ClassMapper {
  @Select("SELECT * from class")
  ArrayList<Class> selectAllclass();

  @Select("SELECT * FROM class WHERE classId = #{classId}")
  Class selectByClassId(int id);

  @Insert("INSERT INTO class (className) VALUES (#{className});")
  @Options(useGeneratedKeys = true, keyColumn = "classId", keyProperty = "classId")
  void InsertClass(Class addclass);

}
