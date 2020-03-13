package life.majiang.community.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import life.majiang.community.model.Quesstion;

@Mapper
public interface QuesstionMapper {

	@Insert("insert into question (title,description,gmt_create,gmt_modified,creator,tag) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
	void create(Quesstion quesstion);

	@Select("select * from question limit #{offset},#{size}")
	List<Quesstion> list(@Param(value = "offset") Integer offset, @Param(value = "size") Integer size);
	
	@Select("select count(1) from question")
	Integer count();

	@Select("select * from question where creator = #{userId} limit #{offset},#{size}")
	List<Quesstion> listByUserId(@Param("userId") Integer userId, @Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

	@Select("select count(1) from question where creator = #{userId}")
	Integer countByUserId(@Param("userId") Integer userId);

	@Select("select * from question where id = #{id}")
	Quesstion getById(@Param("id") Integer id);

	@Update("update question set title = #{title}, description = #{description}, gmt_modified = #{gmtModified}, tag = #{tag} where id = #{id}")
	void update(Quesstion quesstion);
}
