package com.maxwell.pos.service;

import java.util.List;

import com.maxwell.pos.model.User;
import com.maxwell.pos.vo.UserVO;
import com.maxwell.pos.vo.base.DataGrid;

public interface UserManager {
	
	public boolean add(User u);
	
	public List<User> list();
	
	public User getUserById(int id);
	
	public User get(String account, String password);

	public void changePassword(Integer userId, String newPassword);
	
	public void update(User user);

	public void delete(User user);

	public DataGrid getDataGrid(UserVO userVO, Integer rows, Integer page,
			String sort, String order);

	public String delete(String ids);
}
