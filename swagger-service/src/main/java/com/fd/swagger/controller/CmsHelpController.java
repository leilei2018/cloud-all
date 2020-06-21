package com.fd.swagger.controller;

import com.fd.swagger.feign.ICmsHelp;
import com.fd.swagger.model.common.JsonResult;
import com.fd.swagger.model.dto.CmsHelpAddDto;
import com.fd.swagger.model.dto.CmsHelpDto;
import com.fd.swagger.model.dto.CmsHelpUpdateDto;
import com.fd.swagger.service.CmsHelpService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/cmsHelp")
@Api(tags = "CMS帮助管理")
public class CmsHelpController {
    @Autowired
    private ICmsHelp service;

    @ApiOperation("分页列表查询")
    @PostMapping("/list")
    public JsonResult list(@Validated @RequestBody CmsHelpDto dto) {
        return JsonResult.ok(service.list(dto));
    }

    @ApiOperation("新增帮助")
    @PostMapping("/insert")
    public JsonResult insert(@Validated @RequestBody CmsHelpAddDto dto) {
        return JsonResult.ok(service.insert(dto));
    }

    @ApiOperation("根据ID查询详情信息")
    @GetMapping("/info/{id}")
    public JsonResult findById(@PathVariable("id") Long id) {
        return JsonResult.ok(service.findById(id));
    }


    @ApiOperation("修改帮助")
    @PostMapping("/update")
    public JsonResult update(@Validated @RequestBody CmsHelpUpdateDto dto) {
        return JsonResult.ok(service.update(dto));
    }

    @PostMapping( "/delete")
    @ApiOperation("根据id删除帮助")
    public JsonResult delete(@RequestBody List<Long> ids) {
        return JsonResult.ok(service.delete(ids));
    }
}
