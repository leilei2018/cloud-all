package com.fd.swagger.model.dto;

import com.fd.swagger.model.common.PageParamDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("cms帮助列表查询dto")
public class CmsHelpDto extends PageParamDTO {
    @ApiModelProperty(value = "标题",position = 4)
    private String title;
    @ApiModelProperty(value="开始日期",example = "2020-01-01")
    private Date startTime;
    @ApiModelProperty(value="结束日期",example = "2020-12-31")
    private Date endTime;
}
