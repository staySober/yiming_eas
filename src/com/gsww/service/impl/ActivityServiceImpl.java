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
import com.gsww.entity.Activity;
import com.gsww.entity.Enclosure;
import com.gsww.entity.User;
import com.gsww.service.ActivityService;
import com.gsww.util.Page;
import com.gsww.util.PageResult;
import com.gsww.util.PageUtil;
import com.gsww.util.TimeHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("activityService")
@Transactional(readOnly = true)
public class ActivityServiceImpl implements ActivityService {

	@Resource(name = "activityDao")
	private BaseDao<Activity, String> activityDao;

	@Resource(name = "enclosureDao")
	private BaseDao<Enclosure, String> enclosureDao;

	@Override
	public PageResult queryactivityByPage(Page page, Activity activity, User user) {
		Map<String, Object> map = new HashMap<String, Object>();
		String hql = "from Activity a where 1=1";

		if (StringUtils.isNotBlank(activity.getName())) {
			hql += " and a.name name";
			map.put("name", "%" + activity.getName() + "%");
		}
		if (StringUtils.isNotBlank(user.getUserId())) {
			hql += " and a.user=:user";
			map.put("user", user);
		}
		page = PageUtil.createPage(page.getEveryPage(), (int) activityDao.countHqlResult(hql, map), page.getCurrentPage());// 根据总记录数创建分页信息
		List<Activity> list = activityDao.findByPage(page, hql, map);// 通过分页信息取得试题
		PageResult result = new PageResult(page, list);// 封装分页信息和记录信息，返回给调用处
		return result;
	}

	@Override
	@Transactional(readOnly = false)
	public void add(Activity activity, User user, File file, String fileName) throws Exception {
		activity.setUser(user);
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
			activity.setData(enclosure);
		}
		activity.setCreateTime(TimeHelper.getCurrentTime());
		activityDao.save(activity);
	}

	@Override
	public Activity getById(String id) {
		return activityDao.getByKey(id);
	}

	@Override
	@Transactional(readOnly = false)
	public void edit(Activity activity, File file, String fileName) throws Exception {
		Activity r = activityDao.getByKey(activity.getActivityId());
		BeanUtils.copyProperties(activity, r, new String[] { "createTime", "data", "user" });
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
		Activity activity = getById(id);
		
if(activity.getData()!=null) enclosureDao.delete(activity.getData());
		
activityDao.delete(activity);
	

	}

	@Override
	public List<Activity> getAll() {
		return activityDao.getAll();
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
