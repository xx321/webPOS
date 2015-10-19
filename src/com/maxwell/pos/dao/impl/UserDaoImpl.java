package com.maxwell.pos.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.maxwell.pos.dao.UserDao;
import com.maxwell.pos.model.User;
import com.maxwell.pos.vo.UserVO;
import com.maxwell.pos.vo.base.DataGrid;

@Component("userDao")
public class UserDaoImpl extends SuperDaoImpl<User, Integer> implements UserDao {

	@SuppressWarnings("unchecked")
	public boolean exists(User u) {
		String hql = "from User u where u.account=?";
		List<User> users = getHibernateTemplate().find(hql, new Object[]{ u.getAccount() });

		if(users != null && users.size() > 0)
			return true;
		return false;
	}

	public User get(String account, String password) {
		List<?> users = getHibernateTemplate().find(
				"from User as u where u.account=? and u.password=?",
				new Object[] { account, password });

		if (users.size() > 0) {
			return (User) users.get(0);
		} else {
			return null;
		}
	}

	public DataGrid getDataGrid(UserVO userVO, Integer rows, Integer page,
			String sort, String order) {
		DataGrid datagrid = new DataGrid();
		
		StringBuffer sb = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		
		sb.append("from User u where 1=1 ");
		addWhere(userVO, sb, values);
		
		Long total = (Long) queryObject("select count(*) " + sb.toString(), values);
		datagrid.setTotal(total);
		
		addOrderBy(sb, sort, order);
		
		@SuppressWarnings("unchecked")
		List<User> users = (List<User>) find(sb.toString(), values, rows, page);
		
		List<UserVO> userVOs = new ArrayList<UserVO>();
		for (User user : users) {
			userVO = new UserVO();
			BeanUtils.copyProperties(user, userVO);
			userVOs.add(userVO);
		}
		datagrid.setRows(userVOs);
		return datagrid;
	}

	//增加查詢功能 : 添加 where 條件  方法。(UserVO 專用)
	private void addWhere(UserVO userVO, StringBuffer sb, List<Object> values) {
		
		if (userVO.getUsername() != null && !userVO.getUsername().trim().equals("")) {
			sb.append(" and u.username like ? ");
			values.add("%%" + userVO.getUsername().trim() + "%%");
		}

	}
}
