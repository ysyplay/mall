package com.mmall.controller.portal;

import com.mmall.common.Const;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import com.mmall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by runa on 2017/11/3.
 */
@Controller
@RequestMapping("/user/")
public class UserController
{
    @Autowired
    private IUserService iUserService;

    //登录
    @RequestMapping(value = "login.do",method = RequestMethod.POST)
    @ResponseBody//返回值序列化成json
    public ServerResponse<User> login(String username, String password, HttpSession session)
    {
        //service->mybatis->dao
        ServerResponse<User> response = iUserService.login(username,password);
        if (response.isSuccess())
        {
            session.setAttribute(Const.CURRENT_USER,response.getData());
        }
        return response;
    }
    //退出登录
    @RequestMapping(value = "logout.do",method = RequestMethod.GET)
    @ResponseBody//返回值序列化成json
    public  ServerResponse<String> logout(HttpSession session)
    {
        session.removeAttribute(Const.CURRENT_USER);
        return  ServerResponse.createBySuccess();
    }
    //注册
    @RequestMapping(value = "registr.do",method = RequestMethod.POST)
    @ResponseBody//返回值序列化成json
    public ServerResponse<String> registr(User user)
    {
        return iUserService.register(user);
    }

    @RequestMapping(value = "checkValid.do",method = RequestMethod.GET)
    @ResponseBody//返回值序列化成json
    public ServerResponse<String> checkValid(String str,String type)
    {
      return iUserService.checkValid(str,type);
    }

}
