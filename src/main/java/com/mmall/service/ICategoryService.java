package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.pojo.Category;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by runa on 2017/11/9.
 */
public interface ICategoryService {
    public ServerResponse addCategory(String categoryName, Integer parentId);
    ServerResponse setCategoryName(Integer categoryId, String categoryName);
    ServerResponse<List<Category>> getChildrenParallelCategory(Integer parentId);
    ServerResponse getCategoryIdAndDeepChildrenCategory(Integer categoryId);
}
