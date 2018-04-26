package com.john.springdemo.service.impl;

import com.github.pagehelper.PageInfo;
import com.john.springdemo.dao.UserMapper;
import com.john.springdemo.model.dto.User;
import com.john.springdemo.model.vo.UserVO;
import com.john.springdemo.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Service("UserService")
public class UserServiceImpl implements IUserService {
    @Resource
    private UserMapper mapper;

    private List<UserVO> object2VO(List<User> list) throws Exception{
        List<UserVO> listVO=new ArrayList<>();
        for (User u:list ) {
            UserVO vo=new UserVO();
            Class<?>clazz=u.getClass();
            Class<?>clazzVO=vo.getClass();
            for (Field f:clazz.getDeclaredFields()) {
                String fName=f.getName();
                if(fName!="pwd") {
                    f.setAccessible(true);
                    Field fVO = clazzVO.getDeclaredField(fName);
                    fVO.setAccessible(true);
                    fVO.set(vo, f.get(u));
                }
            }
            listVO.add(vo);
        }
        return listVO;
    }
    @Override
    public UserVO findUser(int id) throws Exception {

        User user=mapper.selectByPrimaryKey(id);
        if(user==null)
            return null;

        List<User> list=new ArrayList<>();
        list.add(user);
        return object2VO(list).get(0);
    }
    private Integer selectDataCount(){
        return mapper.selectDataCount();
    }

    @Override
    public PageInfo<UserVO> findAllByPage(int pageIndex,int pageSize) {
        //PageInfo<UserVO> pageInfo=new PageInfo<>(pageIndex,pageSize);
        //List<User>list=mapper.selectDataPage()
        return null;
    }
}
