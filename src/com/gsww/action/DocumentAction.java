package com.gsww.action;

import java.io.File;

import com.gsww.entity.Document;
import com.gsww.entity.User;
import com.gsww.service.DocumentService;
import com.gsww.util.Page;
import com.gsww.util.PageResult;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

@Results({ @Result(name = "list", location = "/sys/document/document-list.jsp"),
	       @Result(name = "input", location = "/sys/document/document-add.jsp"),
	       @Result(name = "edit", location = "/sys/document/document-edit.jsp"),
	       @Result(name = "success", location = "document.action", type = "redirect")

})
public class DocumentAction extends BaseAction<Document> {

	private static final long serialVersionUID = 7795872940645683836L;
	private Document document = new Document();
	private int currentPage;
	@Autowired
	private DocumentService documentService;
	private String myFileContentType;
	private String myFileFileName;
	private File myFile;

	@Override
	public String list() throws Exception {
		User user = (User) getSession().getAttribute("session_user_key");
		Page page = new Page();
		page.setEveryPage(10);// 每页显示10条记录
		page.setCurrentPage(currentPage);// 设置当前页
		PageResult pageResult = documentService.querydocumentByPage(page, document, user);
		getRequest().setAttribute("pageResult", pageResult);
		getRequest().setAttribute("name", document.getName());
		return "list";
	}

	public String input() {
		return "input";
	}

	public String add() {
		try {
			User user = (User) getSession().getAttribute("session_user_key");
			documentService.add(document, user, myFile, myFileFileName);
			getSession().setAttribute("msg", "新增教学文档成功!");
		} catch (Exception e) {
			e.printStackTrace();
			getSession().setAttribute("msg", "新增教学文档失败：" + e.getMessage());
		}
		return SUCCESS;
	}

	public String edit() {
		document = documentService.getById(id);
		getRequest().setAttribute("document", document);
		return "edit";
	}

	public String update() {
		try {
			documentService.edit(document, myFile, myFileFileName);
			getSession().setAttribute("msg", "修改教学文档成功!");
		} catch (Exception e) {
			e.printStackTrace();
			getSession().setAttribute("msg", "修改教学文档失败：" + e.getMessage());
		}
		return SUCCESS;
	}

	public String delete() {
		try {
			document = documentService.getById(id);		
			documentService.delete(id);
			getSession().setAttribute("msg", "删除教学文档成功：");
		} catch (Exception e) {
			e.printStackTrace();
			getSession().setAttribute("msg", "删除教学文档失败：" + e.getMessage());
		}
		return SUCCESS;
	}
	public void downLoad(){
		try {
			documentService.downLoad(id,getRequest(),getResponse());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public Document getModel() {
		return document;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public String getMyFileContentType() {
		return myFileContentType;
	}

	public void setMyFileContentType(String myFileContentType) {
		this.myFileContentType = myFileContentType;
	}

	public String getMyFileFileName() {
		return myFileFileName;
	}

	public void setMyFileFileName(String myFileFileName) {
		this.myFileFileName = myFileFileName;
	}

	public File getMyFile() {
		return myFile;
	}

	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}

}
