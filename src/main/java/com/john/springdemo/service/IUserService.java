package com.john.springdemo.service;

import com.github.pagehelper.PageInfo;
import com.john.springdemo.model.dto.User;
import com.john.springdemo.model.vo.UserVO;

public interface IUserService {
    UserVO findUser(int id) throws Exception;
    PageInfo<UserVO> findAllByPage(int pageIndex,int pageSize)throws Exception;
    int addUser(UserVO user)throws Exception;
}
