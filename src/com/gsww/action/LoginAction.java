package com.gsww.action;

import com.gsww.entity.User;
import com.gsww.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

@Results({ @Result(name = "login", location = "/index.jsp"),
	       @Result(name = "noLogin", location = "/login.jsp") })
public class LoginAction extends BaseAction<User> {

	private static final long serialVersionUID = -1240806275276547968L;

	private User user = new User();
	@Autowired
	private UserService userService;

	@Override
	public String list() throws Exception {
		User u = userService.login(user.getAccount(), user.getPassword());
		if (u != null && StringUtils.isNotBlank(u.getUserId())) {
			getSession().setAttribute("session_user_key", u);
			return "login";
		} else {
			addActionError("账号或密码错误,请重新登陆!");
			return "noLogin";
		}
	}
	@Override
	public User getModel() {
		return user;
	}

}
