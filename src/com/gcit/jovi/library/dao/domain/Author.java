package com.gcit.jovi.library.dao.domain;

public class Author
{
	private Integer	authorId;
	private String	authorName;

	public Author()
	{
	}
	
	public Author(int authorId)
	{
		this.authorId = authorId;
	}
	
	public Author(String authorName)
	{
		this.authorName = authorName;
	}
	
	public Author(Integer authorId, String authorName)
	{
		super();
		this.authorId = authorId;
		this.authorName = authorName;
	}

	public Integer getAuthorId()
	{
		return authorId;
	}

	public void setAuthorId(Integer authorId)
	{
		this.authorId = authorId;
	}

	public String getAuthorName()
	{
		return authorName;
	}

	public void setAuthorName(String authorName)
	{
		this.authorName = authorName;
	}
}
