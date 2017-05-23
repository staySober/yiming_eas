package com.gsww.action;

import java.io.File;

import com.gsww.entity.User;
import com.gsww.service.UserService;
import com.gsww.util.Page;
import com.gsww.util.PageResult;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

@Results({ @Result(name = "list", location = "/sys/user/user-list.jsp"),
	       @Result(name = "teamList", location = "/sys/user/user-teamList.jsp"),
	       @Result(name = "input", location = "/sys/user/user-add.jsp"),
	       @Result(name = "edit", location = "/sys/user/user-edit.jsp"),
	       @Result(name = "view", location = "/sys/user/user-view.jsp"),
	       @Result(name = "rePwd", location = "/sys/user/user-rePwd.jsp"),
	       @Result(name = "success", location = "user.action", type = "redirect"),
	       @Result(name = "noLogin", location = "/login.jsp"),

})
public class UserAction extends BaseAction<User> {

	private static final long serialVersionUID = -8396360507495385802L;
	private User user = new User();
	private int currentPage;
	@Autowired
	private UserService userService;
	private String myFileContentType;
	private String myFileFileName;
	private File myFile;

	@Override
	public String list() throws Exception {
		User sysUser = (User) getSession().getAttribute("session_user_key");
		Page page = new Page();
		page.setEveryPage(10);// 每页显示10条记录
		page.setCurrentPage(currentPage);// 设置当前页
		PageResult pageResult = userService.queryUserByPage(page, user, sysUser);
		getRequest().setAttribute("pageResult", pageResult);
		getRequest().setAttribute("name", user.getName());
		getRequest().setAttribute("account", user.getAccount());
		return "list";
	}
	public String teamList() throws Exception {
		User sysUser = (User) getSession().getAttribute("session_user_key");
		Page page = new Page();
		page.setEveryPage(10);// 每页显示10条记录
		page.setCurrentPage(currentPage);// 设置当前页
		PageResult pageResult = userService.queryTeamByPage(page, user, sysUser);
		getRequest().setAttribute("pageResult", pageResult);
		getRequest().setAttribute("name", user.getName());
		getRequest().setAttribute("account", user.getAccount());
		return "teamList";
	}
	public String input() {
		return "input";
	}

	public String add() {
		try {
			userService.add(user,myFile,myFileFileName);
			getSession().setAttribute("msg", "注册用户成功!");
		} catch (Exception e) {
			e.printStackTrace();
			getSession().setAttribute("msg", "注册用户失败：" + e.getMessage());
		}
		return "noLogin";
	}

	public String edit() {
		user = userService.getById(id);
		getRequest().setAttribute("user", user);
		return "edit";
	}
	public String rePwd() {
		user = userService.getById(id);
		getRequest().setAttribute("user", user);
		return "rePwd";
	}
	public String savaPwd(){
		try {
			userService.edit(user);
			getSession().setAttribute("msg", "修改个人信息成功!");
		} catch (Exception e) {
			e.printStackTrace();
			getSession().setAttribute("msg", "修改个人信息失败：" + e.getMessage());
		}
		return SUCCESS;
	}
	@Override
	public User getModel() {
		return user;
	}

	public String update() {
		try {
			userService.edit(user, myFile,myFileFileName);
			getSession().setAttribute("msg", "修改个人信息成功!");
		} catch (Exception e) {
			e.printStackTrace();
			getSession().setAttribute("msg", "修改个人信息失败：" + e.getMessage());
		}
		return SUCCESS;
	}

	public String delete() {
		try {
			userService.delete(id);
			getSession().setAttribute("msg", "删除用户成功!");
		} catch (Exception e) {
			e.printStackTrace();
			getSession().setAttribute("msg", "删除用户失败：" + e.getMessage());
		}
		return SUCCESS;
	}
	public String view(){
		user = userService.getById(id);
		getRequest().setAttribute("user", user);
		return "view";
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
