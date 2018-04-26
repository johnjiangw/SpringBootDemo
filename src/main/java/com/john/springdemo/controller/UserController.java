package com.john.springdemo.controller;


import com.john.springdemo.common.constant.ResponseCode;
import com.john.springdemo.model.vo.ResponseVO;
import com.john.springdemo.model.vo.UserVO;

import com.john.springdemo.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {
    private static Logger logger=LoggerFactory.getLogger(UserController.class);
    @Resource(name = "UserService")
    private IUserService userService;
    @RequestMapping(value = "",method = RequestMethod.GET)
    public ResponseVO<List<UserVO>> queryUser(){
        ResponseVO<List<UserVO>> response=new ResponseVO<>();
        try
        {

        }
        catch (Exception ex){
            logger.error(ex.getMessage());
            response.setCode(ResponseCode.ERROR);
            response.setMsg(ex.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseVO<UserVO> queryUser(@PathVariable String id){
        ResponseVO<UserVO> response = new ResponseVO<>();
        try {
            if(StringUtils.isEmpty(id))
            {
                response.setCode(ResponseCode.FAULT);
                response.setMsg("请求参数id不能为空");
                return response;
            }
            UserVO vo=userService.findUser(Integer.parseInt(id));
            response.setCode(ResponseCode.SUCCESS);
            response.setMsg("成功");
            response.setData(vo);
        }
        catch (Exception ex){
            logger.error(ex.getMessage());
            response.setCode(ResponseCode.ERROR);
            response.setMsg(ex.getMessage());
        }
        return response;
    }


    @RequestMapping(value = "/userInfo",method = RequestMethod.POST)
    public ResponseVO addUser(@RequestBody UserVO userInfo){

        ResponseVO response=new ResponseVO();

        //response.setMsg("成功"+userInfo.getUsername());

        return  response;
    }

    @RequestMapping(value = "/userInfo",method = RequestMethod.PUT)
    public String editUser(@RequestBody UserVO userInfo){
        return userInfo.toString();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable String id){
        return "删除成功";//userInfo.toString();
    }
}
