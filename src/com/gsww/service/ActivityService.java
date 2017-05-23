package com.gsww.service;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gsww.entity.Activity;
import com.gsww.entity.User;
import com.gsww.util.Page;
import com.gsww.util.PageResult;

public interface ActivityService {



	public PageResult queryactivityByPage(Page page, Activity activity, User user);


	public void add(Activity activity, User user, File file, String fileName)throws Exception ;


	public Activity getById(String id);


	public void edit(Activity activity, File file, String fileName)throws Exception;


	public void delete(String id);
	
	public List<Activity> getAll();


	public void downLoad(String id, HttpServletRequest request, HttpServletResponse response) throws Exception ;
}
