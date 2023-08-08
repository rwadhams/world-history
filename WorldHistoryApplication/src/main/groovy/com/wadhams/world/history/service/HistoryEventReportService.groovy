package com.wadhams.world.history.service

import com.wadhams.world.history.biz.HistoryEvent
import com.wadhams.world.history.comparator.HistoryEventStartComparator
import com.wadhams.world.history.dto.ReportingDTO

class HistoryEventReportService {
	def report(List<HistoryEvent> historyEventList, PrintWriter pw) {
		historyEventList.each {he ->
			pw.println "${he.name}"
			if (he.hasBirthText()) {
				pw.println "\t${he.buildBirthText()}"
			}
			
			if (he.hasStartText()) {
				pw.print "\t${he.buildStartText()}"
				if (he.hasDuration()) {
					pw.println " (Duration: ${he.buildDurationText()})"
				}
				else {
					pw.println ''
				}
			}
			
			he.descriptionList.each {text ->
				pw.println "\t$text"
			}
			
			pw.println ''
		}
	}
}
