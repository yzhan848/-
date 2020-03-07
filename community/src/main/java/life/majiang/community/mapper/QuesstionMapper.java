package life.majiang.community.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import life.majiang.community.model.Quesstion;

@Mapper
public interface QuesstionMapper {

	@Insert("insert into question (title,description,gmt_create,gmt_modified,creator,tag) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
	void create(Quesstion quesstion);

	@Select("select * from question")
	List<Quesstion> list();
}
