package com.wadhams.world.history.service

import com.wadhams.world.history.biz.HistoryEvent
import com.wadhams.world.history.comparator.HistoryEventStartComparator
import com.wadhams.world.history.dto.ReportingDTO

class GenericTimelineReportService {
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
	
			report(genericList, pw)
		}
	}

	def report(List<HistoryEvent> genericList, PrintWriter pw) {
		genericList.each {he ->
			pw.println "${he.name}"
			pw.print "\t${he.buildHistoryDateText()}"
			if (he.hasDuration()) {
				pw.println " (Duration: ${he.buildDurationText()})"
			}
			else {
				pw.println ''
			}
			he.descriptionList.each {text ->
				pw.println "\t$text"
			}
			pw.println ''
		}
	}
}
