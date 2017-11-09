package com.mmall.service;

import com.mmall.common.ServerResponse;

/**
 * Created by runa on 2017/11/9.
 */
public interface ICategoryService {
    public ServerResponse addCategory(String categoryName, Integer parentId);
    ServerResponse setCategoryName(Integer categoryId, String categoryName);
}
