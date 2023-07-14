package com.wadhams.world.history.service

import com.wadhams.world.history.biz.HistoryEvent
import com.wadhams.world.history.date.HistoryDate
import com.wadhams.world.history.dto.HistoryDTO

class HistoryEventService {
	List<HistoryEvent> buildHistoryEventList(List<HistoryDTO> historyDTOList) {
		List<HistoryEvent> historyEventList = []
		
		historyDTOList.each {dto ->
			HistoryEvent he = new HistoryEvent()
			
			he.name = dto.name
			he.categoryList = dto.categoryList
			he.descriptionList = dto.descriptionList
			he.start = new HistoryDate(dto.startTimeScale, dto.startText)
			if (dto.endTimeScale) {
				he.end = new HistoryDate(dto.endTimeScale, dto.endText)
			}
			//TODO he.duration
			
			historyEventList << he
		}
		
		return historyEventList
	}
}
