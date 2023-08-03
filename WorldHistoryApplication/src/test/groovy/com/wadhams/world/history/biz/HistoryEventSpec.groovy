package com.wadhams.world.history.biz

import com.wadhams.world.history.date.HistoryDate
import com.wadhams.world.history.date.TimeScale

import spock.lang.Specification

class HistoryEventSpec extends Specification {
		
	def "hasDuration() returns false, with default constructor"() {
		given:
			HistoryEvent he = new HistoryEvent()
		
		expect:
			!he.hasDuration()	//false
	}
	
	def "hasDuration() returns false, without end HistoryDate"() {
		given:
			HistoryEvent he = new HistoryEvent()
			he.start = new HistoryDate(TimeScale.YearMonth, '09/1962')
			
		expect:
			!he.hasDuration()	//false
	}
	
	def "buildDurationText() returns correct text, when inputDurationText populated"() {
		given:
			String text = '252 million years'
			HistoryEvent he = new HistoryEvent()
			he.inputDurationText = text
		
		expect:
			he.hasDuration()	//true
			he.buildDurationText() == text
	}
	
	def "buildDurationText() returns correct text, with TimeScale.YearMonth, two years, months"() {
		given:
			HistoryEvent he = new HistoryEvent()
			he.start = new HistoryDate(TimeScale.YearMonth, '02/1962')
			he.end = new HistoryDate(TimeScale.YearMonth, '11/1964')
			
		expect:
			he.hasDuration()	//true
			he.buildDurationText() == '2 years, 9 months'
	}
	
	def "buildDurationText() returns correct text, with TimeScale.YearMonth, two years, zero months"() {
		given:
			HistoryEvent he = new HistoryEvent()
			he.start = new HistoryDate(TimeScale.YearMonth, '02/1962')
			he.end = new HistoryDate(TimeScale.YearMonth, '02/1964')
			
		expect:
			he.hasDuration()	//true
			he.buildDurationText() == '2 years'
	}
	
	def "buildDurationText() returns correct text, with TimeScale.YearMonth, one year, months"() {
		given:
			HistoryEvent he = new HistoryEvent()
			he.start = new HistoryDate(TimeScale.YearMonth, '09/1962')
			he.end = new HistoryDate(TimeScale.YearMonth, '11/1963')
			
		expect:
			he.hasDuration()	//true
			he.buildDurationText() == '1 year, 2 months'
	}
	
	def "buildDurationText() returns correct text, with TimeScale.YearMonth, one year, zero months"() {
		given:
			HistoryEvent he = new HistoryEvent()
			he.start = new HistoryDate(TimeScale.YearMonth, '09/1962')
			he.end = new HistoryDate(TimeScale.YearMonth, '09/1963')
			
		expect:
			he.hasDuration()	//true
			he.buildDurationText() == '1 year'
	}
	
	def "buildDurationText() returns correct text, with TimeScale.YearMonth, months only"() {
		given:
			HistoryEvent he = new HistoryEvent()
			he.start = new HistoryDate(TimeScale.YearMonth, '05/1962')
			he.end = new HistoryDate(TimeScale.YearMonth, '11/1962')
			
		expect:
			he.hasDuration()	//true
			he.buildDurationText() == '6 months'
	}

	def "buildHistoryDateText() with start only, TimeScale.GYA"() {
		given:
			HistoryEvent he = new HistoryEvent()
			he.start = new HistoryDate(TimeScale.GYA, '13.8')
			
		expect:
			he.buildHistoryDateText() == '13.8 billion years ago'
	}
	
	def "buildHistoryDateText() with start and end, TimeScale.YearMonth"() {
		given:
			HistoryEvent he = new HistoryEvent()
			he.start = new HistoryDate(TimeScale.YearMonth, '07/1914')
			he.end = new HistoryDate(TimeScale.YearMonth, '11/1918')
			
		expect:
			he.buildHistoryDateText() == 'Jul 1914 - Nov 1918'
	}
	
}
