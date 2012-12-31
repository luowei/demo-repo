package com.vvvv.entity;

public class BookType
{
	private int bookTypeId;
	private int parentId;
	private String bookTypeName;
	private String context;
	private int isDelete;

	public BookType()
	{
	}
	public BookType(int parentId, String bookTypeName,
			String context)
	{
		super();
		this.parentId = parentId;
		this.bookTypeName = bookTypeName;
		this.context = context;
	}
	public BookType(int parentId, String bookTypeName,
			String context, int isDelete)
	{
		super();
		this.parentId = parentId;
		this.bookTypeName = bookTypeName;
		this.context = context;
		this.isDelete = isDelete;
	}
	public BookType(int bookTypeId, int parentId, String bookTypeName,
			String context, int isDelete)
	{
		super();
		this.bookTypeId = bookTypeId;
		this.parentId = parentId;
		this.bookTypeName = bookTypeName;
		this.context = context;
		this.isDelete = isDelete;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + bookTypeId;
		result = prime * result
				+ ((bookTypeName == null) ? 0 : bookTypeName.hashCode());
		result = prime * result + ((context == null) ? 0 : context.hashCode());
		result = prime * result + isDelete;
		result = prime * result + parentId;
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookType other = (BookType) obj;
		if (bookTypeId != other.bookTypeId)
			return false;
		if (bookTypeName == null)
		{
			if (other.bookTypeName != null)
				return false;
		} else if (!bookTypeName.equals(other.bookTypeName))
			return false;
		if (context == null)
		{
			if (other.context != null)
				return false;
		} else if (!context.equals(other.context))
			return false;
		if (isDelete != other.isDelete)
			return false;
		if (parentId != other.parentId)
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "BookType [bookTypeId=" + bookTypeId + ", bookTypeName="
				+ bookTypeName + ", context=" + context + ", isDelete="
				+ isDelete + ", parentId=" + parentId + "]";
	}

	public int getBookTypeId()
	{
		return bookTypeId;
	}

	public void setBookTypeId(int bookTypeId)
	{
		this.bookTypeId = bookTypeId;
	}

	public int getParentId()
	{
		return parentId;
	}

	public void setParentId(int parentId)
	{
		this.parentId = parentId;
	}

	public String getBookTypeName()
	{
		return bookTypeName;
	}

	public void setBookTypeName(String bookTypeName)
	{
		this.bookTypeName = bookTypeName;
	}

	public String getContext()
	{
		return context;
	}

	public void setContext(String context)
	{
		this.context = context;
	}

	public int getisDelete()
	{
		return isDelete;
	}

	public void setisDelete(int isDelete)
	{
		this.isDelete = isDelete;
	}
}
	