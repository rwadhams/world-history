package com.wadhams.world.history.dto

import com.wadhams.world.history.date.TimeScale

import groovy.transform.ToString

@ToString(includeNames=true)
class HistoryDTO {
	String name
	List<String> categoryList = []
	
	//start
	TimeScale startTimeScale
	String startText
	
	//end
	TimeScale endTimeScale
	String endText
	
	List<String> descriptionList = []
}
