package com.gsww.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gsww.dao.base.BaseDao;
import com.gsww.entity.Course;
import com.gsww.entity.Enclosure;
import com.gsww.entity.User;
import com.gsww.service.CourseService;
import com.gsww.util.Page;
import com.gsww.util.PageResult;
import com.gsww.util.PageUtil;
import com.gsww.util.TimeHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("courseService")
@Transactional(readOnly = true)
public class CourseServiceImpl implements CourseService {

	@Resource(name = "courseDao")
	private BaseDao<Course, String> courseDao;

	@Resource(name = "enclosureDao")
	private BaseDao<Enclosure, String> enclosureDao;

	@Override
	public PageResult queryCourseByPage(Page page, Course course, User user) {
		Map<String, Object> map = new HashMap<String, Object>();
		String hql = "from Course a where 1=1";

		if (StringUtils.isNotBlank(course.getName())) {
			hql += " and a.name like:name";
			map.put("name", "%" + course.getName() + "%");
		}
		if (StringUtils.isNotBlank(user.getUserId())) {
			hql += " and a.user=:user";
			map.put("user", user);
		}
		page = PageUtil.createPage(page.getEveryPage(), (int) courseDao.countHqlResult(hql, map), page.getCurrentPage());// 根据总记录数创建分页信息
		List<Course> list = courseDao.findByPage(page, hql, map);// 通过分页信息取得试题
		PageResult result = new PageResult(page, list);// 封装分页信息和记录信息，返回给调用处
		return result;
	}

	@Override
	@Transactional(readOnly = false)
	public void add(Course course, User user, File file0, String fileName0, File file1, String fileName1) throws Exception {
		course.setUser(user);
		if (file0 != null && file0.length() > 0) {
			byte[] buffer = new byte[1024];
			Enclosure enclosure = new Enclosure();
			FileInputStream in = new FileInputStream(file0);
			FileChannel fc = in.getChannel();
			buffer = new byte[Integer.parseInt(String.valueOf(fc.size()))];
			in.read(buffer);
			enclosure.setCreateTime(TimeHelper.getCurrentTime());
			enclosure.setFile(buffer);
			enclosure.setFileName(fileName0);
			enclosureDao.save(enclosure);
			course.setCourseTable(enclosure);
		}
		if (file1 != null && file1.length() > 0) {
			byte[] buffer = new byte[1024];
			Enclosure enclosure = new Enclosure();
			FileInputStream in = new FileInputStream(file1);
			FileChannel fc = in.getChannel();
			buffer = new byte[Integer.parseInt(String.valueOf(fc.size()))];
			in.read(buffer);
			enclosure.setCreateTime(TimeHelper.getCurrentTime());
			enclosure.setFile(buffer);
			enclosure.setFileName(fileName1);
			enclosureDao.save(enclosure);
			course.setData(enclosure);
		}

		courseDao.save(course);
	}

	@Override
	public Course getById(String id) {
		return courseDao.getByKey(id);
	}

	@Override
	@Transactional(readOnly = false)
	public void edit(Course course, File file0, String fileName0, File file1, String fileName1) throws Exception {
		Course r = courseDao.getByKey(course.getCourseId());
		BeanUtils.copyProperties(course, r, new String[] { "courseTable", "data", "user" });
		if (file0 != null && file0.length() > 0) {
			byte[] buffer = new byte[1024];
			Enclosure enclosure = new Enclosure();
			FileInputStream in = new FileInputStream(file0);
			FileChannel fc = in.getChannel();
			buffer = new byte[Integer.parseInt(String.valueOf(fc.size()))];
			in.read(buffer);
			enclosure.setCreateTime(TimeHelper.getCurrentTime());
			enclosure.setFile(buffer);
			enclosure.setFileName(fileName0);
			enclosureDao.save(enclosure);
			r.setCourseTable(enclosure);
		}
		if (file1 != null && file1.length() > 0) {
			byte[] buffer = new byte[1024];
			Enclosure enclosure = new Enclosure();
			FileInputStream in = new FileInputStream(file1);
			FileChannel fc = in.getChannel();
			buffer = new byte[Integer.parseInt(String.valueOf(fc.size()))];
			in.read(buffer);
			enclosure.setCreateTime(TimeHelper.getCurrentTime());
			enclosure.setFile(buffer);
			enclosure.setFileName(fileName1);
			enclosureDao.save(enclosure);
			r.setData(enclosure);
		}

	}

	@Override
	@Transactional(readOnly = false)
	public void delete(String id) {
		Course course = getById(id);
		
		if(course.getData()!=null) enclosureDao.delete(course.getData());
		
		courseDao.delete(course);
		
		
	
		courseDao.delete(course);

	}

	@Override
	public List<Course> getAll() {
		return courseDao.getAll();
	}

	@Override
	public void downLoad(String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Enclosure enclosure = enclosureDao.getByKey(id);
		if (enclosure != null) {
			String flieName = encodeChineseDownloadFileName(request, enclosure.getFileName());
			response.addHeader("Content-Disposition", "attachment;filename=" + flieName);
			response.setContentType("application/octet-stream");
			OutputStream os = new BufferedOutputStream(response.getOutputStream());
			os.write(enclosure.getFile());
			os.flush();
			os.close();
		}
	}

	public static String encodeChineseDownloadFileName(HttpServletRequest request, String fileName) {
		String agent = request.getHeader("USER-AGENT");
		try {
			if (null != agent && -1 != agent.indexOf("MSIE")) {
				fileName = URLEncoder.encode(fileName, "utf-8");
			} else {
				fileName = new String(fileName.getBytes("utf-8"), "iso8859-1");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return fileName;
	}
}
