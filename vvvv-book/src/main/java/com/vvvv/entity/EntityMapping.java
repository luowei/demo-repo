package com.vvvv.entity;

import java.sql.ResultSet;

public interface EntityMapping
{
    /**
     * 把当前行数据转换成一个实体对象
     * @param rs
     * @return 具体实体
     */
    public Object mapping(ResultSet rs);
}
