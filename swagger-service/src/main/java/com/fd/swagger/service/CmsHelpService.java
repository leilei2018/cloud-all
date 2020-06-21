package com.fd.swagger.service;

import com.fd.swagger.model.common.PagedResult;
import com.fd.swagger.model.dto.CmsHelpAddDto;
import com.fd.swagger.model.dto.CmsHelpDto;
import com.fd.swagger.model.dto.CmsHelpUpdateDto;
import com.fd.swagger.model.vo.CmsHelpVo;

import java.util.List;

public interface CmsHelpService {

    PagedResult<CmsHelpVo> queryPage(CmsHelpDto dto);

    List<CmsHelpVo> findList(CmsHelpDto dto);

    CmsHelpVo findById(Long id);

    Long insert(CmsHelpAddDto t);

    int update(CmsHelpUpdateDto t);

    int deleteByIds(List<Long> ids);
}
