package com.book.bean;

public class Book {
	private long id;
	private String name;
	private String author;
	private String img;
	private String detail;
	
	public Book() {
		super();
	}
	
	
	public Book(String name, String author, String img, String detail) {
		super();
		this.name = name;
		this.author = author;
		this.img = img;
		this.detail = detail;
	}


	public Book(long id, String name, String author, String img, String detail) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
		this.img = img;
		this.detail = detail;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	
}
