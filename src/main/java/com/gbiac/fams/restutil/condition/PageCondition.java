package com.gbiac.fams.restutil.condition;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jeecgframework.core.common.exception.BusinessException;

import java.io.Serializable;
import java.util.List;

/**
 * @author 甘桂祥
 * @date 2018/7/3
 */
@Data
@ApiModel
public class PageCondition implements Serializable {

    private static final long serialVersionUID = 6597248219973681302L;

    @ApiModelProperty(value = "当前页数")
    private Integer page;

    @ApiModelProperty(value = "每页大小")
    private Integer size;

    @ApiModelProperty(value = "排序，排序字段与排序方式以'-'分割，如：createTime-asc")
    private List<String> sorts;

    public PageCondition() {
    }

    public PageCondition(Integer page, Integer size) {
        if (page <= 0) {
            throw new BusinessException("当前页数必须大于0");
        }
        if (size <= 0) {
            throw new BusinessException("每页数量必须大于0");
        }
        this.page = page;
        this.size = size;
    }

    public PageCondition(Integer page, Integer size, List<String> sorts) {
        this.page = page;
        this.size = size;
        this.sorts = sorts;
    }


}
