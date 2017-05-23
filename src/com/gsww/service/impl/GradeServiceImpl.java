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
import com.gsww.entity.Enclosure;
import com.gsww.entity.Grade;
import com.gsww.entity.User;
import com.gsww.service.GradeService;
import com.gsww.util.Page;
import com.gsww.util.PageResult;
import com.gsww.util.PageUtil;
import com.gsww.util.TimeHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("gradeService")
@Transactional(readOnly = true)
public class GradeServiceImpl implements GradeService {

	@Resource(name = "gradeDao")
	private BaseDao<Grade, String> gradeDao;

	@Resource(name = "enclosureDao")
	private BaseDao<Enclosure, String> enclosureDao;

	@Override
	public PageResult queryGradeByPage(Page page, Grade grade, User user) {
		Map<String, Object> map = new HashMap<String, Object>();
		String hql = "from Grade a where 1=1";

		if (StringUtils.isNotBlank(grade.getGradeName())) {
			hql += " and a.gradeName like:gradeName";
			map.put("gradeName", "%" + grade.getGradeName() + "%");
		}
		if (StringUtils.isNotBlank(user.getUserId())) {
			hql += " and a.user=:user";
			map.put("user", user);
		}
		page = PageUtil.createPage(page.getEveryPage(), (int) gradeDao.countHqlResult(hql, map), page.getCurrentPage());// 根据总记录数创建分页信息
		List<Grade> list = gradeDao.findByPage(page, hql, map);// 通过分页信息取得试题
		PageResult result = new PageResult(page, list);// 封装分页信息和记录信息，返回给调用处
		return result;
	}

	@Override
	@Transactional(readOnly = false)
	public void add(Grade grade, User user, File file0, String fileName0, File file1, String fileName1, File file2, String fileName2) throws Exception {
		grade.setUser(user);
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
			grade.setCourseTable(enclosure);
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
			grade.setStudentNames(enclosure);
		}
		if (file2 != null && file2.length() > 0) {
			byte[] buffer = new byte[1024];
			Enclosure enclosure = new Enclosure();
			FileInputStream in = new FileInputStream(file2);
			FileChannel fc = in.getChannel();
			buffer = new byte[Integer.parseInt(String.valueOf(fc.size()))];
			in.read(buffer);
			enclosure.setCreateTime(TimeHelper.getCurrentTime());
			enclosure.setFile(buffer);
			enclosure.setFileName(fileName2);
			enclosureDao.save(enclosure);
			grade.setData(enclosure);
		}
		gradeDao.save(grade);
	}

	@Override
	public Grade getById(String id) {
		return gradeDao.getByKey(id);
	}

	@Override
	@Transactional(readOnly = false)
	public void edit(Grade grade, File file0, String fileName0, File file1, String fileName1, File file2, String fileName2) throws Exception {
		Grade r = gradeDao.getByKey(grade.getGradeId());
		BeanUtils.copyProperties(grade, r, new String[] { "courseTable", "studentNames", "data", "user" });
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
			grade.setCourseTable(enclosure);
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
			grade.setStudentNames(enclosure);
		}
		if (file2 != null && file2.length() > 0) {
			byte[] buffer = new byte[1024];
			Enclosure enclosure = new Enclosure();
			FileInputStream in = new FileInputStream(file2);
			FileChannel fc = in.getChannel();
			buffer = new byte[Integer.parseInt(String.valueOf(fc.size()))];
			in.read(buffer);
			enclosure.setCreateTime(TimeHelper.getCurrentTime());
			enclosure.setFile(buffer);
			enclosure.setFileName(fileName2);
			enclosureDao.save(enclosure);
			grade.setData(enclosure);
		}

	}

	@Override
	@Transactional(readOnly = false)
	public void delete(String id) {
		Grade grade = getById(id);
if(grade.getData()!=null) enclosureDao.delete(grade.getData());
		
	
		


		gradeDao.delete(grade);

	}

	@Override
	public List<Grade> getAll(User user) {
		return gradeDao.find(" from Grade a where a.user=?", user);
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
