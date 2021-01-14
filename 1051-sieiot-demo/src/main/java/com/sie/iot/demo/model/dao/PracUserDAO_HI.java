package com.sie.iot.demo.model.dao;

import com.sie.iot.common.model.dao.BaseCommonDAO_HI;
import com.sie.iot.demo.model.entities.PracUserEntity_HI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("pracUserDAO_HI")
public class PracUserDAO_HI extends BaseCommonDAO_HI<PracUserEntity_HI> {
    private static final Logger LOGGER = LoggerFactory.getLogger(PracUserDAO_HI.class);
    //
    public PracUserDAO_HI() {
        super();
    }
}
