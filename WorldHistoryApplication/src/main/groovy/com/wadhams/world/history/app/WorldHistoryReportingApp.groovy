package com.wadhams.world.history.app

import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import com.wadhams.world.history.controller.WorldHistoryController
import groovy.transform.ToString

class WorldHistoryReportingApp {
	DateTimeFormatter xmlDTF = DateTimeFormatter.ofPattern('dd/MM/yyyy')
	DateTimeFormatter reportingDTF = DateTimeFormatter.ofPattern('EEE dd/MM/yyyy')

	static main(args) {
		println 'WorldHistoryReportingApp started...'
		println ''

		WorldHistoryController controller = new WorldHistoryController()
		controller.execute()
		
		println ''
		println 'WorldHistoryReportingApp ended.'
	}
}
