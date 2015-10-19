package com.maxwell.pos.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.maxwell.pos.action.base.BaseAction;
import com.maxwell.pos.model.User;
import com.maxwell.pos.service.UserManager;
import com.maxwell.pos.vo.UserVO;
import com.maxwell.pos.vo.base.DataGrid;
import com.maxwell.pos.vo.base.Json;
import com.opensymphony.xwork2.ActionContext;

@Component("userAction")
@Scope("prototype")
public class UserAction extends BaseAction {

	private static final long serialVersionUID = -700568109028155292L;
	private UserManager userManager;
	private User user;
	private String message;
	private List<User> users;

	private String oldPassword;
	private String newPassword;
	private String reNewPassword;
	
	private Integer id;
	private String username;
	private String ids;

	@Resource
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getMessage() {
		return message;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public void setReNewPassword(String reNewPassword) {
		this.reNewPassword = reNewPassword;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String login() {
		User user2 = userManager.get(user.getAccount(), user.getPassword());
		if (user2 != null && "1".equals(user2.getStatus().toString())) {
			Map<String, Object> session = ActionContext.getContext().getSession();
			session.put("user", user2);
			return SUCCESS;
		} else {
			message = "帳號或密碼輸入錯誤 ! ";
			return INPUT;
		}
	}
	
	public String logout() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.remove("user");
		return SUCCESS;
	}
	
	public String list() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = (User) session.get("user");
		if(user.getPurview() == 1) {
//			users = userManager.list();
			return SUCCESS;
		} else {
			return NONE;
		}
	}
	
	public String changePassword() {

		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = (User) session.get("user");
		if(!user.getPassword().equals(oldPassword)) {
			message = "舊密碼輸入錯誤!";
			return INPUT;
		}
		if(!newPassword.equals(reNewPassword)) {
			message = "輸入的兩次新密碼不相同!";
			return INPUT;
		}
		if(newPassword.trim().equals("")) {
			message = "新設定的密碼不能為空 !";
			return INPUT;
		}
		user.setPassword(newPassword);
		userManager.changePassword(user.getId(), newPassword);
		message = "密碼修改成功!";	
		return SUCCESS;
	}
	
	public String load() {
		if (id == null) {
			return INPUT;
		}
		user = userManager.getUserById(id);
		return SUCCESS;
	}
	
	public String update() {
		if (user != null && "10".equals(user.getId().toString())) {
			user.setPurview(new Integer(1));
			user.setStatus(new Integer(1));
		}
		userManager.update(user);
		return SUCCESS;
	}
	
	public void delete() {
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg(userManager.delete(ids));
		super.writeJson(j);
		
	}
	public String add() {
		if(userManager.add(user))
			return SUCCESS;
		message = "新增用戶失敗 ! 請檢查帳號是否存在。";
		return INPUT;
	}
	
	public void datagrid() {
		
		UserVO userVO = new UserVO();
		BeanUtils.copyProperties(this, userVO);
		
		DataGrid datagrid = userManager.getDataGrid(userVO, getRows(), getPage(), getSort(), getOrder());

		super.writeJson(datagrid);
	}
	
	public void audit() {
		Json j = new Json();
		
		User user2 = userManager.get(user.getAccount(), user.getPassword());
		
		if (user2 != null && "1".equals(user2.getPurview().toString()) && "1".equals(user2.getStatus().toString())) {
			j.setSuccess(true);
		} else {
			j.setSuccess(false);
			j.setMsg("權限不足，不能進行審核 !");
		}
		super.writeJson(j);
	}
}
