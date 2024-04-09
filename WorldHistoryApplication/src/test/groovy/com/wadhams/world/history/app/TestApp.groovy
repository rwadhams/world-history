package com.wadhams.world.history.app

import com.wadhams.world.history.biz.HistoryEvent
import com.wadhams.world.history.comparator.HistoryEventStartComparator
import com.wadhams.world.history.controller.WorldHistoryController
import com.wadhams.world.history.dto.HistoryDTO
import com.wadhams.world.history.service.HistoryEventBuilderService
import com.wadhams.world.history.service.HistoryEventReportService
import com.wadhams.world.history.service.HistoryXMLService
import com.wadhams.world.history.service.ValidationService

class TestApp {
	static main(args) {
		println 'TestApp started...'
		println ''

		TestApp app = new TestApp()
		app.execute()
		
		println 'TestApp ended.'
	}
	
	def execute() {
		HistoryXMLService historyXMLService = new HistoryXMLService()
		historyXMLService.historyFilename = 'TestHistory03.xml'
		List<HistoryDTO> historyDTOList = historyXMLService.loadHistoryData()
		historyDTOList.each {dto ->
			println dto
		}
		println ''
		println '------------------------------------------------------------'
		println ''
		
//		HistoryEventBuilderService historyEventBuilderService = new HistoryEventBuilderService()
//		List<HistoryEvent> historyEventList = historyEventBuilderService.buildHistoryEventList(historyDTOList)
//		historyEventList.each {he ->
//			println he
//		}
//		println ''
//		println '------------------------------------------------------------'
//		println ''
		
//		Collections.sort(historyEventList, new HistoryEventStartComparator())
		
//		ValidationService validationService = new ValidationService()
//		validationService.validate(historyEventList)
//		println ''
//		println '------------------------------------------------------------'
//		println ''
		
//		PrintWriter pw = new PrintWriter(System.out, true)
//		HistoryEventReportService historyEventReportService = new HistoryEventReportService()
//		historyEventReportService.report(historyEventList, pw)
//		println ''
		
	}
}
