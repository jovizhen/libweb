package com.gcit.jovi.library.dao.domain;

public class LibraryBranch
{
	private Integer	branchId;
	private String	branchName;
	private String	branchAddress;

	public LibraryBranch()
	{
		
	}
	
	public LibraryBranch(int branchId)
	{
		this.branchId = branchId;
	}

	public LibraryBranch(String branchName, String branchAddress)
	{
		super();
		this.branchName = branchName;
		this.branchAddress = branchAddress;
	}
	
	public LibraryBranch(Integer branchId, String branchName, String branchAddress)
	{
		super();
		this.branchId = branchId;
		this.branchName = branchName;
		this.branchAddress = branchAddress;
	}

	public Integer getBranchId()
	{
		return branchId;
	}

	public void setBranchId(Integer branchId)
	{
		this.branchId = branchId;
	}

	public String getBranchName()
	{
		return branchName;
	}

	public void setBranchName(String branchName)
	{
		this.branchName = branchName;
	}

	public String getBranchAddress()
	{
		return branchAddress;
	}

	public void setBranchAddress(String branchAddress)
	{
		this.branchAddress = branchAddress;
	}
}
