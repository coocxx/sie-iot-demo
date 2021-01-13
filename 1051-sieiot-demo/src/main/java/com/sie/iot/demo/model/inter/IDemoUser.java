package com.sie.iot.demo.model.inter;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

import com.sie.iot.common.bean.OrderByBean;
import com.siefw.hibernate.core.paging.Pagination;
import com.sie.iot.demo.model.entities.DemoUserEntity_HI;
import com.sie.iot.common.model.inter.IBaseCommon;

public interface IDemoUser extends IBaseCommon<DemoUserEntity_HI> {

    DemoUserEntity_HI findById(Long id);

    List<DemoUserEntity_HI> findList(JSONObject jsonObject, OrderByBean orderByBean);

    Pagination<DemoUserEntity_HI> findPagination(JSONObject jsonObject, Integer pageIndex, Integer pageRows, OrderByBean orderByBean);
}
