package com.gsww.action;

import java.io.File;

import com.gsww.entity.Grade;
import com.gsww.entity.User;
import com.gsww.service.GradeService;
import com.gsww.util.Page;
import com.gsww.util.PageResult;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

@Results({ @Result(name = "list", location = "/sys/grade/grade-list.jsp"),
	       @Result(name = "input", location = "/sys/grade/grade-add.jsp"),
	       @Result(name = "edit", location = "/sys/grade/grade-edit.jsp"),
	       @Result(name = "success", location = "grade.action", type = "redirect")

})
public class GradeAction extends BaseAction<Grade> {

	private static final long serialVersionUID = 7795872940645683836L;
	private Grade grade = new Grade();
	private int currentPage;
	@Autowired
	private GradeService gradeService;
	private String myFile0ContentType;
	private String myFile0FileName;
	private File myFile0;
	private String myFile1ContentType;
	private String myFile1FileName;
	private File myFile1;
	private String myFile2ContentType;
	private String myFile2FileName;
	private File myFile2;

	@Override
	public String list() throws Exception {
		User user = (User) getSession().getAttribute("session_user_key");
		Page page = new Page();
		page.setEveryPage(10);// 每页显示10条记录
		page.setCurrentPage(currentPage);// 设置当前页
		PageResult pageResult = gradeService.queryGradeByPage(page, grade, user);
		getRequest().setAttribute("pageResult", pageResult);
		getRequest().setAttribute("gradeName", grade.getGradeName());
		return "list";
	}

	public String input() {
		return "input";
	}

	public String add() {
		try {
			User user = (User) getSession().getAttribute("session_user_key");
			gradeService.add(grade, user, myFile0, myFile0FileName, myFile1, myFile1FileName, myFile2, myFile2FileName);
			getSession().setAttribute("msg", "新增班级信息成功!");
		} catch (Exception e) {
			e.printStackTrace();
			getSession().setAttribute("msg", "新增班级信息失败：" + e.getMessage());
		}
		return SUCCESS;
	}

	public String edit() {
		grade = gradeService.getById(id);
		getRequest().setAttribute("grade", grade);
		return "edit";
	}

	public String update() {
		try {
			gradeService.edit(grade, myFile0, myFile0FileName, myFile1, myFile1FileName, myFile2, myFile2FileName);
			getSession().setAttribute("msg", "修改班级信息成功!");
		} catch (Exception e) {
			e.printStackTrace();
			getSession().setAttribute("msg", "修改班级信息失败：" + e.getMessage());
		}
		return SUCCESS;
	}

	public String delete() {
		try {
			gradeService.delete(id);
			getSession().setAttribute("msg", "删除班级信息成功：");
		} catch (Exception e) {
			e.printStackTrace();
			getSession().setAttribute("msg", "删除班级信息失败：" + e.getMessage());
		}
		return SUCCESS;
	}
	public void downLoad(){
		try {
			gradeService.downLoad(id,getRequest(),getResponse());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public Grade getModel() {
		return grade;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public String getMyFile0ContentType() {
		return myFile0ContentType;
	}

	public void setMyFile0ContentType(String myFile0ContentType) {
		this.myFile0ContentType = myFile0ContentType;
	}

	public String getMyFile0FileName() {
		return myFile0FileName;
	}

	public void setMyFile0FileName(String myFile0FileName) {
		this.myFile0FileName = myFile0FileName;
	}

	public File getMyFile0() {
		return myFile0;
	}

	public void setMyFile0(File myFile0) {
		this.myFile0 = myFile0;
	}

	public String getMyFile1ContentType() {
		return myFile1ContentType;
	}

	public void setMyFile1ContentType(String myFile1ContentType) {
		this.myFile1ContentType = myFile1ContentType;
	}

	public String getMyFile1FileName() {
		return myFile1FileName;
	}

	public void setMyFile1FileName(String myFile1FileName) {
		this.myFile1FileName = myFile1FileName;
	}

	public File getMyFile1() {
		return myFile1;
	}

	public void setMyFile1(File myFile1) {
		this.myFile1 = myFile1;
	}

	public String getMyFile2ContentType() {
		return myFile2ContentType;
	}

	public void setMyFile2ContentType(String myFile2ContentType) {
		this.myFile2ContentType = myFile2ContentType;
	}

	public String getMyFile2FileName() {
		return myFile2FileName;
	}

	public void setMyFile2FileName(String myFile2FileName) {
		this.myFile2FileName = myFile2FileName;
	}

	public File getMyFile2() {
		return myFile2;
	}

	public void setMyFile2(File myFile2) {
		this.myFile2 = myFile2;
	}



}
