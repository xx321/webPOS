package com.maxwell.pos.dao;

import com.maxwell.pos.model.Subject;
import com.maxwell.pos.vo.SubjectVO;
import com.maxwell.pos.vo.base.DataGrid;

public interface SubjectDao extends SuperDao<Subject, Integer> {

	DataGrid getDataGrid(SubjectVO subjectVO, Integer rows, Integer page,
			String sort, String order);


}
