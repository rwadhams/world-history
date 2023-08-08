package com.wadhams.world.history.service

import com.wadhams.world.history.biz.HistoryEvent
import com.wadhams.world.history.comparator.HistoryEventStartComparator
import com.wadhams.world.history.dto.ReportingDTO

class ValidationService {
	def validate(List<HistoryEvent> historyEventList) {
		historyEventList.each {he ->
			println he
			println "${he.name}"
			println "sortDate: ${he.sortDate}"
			if (he.hasBirthText()) {
				println 'hasBirthText(): true'
				println "${he.buildBirthText()}"
			}
			else {
				println 'hasBirthText(): false'
			}
			if (he.hasStartText()) {
				println 'hasStartText(): true'
				println "${he.buildStartText()}"
			}
			else {
				println 'hasStartText(): false'
			}
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
