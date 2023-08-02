package com.wadhams.world.history.service

import com.wadhams.world.history.date.HistoryDate
import com.wadhams.world.history.date.HistoryDuration
import com.wadhams.world.history.date.TimeScale

class TextReportingService {
	
	String getStartingText(HistoryDate start) {
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
	
	String getInputDurationText(HistoryDuration duration) {
		switch (duration.timeScale) {
			case TimeScale.KYA :
				return "Duration: ${duration.value} thousand years"
			case TimeScale.MYA :
				return "Duration: ${duration.value} million years"
			case TimeScale.GYA :
				return "Duration: ${duration.value} billion years"
			default :
				return "More work required here..."
		}
	}
}
