package com.wadhams.world.history.service

import com.wadhams.world.history.biz.HistoryEvent
import com.wadhams.world.history.comparator.HistoryEventStartComparator
import com.wadhams.world.history.dto.ReportingDTO

class GenericTimelineReportService {
	HistoryEventReportService historyEventReportService = new HistoryEventReportService()
	
	List<ReportingDTO> reportingDTOList
	List<HistoryEvent> historyEventList
	
	def GenericTimelineReportService(List<ReportingDTO> reportingDTOList, List<HistoryEvent> historyEventList) {
		this.reportingDTOList = reportingDTOList
		this.historyEventList = historyEventList
	}
	
	def execute() {
		reportingDTOList.each {dto ->
			//println dto
			generic(dto)
		}
	}
	
	def generic(ReportingDTO dto) {
		List<HistoryEvent> genericList = historyEventList.findAll {it.categoryList.contains(dto.category)}
		println "genericList.size(): ${genericList.size()}"
		println ''
		
		Collections.sort(genericList, new HistoryEventStartComparator())
		
		File f = new File("out/${dto.filename}")
		
		f.withPrintWriter {pw ->
			pw.println dto.heading
			String u1 = ''.padRight(dto.heading.size(), '-')
			pw.println u1
	
			historyEventReportService.report(genericList, pw)
		}
	}

}
