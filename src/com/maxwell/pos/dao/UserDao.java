package com.maxwell.pos.dao;

import com.maxwell.pos.model.User;
import com.maxwell.pos.vo.UserVO;
import com.maxwell.pos.vo.base.DataGrid;

public interface UserDao extends SuperDao<User, Integer> {
	
	public boolean exists(User u);
	
	public User get(String account, String password);

	public DataGrid getDataGrid(UserVO userVO, Integer rows, Integer page,
			String sort, String order);

}
