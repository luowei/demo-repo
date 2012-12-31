package com.vvvv.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.vvvv.entity.BookInfo;
import com.vvvv.entity.EntityMapping;

public class BookInfoMapping implements EntityMapping
{

	@Override
	public Object mapping(ResultSet rs)
	{
		BookInfo bookInfo;
		try
		{
			bookInfo = new BookInfo();
			bookInfo.setAuthor(rs.getString("author"));
			bookInfo.setBigImg(rs.getString("bigImg"));
			bookInfo.setBookId(rs.getInt("bookId"));
			bookInfo.setBookName(rs.getString("bookName"));
			bookInfo.setBookStatus(rs.getInt("bookStatus"));
			bookInfo.setBooktypeId(rs.getInt("booktypeId"));
			bookInfo.setContext(rs.getString("context"));
			bookInfo.setPbdate(rs.getDate("pbdate"));
			bookInfo.setPbName(rs.getString("pbName"));
			bookInfo.setPrice(rs.getFloat("price"));
			bookInfo.setSmallImg(rs.getString("smallImg"));
			bookInfo.setVvvvprice(rs.getFloat("vvvvprice"));
			return bookInfo;
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}

}
