package com.sie.iot.demo.model.inter.server;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.sie.iot.common.bean.OrderByBean;
import com.sie.iot.demo.model.entities.DemoStaffEntity_HI;
import com.sie.iot.demo.model.inter.IDemoStaff;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sie.iot.common.util.SaafToolUtils;
import com.siefw.base.utils.SToolUtils;
import com.siefw.hibernate.core.paging.Pagination;
import com.sie.iot.demo.model.entities.DemoDeptEntity_HI;
import com.siefw.hibernate.core.dao.ViewObject;
import com.sie.iot.demo.model.inter.IDemoDept;
import com.sie.iot.common.model.inter.server.BaseCommonServer;

@Component("demoDeptServer")
public class DemoStaffServer extends BaseCommonServer<DemoStaffEntity_HI> implements IDemoStaff {
    private static final Logger LOGGER = LoggerFactory.getLogger(DemoStaffServer.class);

    @Autowired
    private ViewObject<DemoStaffEntity_HI> demoStaffDAO_HI;

    public DemoStaffServer() {
        super();
    }

    @Override
    public Object save(DemoStaffEntity_HI demoStaffEntity_HI) {
        return demoStaffDAO_HI.save(demoStaffEntity_HI);
    }
    @Override
    public void update(DemoStaffEntity_HI demoStaffEntity_HI) {
        demoStaffDAO_HI.saveOrUpdate(demoStaffEntity_HI);
    }
    @Override
    public DemoStaffEntity_HI findById(Long id) {
        DemoStaffEntity_HI demoStaffEntity_HI = this.getById(id);
        return demoStaffEntity_HI;
    }
//	@Override
//	public DemoStaffEntity_HI findById(Long id) {
//		DemoStaffEntity_HI demoStaffEntity_HI = this.getById(id);
//		return demoStaffEntity_HI;
//	}

    @Override
    public Pagination<DemoStaffEntity_HI> findPagination(JSONObject jsonObject, Integer pageIndex, Integer pageRows, OrderByBean orderByBean) {
        Map<String, Object> paramsMap = SToolUtils.fastJsonObj2Map(jsonObject);
        StringBuffer querySQLSB = new StringBuffer(" from DemoDeptEntity_HI where deleteFlag=0 ");

        SaafToolUtils.parperParam(jsonObject, "dept_name", "deptName", querySQLSB, paramsMap, "like");

//		if(jsonObject.get("acAssetStatuss")!=null){
//			List<String> acAssetStatuss =  JSONObject.parseArray(JSONObject.toJSONString(jsonObject.get("acAssetStatuss")),String.class);
//			paramsMap.put("acAssetStatuss", acAssetStatuss);
//			querySQLSB.append(" and acAssetStatus in ( :acAssetStatuss )");
//		}else{
//			paramsMap.remove("acAssetStatuss");
//		}


        if(orderByBean==null){
            OrderByBean orderByBeanDefault = new OrderByBean();
            orderByBeanDefault.setAttributeName("creation_date");
            orderByBeanDefault.setSortType("desc");
            orderByBean = orderByBeanDefault;
        }
        SaafToolUtils.sortUtil(orderByBean, querySQLSB);
        Pagination<DemoStaffEntity_HI> demoStaffEntity_HIs = demoStaffDAO_HI.findPagination(querySQLSB.toString(), paramsMap, pageIndex, pageRows);
        return demoStaffEntity_HIs;
    }

    @Override
    public boolean checkUserName(String userName) {
        Map<String,Object> condition = new HashMap<>();
        condition.put("userName",userName);
        List<DemoStaffEntity_HI> list = demoStaffDAO_HI.findByProperty(condition);
        return list!=null&&list.size()>0;
    }


}
