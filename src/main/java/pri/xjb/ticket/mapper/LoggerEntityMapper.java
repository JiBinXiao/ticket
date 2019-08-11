package pri.xjb.ticket.mapper;


import pri.xjb.ticket.model.LoggerEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface LoggerEntityMapper {


    int insertSelective(LoggerEntity record);


    int getCountByUsername(@Param("username") String username,
                           @Param("date") String date);
}