package com.sie.iot.demo.model.dao;

import com.sie.iot.common.model.dao.BaseCommonDAO_HI;
import com.sie.iot.demo.model.entities.DemoDeptEntity_HI;
import com.sie.iot.demo.model.entities.DemoStaffEntity_HI;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component("demoStaffDAO_HI")
public class DemoStaffDAO_HI extends BaseCommonDAO_HI<DemoStaffEntity_HI> {
    private static final Logger LOGGER = LoggerFactory.getLogger(DemoStaffDAO_HI.class);

    public DemoStaffDAO_HI() {
        super();
    }

}
