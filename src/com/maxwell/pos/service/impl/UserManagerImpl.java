package com.maxwell.pos.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.maxwell.pos.dao.UserDao;
import com.maxwell.pos.model.User;
import com.maxwell.pos.service.UserManager;
import com.maxwell.pos.vo.UserVO;
import com.maxwell.pos.vo.base.DataGrid;

@Component("userManager")
public class UserManagerImpl implements UserManager {

	private UserDao userDao;
	
	@Resource
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public boolean add(User u) {
		
		if(userDao.exists(u))
			return false;
		
		userDao.save(u);
		return true;
	}

	public List<User> list() {
		return userDao.findAll();
	}

	public User getUserById(int id) {
		return userDao.findById(id);
	}

	public User get(String account, String password) {
		return userDao.get(account, password);
	}

	public void changePassword(Integer userId, String newPassword) {
		User user = userDao.findById(userId);
		if (user != null) {
			user.setPassword(newPassword);
			userDao.update(user);
		}
	}

	public void update(User user) {
		userDao.update(user);
	}

	public void delete(User user) {
		if(user != null)
			userDao.delete(user);
	}

	public DataGrid getDataGrid(UserVO userVO, Integer rows, Integer page,
			String sort, String order) {
		
		return userDao.getDataGrid(userVO, rows, page, sort, order);
	}

	public String delete(String ids) {
		String message = null;
		if (ids != null) {
			for (String id : ids.split(",")) {
				if (id.trim().equals("10")) {
					message = "超級管理員角色，不可刪除 ! ";
					continue;
				}
				User user = userDao.findById(new Integer(id.trim()));
				if (user == null) {
					message = "該用戶不存在 ! ";
					continue;
				}
				if (user.getCheckouts().size() >0 || user.getTradeitems().size() > 0) {
					message = "您所要刪除的用戶已經在使用 !! 建議您將用戶狀態設定為\"離職 \"。";
					continue;
				}
				userDao.delete(user);
				message = "該用戶已經成功刪除 !! ";
			}
		}
		return message;
	}

}
