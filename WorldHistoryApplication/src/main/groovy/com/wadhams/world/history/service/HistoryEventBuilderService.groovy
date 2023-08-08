package com.wadhams.world.history.service

import com.wadhams.world.history.biz.HistoryEvent
import com.wadhams.world.history.date.HistoryDate
import com.wadhams.world.history.dto.HistoryDTO

class HistoryEventBuilderService {
	List<HistoryEvent> buildHistoryEventList(List<HistoryDTO> historyDTOList) {
		List<HistoryEvent> historyEventList = []
		
		historyDTOList.each {dto ->
			HistoryEvent he = new HistoryEvent()
			
			he.name = dto.name
			
			he.categoryList = dto.categoryList
			
			he.descriptionList = dto.descriptionList

			//start and sortDate
			if (dto.startTimeScale) {
				he.start = new HistoryDate(dto.startTimeScale, dto.startText)
				he.sortDate = he.start
			}
			
			//born and sortDate
			if (dto.bornTimeScale) {
				he.born = new HistoryDate(dto.bornTimeScale, dto.bornText)
				if (he.sortDate == null) {
					he.sortDate = he.born
				}
			}
			
			if (dto.diedTimeScale) {
				he.died = new HistoryDate(dto.diedTimeScale, dto.diedText)
			}
			
			if (dto.endTimeScale) {
				he.end = new HistoryDate(dto.endTimeScale, dto.endText)
			}

			if (dto.durationText) {
				he.inputDurationText = dto.durationText
			}

			historyEventList << he
		}
		
		return historyEventList
	}
}
