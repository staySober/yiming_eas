package com.gsww.service;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gsww.entity.Grade;
import com.gsww.entity.User;
import com.gsww.util.Page;
import com.gsww.util.PageResult;

public interface GradeService {



	public PageResult queryGradeByPage(Page page, Grade grade, User user);


	public void add(Grade grade, User user, File file0, String fileName0, File file1, String fileName1, File file2,
                    String fileName2)throws Exception ;


	public Grade getById(String id);


	public void edit(Grade grade, File file0, String fileName0, File file1, String fileName1, File file2,
                     String fileName2)throws Exception;


	public void delete(String id);
	
	public List<Grade> getAll(User user);


	public void downLoad(String id, HttpServletRequest request, HttpServletResponse response) throws Exception ;
}
