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
    //校验
    @RequestMapping(value = "checkValid.do",method = RequestMethod.GET)
    @ResponseBody//返回值序列化成json
    public ServerResponse<String> checkValid(String str,String type)
    {
      return iUserService.checkValid(str,type);
    }
    //获取用户信息
    @RequestMapping(value = "getUserInfo.do",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<User> getUserInfo(HttpSession session)
    {
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user!=null)
        {
            return ServerResponse.createBySuccess(user);
        }
        return ServerResponse.createByErrorMessage("用户信息获取失败");
    }
    //忘记密码
    @RequestMapping(value = "forgetGetQusetion.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetGetQusetion(String username)
    {
        return iUserService.selectQuestion(username);
    }
    //校验问题答案
    @RequestMapping(value = "forgetCheckAnswer.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetCheckAnswer(String username,String question,String answer)
    {
        return iUserService.checkAnswer(username, question, answer);
    }
    //忘记密码中的重置密码
    @RequestMapping(value = "forgetRestPassword.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetRestPassword(String username,String passwordNew,String forgetToken)
    {
        return iUserService.forgetRestPassword(username, passwordNew, forgetToken);
    }
    //登录状态中的重置密码
    @RequestMapping(value = "restPassword.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> restPassword(HttpSession session,String passwordOld,String passwordNew)
    {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null)
        {
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        return iUserService.restPassword(user,passwordOld,passwordNew);
    }

}
