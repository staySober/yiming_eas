package com.gsww.service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.gsww.dao.base.BaseDao;
import com.gsww.entity.User;
import com.gsww.service.UserService;
import com.gsww.util.Page;
import com.gsww.util.PageResult;
import com.gsww.util.PageUtil;
import com.gsww.util.TimeHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

	@Resource(name = "userDao")
	private BaseDao<User, String> userDao;

	@Override
	public User login(String account, String passWord) {
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("account", account);
		values.put("password", passWord);
		return userDao.findUnique("from User a where a.account=:account and a.password=:password", values);
	}

	@Override
	public PageResult queryUserByPage(Page page, User user, User sysUser) {
		Map<String, Object> map = new HashMap<String, Object>();
		String hql = "from User a where 1=1";

		if (StringUtils.isNotBlank(user.getName())) {
			hql += " and a.name like:name";
			map.put("name", "%" + user.getName() + "%");
		}
		if (StringUtils.isNotBlank(sysUser.getAccount())) {
			hql += " and a.account=:account";
			map.put("account", sysUser.getAccount());
		}
		if (StringUtils.isNotBlank(user.getAccount())) {
			hql += " and a.account=:account";
			map.put("account", user.getAccount());
		}
		page = PageUtil.createPage(page.getEveryPage(), (int) userDao.countHqlResult(hql, map), page.getCurrentPage());// 根据总记录数创建分页信息
		List<User> list = userDao.findByPage(page, hql, map);// 通过分页信息取得试题
		PageResult result = new PageResult(page, list);// 封装分页信息和记录信息，返回给调用处
		return result;
	}

	@Override
	@Transactional(readOnly = false)
	public void add(User user, File myFile, String myFileFileName) throws Exception {
		user.setCreateTime(TimeHelper.getCurrentTime());
		user.setUserType("0");
		if (myFile != null && myFile.length() > 0) {
			String fileName = upLoadPhoto(myFile, myFileFileName);
			user.setPhoto(fileName);

		}
		userDao.saveReturnId(user);
	}

	private String upLoadPhoto(File myFile, String myFileFileName) throws FileNotFoundException, IOException {
		String suffix = myFileFileName.substring(myFileFileName.lastIndexOf("."));
		String fileName = System.currentTimeMillis() + suffix;
		String path = ServletActionContext.getServletContext().getRealPath("/") + "/upload/" + fileName;
		InputStream is = new BufferedInputStream(new FileInputStream(myFile));
		OutputStream os = new BufferedOutputStream(new FileOutputStream(path));
		byte[] buffer = new byte[1024];
		int len = 0;
		while (-1 != (len = is.read(buffer))) {
			os.write(buffer, 0, len);
		}
		os.close();
		is.close();
		return fileName;
	}

	@Override
	public User getById(String id) {
		return userDao.getByKey(id);
	}

	@Override
	@Transactional(readOnly = false)
	public void edit(User user, File myFile, String myFileFileName) throws Exception {
		User u = userDao.getByKey(user.getUserId());
		BeanUtils.copyProperties(user, u, new String[] { "password", "createTime", "userType", "photo" });
		if (myFile != null && myFile.length() > 0) {
			String fileName = upLoadPhoto(myFile, myFileFileName);
			u.setPhoto(fileName);
		}
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(String id) {
		userDao.delete(id);
	}

	@Override
	@Transactional(readOnly = false)
	public void edit(User user) {
		User u = userDao.getByKey(user.getUserId());
		u.setPassword(user.getPassword());
	}

	@Override
	public PageResult queryTeamByPage(Page page, User user, User sysUser) {
		Map<String, Object> map = new HashMap<String, Object>();
		String hql = "from User a where 1=1";

		if (StringUtils.isNotBlank(user.getName())) {
			hql += " and a.name like:name";
			map.put("name", "%" + user.getName() + "%");
		}
		if (StringUtils.isNotBlank(user.getAccount())) {
			hql += " and a.account=:account";
			map.put("account", user.getAccount());
		}
		page = PageUtil.createPage(page.getEveryPage(), (int) userDao.countHqlResult(hql, map), page.getCurrentPage());// 根据总记录数创建分页信息
		List<User> list = userDao.findByPage(page, hql, map);// 通过分页信息取得试题
		PageResult result = new PageResult(page, list);// 封装分页信息和记录信息，返回给调用处
		return result;
	}

}
