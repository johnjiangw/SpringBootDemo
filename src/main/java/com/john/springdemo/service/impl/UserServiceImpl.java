package com.john.springdemo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.john.springdemo.common.utils.ReflectUtil;
import com.john.springdemo.dao.UserMapper;
import com.john.springdemo.model.dto.User;
import com.john.springdemo.model.vo.UserVO;
import com.john.springdemo.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("UserService")
public class UserServiceImpl implements IUserService {
    @Resource
    private UserMapper mapper;

    private List<UserVO> object2VO(List<User> list) throws Exception{
        List<UserVO> listVO=new ArrayList<>();
        for (User u:list ) {
            UserVO vo=new UserVO();
            vo=ReflectUtil.copyValue(u,vo,new String[]{"pwd"});
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
    public PageInfo<UserVO> findAllByPage(int pageIndex,int pageSize) throws Exception {
        //分页调用方式1
        PageHelper.startPage(1,1);
        //PageHelper.count()
        List<User>list=mapper.selectAll();
        List<UserVO>listVO=object2VO(list);
        PageInfo<UserVO>pageInfo=new PageInfo<>(listVO,1);
        return pageInfo;
    }
    @Override
    public int addUser(UserVO userVO)throws Exception{
        User u=new User();
        u=ReflectUtil.copyValue(userVO,u);
        u.setPwd("123456");
        u.setLastUpdate(new Date());
        return mapper.insertSelective(u);
    }
}
