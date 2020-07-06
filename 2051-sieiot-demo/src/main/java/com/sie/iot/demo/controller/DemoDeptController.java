package com.sie.iot.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.sie.iot.demo.model.inter.IDemoDept;

import com.alibaba.fastjson.JSONObject;
import com.siefw.base.utils.SToolUtils;
import org.apache.commons.lang.StringUtils;
import com.sie.iot.common.bean.ResponseData;
import com.sie.iot.common.bean.ResponseData;
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

import com.sie.iot.demo.bean.DemoDeptBean;

import com.sie.iot.demo.model.entities.DemoDeptEntity_HI;

@RestController
@RequestMapping("/demo-dept")
public class DemoDeptController extends CommonAbstractService{
	private static final Logger LOGGER = LoggerFactory.getLogger(DemoDeptController.class);

	@Autowired
	private IDemoDept demoDeptServer;

	@Override
	public IBaseCommon getBaseCommonServer(){
		return this.demoDeptServer;
	}

	/**
	 * 通过部门ID获取部门
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/get-Id")
	public ResponseData findById(Long id) {
//		UserSessionBean userSessionBean = this.getUserSessionBean();
//		AuthorizationCommonUtils.validatorTokenInfo(userSessionBean);
		try {
		    DemoDeptEntity_HI demoDeptEntity_HI = demoDeptServer.findById(id);
		    return new ResponseData(StatusCodeEnum.SUCCESS_CODE.getStatusCode(), demoDeptEntity_HI, "查询成功");
		} catch (Exception e) {
		    LOGGER.error(e.getMessage());
		    return new ResponseData(StatusCodeEnum.ERROR_CODE.getStatusCode(), "操作失败：" + e.getMessage());
		}
	}

	@PostMapping(value="/find-pagination")
	public ResponseData findByPage(@RequestBody PaginationRequestData<DemoDeptBean> paginationRequestData){
//	    UserSessionBean userSessionBean = this.getUserSessionBean();
//	   AuthorizationCommonUtils.validatorTokenInfo(userSessionBean);
		Integer pageIndex = paginationRequestData.getPageIndex();
		Integer pageRows = paginationRequestData.getPageRows();
		DemoDeptBean demoDeptBean = paginationRequestData.getParams();
	    JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(demoDeptBean));
	    try {
	        Pagination<DemoDeptEntity_HI> pagination = demoDeptServer.findPagination(jsonObject, pageIndex, pageRows, paginationRequestData.getOrderByBean());
	        return new ResponseData(StatusCodeEnum.SUCCESS_CODE.getStatusCode(),pagination," 分页查询成功");
	    } catch (Exception e) {
	        LOGGER.error(" find - pagination error:"+e);
	        return new ResponseData(StatusCodeEnum.ERROR_CODE.getStatusCode(),"分页查询出错:" + e.getMessage());
	    }
	}

	@PostMapping(value = "/save")
	public ResponseData save(@RequestBody RequestData<DemoDeptBean> requestData){
//		UserSessionBean userSessionBean = this.getUserSessionBean();
//		AuthorizationCommonUtils.validatorTokenInfo(userSessionBean);
	    DemoDeptBean demoDeptBean = requestData.getParams();
	    //校验
		if(StrUtil.isNotBlank(demoDeptBean.getDeptName())){
			//判断部门名称是否重复
			if(demoDeptServer.checkDeptName(demoDeptBean.getDeptName())){
				return new ResponseData(StatusCodeEnum.ERROR_CODE.getStatusCode(), "部门名称已经存在");
			}
		}else{
			return new ResponseData(StatusCodeEnum.ERROR_CODE.getStatusCode(), "部门名称不能为空");
		}
	    DemoDeptEntity_HI demoDeptEntity_HI = JSONObject.parseObject(JSONObject.toJSONString(demoDeptBean),DemoDeptEntity_HI.class);
	    //固定
	    demoDeptEntity_HI.setOperatorUserId(1L);
	    try {
	        demoDeptServer.save(demoDeptEntity_HI);
	        return new ResponseData(StatusCodeEnum.SUCCESS_CODE.getStatusCode(), demoDeptEntity_HI, " 保存成功");
	    }catch (Exception e){
	        return new ResponseData(StatusCodeEnum.ERROR_CODE.getStatusCode(), " 保存失败【" + e.getMessage() + "】");
	    }
	}

	@GetMapping(value = "/delete-id")
	public ResponseData deleteById(@RequestParam Long id){
//		UserSessionBean userSessionBean = this.getUserSessionBean();
//		AuthorizationCommonUtils.validatorTokenInfo(userSessionBean);
	    try{
	       demoDeptServer.deleteById(id);
	        return new ResponseData(StatusCodeEnum.SUCCESS_CODE.getStatusCode(), "删除成功");
	    }catch(Exception e){
	        return new ResponseData(StatusCodeEnum.ERROR_CODE.getStatusCode(), "删除失败，" + e.getMessage());
	    }
	}

	@PostMapping(value="/update")
	public ResponseData update(@RequestBody RequestData<DemoDeptBean> requestData){
//		UserSessionBean userSessionBean = this.getUserSessionBean();
//		AuthorizationCommonUtils.validatorTokenInfo(userSessionBean);
	    DemoDeptBean demoDeptBean = requestData.getParams();
	    DemoDeptEntity_HI demoDeptEntity_HI = demoDeptServer.getById(demoDeptBean.getDeptId());
	    if(null != demoDeptEntity_HI){
	    	//判断部门名称是否重复 TODO

	    	//固定
			demoDeptEntity_HI.setOperatorUserId(1L);
	    }else{
	        return new ResponseData(StatusCodeEnum.ERROR_CODE.getStatusCode(), " 您所更新的数据不存在");
	    }
	    try{
	        demoDeptServer.update(demoDeptEntity_HI);
	        return new ResponseData(StatusCodeEnum.SUCCESS_CODE.getStatusCode(), demoDeptEntity_HI," 更新成功");
	    }catch (Exception e){
	        return new ResponseData(StatusCodeEnum.ERROR_CODE.getStatusCode(), " 保存失败【" + e.getMessage() + "】");
	    }
	}
}