package com.fd.swagger.feign.controller;

import java.util.List;

import com.fd.swagger.feign.ICmsHelp;
import com.fd.swagger.model.common.BaseResult;
import com.fd.swagger.model.dto.CmsHelpAddDto;
import com.fd.swagger.model.dto.CmsHelpDto;
import com.fd.swagger.model.dto.CmsHelpUpdateDto;
import com.fd.swagger.service.CmsHelpService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


/**
 * 帮助 信息操作处理
 * 
 * @author async
 * @date 2020-06-21
 */
@RestController
@Api(tags="帮助管理V2")
@RequestMapping("/cmsHelp2")
public class IFeignCmsHelpController implements ICmsHelp {

	@Autowired
	private CmsHelpService service;

	@RequiresPermissions("model:cmsHelp:list")
	@ApiOperation("分页列表查询")
	@Override
	public BaseResult list( CmsHelpDto dto) {
		return BaseResult.success(service.queryPage(dto));
	}

	@RequiresPermissions("model:cmsHelp:insert")
	@ApiOperation("新增帮助")
	@Override
	public BaseResult insert( CmsHelpAddDto dto) {
		return BaseResult.success(service.insert(dto));
	}

	@RequiresPermissions("model:cmsHelp:info")
	@ApiOperation("根据ID查询详情信息")
	@Override
	public BaseResult findById(@PathVariable("id") Long id) {
		return BaseResult.success(service.findById(id));
	}


	@RequiresPermissions("model:cmsHelp:update")
	@ApiOperation("修改帮助")
	@Override
	public BaseResult update( CmsHelpUpdateDto dto) {
		return BaseResult.success(service.update(dto));
	}

	@RequiresPermissions("model:cmsHelp:delete")
	@ApiOperation("根据id删除帮助")
	@Override
	public BaseResult delete(List<Long> ids) {
		return BaseResult.success(service.deleteByIds(ids));
	}
}
