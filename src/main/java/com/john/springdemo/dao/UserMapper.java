package com.john.springdemo.dao;

import com.john.springdemo.model.dto.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    int selectDataCount();
    List<User> selectDataPage(int rowIndex,int count)throws Exception;
    List<User>selectAll();
}