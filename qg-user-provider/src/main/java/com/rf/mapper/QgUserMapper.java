package com.rf.mapper;

import com.rf.pojo.QgUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface QgUserMapper {

    public QgUser getQgUserById(@Param("id") String id)throws Exception;

}
