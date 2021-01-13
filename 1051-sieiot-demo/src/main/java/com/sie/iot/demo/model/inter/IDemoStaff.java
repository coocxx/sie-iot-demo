package com.sie.iot.demo.model.inter;

import com.alibaba.fastjson.JSONObject;
import java.util.List;
import com.sie.iot.common.bean.OrderByBean;
import com.sie.iot.demo.model.entities.DemoStaffEntity_HI;
import com.siefw.hibernate.core.paging.Pagination;
import com.sie.iot.demo.model.entities.DemoDeptEntity_HI;
import com.sie.iot.common.model.inter.IBaseCommon;

public interface IDemoStaff extends IBaseCommon<DemoStaffEntity_HI>{

    DemoStaffEntity_HI findById(Long id);
    Pagination<DemoStaffEntity_HI> findPagination(JSONObject jsonObject, Integer pageIndex, Integer pageRows, OrderByBean orderByBean);
    boolean checkUserName(String userName);
}
