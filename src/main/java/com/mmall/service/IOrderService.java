package com.mmall.service;

import com.mmall.common.ServerResponse;

/**
 * Created by runa on 2017/11/29.
 */
public interface IOrderService
{
    ServerResponse pay(Long orderNo, Integer userId, String path);
}
