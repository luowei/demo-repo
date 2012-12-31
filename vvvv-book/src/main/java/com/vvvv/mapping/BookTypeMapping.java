package com.vvvv.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.vvvv.entity.BookType;
import com.vvvv.entity.EntityMapping;

public class BookTypeMapping implements EntityMapping
{
    @Override
    public BookType mapping(ResultSet rs)
    {
        try
        {
            BookType bookType=new BookType(
                    rs.getInt("bookTypeId"),
                    rs.getInt("parentId"),
                    rs.getString("bookTypeName"),
                    rs.getString("context"),
                    rs.getInt("isDelete"));
			/*
			 //上面的效果和以下一样
			 BookType bookType=new BookType();
			 bookType.setBookTypeId(rs.getInt("bookTypeId"));
			 bookType.setParentId(rs.getInt("parentId"));
			 bookType.setBookTypeName(rs.getString("bookTypeName"));
			 bookType.setContext(rs.getString("context"));
			 bookType.setIsdelete(rs.getInt("isdelete"));
			 */
            return bookType;
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
