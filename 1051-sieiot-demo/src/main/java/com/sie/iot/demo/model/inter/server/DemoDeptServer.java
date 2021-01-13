package com.sie.iot.demo.model.inter.server;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.sie.iot.common.bean.OrderByBean;
import com.sie.iot.demo.model.entities.DemoStaffEntity_HI;
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
public class DemoDeptServer extends BaseCommonServer<DemoDeptEntity_HI> implements IDemoDept{
	private static final Logger LOGGER = LoggerFactory.getLogger(DemoDeptServer.class);

	@Autowired
	private ViewObject<DemoDeptEntity_HI> demoDeptDAO_HI;

	public DemoDeptServer() {
		super();
	}

	@Override
	public Object save(DemoDeptEntity_HI demoDeptEntity_HI) {
		return demoDeptDAO_HI.save(demoDeptEntity_HI);
	}
	@Override
	public void update(DemoDeptEntity_HI demoDeptEntity_HI) {
		demoDeptDAO_HI.saveOrUpdate(demoDeptEntity_HI);
	}
	@Override
	public DemoDeptEntity_HI findById(Long id) {
		DemoDeptEntity_HI demoDeptEntity_HI = this.getById(id);
		return demoDeptEntity_HI;
	}
//	@Override
//	public DemoStaffEntity_HI findById(Long id) {
//		DemoStaffEntity_HI demoStaffEntity_HI = this.getById(id);
//		return demoStaffEntity_HI;
//	}

	@Override
	public Pagination<DemoDeptEntity_HI> findPagination(JSONObject jsonObject, Integer pageIndex, Integer pageRows, OrderByBean orderByBean) {
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
		Pagination<DemoDeptEntity_HI> demoDeptEntity_HIs = demoDeptDAO_HI.findPagination(querySQLSB.toString(), paramsMap, pageIndex, pageRows);
		return demoDeptEntity_HIs;
	}

    @Override
    public boolean checkDeptName(String deptName) {
        Map<String,Object> condition = new HashMap<>();
        condition.put("deptName",deptName);
		List<DemoDeptEntity_HI> list = demoDeptDAO_HI.findByProperty(condition);
		return list!=null&&list.size()>0;
	}


}
