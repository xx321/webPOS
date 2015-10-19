package com.maxwell.pos.service;

import java.util.Date;
import java.util.List;

import com.maxwell.pos.vo.reportVO.CostVO;
import com.maxwell.pos.vo.reportVO.FeeVO;
import com.maxwell.pos.vo.reportVO.IncomeVO;

public interface ReportManager {

	IncomeVO getIncomeBy(Date beginDate, Date endDate);

	List<CostVO> getCostsBy(Date beginDate, Date endDate);

	List<FeeVO> getFeesBy(Date beginDate, Date endDate);

}
