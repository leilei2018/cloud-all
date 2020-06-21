package com.fd.swagger.mapper;

import com.fd.swagger.model.entity.CmsHelp;
import com.fd.swagger.model.vo.CmsHelpVo;

import java.util.List;

public interface CmsHelpMapper {

    Long insert(CmsHelp t);

    int update(CmsHelp t);

    List<CmsHelpVo> findList(CmsHelp t);

    List<CmsHelpVo> findByIds(List<Long> ids);

    int deleteByIds(List<Long> ids);
}
