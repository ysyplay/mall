package com.mmall.service;

import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.Shipping;



/**
 * Created by runa on 2017/11/24.
 */
public interface IShippingService
{
    ServerResponse add(Integer userId, Shipping shipping);
    ServerResponse del(Integer userId,  Integer shippingId);
    ServerResponse update(Integer userId,  Shipping shipping);
    ServerResponse<PageInfo> list(Integer userId, int pageNum, int pageSize);

}
