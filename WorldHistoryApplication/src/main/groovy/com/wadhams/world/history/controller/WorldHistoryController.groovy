package com.wadhams.world.history.controller

import com.wadhams.world.history.dto.HistoryDTO
import com.wadhams.world.history.report.GeologyTimelineReportService
import com.wadhams.world.history.service.HistoryXMLService

class WorldHistoryController {
	def execute() {
		HistoryXMLService historyXMLService = new HistoryXMLService()
		List<HistoryDTO> historyList = historyXMLService.loadHistoryData()
		historyList.each {h ->
			println h
		}
		println ''
		
		//Geology Timeline
		List<HistoryDTO> geologyList = historyList.findAll {it.categoryList.contains('Geology')}
		println "geologyList.size(): ${geologyList.size()}"
		GeologyTimelineReportService geologyTimelineReportService = new GeologyTimelineReportService()
		geologyTimelineReportService.execute(geologyList)
		
		
		
	}
}
