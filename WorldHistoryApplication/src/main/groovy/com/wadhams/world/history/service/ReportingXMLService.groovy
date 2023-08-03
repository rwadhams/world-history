package com.wadhams.world.history.service

import com.wadhams.world.history.dto.HistoryDTO
import com.wadhams.world.history.dto.ReportingDTO
import java.time.LocalDate
import java.time.YearMonth
import java.time.Year
import java.time.format.DateTimeFormatter
import com.wadhams.world.history.date.TimeScale

class ReportingXMLService {
	String reportingFilename = 'Reporting.xml'
	
	List<ReportingDTO> loadReportingData() {
		List<ReportingDTO> reportingList = []
		
		File reportingFile
		URL resource = getClass().getClassLoader().getResource(reportingFilename)
		if (resource == null) {
			throw new IllegalArgumentException("file not found!")
		} 
		else {
			reportingFile = new File(resource.toURI())
		}
		println "Processing: ${reportingFile.name}"
		println ''
		
		def reporting = new XmlSlurper().parse(reportingFile)
		def transactions = reporting.data

		transactions.each {txn ->
			//println txn
			reportingList << build(txn)
		}
		
		return reportingList
	}
	
	ReportingDTO build(txn) {
			ReportingDTO dto = new ReportingDTO()
			
			//category
			String category = txn.category.text()
			dto.category = category
			
			//filename
			String filename = txn.filename.text()
			dto.filename = filename
			
			//heading
			String heading = txn.heading.text()
			dto.heading = heading
			
			return dto
	}
}
