package com.gsww.service;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gsww.entity.Course;
import com.gsww.entity.User;
import com.gsww.util.Page;
import com.gsww.util.PageResult;

public interface CourseService {

	public PageResult queryCourseByPage(Page page, Course course, User user);

	public void add(Course course, User user, File file0, String fileName0, File file1, String fileName1) throws Exception;

	public Course getById(String id);

	public void edit(Course course, File file0, String fileName0, File file1, String fileName1) throws Exception;

	public void delete(String id);

	public List<Course> getAll();

	public void downLoad(String id, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
