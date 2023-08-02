package com.wadhams.world.history.date

import groovy.transform.ToString

@ToString(includeNames=true)
class HistoryDuration {
	TimeScale timeScale
	BigDecimal value
	
	def HistoryDuration(TimeScale timeScale, String text) {
		this.timeScale = timeScale
		this.value = new BigDecimal(text)
	}
	
}
