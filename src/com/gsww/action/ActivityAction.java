package com.gsww.action;

import java.io.File;
import java.util.List;

import com.gsww.entity.Activity;
import com.gsww.entity.Grade;
import com.gsww.entity.User;
import com.gsww.service.ActivityService;
import com.gsww.service.GradeService;
import com.gsww.util.Page;
import com.gsww.util.PageResult;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

@Results({ @Result(name = "list", location = "/sys/activity/activity-list.jsp"),
	       @Result(name = "input", location = "/sys/activity/activity-add.jsp"),
	       @Result(name = "edit", location = "/sys/activity/activity-edit.jsp"),
	       @Result(name = "success", location = "activity.action", type = "redirect")

})
public class ActivityAction extends BaseAction<Activity> {

	private static final long serialVersionUID = 7795872940645683836L;
	private Activity activity = new Activity();
	private int currentPage;
	@Autowired
	private ActivityService activityService;
	private String myFileContentType;
	private String myFileFileName;
	private File myFile;
	@Autowired
    private GradeService gradeService;

	@Override
	public String list() throws Exception {
		User user = (User) getSession().getAttribute("session_user_key");
		Page page = new Page();
		page.setEveryPage(10);// 每页显示10条记录
		page.setCurrentPage(currentPage);// 设置当前页
		PageResult pageResult = activityService.queryactivityByPage(page, activity, user);
		getRequest().setAttribute("pageResult", pageResult);
		getRequest().setAttribute("name", activity.getName());
		return "list";
	}

	public String input() {	
		User user = (User) getSession().getAttribute("session_user_key");
		List<Grade> grades = gradeService.getAll(user);
		getRequest().setAttribute("grades", grades);
		return "input";
	}

	public String add() {
		try {
			User user = (User) getSession().getAttribute("session_user_key");
			activityService.add(activity, user, myFile, myFileFileName);
			getSession().setAttribute("msg", "新增活动信息成功!");
		} catch (Exception e) {
			e.printStackTrace();
			getSession().setAttribute("msg", "新增活动信息失败：" + e.getMessage());
		}
		return SUCCESS;
	}

	public String edit() {
		User user = (User) getSession().getAttribute("session_user_key");
		List<Grade> grades = gradeService.getAll(user);
		getRequest().setAttribute("grades", grades);
		activity = activityService.getById(id);
		getRequest().setAttribute("activity", activity);
		return "edit";
	}

	public String update() {
		try {
			activityService.edit(activity, myFile, myFileFileName);
			getSession().setAttribute("msg", "修改活动信息成功!");
		} catch (Exception e) {
			e.printStackTrace();
			getSession().setAttribute("msg", "修改活动信息失败：" + e.getMessage());
		}
		return SUCCESS;
	}

	public String delete() {
		try {
			activityService.delete(id);
			getSession().setAttribute("msg", "删除活动信息成功：");
		} catch (Exception e) {
			e.printStackTrace();
			getSession().setAttribute("msg", "删除活动信息失败：" + e.getMessage());
		}
		return SUCCESS;
	}
	public void downLoad(){
		try {
			activityService.downLoad(id,getRequest(),getResponse());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public Activity getModel() {
		return activity;
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
