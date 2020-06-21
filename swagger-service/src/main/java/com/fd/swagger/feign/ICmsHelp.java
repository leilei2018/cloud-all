package com.fd.swagger.feign;

import com.fd.swagger.model.common.BaseResult;
import com.fd.swagger.model.dto.CmsHelpAddDto;
import com.fd.swagger.model.dto.CmsHelpDto;
import com.fd.swagger.model.dto.CmsHelpUpdateDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "swagger-service", path = "/cmsHelp2",configuration = {})
public interface ICmsHelp {
    /**
     * 列表查询
     *
     * @param dto
     * @return
     */
    @PostMapping("/list")
    public BaseResult list(@Validated @RequestBody CmsHelpDto dto);

    /**
     * 新增
     *
     * @param dto
     * @return
     */
    @PostMapping("/insert")
    public BaseResult insert(@Validated @RequestBody CmsHelpAddDto dto);

    /**
     * 根据ID查询详情信息
     *
     * @return
     */
    @GetMapping("/info/{id}")
    public BaseResult findById(@PathVariable("id") Long id);

    /**
     * 修改更新
     *
     * @param dto
     * @return
     */
    @PostMapping("/update")
    public BaseResult update(@Validated @RequestBody CmsHelpUpdateDto dto);

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @PostMapping("/delete")
    public BaseResult delete(@RequestBody List<Long> ids);
}
