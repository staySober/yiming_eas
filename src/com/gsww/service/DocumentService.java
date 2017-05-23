package com.gsww.service;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gsww.entity.Document;
import com.gsww.entity.User;
import com.gsww.util.Page;
import com.gsww.util.PageResult;

public interface DocumentService {



	public PageResult querydocumentByPage(Page page, Document document, User user);


	public void add(Document document, User user, File myFile, String myFileFileName)throws Exception ;


	public Document getById(String id);


	public void edit(Document document, File myFile, String myFileFileName)throws Exception;


	public void delete(String id);
	
	public List<Document> getAll();


	public void downLoad(String id, HttpServletRequest request, HttpServletResponse response) throws Exception ;
}
