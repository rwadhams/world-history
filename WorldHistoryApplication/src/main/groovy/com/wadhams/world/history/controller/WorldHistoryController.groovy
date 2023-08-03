package com.wadhams.world.history.controller

import com.wadhams.world.history.biz.HistoryEvent
import com.wadhams.world.history.dto.HistoryDTO
import com.wadhams.world.history.dto.ReportingDTO
import com.wadhams.world.history.report.GeologyTimelineReportService
import com.wadhams.world.history.service.GenericTimelineReportService
import com.wadhams.world.history.service.HistoryEventService
import com.wadhams.world.history.service.HistoryXMLService
import com.wadhams.world.history.service.ReportingXMLService

class WorldHistoryController {
	def execute() {
		ReportingXMLService reportingXMLService = new ReportingXMLService()
		List<ReportingDTO> reportingDTOList = reportingXMLService.loadReportingData()
//		reportingDTOList.each {dto ->
//			println dto
//		}
//		println ''
		
		HistoryXMLService historyXMLService = new HistoryXMLService()
		List<HistoryDTO> historyDTOList = historyXMLService.loadHistoryData()
//		historyDTOList.each {dto ->
//			println dto
//		}
//		println ''
		
		HistoryEventService historyEventService = new HistoryEventService()
		List<HistoryEvent> historyEventList = historyEventService.buildHistoryEventList(historyDTOList)
//		historyEventList.each {he ->
//			println he
//		}
//		println ''
		
		GenericTimelineReportService genericTimelineReportService = new GenericTimelineReportService(reportingDTOList, historyEventList)
		genericTimelineReportService.execute()
		println ''

/*		
		//Geology Timeline
		List<HistoryEvent> geologyList = historyEventList.findAll {it.categoryList.contains('Geology')}
		println "geologyList.size(): ${geologyList.size()}"
		GeologyTimelineReportService geologyTimelineReportService = new GeologyTimelineReportService()
		geologyTimelineReportService.execute(geologyList)
*/		
		
		println ''
	}
}
