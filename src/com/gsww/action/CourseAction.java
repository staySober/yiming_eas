package com.gsww.action;

import java.io.File;

import com.gsww.entity.Course;
import com.gsww.entity.User;
import com.gsww.service.CourseService;
import com.gsww.util.Page;
import com.gsww.util.PageResult;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

@Results({ @Result(name = "list", location = "/sys/course/course-list.jsp"),
	       @Result(name = "input", location = "/sys/course/course-add.jsp"),
	       @Result(name = "edit", location = "/sys/course/course-edit.jsp"),
	       @Result(name = "success", location = "course.action", type = "redirect")

})
public class CourseAction extends BaseAction<Course> {

	private static final long serialVersionUID = 7795872940645683836L;
	private Course course = new Course();
	private int currentPage;
	@Autowired
	private CourseService courseService;
	private String myFile0ContentType;
	private String myFile0FileName;
	private File myFile0;
	private String myFile1ContentType;
	private String myFile1FileName;
	private File myFile1;

	@Override
	public String list() throws Exception {
		User user = (User) getSession().getAttribute("session_user_key");
		Page page = new Page();
		page.setEveryPage(10);// 每页显示10条记录
		page.setCurrentPage(currentPage);// 设置当前页
		PageResult pageResult = courseService.queryCourseByPage(page, course, user);
		getRequest().setAttribute("pageResult", pageResult);
		getRequest().setAttribute("name", course.getName());
		return "list";
	}

	public String input() {
		return "input";
	}

	public String add() {
		try {
			User user = (User) getSession().getAttribute("session_user_key");
			courseService.add(course, user, myFile0, myFile0FileName, myFile1, myFile1FileName);
			getSession().setAttribute("msg", "新增课程信息成功!");
		} catch (Exception e) {
			e.printStackTrace();
			getSession().setAttribute("msg", "新增课程信息失败：" + e.getMessage());
		}
		return SUCCESS;
	}

	public String edit() {
		course = courseService.getById(id);
		getRequest().setAttribute("course", course);
		return "edit";
	}

	public String update() {
		try {
			courseService.edit(course, myFile0, myFile0FileName, myFile1, myFile1FileName);
			getSession().setAttribute("msg", "修改课程信息成功!");
		} catch (Exception e) {
			e.printStackTrace();
			getSession().setAttribute("msg", "修改课程信息失败：" + e.getMessage());
		}
		return SUCCESS;
	}

	public String delete() {
		try {
			courseService.delete(id);
			getSession().setAttribute("msg", "删除课程信息成功：");
		} catch (Exception e) {
			e.printStackTrace();
			getSession().setAttribute("msg", "删除课程信息失败：" + e.getMessage());
		}
		return SUCCESS;
	}
	public void downLoad(){
		try {
			courseService.downLoad(id,getRequest(),getResponse());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public Course getModel() {
		return course;
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
}
