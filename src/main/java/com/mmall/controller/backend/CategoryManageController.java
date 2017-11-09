package com.mmall.controller.backend;

import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import com.mmall.service.ICategoryService;
import com.mmall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by runa on 2017/11/9.
 */
@Controller
@RequestMapping("/manage/category")
public class CategoryManageController
{
    @Autowired
    private IUserService iUserService;
    @Autowired
    private ICategoryService iCategoryService;

    @RequestMapping(value="addCategory.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse addCategory(HttpSession session,String categoryName,@RequestParam(value = "parentId",defaultValue = "0") int parentId)
    {
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user ==null)
        {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录");
        }
        if (iUserService.checkAdminRole(user).isSuccess())
        {
            return iCategoryService.addCategory(categoryName,parentId);
        }
        else
        {
            return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限");
        }
    }

    @RequestMapping(value="setCategoryName.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse setCategoryName(HttpSession session,Integer categoryId,String categoryName)
    {

        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user ==null)
        {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录");
        }
        if (iUserService.checkAdminRole(user).isSuccess())
        {
            return iCategoryService.setCategoryName(categoryId, categoryName);
        }
        else
        {
            return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限");
        }
    }

    @RequestMapping(value="getChildrenParallelCategory.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getChildrenParallelCategory(HttpSession session,@RequestParam(value = "parentId",defaultValue = "0") int parentId)
    {

        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user ==null)
        {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录");
        }
        if (iUserService.checkAdminRole(user).isSuccess())
        {
            return iCategoryService.getChildrenParallelCategory(parentId);
        }
        else
        {
            return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限");
        }
    }
}
