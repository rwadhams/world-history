package com.wadhams.world.history.biz

import java.time.LocalDate
import java.time.Year
import java.time.YearMonth
import com.wadhams.world.history.date.HistoryDate
import com.wadhams.world.history.date.HistoryPeriod
import com.wadhams.world.history.date.TimeScale

import groovy.transform.ToString

@ToString(includeNames=true)
class HistoryEvent {
	String name
	List<String> categoryList = []
	List<String> descriptionList = []
	
	//date-related values
	HistoryDate start
	HistoryDate end
	HistoryPeriod duration
	
	String reportStart() {
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
				return "More work required here..."
		}
	}
}
