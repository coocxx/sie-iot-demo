package com.sie.iot.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.sie.iot.common.bean.PaginationRequestData;
import com.sie.iot.common.bean.RequestData;
import com.sie.iot.common.bean.ResponseData;
import com.sie.iot.common.iotenum.StatusCodeEnum;
import com.sie.iot.common.model.inter.IBaseCommon;
import com.sie.iot.common.services.CommonAbstractService;
import com.sie.iot.demo.bean.PracUserBean;
import com.sie.iot.demo.model.entities.PracUserEntity_HI;
import com.sie.iot.demo.model.inter.IDemoUser;
import com.siefw.hibernate.core.paging.Pagination;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/demo-user")
@Api(value = "用户管理", tags = {"用户管理"})
public class PracUserController extends CommonAbstractService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PracUserController.class);

    @Autowired
    private IDemoUser pracUserServer;

    @Override
    public IBaseCommon getBaseCommonServer() { return this.pracUserServer; }

    @ApiOperation(value = "通过用户ID获取用户", notes = "通过用户ID获取用户", httpMethod = "GET")
    @GetMapping(value = "/get-id")
    public ResponseData findById(Long user_id) {
        try {
            PracUserEntity_HI pracUserEntity_HI = pracUserServer.findById(user_id);
            return new ResponseData(StatusCodeEnum.SUCCESS_CODE.getStatusCode(), pracUserEntity_HI, "查询成功");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return new ResponseData(StatusCodeEnum.ERROR_CODE.getStatusCode(),"操作失败" + e.getMessage());
        }
    }

    @ApiOperation(value = "获取用户列表", notes = "获取用户列表", httpMethod = "POST" )
    @PostMapping(value="/find-pagination")
    public ResponseData findByPage(@RequestBody PaginationRequestData<PracUserBean> paginationRequestData) {
        Integer pageIndex = paginationRequestData.getPageIndex();
        Integer pageRows = paginationRequestData.getPageRows();
        PracUserBean pracUserBean = paginationRequestData.getParams();
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(pracUserBean));
        try {
            Pagination<PracUserEntity_HI> pagination = pracUserServer.findPagination(jsonObject, pageIndex, pageRows, paginationRequestData.getOrderByBean());
            return new ResponseData(StatusCodeEnum.SUCCESS_CODE.getStatusCode(), pagination,"分页查询成功");
        }   catch (Exception e) {
            LOGGER.error(" find - pagination error:" + e);
            return new ResponseData(StatusCodeEnum.ERROR_CODE.getStatusCode(), "分页查询出错:" + e.getMessage());
        }
    }

    @ApiOperation(value = "保存", notes = "保存", httpMethod = "POST" )
    @PostMapping(value="/save")
    public ResponseData save(@RequestBody RequestData<PracUserBean> requestData){
        PracUserBean pracUserBean = requestData.getParams();
        if(StrUtil.isBlank(pracUserBean.getUserName())) {
//            if(!pracUserServer.checkUserName(pracUserBean.getUserName())){
                //return new ResponseData(StatusCodeEnum.ERROR_CODE.getStatusCode(), "用户名称已存在")
            return new ResponseData(StatusCodeEnum.ERROR_CODE.getStatusCode(), "用户名称不能为空");
            //}
        }

        PracUserEntity_HI pracUserEntity_HI = JSONObject.parseObject(JSONObject.toJSONString(pracUserBean), PracUserEntity_HI.class);
        pracUserEntity_HI.setOperatorUserId(1L);
        try {
            pracUserServer.save(pracUserEntity_HI);
            return new ResponseData(StatusCodeEnum.SUCCESS_CODE.getStatusCode(), pracUserEntity_HI, "保存成功");
        } catch (Exception e) {
            return new ResponseData(StatusCodeEnum.ERROR_CODE.getStatusCode(), "保存失败 【" + e.getMessage() + "】");
        }
    }

    @ApiOperation(value = "删除", notes = "删除", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_id", value = "用户ID", paramType = "query", dataType = "long", required = true),
    })
    @GetMapping(value = "/delete-id")
    public ResponseData deleteById(@RequestParam Long user_id) {
        try{
            pracUserServer.deleteById(user_id);
            return new ResponseData(StatusCodeEnum.SUCCESS_CODE.getStatusCode(), "删除成功");
        } catch (Exception e) {
            return new ResponseData(StatusCodeEnum.ERROR_CODE.getStatusCode(), "删除失败," + e.getMessage());
        }
    }

    @ApiOperation(value = "更新", notes = "更新", httpMethod = "POST")
    @PostMapping(value="/update")
    public ResponseData update(@RequestBody RequestData<PracUserBean> requestData) {
        PracUserBean pracUserBean = requestData.getParams();
        PracUserEntity_HI pracUserEntity_HI = pracUserServer.getById(pracUserBean.getUserId());
        if(pracUserEntity_HI != null) {
            BeanUtils.copyProperties(pracUserBean,pracUserEntity_HI);
            pracUserEntity_HI.setOperatorUserId(1L);
        } else {
            return new ResponseData(StatusCodeEnum.ERROR_CODE.getStatusCode(), "您更新的数据集不存在");
        }
        try {
            pracUserServer.update(pracUserEntity_HI);
            return new ResponseData(StatusCodeEnum.SUCCESS_CODE.getStatusCode(), pracUserEntity_HI, "更新成功");
        } catch (Exception e) {
            return new ResponseData(StatusCodeEnum.ERROR_CODE.getStatusCode(), " 保存失败【" + e.getMessage() + "】");
        }
    }
}
