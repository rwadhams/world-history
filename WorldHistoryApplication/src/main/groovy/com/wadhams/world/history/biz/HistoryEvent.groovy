package com.wadhams.world.history.biz

import java.time.LocalDate
import java.time.Period
import java.time.Year
import java.time.YearMonth
import java.time.format.DateTimeFormatter

import com.wadhams.world.history.date.HistoryDate
import com.wadhams.world.history.date.HistoryPeriod
import com.wadhams.world.history.date.TimeScale

import groovy.transform.ToString

@ToString(includeNames=true)
class HistoryEvent {
	static DateTimeFormatter localDateDTF = DateTimeFormatter.ofPattern('dd/MM/yyyy')
	static DateTimeFormatter yearMonthDTF = DateTimeFormatter.ofPattern('LLL yyyy')
	
	String name
	List<String> categoryList = []
	
	//date-related values
	HistoryDate start
	HistoryDate end
	String inputDurationText

	List<String> descriptionList = []
	
	String buildHistoryDateText() {
		//start HistoryDate only, no end HistoryDate
		if (start && !end) {
			switch (start.timeScale) {
				case TimeScale.CE :
					return "Starting ${start.year} CE"
				case TimeScale.BCE :
					return "Starting ${Math.abs(start.value)} BCE"
				case TimeScale.KYA :
					return "${start.value} thousand years ago"
				case TimeScale.MYA :
					return "${start.value} million years ago"
				case TimeScale.GYA :
					return "${start.value} billion years ago"
				default :
					println "Should not happen: ${toString()}"
					throw new RuntimeException()
			}
		}

		//both start HistoryDate and end HistoryDate
		String startText
		switch (start.timeScale) {
			case TimeScale.LocalDate :
				startText = "${start.localDate.format(localDateDTF)} CE"
				break
			case TimeScale.YearMonth :
				startText = "${start.yearMonth.format(yearMonthDTF)}"
				break
			case TimeScale.CE :
				startText = "${start.year} CE"
				break
			case TimeScale.BCE :
				startText = "${Math.abs(start.year.getValue())} BCE"
				break
			default :
				println "Should not happen: ${toString()}"
				throw new RuntimeException()
		}
		String endText
		switch (end.timeScale) {
			case TimeScale.LocalDate :
				endText = "${end.localDate.format(localDateDTF)} CE"
				break
			case TimeScale.YearMonth :
				endText = "${end.yearMonth.format(yearMonthDTF)}"
				break
			case TimeScale.CE :
				endText = "${end.year} CE"
				break
			case TimeScale.BCE :
				endText = "${Math.abs(end.year.getValue())} BCE"
				break
			default :
				println "Should not happen: ${toString()}"
				throw new RuntimeException()
		}
		return "$startText - $endText" 
	}
	
	boolean hasDuration() {
		if (inputDurationText) return true
		
		//TODO consider excluding GYA, MYA and KYA if found in 'start'
		if (start && end) return true
		
		return false
	}
	
	String buildDurationText() {
		if (inputDurationText) {
			return inputDurationText
		}
		
		if (start.timeScale == TimeScale.LocalDate || start.timeScale == TimeScale.YearMonth) {
			if (start.timeScale != end.timeScale) {
				println "Should not happen: ${toString()}"
				throw new RuntimeException('Mis-matched TimeScale')
			}
			Period period = Period.between(start.yearMonth, end.yearMonth)
			int pYears = period.getYears()
			int pMonths = period.getMonths()
			//println "$pYears year(s), $pMonths months"
			String result = ''
			if (pYears > 0) {
				result += "$pYears year${(pYears > 1)?'s':''}"
			}
			if (pYears > 0 && pMonths > 0) {
				result += ", "
			}
			if (pMonths > 0) {
				result += "$pMonths months"
			}
			return result
		}
		
		if (start.timeScale == TimeScale.BCE || start.timeScale == TimeScale.CE) {
			if (start.timeScale == TimeScale.CE && end.timeScale == TimeScale.BCE) {
				println "Should not happen: ${toString()}"
				throw new RuntimeException('Invalid TimeScales')
			}
			Period period = Period.between(start.year, end.year)
			int pYears = period.getYears()
			//println "$pYears year(s)"
			
			return "$pYears year${(pYears > 1)?'s':''}"
		}
		
		println "Should not happen: ${toString()}"
		throw new RuntimeException('Unable to buildDurationText')
	}
}
