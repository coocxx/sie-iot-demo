package com.sie.iot.demo.model.inter.server;

import com.alibaba.fastjson.JSONObject;
import com.sie.iot.common.bean.OrderByBean;
import com.sie.iot.common.model.inter.server.BaseCommonServer;
import com.sie.iot.common.util.SaafToolUtils;
import com.sie.iot.demo.model.entities.PracUserEntity_HI;
import com.sie.iot.demo.model.inter.IDemoUser;
import com.siefw.base.utils.SToolUtils;
import com.siefw.hibernate.core.dao.ViewObject;
import com.siefw.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("pracUserServer")
public class PracUserServer extends BaseCommonServer<PracUserEntity_HI> implements IDemoUser {
    private static final Logger LOGGER = LoggerFactory.getLogger(PracUserServer.class);

    @Autowired
    private ViewObject<PracUserEntity_HI> pracUserDAO_HI;

    public PracUserServer() {
        super();
    }

    @Override
    public Object save(PracUserEntity_HI pracUserEntity_HI) {
        return pracUserDAO_HI.save(pracUserEntity_HI);
    }
    @Override
    public void update(PracUserEntity_HI pracUserEntity_HI) {
        pracUserDAO_HI.saveOrUpdate(pracUserEntity_HI);
    }
    @Override
    public PracUserEntity_HI findById(Long id) {
        PracUserEntity_HI pracUserEntity_HI = this.getById(id);
        return pracUserEntity_HI;
    }

    @Override
    public Pagination<PracUserEntity_HI> findPagination(JSONObject jsonObject, Integer pageIndex, Integer pageRows, OrderByBean orderByBean) {
        Map<String, Object> paramsMap = SToolUtils.fastJsonObj2Map(jsonObject);
        StringBuffer querySQLSB = new StringBuffer(" from PracUserEntity_HI where deleteFlag=0 ");

        SaafToolUtils.parperParam(jsonObject, "user_name", "userName", querySQLSB, paramsMap, "like");

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
        Pagination<PracUserEntity_HI> pracUserEntity_HIs = pracUserDAO_HI.findPagination(querySQLSB.toString(), paramsMap, pageIndex, pageRows);
        return pracUserEntity_HIs;
    }

    @Override
    public boolean checkUserName(String userName) {
        Map<String,Object> condition = new HashMap<>();
        condition.put("userName",userName);
        List<PracUserEntity_HI> list = pracUserDAO_HI.findByProperty(condition);
        return list!=null&&list.size()>0;
    }
}
