package com.maxwell.pos.comparator;

import java.util.Comparator;

import com.maxwell.pos.vo.TradeitemVO;

public class AmountScaleComparator implements Comparator<TradeitemVO>{
	
	public int compare(TradeitemVO o1, TradeitemVO o2) {
		if (o1.getAmountScale() > o2.getAmountScale())
			return 1;
		if (o1.getAmountScale() < o2.getAmountScale())
			return -1;
		return 0;
	}
	
}
