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
	
	HistoryDate sortDate
	
	//born/died date-related values
	HistoryDate born
	HistoryDate died
	
	//date-related values
	HistoryDate start	//required. used for sorting. if missing in HistoryDTO, substitute born date
	HistoryDate end
	String inputDurationText

	List<String> descriptionList = []
	
	boolean hasStartText() {
		return start != null
	}
	
	String buildStartText() {
		//start HistoryDate only, no end HistoryDate
		if (start && !end) {
			switch (start.timeScale) {
				case TimeScale.CE :
					return "Starting ${start.year} CE"
				case TimeScale.BCE :
					return "Starting ${Math.abs(start.year.getValue())} BCE"
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
				startText = "${start.localDate.format(localDateDTF)}"
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
				endText = "${end.localDate.format(localDateDTF)}"
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
		
		return buildDurationTextFromDates(start, end)
	}
	
	boolean hasBirthText() {
		return born != null
	}
	
	String buildBirthText() {
		String result = 'Born '
		switch (born.timeScale) {
			case TimeScale.LocalDate :
				result += "${born.localDate.format(localDateDTF)}"
				break
			case TimeScale.YearMonth :
				result += "${born.yearMonth.format(yearMonthDTF)}"
				break
			case TimeScale.CE :
				result += "${born.year} CE"
				break
			case TimeScale.BCE :
				result += "${Math.abs(born.year.getValue())} BCE"
				break
			default :
				println "Should not happen: ${toString()}"
				throw new RuntimeException('Unable to build born text')
		}
			
		if (died) {
			result += ', Died '
			switch (died.timeScale) {
				case TimeScale.LocalDate :
					result += "${died.localDate.format(localDateDTF)}"
					break
				case TimeScale.YearMonth :
					result += "${died.yearMonth.format(yearMonthDTF)}"
					break
				case TimeScale.CE :
					result += "${died.year} CE"
					break
				case TimeScale.BCE :
					result += "${Math.abs(died.year.getValue())} BCE"
					break
				default :
					println "Should not happen: ${toString()}"
					throw new RuntimeException('Unable to build died text')
			}

			result += ', Aged '
			
			//duration
			result += buildDurationTextFromDates(born, died)
		}
		
		return result
	}
	
	String buildDurationTextFromDates(HistoryDate first, HistoryDate second) {
		if (first.timeScale == TimeScale.LocalDate || first.timeScale == TimeScale.YearMonth) {
			if (first.timeScale != second.timeScale) {
				println "Should not happen: First: ${first.toString()}, Second: ${second.toString()}"
				throw new RuntimeException('Mis-matched TimeScale')
			}
			Period period = Period.between(first.yearMonth, second.yearMonth)
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
		
		if (first.timeScale == TimeScale.BCE || first.timeScale == TimeScale.CE) {
			if (first.timeScale == TimeScale.CE && second.timeScale == TimeScale.BCE) {
				println "Should not happen: First: ${first.toString()}, Second: ${second.toString()}"
				throw new RuntimeException('Invalid TimeScales')
			}
			Period period = Period.between(first.year, second.year)
			int pYears = period.getYears()
			//println "$pYears year(s)"
			
			return "$pYears year${(pYears > 1)?'s':''}"
		}
		
		println "Should not happen: First: ${first.toString()}, Second: ${second.toString()}"
		throw new RuntimeException('Unable to buildGenericDurationText')
	}
	
}
