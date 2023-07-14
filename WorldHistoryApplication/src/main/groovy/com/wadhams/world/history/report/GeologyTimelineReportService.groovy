package com.wadhams.world.history.report

import com.wadhams.world.history.biz.HistoryEvent
import com.wadhams.world.history.comparator.HistoryDTOStartComparator
import com.wadhams.world.history.comparator.HistoryEventStartComparator
import com.wadhams.world.history.dto.HistoryDTO

class GeologyTimelineReportService {
	def execute(List<HistoryEvent> historyEventList) {
		Collections.sort(historyEventList, new HistoryEventStartComparator())
		
		File f = new File("out/geology-timeline-report.txt")
		
		f.withPrintWriter {pw ->
			pw.println 'GEOLOGY TIMELINE REPORT'
			pw.println '-----------------------'
	
			//report(historyEventList, pw)
		}
	}
}
