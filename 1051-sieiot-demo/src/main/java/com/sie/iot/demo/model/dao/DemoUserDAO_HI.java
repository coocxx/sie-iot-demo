package com.sie.iot.demo.model.dao;

import com.sie.iot.common.model.dao.BaseCommonDAO_HI;
import com.sie.iot.demo.model.entities.DemoUserEntity_HI;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component("demoUserDAO_HI")
public class DemoUserDAO_HI extends BaseCommonDAO_HI<DemoUserEntity_HI> {
    private static final Logger LOGGER = LoggerFactory.getLogger(DemoUserDAO_HI.class);

    public DemoUserDAO_HI() {
        super();
    }

}
