package com.wadhams.world.history.service

import com.wadhams.world.history.biz.HistoryEvent
import com.wadhams.world.history.comparator.HistoryEventStartComparator
import com.wadhams.world.history.dto.ReportingDTO

class ValidationService {
	def validate(List<HistoryEvent> historyEventList) {
		historyEventList.each {he ->
			println he
			println "${he.name}"
			println "${he.buildHistoryDateText()}"
			if (he.hasDuration()) {
				println 'hasDuration(): true'
				println "${he.buildDurationText()}"
			}
			else {
				println 'hasDuration(): false'
			}
			println "categoryList.size()...: ${he.categoryList.size()}"
			println "descriptionList.size(): ${he.descriptionList.size()}"
			println '----------------------------------------------------'
			println ''
		}
	}
}
