package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.pojo.Shipping;



/**
 * Created by runa on 2017/11/24.
 */
public interface IShippingService
{
    ServerResponse add(Integer userId, Shipping shipping);
}
