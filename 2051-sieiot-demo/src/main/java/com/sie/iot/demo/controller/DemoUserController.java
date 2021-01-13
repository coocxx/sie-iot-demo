package com.sie.iot.demo.controller;

import com.sie.iot.component.exception.ApplicationRuntimeException;
import com.sie.iot.component.exception.ApplicationValidationException;
import com.sie.iot.demo.model.inter.IDemoUser;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import com.sie.iot.common.bean.ResponseData;
import com.sie.iot.common.iotenum.ResponseMsgCode;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import com.sie.iot.common.bean.PaginationRequestData;
import com.sie.iot.common.bean.RequestData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.sie.iot.common.services.CommonAbstractService;
import com.sie.iot.common.model.inter.IBaseCommon;
import com.siefw.hibernate.core.paging.Pagination;

import com.sie.iot.demo.bean.DemoUserBean;

import com.sie.iot.demo.model.entities.DemoUserEntity_HI;

@RestController
@RequestMapping("/demoUserController")
@Api(value = "人员管理", tags = {"人员管理"})
public class DemoUserController extends CommonAbstractService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DemoUserController.class);

    @Autowired
    private IDemoUser demoUserServer;

    @Override
    public IBaseCommon getBaseCommonServer() {
        return this.demoUserServer;
    }

    /**
     * 根据Id查询数据
     *
     * @param id 参数id
     *           {
     *           id:主键Id
     *           }
     * @return
     * @author AutoGenerate
     * @creteTime 2021/01/13
     */
    @GetMapping(value = "/get-Id")
    public ResponseData findById(Long id) {
//		UserSessionBean userSessionBean = this.getUserSessionBean();
//		AuthorizationCommonUtils.validatorTokenInfo(userSessionBean);
        try {
            DemoUserEntity_HI demoUserEntity_HI = demoUserServer.findById(id);
            return new ResponseData(ResponseMsgCode.SUCCESS.msgCode, demoUserEntity_HI, redisTemplate);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new ApplicationRuntimeException(ResponseMsgCode.ERROR.msgCode, e);
        }
    }

    @PostMapping(value = "/find-pagination")
    public ResponseData findByPage(@RequestBody PaginationRequestData<DemoUserBean> paginationRequestData) {
//	    UserSessionBean userSessionBean = this.getUserSessionBean();
//	   AuthorizationCommonUtils.validatorTokenInfo(userSessionBean);
        Integer pageIndex = paginationRequestData.getPageIndex();
        Integer pageRows = paginationRequestData.getPageRows();
        DemoUserBean demoUserBean = paginationRequestData.getParams();
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(demoUserBean));
        try {
            Pagination<DemoUserEntity_HI> pagination = demoUserServer.findPagination(jsonObject, pageIndex, pageRows, paginationRequestData.getOrderByBean());
            return new ResponseData(ResponseMsgCode.SUCCESS.msgCode, pagination, redisTemplate);
        } catch (Exception e) {
            LOGGER.error(" find - pagination error:" + e);
            throw new ApplicationRuntimeException(ResponseMsgCode.ERROR.msgCode, e);
        }
    }

    @PostMapping(value = "/save")
    public ResponseData save(@RequestBody RequestData<DemoUserBean> requestData) {
//    UserSessionBean userSessionBean = this.getUserSessionBean();
//    AuthorizationCommonUtils.validatorTokenInfo(userSessionBean);
        DemoUserBean demoUserBean = requestData.getParams();
        DemoUserEntity_HI demoUserEntity_HI = JSONObject.parseObject(JSONObject.toJSONString(demoUserBean), DemoUserEntity_HI.class);
        //固定
//    demoUserEntity_HI.setOperatorUserId(userSessionBean.getUserId());
        try {
            demoUserServer.save(demoUserEntity_HI);
            return new ResponseData(ResponseMsgCode.SUCCESS.msgCode, null, redisTemplate);
        } catch (Exception e) {
            throw new ApplicationRuntimeException(ResponseMsgCode.ERROR.msgCode, e);
        }
    }

    @GetMapping(value = "/delete-id")
    public ResponseData deleteById(@RequestParam Long id) {
//    UserSessionBean userSessionBean = this.getUserSessionBean();
//    AuthorizationCommonUtils.validatorTokenInfo(userSessionBean);
        try {
            demoUserServer.deleteById(id);
            return new ResponseData(ResponseMsgCode.SUCCESS.msgCode, null, redisTemplate);
        } catch (Exception e) {
            throw new ApplicationRuntimeException(ResponseMsgCode.ERROR.msgCode, e);
        }
    }

    @PostMapping(value = "/update")
    public ResponseData update(@RequestBody RequestData<DemoUserBean> requestData) throws ApplicationValidationException {
//    UserSessionBean userSessionBean = this.getUserSessionBean();
//    AuthorizationCommonUtils.validatorTokenInfo(userSessionBean);
        DemoUserBean demoUserBean = requestData.getParams();
        Map<String, Object> requestMap = demoUserBean.getRequestData();
        Long id = requestMap.get(" id") != null ? Long.parseLong(requestMap.get(" id") + "") : null;
        DemoUserEntity_HI demoUserEntity_HI = demoUserServer.getById(id);
        if (null != demoUserEntity_HI) {
            //固定
//    demoUserEntity_HI.setOperatorUserId(userSessionBean.getUserId());
        } else {
            throw new ApplicationValidationException(ResponseMsgCode.UPDATE_DATA_NOT_EXIST.msgCode);
        }
        try {
            demoUserServer.update(demoUserEntity_HI);
            return new ResponseData(ResponseMsgCode.SUCCESS.msgCode, demoUserEntity_HI, redisTemplate);
        } catch (Exception e) {
            throw new ApplicationRuntimeException(ResponseMsgCode.ERROR.msgCode, e);
        }
    }
}