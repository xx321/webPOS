package com.maxwell.pos.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.maxwell.pos.action.base.BaseAction;
import com.maxwell.pos.model.Subject;
import com.maxwell.pos.service.SubjectManager;
import com.maxwell.pos.vo.SubjectVO;
import com.maxwell.pos.vo.base.DataGrid;
import com.maxwell.pos.vo.base.Json;
import com.opensymphony.xwork2.ModelDriven;

@Component("subjectAction")
@Scope("prototype")
public class SubjectAction extends BaseAction implements ModelDriven<SubjectVO>{

	private static final long serialVersionUID = 5917487182202627641L;

	private SubjectManager subjectManager;
	
	@Resource
	public void setSubjectManager(SubjectManager subjectManager) {
		this.subjectManager = subjectManager;
	}
	private SubjectVO subjectVO = new SubjectVO();
	
	public SubjectVO getModel() {
		return subjectVO;
	}
	
	private Subject subject;
	
	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public String add() {
		subjectManager.add(subject);
		return SUCCESS;
	}
	
	public String load() {
		if (subjectVO.getId() ==null)
			return INPUT;
		subject = subjectManager.get(subjectVO.getId());
		return SUCCESS;
	}
	
	public String update() {
		subjectManager.update(subject);
		return SUCCESS;
	}
	
	public void delete() {
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg(subjectManager.delete(subjectVO.getIds()));
		super.writeJson(j);
	}
	
	public void datagrid() {
		
		DataGrid datagrid = subjectManager.getDataGrid(subjectVO, getRows(), getPage(), getSort(), getOrder());
		
		super.writeJson(datagrid);
	}

}
