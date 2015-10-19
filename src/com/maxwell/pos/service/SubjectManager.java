package com.maxwell.pos.service;

import com.maxwell.pos.model.Subject;
import com.maxwell.pos.vo.SubjectVO;
import com.maxwell.pos.vo.base.DataGrid;

public interface SubjectManager {

	void add(Subject subject);

	Subject get(Integer id);

	void update(Subject subject);

	String delete(String ids);
	
	DataGrid getDataGrid(SubjectVO subjectVO, Integer rows, Integer page,
			String sort, String order);

}
