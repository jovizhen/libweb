package com.gcit.jovi.library.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.gcit.jovi.library.dao.domain.LibraryBranch;

public class LibraryBranchDao extends BaseDao<LibraryBranch>
{
	public LibraryBranchDao()
	{
		
	}
	
	public LibraryBranchDao(Connection connection)
	{
		super(connection);
	}

	@Override
	public void create(LibraryBranch branch) throws ClassNotFoundException, SQLException
	{
		executeUpdate(getConnection(), 
				"insert into tbl_library_branch (branchName, branchAddress) values (?,?)", 
				new Object[]
				{
					(branch.getBranchName()==null)? null:branch.getBranchName(),
                    (branch.getBranchAddress()==null)? null:branch.getBranchAddress()
				});
	}

	@Override
	public void delete(LibraryBranch branch) throws ClassNotFoundException, SQLException
	{
		executeUpdate(getConnection(), 
				"delete from tbl_library_branch where branchId = (?)", 
				new Object[]{branch.getBranchId()});
	}

	@Override
	public void update(LibraryBranch branch) throws ClassNotFoundException, SQLException
	{
		executeUpdate(getConnection(), 
				"update tbl_library_branch set branchName = (?), branchAddress = (?) where branchId = (?)", 
				new Object[]
				{
					(branch.getBranchName()==null)? null:branch.getBranchName(),
					(branch.getBranchAddress()==null)? null:branch.getBranchAddress(),
					branch.getBranchId()
				});
	}

	@Override
	public ArrayList<LibraryBranch> readAll() throws ClassNotFoundException, SQLException
	{
		return executeQuery(getConnection(), 
				"select * from tbl_library_branch", null);
	}


	public LibraryBranch read(int id) throws ClassNotFoundException, SQLException
	{
		ArrayList<LibraryBranch> result = executeQuery(getConnection(), 
				"select * from tbl_library_branch where branchId = ?", 
				new Object[]{ id });
		if (result != null && result.size() != 0)
		{
			return result.get(0);
		}
		return null;
	}

	@Override
	public ArrayList<LibraryBranch> parseResult(ResultSet resultSet) throws SQLException
	{
		ArrayList<LibraryBranch> branchList = new ArrayList<LibraryBranch>();
		while (resultSet.next())
		{
			LibraryBranch branch = new LibraryBranch();
			branch.setBranchId(resultSet.getInt("branchId"));
			branch.setBranchName(resultSet.getString("branchName"));
			branch.setBranchAddress(resultSet.getString("branchAddress"));
			branchList.add(branch);
		}
		return branchList;
	}

}
