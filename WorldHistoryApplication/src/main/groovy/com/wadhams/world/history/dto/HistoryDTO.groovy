package com.wadhams.world.history.dto

import com.wadhams.world.history.date.TimeScale

import groovy.transform.ToString

@ToString(includeNames=true)
class HistoryDTO {
	String name

	List<String> categoryList = []
	
	//born. Required, if born not provided.
	TimeScale bornTimeScale
	String bornText
	
	//died. Optional. May still be living.
	TimeScale diedTimeScale
	String diedText
	
	//start. Required, if born not provided.
	TimeScale startTimeScale
	String startText
	
	//end. Optional.
	TimeScale endTimeScale
	String endText
	
	//duration. Optional. Can be included when 'end' is not available or missing 
	String durationText		//e.g. '252 million years', '800 years' 
	
	List<String> descriptionList = []
}
