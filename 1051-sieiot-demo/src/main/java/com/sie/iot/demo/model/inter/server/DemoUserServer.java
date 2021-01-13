package com.sie.iot.demo.model.inter.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.util.List;
import java.util.Map;
import com.sie.iot.common.bean.OrderByBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sie.iot.common.util.SaafToolUtils;
import com.siefw.base.utils.SToolUtils;
import com.siefw.hibernate.core.paging.Pagination;
import com.sie.iot.demo.model.entities.DemoUserEntity_HI;
import com.siefw.hibernate.core.dao.ViewObject;
import com.sie.iot.demo.model.inter.IDemoUser;
import com.sie.iot.common.model.inter.server.BaseCommonServer;

@Component("demoUserServer")
public class DemoUserServer extends BaseCommonServer<DemoUserEntity_HI> implements IDemoUser{
	private static final Logger LOGGER = LoggerFactory.getLogger(DemoUserServer.class);

	@Autowired
	private ViewObject<DemoUserEntity_HI> demoUserDAO_HI;

	public DemoUserServer() {
		super();
	}

	@Override
	public Object save(DemoUserEntity_HI demoUserEntity_HI) {
		return demoUserDAO_HI.save(demoUserEntity_HI);
	}
	@Override
	public void update(DemoUserEntity_HI demoUserEntity_HI) {
		demoUserDAO_HI.saveOrUpdate(demoUserEntity_HI);
	}
	@Override
	public DemoUserEntity_HI findById(Long id) {
		DemoUserEntity_HI demoUserEntity_HI = this.getById(id);
		return demoUserEntity_HI;
	}
	@Override
	public List<DemoUserEntity_HI> findList(JSONObject jsonObject, OrderByBean orderByBean) {
		Map<String, Object> paramsMap = SToolUtils.fastJsonObj2Map(jsonObject);
		StringBuffer querySQLSB = new StringBuffer(" from DemoUserEntity_HI where 1=1 ");
		//f(jsonObject.containsKey("unitName")){
		//  querySQLSB.append("and unitName like :%unitName% ");
		//}
		//if(jsonObject.containsKey("useStatus")){
		//  querySQLSB.append("and useStatus = :useStatus ");
		//}
		SaafToolUtils.sortUtil(orderByBean, querySQLSB);
		List<DemoUserEntity_HI> demoUserEntity_HIs = demoUserDAO_HI.findList(querySQLSB.toString(), paramsMap);
		return demoUserEntity_HIs;
	}
	@Override
	public Pagination<DemoUserEntity_HI> findPagination(JSONObject jsonObject, Integer pageIndex, Integer pageRows, OrderByBean orderByBean) {
		Map<String, Object> paramsMap = SToolUtils.fastJsonObj2Map(jsonObject);
		StringBuffer querySQLSB = new StringBuffer(" from DemoUserEntity_HI where 1=1 ");
		//if(jsonObject.containsKey("unitName")){
		//    querySQLSB.append("and unitName like :%unitName% ");
		//}
		//if(jsonObject.containsKey("useStatus")){
		//    querySQLSB.append("and useStatus = :useStatus ");
		//}
		SaafToolUtils.sortUtil(orderByBean, querySQLSB);
		Pagination<DemoUserEntity_HI> demoUserEntity_HIs = demoUserDAO_HI.findPagination(querySQLSB.toString(), paramsMap, pageIndex, pageRows);
		return demoUserEntity_HIs;
	}
}
