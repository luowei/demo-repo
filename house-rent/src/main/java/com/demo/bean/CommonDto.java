package com.demo.bean;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 12-11-9
 * Time: 上午12:00
 * To change this template use File | Settings | File Templates.
 */
public class CommonDto implements Serializable {

    private Integer pageNumber = 1;
    private Integer pageSize = 20;
    private String sort = "id:asc";


    public Integer getPageNumber() {
        return pageNumber;
    }


    public CommonDto setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
        return this;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public CommonDto setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }


    public String getSort() {
        return sort;
    }

    public CommonDto setSort(String sort) {
        this.sort = sort;
        return this;
    }

}
