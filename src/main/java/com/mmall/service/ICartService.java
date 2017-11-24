package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.vo.CartVo;

/**
 * Created by runa on 2017/11/23.
 */
public interface ICartService
{
    ServerResponse<CartVo> add(Integer userId, Integer productId, Integer count);
    ServerResponse<CartVo> list (Integer userId);
    ServerResponse<CartVo> update(Integer userId, Integer productId, Integer count);
    ServerResponse<CartVo> deleteProduct(Integer userId,String productIds);
    ServerResponse<Integer> getCount(Integer userId);
}
