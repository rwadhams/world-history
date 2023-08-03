package com.wadhams.world.history.dto

import com.wadhams.world.history.date.TimeScale

import groovy.transform.ToString

@ToString(includeNames=true)
class ReportingDTO {
	String category
	String filename
	String heading
}
