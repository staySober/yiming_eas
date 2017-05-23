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
import com.gsww.entity.Document;
import com.gsww.entity.Enclosure;
import com.gsww.entity.User;
import com.gsww.service.DocumentService;
import com.gsww.util.Page;
import com.gsww.util.PageResult;
import com.gsww.util.PageUtil;
import com.gsww.util.TimeHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("documentService")
@Transactional(readOnly = true)
public class DocumentServiceImpl implements DocumentService {

	@Resource(name = "documentDao")
	private BaseDao<Document, String> documentDao;

	@Resource(name = "enclosureDao")
	private BaseDao<Enclosure, String> enclosureDao;

	@Override
	public PageResult querydocumentByPage(Page page, Document document, User user) {
		Map<String, Object> map = new HashMap<String, Object>();
		String hql = "from Document a where 1=1";

		if (StringUtils.isNotBlank(document.getName())) {
			hql += " and a.name like:name";
			map.put("name", "%" + document.getName() + "%");
		}
		if (StringUtils.isNotBlank(user.getUserId())) {
			hql += " and a.user=:user";
			map.put("user", user);
		}
		page = PageUtil.createPage(page.getEveryPage(), (int) documentDao.countHqlResult(hql, map), page.getCurrentPage());// 根据总记录数创建分页信息
		List<Document> list = documentDao.findByPage(page, hql, map);// 通过分页信息取得试题
		PageResult result = new PageResult(page, list);// 封装分页信息和记录信息，返回给调用处
		return result;
	}

	@Override
	@Transactional(readOnly = false)
	public void add(Document document, User user, File myFile, String myFileFileName) throws Exception {
		document.setCreateTime(TimeHelper.getCurrentTime());
		document.setUser(user);
		if (myFile != null && myFile.length() > 0) {
			byte[] buffer = new byte[1024];
			Enclosure enclosure = new Enclosure();
			FileInputStream in = new FileInputStream(myFile);
			FileChannel fc = in.getChannel();
			buffer = new byte[Integer.parseInt(String.valueOf(fc.size()))];
			in.read(buffer);
			enclosure.setCreateTime(TimeHelper.getCurrentTime());
			enclosure.setFile(buffer);
			enclosure.setFileName(myFileFileName);
			enclosureDao.save(enclosure);
			document.setData(enclosure);
		}
		documentDao.save(document);
	}

	@Override
	public Document getById(String id) {
		return documentDao.getByKey(id);
	}

	@Override
	@Transactional(readOnly = false)
	public void edit(Document document, File myFile, String myFileFileName) throws Exception {
		Document r = documentDao.getByKey(document.getDocumentId());
		BeanUtils.copyProperties(document, r, new String[] { "createTime", "data", "user" });
		if (myFile != null && myFile.length() > 0) {
			byte[] buffer = new byte[1024];
			Enclosure enclosure = new Enclosure();
			FileInputStream in = new FileInputStream(myFile);
			FileChannel fc = in.getChannel();
			buffer = new byte[Integer.parseInt(String.valueOf(fc.size()))];
			in.read(buffer);
			enclosure.setCreateTime(TimeHelper.getCurrentTime());
			enclosure.setFile(buffer);
			enclosure.setFileName(myFileFileName);
			enclosureDao.save(enclosure);
			r.setData(enclosure);
		}

	}

	@Override
	@Transactional(readOnly = false)
	public void delete(String id) {
		Document document = getById(id);
		if(document.getData()!=null) enclosureDao.delete(document.getData());
		
		documentDao.delete(document);

	}

	@Override
	public List<Document> getAll() {
		return documentDao.getAll();
	}

	@Override
	public void downLoad(String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Document document = getById(id);
		Enclosure enclosure = document.getData();
		if(enclosure!=null){
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
