package com.vvvv.dao;

import java.sql.SQLException;
import java.util.List;

import com.vvvv.db.JdbcTemplate;
import com.vvvv.entity.BookType;
import com.vvvv.mapping.BookTypeMapping;

public class BookTypeDAO
{
	private JdbcTemplate jdbcTemplate=new JdbcTemplate();
	/**
	 * ���Id�����������
	 * @param Id
	 * @return
	 */
	public BookType findById(int Id)
	{
		String sql="select bookTypeId,parentId,bookTypeName,context,isDelete from booktype where bookTypeId=?";
		//List<BookType> bookTypes=(List<BookType>)(Object)jdbcTemplate.query(sql, new BookTypeMapping());
		BookType bookType=null;
		try
		{
			bookType = (BookType)jdbcTemplate.query(sql, new BookTypeMapping(),Id).get(0);
			return bookType;
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return bookType;
	}
	
	/**
	 * �޸����
	 * @param bookType
	 */
	public boolean update(BookType bookType)
	{
		String sql="update bookType set parentId=?,bookTypeName=?,isDelete=?,context=? where bookTypeId=?";
		//Object[] values=new Object[]{bookType.getParentId(),bookType.getBookTypeName(),bookType.getisDelete(),bookType.getContext(),bookType.getBookTypeId()};
		int rows=0;
		try
		{
			//rows=jdbcTemplate.update(sql, values);
			rows=jdbcTemplate.update(sql, bookType.getParentId(),
					bookType.getBookTypeName(),bookType.getisDelete(),
					bookType.getContext(),bookType.getBookTypeId());
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (rows==1);
	}
	/**
	 * �����������
	 * @param bookType
	 * @return
	 */
	public boolean save(BookType bookType)
	{
		String sql="insert into booktype(parentId,bookTypeName,context) values(?,?,?)";
		//Object[] values=new Object[]{bookType.getParentId(),bookType.getBookTypeName(),bookType.getContext()};
		int rows=0;
		try
		{
			//rows=jdbcTemplate.update(sql, values);
			rows=jdbcTemplate.update(sql, bookType.getParentId(),bookType.getBookTypeName(),bookType.getContext());
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (rows==1);
	}
	
	/**
	 * �޸���ݵ�isDelete״̬,�߼�ɾ��
	 * @param id
	 * @throws java.sql.SQLException
	 */
	public boolean updateisDelete(int id)
	{
		String sql="update booktype set isDelete=2 where  bookTypeId="+id;
		//jdbcTemplate.update(sql, null);
		try
		{
			return (jdbcTemplate.update(sql)==1);
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * ɾ�����
	 * @param id
	 */
	public boolean delete(int id)
	{
		String sql="delete from booktype where  bookTypeId="+id;
		//jdbcTemplate.update(sql, null);
		try
		{
			return (jdbcTemplate.update(sql)==1);
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	//public List<BookType> findAll(int isDelete) 
	public List<BookType> findAll(int... id)
	{
		//String sql="select bookTypeId,parentId,bookTypeName,context,isDelete from booktype where isDelete="+isDelete;
		String sql1="select bookTypeId, parentId, bookTypeName, context,isDelete from  bookType";
		StringBuffer sql=new StringBuffer(sql1);
		//System.out.println(id[0]);
		if(id.length==0)
		{
			id=new int[]{0};
			//id=new int[1];
			//id[0]=0;
			//System.out.println(id[0]);
		}
		switch(id[0])
		{
		case 0:
			break;
		case 255:
			sql.append(" where parentId=0");
			break;
		default:
			sql.append(" where parentId="+id[0]);
			break;
		}
		//System.out.println(sql);
		//List<BookType> bookTypes=(List<BookType>)(Object)jdbcTemplate.query(sql, new BookTypeMapping());
		List<BookType> bookTypes=null;
		try
		{
			bookTypes = (List<BookType>)jdbcTemplate.query(sql.toString(), new BookTypeMapping());
			return bookTypes;
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	
}
