package com.wadhams.world.history.comparator

import com.wadhams.world.history.biz.HistoryEvent
import com.wadhams.world.history.date.HistoryDate
import com.wadhams.world.history.date.TimeScale

import spock.lang.Specification

class HistoryEventStartComparatorSpec extends Specification {
	def "correct sort order"() {
		given:
			HistoryEventStartComparator comparator = new HistoryEventStartComparator()
			HistoryEvent first = new HistoryEvent(name: 'first', start: new HistoryDate(TimeScale.GYA, '13.8'))
			HistoryEvent middle = new HistoryEvent(name: 'middle', start: new HistoryDate(TimeScale.GYA, '9'))
			HistoryEvent last = new HistoryEvent(name: 'last', start: new HistoryDate(TimeScale.GYA, '4.52'))
			List<HistoryEvent> heList = []
			heList << middle
			heList << last
			heList << first
			
		when:
			Collections.sort(heList, comparator)
			
		then:
			heList[0].name == 'first'
			heList[1].name == 'middle'
			heList[2].name == 'last'
	}
}
