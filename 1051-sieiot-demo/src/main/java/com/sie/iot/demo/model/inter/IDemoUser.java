package com.sie.iot.demo.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.sie.iot.common.bean.OrderByBean;
import com.sie.iot.common.model.inter.IBaseCommon;
import com.sie.iot.demo.model.entities.PracUserEntity_HI;
import com.siefw.hibernate.core.paging.Pagination;

public interface IDemoUser extends IBaseCommon<PracUserEntity_HI> {
    PracUserEntity_HI findById(Long id);
    Pagination<PracUserEntity_HI> findPagination(JSONObject jsonObject, Integer pageIndex, Integer pageRows, OrderByBean orderByBean);
    boolean checkUserName(String UserName);
}
