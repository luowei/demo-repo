package com.vvvv.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.vvvv.db.JdbcTemplate;
import com.vvvv.entity.BookInfo;
import com.vvvv.mapping.BookInfoMapping;

public class BookInfoDAO
{
	private JdbcTemplate jdbcTemplate=new JdbcTemplate();
	public BookInfo findById(int Id)
	{
		String sql="select bookId,bookName,booktypeId,pbName,author," +
				"context,smallImg,bigImg,price,pbdate,bookStatus,vvvvprice" +
				" from bookInfo where bookId=?";
		//List<BookInfo> BookInfos=(List<BookInfo>)(Object)jdbcTemplate.query(sql, new BookInfoMapping());
		BookInfo bookInfo=null;
		try
		{
			bookInfo = (BookInfo)jdbcTemplate.query(sql, new BookInfoMapping(),Id).get(0);
			return bookInfo;
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return bookInfo;
		
	}
	
	/**
	 * �޸����
	 * @param bookInfo
	 */
	public boolean update(BookInfo bookInfo)
	{
		String sql="update bookInfo set bookName=?,booktypeId=?,pbName=?,author=?," +
				"context=?,smallImg=?,bigImg=?,price=?,pbdate=?,bookStatus=?,vvvvprice=?" +
				" where bookId=?";
		//Object[] values=new Object[]{bookInfo.getBooktypeId(),bookInfo.getBookInfoName(),bookInfo.getisDelete(),bookInfo.getContext(),bookInfo.getBookInfoId()};
		int rows=0;
		try
		{
			//rows=jdbcTemplate.update(sql, values);
			//System.out.println(bookInfo.getPbdate());
			rows=jdbcTemplate.update(sql, bookInfo.getBookName(),bookInfo.getBooktypeId(),
					bookInfo.getPbName(),bookInfo.getAuthor(),bookInfo.getContext(),
					bookInfo.getSmallImg(),bookInfo.getBigImg(),bookInfo.getPrice(),
					bookInfo.getPbdate(),bookInfo.getBookStatus(),bookInfo.getVvvvprice(),
					bookInfo.getBookId()); //ע��������˳��һ�£���Ҫд��
			
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return (rows==1);
	}
	/**
	 * �����������
	 * @param bookInfo
	 * @return
	 */
	public boolean save(BookInfo bookInfo)
	{
		String sql="insert into bookInfo(bookName,booktypeId,pbName,author,context,smallImg," +
				"bigImg,price,pbdate,bookStatus,vvvvprice) values(?,?,?,?,?,?,?,?,?,?,?)";
		int rows=0;
		try
		{
			rows=jdbcTemplate.update(sql, bookInfo.getBookName(),bookInfo.getBooktypeId(),bookInfo.getPbName()
					,bookInfo.getAuthor(),bookInfo.getContext(),bookInfo.getSmallImg(),bookInfo.getBigImg()
					,bookInfo.getPrice(),bookInfo.getPbdate(),bookInfo.getBookStatus(),bookInfo.getVvvvprice());
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return (rows==1);
	}
	
	/**
	 * �޸���ݵ�bookStatus״̬,1���� 2�ϼ�  3������
	 * @param id
	 * @param bookStatus
	 * @throws java.sql.SQLException
	 */
	public boolean updateIsDelete(int id,int bookStatus)
	{
		String sql="update bookInfo set bookStatus=? where  bookId=?";
		//jdbcTemplate.update(sql, null);
		try
		{
			return (jdbcTemplate.update(sql,bookStatus,id)==1);
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * ɾ���¼
	 * @param id
	 */
	public boolean delete(int id)
	{
		String sql="delete from bookInfo where  bookId="+id;
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
	 * ����ҳ��
	 * @param pageSize	ÿҳ������
	 * @param status	״̬
	 * @return
	 */
	public int getPageCount(int pageSize,int status)
	{
		int pageCount=0;
		String sql1="select count(*) from bookInfo " +
		"where bookStatus ";
		//List<BookInfo> bookInfos=(List<BookInfo>)(Object)jdbcTemplate.query(sql, new BookInfoMapping());
		StringBuffer sql=null;
		sql=new StringBuffer(sql1);
		switch(status)
		{
		/*
		case 3:		//�����õ�
			sql.append(" <> 3");
			break;
			*/
		case 4:		//���е�
			sql.append(" <> 4");
			break;
		default:
			sql.append("="+status);
			break;
		}
		try
		{
			int rowCount=jdbcTemplate.query(sql.toString());
			pageCount=rowCount/pageSize;
			if(rowCount%pageSize!=0)
			{
				pageCount++;
			}
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pageCount;
	}
	/**
	 * ��ȡ��ǰҳ�����
	 * @param nowPage
	 * @param pageSize
	 * @param bookStatus
	 * @param bookTypeId
	 * @return
	 */
	public List<BookInfo> getNowPageData(int nowPage,int pageSize,int bookStatus,int... bookTypeId) 
	{
		List<BookInfo> bookInfos=null;
		String sql1=null;
		StringBuffer sql=null;
		if(bookTypeId.length!=0)	//��ѯĳһ�����,��ͼ������е�����ʹ��
		{
			sql1="select " +
					"bookId,bookName,booktypeId,pbName,author,context,smallImg," +
					"bigImg,price,pbdate,bookStatus,vvvvprice from bookInfo " +
					"where booktypeId=";
			sql=new StringBuffer(sql1).append(bookTypeId[0]);
		}
		else		//��ѯĳһ��״̬����
		{
			StringBuffer where=new StringBuffer("where bookStatus");
			switch(bookStatus)
			{
			case 4:		//���е�
				where.append(" <> 4");
				break;
			default:	//����
				where.append("="+bookStatus);
				break;
			}
			sql1="select top " +pageSize+
					" bookId,bookName,booktypeId,pbName,author,context,smallImg," +
					"bigImg,price,pbdate,bookStatus,vvvvprice from bookInfo " +where+
					"and bookId not in(" +
					"select top "+pageSize*(nowPage-1)+" bookId from bookInfo " +where+" )";
			sql=new StringBuffer(sql1);
			//List<BookInfo> bookInfos=(List<BookInfo>)(Object)jdbcTemplate.query(sql, new BookInfoMapping());
		}
		try
		{
			bookInfos = (List<BookInfo>)jdbcTemplate.query(sql.toString(), new BookInfoMapping());
			return bookInfos;
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * status 1,2,3,4 �ֱ�� δ�ϼ�,���ϼ�,������,���е�
	 * @param bookStatus	���״̬
	 * @param bookTypeId
	 * @return
	 */
	public List<BookInfo> findAll(int bookStatus,int... bookTypeId) 
	{
		List<BookInfo> bookInfos=null;
		StringBuffer sql=null;
		String sql1=null;
		if(bookTypeId.length!=0)	//��ѯĳһ�����
		{
			sql1="select bookId,bookName,booktypeId,pbName,author,context,smallImg," +
			"bigImg,price,pbdate,bookStatus,vvvvprice from bookInfo where booktypeId= " +
			bookTypeId[0]+" and bookStatus ";
		}
		else		//��ѯĳһ��״̬����
		{
			sql1="select bookId,bookName,booktypeId,pbName,author,context,smallImg," +
					"bigImg,price,pbdate,bookStatus,vvvvprice from bookInfo " +
					"where bookStatus ";
			//List<BookInfo> bookInfos=(List<BookInfo>)(Object)jdbcTemplate.query(sql, new BookInfoMapping());
		}
		sql=new StringBuffer(sql1);
		switch(bookStatus)
		{
		/*
		case 3:		//�����õ�
			sql.append(" <> 3");
			break;
			*/
		case 4:		//���е�
			sql.append(" <> 4");
			break;
		default:
			sql.append("="+bookStatus);
			break;
		}
		
		try
		{
			bookInfos = (List<BookInfo>)jdbcTemplate.query(sql.toString(), new BookInfoMapping());
			return bookInfos;
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
}