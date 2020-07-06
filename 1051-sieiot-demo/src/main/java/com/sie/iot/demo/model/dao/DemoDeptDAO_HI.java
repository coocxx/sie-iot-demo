package com.sie.iot.demo.model.dao;

import com.sie.iot.common.model.dao.BaseCommonDAO_HI;
import com.sie.iot.demo.model.entities.DemoDeptEntity_HI;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component("demoDeptDAO_HI")
public class DemoDeptDAO_HI extends BaseCommonDAO_HI<DemoDeptEntity_HI> {
	private static final Logger LOGGER = LoggerFactory.getLogger(DemoDeptDAO_HI.class);

	public DemoDeptDAO_HI() {
		super();
	}

}
