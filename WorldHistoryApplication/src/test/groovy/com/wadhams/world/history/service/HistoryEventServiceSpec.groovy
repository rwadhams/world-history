package com.wadhams.world.history.service

import com.wadhams.world.history.biz.HistoryEvent
import com.wadhams.world.history.date.HistoryDate
import com.wadhams.world.history.date.TimeScale

import com.wadhams.world.history.dto.HistoryDTO
import spock.lang.Specification

class HistoryEventServiceSpec extends Specification {
	def "correct transformation of HistoryDTO list to HistoryEvent list"() {
		given:
			List<HistoryDTO> dtoList = []
			
			HistoryDTO dto1 = new HistoryDTO()
			dto1.name = 'first'
			dto1.categoryList = ['cat1']
			dto1.startTimeScale = TimeScale.GYA
			dto1.startText = '11.8'
			dto1.descriptionList = ['desc1', 'desc2']
			dtoList << dto1
			
			HistoryDTO dto2 = new HistoryDTO()
			dto2.name = 'second'
			dto2.categoryList = ['catA', 'catB']
			dto2.startTimeScale = TimeScale.LocalDate
			dto2.startText = '14/03/1879'
			dto2.endTimeScale = TimeScale.LocalDate
			dto2.endText = '18/04/1955'
			dto2.descriptionList = ['desc1']
			dtoList << dto2
			
			HistoryEventService service = new HistoryEventService()
			
		when:
			List<HistoryEvent> heList = service.buildHistoryEventList(dtoList)
			
		then:
			heList.size() == 2
			
			HistoryEvent he1 = heList[0]
			he1.name == 'first'
			he1.categoryList == ['cat1']
			he1.descriptionList == ['desc1', 'desc2']
			he1.start == new HistoryDate(TimeScale.GYA, '11.8')
			he1.end == null
			he1.duration == null
			
			HistoryEvent he2 = heList[1]
			he2.name == 'second'
			he2.categoryList == ['catA', 'catB']
			he2.descriptionList == ['desc1']
			he2.start == new HistoryDate(TimeScale.LocalDate, '14/03/1879')
			he2.end == new HistoryDate(TimeScale.LocalDate, '18/04/1955')
			he2.duration == null
	}
}
