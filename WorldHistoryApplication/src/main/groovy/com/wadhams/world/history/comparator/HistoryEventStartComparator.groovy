package com.wadhams.world.history.comparator

import com.wadhams.world.history.biz.HistoryEvent
import com.wadhams.world.history.date.HistoryDateComparator

class HistoryEventStartComparator implements Comparator<HistoryEvent> {
	static HistoryDateComparator comparator
	
	static {
		comparator = new HistoryDateComparator()
	}
	
	@Override
	public int compare(HistoryEvent he1, HistoryEvent he2) {
		return comparator.compare(he1.sortDate, he2.sortDate)
	}
}
