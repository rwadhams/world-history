package com.wadhams.world.history.comparator

import com.wadhams.world.history.dto.HistoryDTO

class HistoryDTONameComparator implements Comparator<HistoryDTO>{

	@Override
	public int compare(HistoryDTO h1, HistoryDTO h2) {
		return h1.name.compareTo(h2.name)
	}
}
