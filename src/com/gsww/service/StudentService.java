package com.gsww.service;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gsww.entity.Student;
import com.gsww.entity.User;
import com.gsww.util.Page;
import com.gsww.util.PageResult;

public interface StudentService {



	public PageResult queryStudentByPage(Page page, Student student, User user);


	public void add(Student student, User user, File file, String fileName)throws Exception ;


	public Student getById(String id);


	public void edit(Student student, File file, String fileName)throws Exception;


	public void delete(String id);
	
	public List<Student> getAll();


	public void downLoad(String id, HttpServletRequest request, HttpServletResponse response) throws Exception ;
}
