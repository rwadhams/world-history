package com.wadhams.world.history.biz

import java.time.LocalDate
import java.time.Year
import java.time.YearMonth
import com.wadhams.world.history.date.HistoryDate
import com.wadhams.world.history.date.HistoryDuration
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
	HistoryDuration inputDuration

}
