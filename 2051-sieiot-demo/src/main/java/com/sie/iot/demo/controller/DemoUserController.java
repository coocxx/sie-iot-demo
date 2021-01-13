package com.sie.iot.demo.controller;

import com.sie.iot.demo.model.inter.IDemoUser;

import com.alibaba.fastjson.JSONObject;
import com.siefw.base.utils.SToolUtils;
import org.apache.commons.lang.StringUtils;
import com.sie.iot.common.bean.ResponseData;
import com.sie.iot.common.bean.ResponseData;
import com.sie.iot.common.iotenum.ResponseMsgCode;
import com.sie.iot.common.bean.UserSessionBean;
import com.sie.iot.common.util.AuthorizationCommonUtils;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import com.sie.iot.common.bean.PaginationRequestData;
import com.sie.iot.common.bean.RequestData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.sie.iot.common.services.CommonAbstractService;
import com.sie.iot.common.model.inter.IBaseCommon;
import com.siefw.hibernate.core.paging.Pagination;
import com.sie.iot.common.iotenum.StatusCodeEnum;

import com.sie.iot.demo.bean.DemoUserBean;

import com.sie.iot.demo.model.entities.DemoUserEntity_HI;

@RestController
@RequestMapping("/demoUserController")
public class DemoUserController extends CommonAbstractService{
	private static final Logger LOGGER = LoggerFactory.getLogger(DemoUserController.class);

	@Autowired
	private IDemoUser demoUserServer;

	@Override
	public IBaseCommon getBaseCommonServer(){
		return this.demoUserServer;
	}

	/**
	 * 根据Id查询数据
	 * @param id 参数id
	 * {
	 *     id:主键Id
	 * }
	 * @return
	 * @author AutoGenerate
	 * @creteTime 2021/01/13
	 */
	@GetMapping(value = "/get-Id")
	public ResponseData findById(Long id) {
		UserSessionBean userSessionBean = this.getUserSessionBean();
		AuthorizationCommonUtils.validatorTokenInfo(userSessionBean);
		try {
		    DemoUserEntity_HI demoUserEntity_HI = demoUserServer.findById(id);
			return new ResponseData(ResponseMsgCode.SUCCESS.msgCode, demoUserEntity_HI, redisTemplate);
		} catch (Exception e) {
		    LOGGER.error(e.getMessage());
		    return new ResponseData(StatusCodeEnum.ERROR_CODE.getStatusCode(), "操作失败：" + e.getMessage());
		}
	}

	@PostMapping(value="/find-pagination")
	public ResponseData findByPage(@RequestBody PaginationRequestData<DemoUserBean> paginationRequestData){
	    UserSessionBean userSessionBean = this.getUserSessionBean();
	   AuthorizationCommonUtils.validatorTokenInfo(userSessionBean);
	   Integer pageIndex = paginationRequestData.getPageIndex();
	   Integer pageRows = paginationRequestData.getPageRows();
	   DemoUserBean demoUserBean = paginationRequestData.getParams();
	    JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(demoUserBean));
	    try {
	        Pagination<DemoUserEntity_HI> pagination = demoUserServer.findPagination(jsonObject, pageIndex, pageRows, paginationRequestData.getOrderByBean());
	        return new ResponseData(StatusCodeEnum.SUCCESS_CODE.getStatusCode(),pagination," 分页查询成功");
	    } catch (Exception e) {
	        LOGGER.error(" find - pagination error:"+e);
	        return new ResponseData(StatusCodeEnum.ERROR_CODE.getStatusCode(),"分页查询出错:" + e.getMessage());
	    }
	}
@PostMapping(value = "/save")
public ResponseData save(@RequestBody RequestData<DemoUserBean> requestData){
    UserSessionBean userSessionBean = this.getUserSessionBean();
    AuthorizationCommonUtils.validatorTokenInfo(userSessionBean);
    DemoUserBean demoUserBean = requestData.getParams();
    DemoUserEntity_HI demoUserEntity_HI = JSONObject.parseObject(JSONObject.toJSONString(demoUserBean),DemoUserEntity_HI.class);
    //固定
    demoUserEntity_HI.setOperatorUserId(userSessionBean.getUserId());
    try {
        demoUserServer.save(demoUserEntity_HI);
        return new ResponseData(StatusCodeEnum.SUCCESS_CODE.getStatusCode(), demoUserEntity_HI, " 保存成功");
    }catch (Exception e){
        return new ResponseData(StatusCodeEnum.ERROR_CODE.getStatusCode(), " 保存失败【" + e.getMessage() + "】");
    }
}
@GetMapping(value = "/delete-id")
public ResponseData deleteById(@RequestParam Long id){
    UserSessionBean userSessionBean = this.getUserSessionBean();
    AuthorizationCommonUtils.validatorTokenInfo(userSessionBean);
    try{
       demoUserServer.deleteById(id);
        return new ResponseData(StatusCodeEnum.SUCCESS_CODE.getStatusCode(), "删除成功");
    }catch(Exception e){
        return new ResponseData(StatusCodeEnum.ERROR_CODE.getStatusCode(), "删除失败，" + e.getMessage());
    }
}
@PostMapping(value="/update")
public ResponseData update(@RequestBody RequestData<DemoUserBean> requestData){
    UserSessionBean userSessionBean = this.getUserSessionBean();
    AuthorizationCommonUtils.validatorTokenInfo(userSessionBean);
    DemoUserBean demoUserBean = requestData.getParams();
    Map<String, Object> requestMap = demoUserBean.getRequestData();
    Long id = requestMap.get(" id") != null ? Long.parseLong(requestMap.get(" id") + "") : null;
    DemoUserEntity_HI demoUserEntity_HI = demoUserServer.getById(id);
    if(null != demoUserEntity_HI){
    //固定
    demoUserEntity_HI.setOperatorUserId(userSessionBean.getUserId());
    }else{
        return new ResponseData(StatusCodeEnum.ERROR_CODE.getStatusCode(), " 您所更新的数据不存在");
    }
    try{
        demoUserServer.update(demoUserEntity_HI);
        return new ResponseData(StatusCodeEnum.SUCCESS_CODE.getStatusCode(), demoUserEntity_HI," 更新成功");
    }catch (Exception e){
        return new ResponseData(StatusCodeEnum.ERROR_CODE.getStatusCode(), " 保存失败【" + e.getMessage() + "】");
    }
}
}