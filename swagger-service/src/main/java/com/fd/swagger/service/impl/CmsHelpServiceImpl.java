package com.fd.swagger.service.impl;

import com.fd.swagger.mapper.CmsHelpMapper;
import com.fd.swagger.model.common.PagedResult;
import com.fd.swagger.model.dto.CmsHelpAddDto;
import com.fd.swagger.model.dto.CmsHelpDto;
import com.fd.swagger.model.dto.CmsHelpUpdateDto;
import com.fd.swagger.model.entity.CmsHelp;
import com.fd.swagger.model.vo.CmsHelpVo;
import com.fd.swagger.service.CmsHelpService;
import com.fd.swagger.util.BeanConvertUtils;
import com.fd.swagger.util.ObjectFastjsonConverter;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
@Service
public class CmsHelpServiceImpl implements CmsHelpService {
    @Autowired
    private CmsHelpMapper baseMapper;

    @Override
    public PagedResult<CmsHelpVo> queryPage(CmsHelpDto dto){
        PageHelper.startPage(dto.getPageNo(),dto.getPageSize());
        CmsHelp queryObj = ObjectFastjsonConverter.doObjToObj(dto, CmsHelp.class);
        List<CmsHelpVo> page = baseMapper.findList(queryObj);
        PagedResult<CmsHelpVo> pagedResult = BeanConvertUtils.toPagedResult(page);
        return pagedResult;
    }
    @Override
    public List<CmsHelpVo> findList(CmsHelpDto dto){
        CmsHelp queryObj = ObjectFastjsonConverter.doObjToObj(dto, CmsHelp.class);
        List<CmsHelpVo> list = baseMapper.findList(queryObj);
        return list;
    }
    @Override
    public CmsHelpVo findById(Long id){
        List<CmsHelpVo> byIds = baseMapper.findByIds(Arrays.asList(id));
        if (byIds!=null&&!byIds.isEmpty()){
            return byIds.get(0);
        }
        return null;
    }
    @Override
    public Long insert(CmsHelpAddDto dto){
        CmsHelp t = ObjectFastjsonConverter.doObjToObj(dto, CmsHelp.class);
        baseMapper.insert(t);
        return t.getId();
    }
    @Override
    public int update(CmsHelpUpdateDto dto){
        CmsHelp t = ObjectFastjsonConverter.doObjToObj(dto, CmsHelp.class);
        baseMapper.update(t);
        return 1;
    }
    @Override
    public int deleteByIds(List<Long> ids){
        return baseMapper.deleteByIds(ids);
    }
}
