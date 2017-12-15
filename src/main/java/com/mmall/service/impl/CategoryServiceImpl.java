package com.mmall.service.impl;

import com.google.common.collect.Interner;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mmall.common.ServerResponse;
import com.mmall.dao.CategoryMapper;
import com.mmall.pojo.Category;
import com.mmall.service.ICartService;
import com.mmall.service.ICategoryService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Created by runa on 2017/11/9.
 */
@Service("iCategoryService")
public class CategoryServiceImpl implements ICategoryService
{
    @Autowired
    private CategoryMapper categoryMapper;

    private org.slf4j.Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    public ServerResponse addCategory(String categoryName,Integer parentId)
    {
        if (parentId == null || StringUtils.isBlank(categoryName))
        {
            return ServerResponse.createByErrorMessage("添加品类参数错误");
        }
        Category category = new Category();
        category.setName(categoryName);
        category.setParentId(parentId);
        category.setStatus(true);
        int rowCount = categoryMapper.insert(category);
        if (rowCount>0)
        {
            return ServerResponse.createBySuccessMessage("添加品类成功");
        }
        return ServerResponse.createByErrorMessage("添加品类失败");
    }

    /**
     * 更新品类名
     * @param categoryId
     * @param categoryName
     * @return
     */
    public ServerResponse setCategoryName(Integer categoryId, String categoryName)
    {
        if (categoryId == null || StringUtils.isBlank(categoryName))
        {
            return ServerResponse.createByErrorMessage("品类参数错误");
        }
        Category category = new Category();
        category.setId(categoryId);
        category.setName(categoryName);
        int rowCount = categoryMapper.updateByPrimaryKeySelective(category);
        if (rowCount > 0)
        {
            return ServerResponse.createBySuccessMessage("更新添加品类成功");
        }
        return ServerResponse.createByErrorMessage("更新添加品类失败");
    }

    /**
     * 获取下一级分类
     */
    public ServerResponse<List<Category>> getChildrenParallelCategory(Integer parentId)
    {
        List<Category> categoryList = categoryMapper.selectChildrenParallelCategory(parentId);
        if (CollectionUtils.isEmpty(categoryList))
        {
            logger.info("未找到当前分类的子分类");
            return ServerResponse.createBySuccessMessage("未找到当前分类的子分类");
        }
        return ServerResponse.createBySuccess(categoryList);
    }

    /**
     * * 递归查找本节点Id所有子Id
     */
    public ServerResponse selectCategoryAndChildrenById(Integer categoryId)
    {
       Set<Category> categorySet = Sets.newHashSet();
       categorySet = findChildCategory(categorySet,categoryId);

       List<Integer> categoryIDList = Lists.newArrayList();
       if (categoryId != null)
       {
           for (Category categoryItem :categorySet)
           {
               categoryIDList.add(categoryItem.getId());
           }
       }
       return ServerResponse.createBySuccess(categoryIDList);
    }

    private Set<Category> findChildCategory(Set<Category> categorySet,Integer categoryId)
    {
         Category category = categoryMapper.selectByPrimaryKey(categoryId);
         if (category != null)
         {
             categorySet.add(category);
         }
         List<Category> categoryList = categoryMapper.selectChildrenParallelCategory(categoryId);
         for (Category categoryItem: categoryList)
         {
             findChildCategory(categorySet,categoryItem.getId());
         }
        return categorySet;
    }
}