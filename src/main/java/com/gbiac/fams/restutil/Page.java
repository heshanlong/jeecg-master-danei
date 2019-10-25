package com.gbiac.fams.restutil;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

import com.gbiac.fams.restutil.condition.PageCondition;
import com.google.common.collect.Lists;

/**
 * 分页工具
 *
 * @author 甘桂祥
 * @date 2018/7/9
 */
@Data
public class Page<T> implements Serializable {

    private static final long serialVersionUID = 4542617637761955078L;

    /**
     * 当前页
     */
    private Integer currentPage = 1;
    /**
     * 每页大小
     */
    private Integer pageSize = 10;
    /**
     * 总页数
     */
    private Integer totalPage;
    /**
     * 总条数
     */
    private Long totalElement = 0L;
    /**
     * 前一页
     */
    private Integer previousPage;
    /**
     * 下一页
     */
    private Integer nextPage;
    /**
     * 第一页
     */
    private Integer firstPage = 1;
    /**
     * 最后一页
     */
    private Integer lastPage;
    /**
     * 每页的内容
     */
    private List<T> content = Lists.newArrayList();

    public Page(PageCondition pageCondition, long totalElement) {
        this(pageCondition.getPage(), pageCondition.getSize(), totalElement);
    }

    public Page(int currentPage, int pageSize, long totalElement) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalElement = totalElement;
        // 新建对象时初始化参数
        otherAttr();
    }


    /**
     * 设置其他参数
     */
    private void otherAttr() {
        // 总页数
        this.totalPage = Math.toIntExact(this.totalElement % this.pageSize > 0 ? this.totalElement / this.pageSize + 1 : this.totalElement / this.pageSize);
        // 第一页
        this.firstPage = 1;
        // 最后一页
        this.lastPage = this.totalPage;
        // 前一页
        if (this.currentPage > 1) {
            this.previousPage = this.currentPage - 1;
        } else {
            this.previousPage = this.firstPage;
        }
        // 下一页
        if (this.currentPage < this.lastPage) {
            this.nextPage = this.currentPage + 1;
        } else {
            this.nextPage = this.lastPage;
        }
    }

}