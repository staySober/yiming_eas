package com.gsww.service;

import java.io.File;

import com.gsww.entity.User;
import com.gsww.util.Page;
import com.gsww.util.PageResult;

public interface UserService {

	public User login(String account, String passWord);


	public PageResult queryUserByPage(Page page, User user, User sysUser);


	public void add(User user, File myFile, String myFileFileName)throws Exception;


	public User getById(String id);


	public void edit(User user, File myFile, String myFileFileName)throws Exception;


	public void delete(String id);


	public void edit(User user);
	
	public PageResult queryTeamByPage(Page page, User user, User sysUser);

}
