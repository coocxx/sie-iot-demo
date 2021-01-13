package com.sie.iot.demo.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.sie.iot.demo.bean.DemoStaffBean;
import com.sie.iot.demo.model.entities.DemoDeptEntity_HI;
import com.sie.iot.demo.model.inter.IDemoDept;

import com.alibaba.fastjson.JSONObject;
import com.sie.iot.demo.model.inter.IDemoStaff;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import com.sie.iot.common.bean.ResponseData;
import org.springframework.web.bind.annotation.*;
import com.sie.iot.common.bean.PaginationRequestData;
import com.sie.iot.common.bean.RequestData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import com.sie.iot.common.services.CommonAbstractService;
import com.sie.iot.common.model.inter.IBaseCommon;
import com.siefw.hibernate.core.paging.Pagination;
import com.sie.iot.common.iotenum.StatusCodeEnum;

import com.sie.iot.demo.bean.DemoDeptBean;

import com.sie.iot.demo.model.entities.DemoStaffEntity_HI;

public class DemoStaffController extends CommonAbstractService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DemoStaffController.class);

    @Autowired
    private IDemoStaff demoStaffServer;//IBaseCommon<DemoDeptEntity_HI>

    @Override
    public IBaseCommon getBaseCommonServer(){
        return this.demoStaffServer;
    }

    /**
     * 通过部门ID获取部门
     * @param id
     * @return
     */
    @ApiOperation(value = "通过使用者ID获取部门",notes = "通过使用者ID获取部门 ",httpMethod ="GET" )
    @GetMapping(value = "/get-Id")
    public ResponseData findById(Long id) {
//		UserSessionBean userSessionBean = this.getUserSessionBean();
//		AuthorizationCommonUtils.validatorTokenInfo(userSessionBean);
        try {
            DemoStaffEntity_HI demoStaffEntity_HI = demoStaffServer.findById(id);
            return new ResponseData(StatusCodeEnum.SUCCESS_CODE.getStatusCode(), demoStaffEntity_HI, "查询成功");//800
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return new ResponseData(StatusCodeEnum.ERROR_CODE.getStatusCode(), "操作失败：" + e.getMessage());
        }
    }

    @ApiOperation(value = "获取使用者列表",notes = "获取使用者列表 ",httpMethod ="POST" )
    @PostMapping(value="/find-pagination")
    public ResponseData findByPage(@RequestBody PaginationRequestData<DemoStaffBean> paginationRequestData){
//	    UserSessionBean userSessionBean = this.getUserSessionBean();
//	   AuthorizationCommonUtils.validatorTokenInfo(userSessionBean);
        Integer pageIndex = paginationRequestData.getPageIndex();
        Integer pageRows = paginationRequestData.getPageRows();
        DemoStaffBean demoStaffBean = paginationRequestData.getParams();
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(demoStaffBean));
        try {
            Pagination<DemoStaffEntity_HI> pagination = demoStaffServer.findPagination(jsonObject, pageIndex, pageRows, paginationRequestData.getOrderByBean());
            return new ResponseData(StatusCodeEnum.SUCCESS_CODE.getStatusCode(),pagination," 分页查询成功");
        } catch (Exception e) {
            LOGGER.error(" find - pagination error:"+e);
            return new ResponseData(StatusCodeEnum.ERROR_CODE.getStatusCode(),"分页查询出错:" + e.getMessage());
        }
    }


    @ApiOperation(value = "保存",notes = "保存 ",httpMethod ="POST" )
    @PostMapping(value = "/save")
    public ResponseData save(@RequestBody RequestData<DemoStaffBean> requestData){
//		UserSessionBean userSessionBean = this.getUserSessionBean();
//		AuthorizationCommonUtils.validatorTokenInfo(userSessionBean);
        DemoStaffBean demoStaffBean = requestData.getParams();
        //校验
        if(StrUtil.isNotBlank(demoStaffBean.getUserName())){
            //判断部门名称是否重复
            if(demoStaffServer.checkUserName(demoStaffBean.getUserName())){
                return new ResponseData(StatusCodeEnum.ERROR_CODE.getStatusCode(), "部门名称已经存在");
            }
        }else{
            return new ResponseData(StatusCodeEnum.ERROR_CODE.getStatusCode(), "部门名称不能为空");
        }
        DemoStaffEntity_HI demoStaffEntity_HI = JSONObject.parseObject(JSONObject.toJSONString(demoStaffBean),DemoStaffEntity_HI.class);
        //固定
        demoStaffEntity_HI.setOperatorUserId(1L);
        try {
            demoStaffServer.save(demoStaffEntity_HI);
            return new ResponseData(StatusCodeEnum.SUCCESS_CODE.getStatusCode(), demoStaffEntity_HI, " 保存成功");
        }catch (Exception e){
            return new ResponseData(StatusCodeEnum.ERROR_CODE.getStatusCode(), " 保存失败【" + e.getMessage() + "】");
        }
    }

    @ApiOperation(value = "删除",notes = "删除 ",httpMethod ="GET" )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "使用者ID", paramType = "query", dataType = "long",required = true),
    })
    @GetMapping(value = "/delete-id")
    public ResponseData deleteById(@RequestParam Long id){
//		UserSessionBean userSessionBean = this.getUserSessionBean();
//		AuthorizationCommonUtils.validatorTokenInfo(userSessionBean);
        try{
            demoStaffServer.deleteById(id);
            return new ResponseData(StatusCodeEnum.SUCCESS_CODE.getStatusCode(), "删除成功");
        }catch(Exception e){
            return new ResponseData(StatusCodeEnum.ERROR_CODE.getStatusCode(), "删除失败，" + e.getMessage());
        }
    }

    @ApiOperation(value = "更新",notes = "更新 ",httpMethod ="POST" )
    @PostMapping(value="/update")
    public ResponseData update(@RequestBody RequestData<DemoStaffBean> requestData){
//		UserSessionBean userSessionBean = this.getUserSessionBean();
//		AuthorizationCommonUtils.validatorTokenInfo(userSessionBean);
        DemoStaffBean demoStaffBean = requestData.getParams();
        DemoStaffEntity_HI demoStaffEntity_HI = demoStaffServer.getById(demoStaffBean.getUserId());
        if(null != demoStaffEntity_HI){
            //判断部门名称是否重复 TODO

            BeanUtil.copyProperties(demoStaffBean,demoStaffEntity_HI,"Code");
            //固定
            demoStaffEntity_HI.setOperatorUserId(1L);
        }else{
            return new ResponseData(StatusCodeEnum.ERROR_CODE.getStatusCode(), " 您所更新的数据不存在");
        }
        try{
            demoStaffServer.update(demoStaffEntity_HI);
            return new ResponseData(StatusCodeEnum.SUCCESS_CODE.getStatusCode(), demoStaffEntity_HI," 更新成功");
        }catch (Exception e){
            return new ResponseData(StatusCodeEnum.ERROR_CODE.getStatusCode(), " 保存失败【" + e.getMessage() + "】");
        }
    }
}