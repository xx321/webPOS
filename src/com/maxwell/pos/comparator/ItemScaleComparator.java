package com.maxwell.pos.comparator;

import java.util.Comparator;

import com.maxwell.pos.vo.TradeitemVO;

public class ItemScaleComparator implements Comparator<TradeitemVO>{

	
	public int compare(TradeitemVO o1, TradeitemVO o2) {
		if (o1.getItemScale() > o2.getItemScale())
			return 1;
		if (o1.getItemScale() < o2.getItemScale())
			return -1;
		return 0;
	}

}
