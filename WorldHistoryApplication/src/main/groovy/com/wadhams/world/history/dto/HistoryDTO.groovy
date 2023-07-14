package com.wadhams.world.history.dto

import com.wadhams.world.history.date.TimeScale
import groovy.transform.ToString
import java.time.LocalDate
import java.time.Year
import java.time.YearMonth

@ToString(includeNames=true)
class HistoryDTO {
	String name
	List<String> categoryList = []
	
	//start
	TimeScale startTimeScale
	BigDecimal startValue
	Year startYear
	YearMonth startYearMonth
	LocalDate startLocalDate
	
	//end
	TimeScale endTimeScale
	BigDecimal endValue
	Year endYear
	YearMonth endYearMonth
	LocalDate endLocalDate
	
	List<String> descriptionList = []
}
