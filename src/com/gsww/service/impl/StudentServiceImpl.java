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
import com.gsww.entity.Student;
import com.gsww.entity.User;
import com.gsww.service.StudentService;
import com.gsww.util.Page;
import com.gsww.util.PageResult;
import com.gsww.util.PageUtil;
import com.gsww.util.TimeHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("studentService")
@Transactional(readOnly = true)
public class StudentServiceImpl implements StudentService {

	@Resource(name = "studentDao")
	private BaseDao<Student, String> studentDao;

	@Resource(name = "enclosureDao")
	private BaseDao<Enclosure, String> enclosureDao;

	@Override
	public PageResult queryStudentByPage(Page page, Student student, User user) {
		Map<String, Object> map = new HashMap<String, Object>();
		String hql = "from Student a where 1=1";

		if (StringUtils.isNotBlank(student.getName())) {
			hql += " and a.name name";
			map.put("name", "%" + student.getName() + "%");
		}
		if (StringUtils.isNotBlank(user.getUserId())) {
			hql += " and a.user=:user";
			map.put("user", user);
		}
		page = PageUtil.createPage(page.getEveryPage(), (int) studentDao.countHqlResult(hql, map), page.getCurrentPage());// 根据总记录数创建分页信息
		List<Student> list = studentDao.findByPage(page, hql, map);// 通过分页信息取得试题
		PageResult result = new PageResult(page, list);// 封装分页信息和记录信息，返回给调用处
		return result;
	}

	@Override
	@Transactional(readOnly = false)
	public void add(Student student, User user, File file, String fileName) throws Exception {
		student.setUser(user);
		if (file != null && file.length() > 0) {
			byte[] buffer = new byte[1024];
			Enclosure enclosure = new Enclosure();
			FileInputStream in = new FileInputStream(file);
			FileChannel fc = in.getChannel();
			buffer = new byte[Integer.parseInt(String.valueOf(fc.size()))];
			in.read(buffer);
			enclosure.setCreateTime(TimeHelper.getCurrentTime());
			enclosure.setFile(buffer);
			enclosure.setFileName(fileName);
			enclosureDao.save(enclosure);
			student.setData(enclosure);
		}
		student.setCreateTime(TimeHelper.getCurrentTime());
		studentDao.save(student);
	}

	@Override
	public Student getById(String id) {
		return studentDao.getByKey(id);
	}

	@Override
	@Transactional(readOnly = false)
	public void edit(Student student, File file, String fileName) throws Exception {
		Student r = studentDao.getByKey(student.getStudentId());
		BeanUtils.copyProperties(student, r, new String[] { "createTime", "data", "user" });
		if (file!= null && file.length() > 0) {
			byte[] buffer = new byte[1024];
			Enclosure enclosure = new Enclosure();
			FileInputStream in = new FileInputStream(file);
			FileChannel fc = in.getChannel();
			buffer = new byte[Integer.parseInt(String.valueOf(fc.size()))];
			in.read(buffer);
			enclosure.setCreateTime(TimeHelper.getCurrentTime());
			enclosure.setFile(buffer);
			enclosure.setFileName(fileName);
			enclosureDao.save(enclosure);
			r.setData(enclosure);
		}
		

	}

	@Override
	@Transactional(readOnly = false)
	public void delete(String id) {
		Student student = getById(id);
		if(student.getData()!=null) enclosureDao.delete(student.getData());
		
	
		

		studentDao.delete(student);

	}

	@Override
	public List<Student> getAll() {
		return studentDao.getAll();
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
