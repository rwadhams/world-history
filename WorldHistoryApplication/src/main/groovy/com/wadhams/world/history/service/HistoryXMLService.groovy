package com.wadhams.world.history.service

import com.wadhams.world.history.dto.HistoryDTO
import java.time.LocalDate
import java.time.YearMonth
import java.time.Year
import java.time.format.DateTimeFormatter
import com.wadhams.world.history.date.TimeScale

class HistoryXMLService {
	String historyFilename = 'History.xml'
	
	List<HistoryDTO> loadHistoryData() {
		List<HistoryDTO> historyList = []
		
		File historyFile
		URL resource = getClass().getClassLoader().getResource(historyFilename)
		if (resource == null) {
			throw new IllegalArgumentException("file not found!")
		} 
		else {
			historyFile = new File(resource.toURI())
		}
		println "Processing: ${historyFile.name}"
		println ''
		
		def history = new XmlSlurper().parse(historyFile)
		def transactions = history.data

		transactions.each {txn ->
			//println txn
			historyList << build(txn)
		}
		
		return historyList
	}
	
	HistoryDTO build(txn) {
			HistoryDTO dto = new HistoryDTO()
			
			//name
			String name = txn.name.text()
			dto.name = name
			
			//categories
			def category = txn.category
			category.each {c ->
				String cat = c.text()
				dto.categoryList << cat
			}
			
			//start
			def startGYA = txn.start.gya
			def startMYA = txn.start.mya
			def startKYA = txn.start.kya
			def startBCE = txn.start.bce
			def startCE  = txn.start.ce
			def startYM = txn.start.ym
			def startDT = txn.start.dt
			
			if (startDT.size()) {
				dto.startTimeScale = TimeScale.LocalDate
				String startText = startDT.text()
				dto.startText = startText
			}
			else if (startYM.size()) {
				dto.startTimeScale = TimeScale.YearMonth
				String startText = startYM.text()
				dto.startText = startText
			}
			else if (startCE.size()) {
				dto.startTimeScale = TimeScale.CE
				String startText = startCE.text()
				dto.startText = startText
			}
			else if (startBCE.size()) {
				dto.startTimeScale = TimeScale.BCE
				String startText = startBCE.text()
				dto.startText = startText
			}
			else if (startKYA.size()) {
				dto.startTimeScale = TimeScale.KYA
				String startText = startKYA.text()
				dto.startText = startText
			}
			else if (startMYA.size()) {
				dto.startTimeScale = TimeScale.MYA
				String startText = startMYA.text()
				dto.startText = startText
			}
			else if (startGYA.size()) {
				dto.startTimeScale = TimeScale.GYA
				String startText = startGYA.text()
				dto.startText = startText
			}

			//end
			def endBCE = txn.end.bce
			def endCE  = txn.end.ce
			def endYM = txn.end.ym
			def endDT = txn.end.dt
			boolean endNotFound = false
			
			if (endDT.size()) {
				dto.endTimeScale = TimeScale.LocalDate
				String endText = endDT.text()
				dto.endText = endText
			}
			else if (endYM.size()) {
				dto.endTimeScale = TimeScale.YearMonth
				String endText = endYM.text()
				dto.endText = endText
			}
			else if (endCE.size()) {
				dto.endTimeScale = TimeScale.CE
				String endText = endCE.text()
				dto.endText = endText
			}
			else if (endBCE.size()) {
				dto.endTimeScale = TimeScale.BCE
				String endText = endBCE.text()
				dto.endText = endText
			}
			else {
				endNotFound = true
			}
			
			//duration
			if (endNotFound) {	//check for duration
				def durationGYA = txn.duration.gya
				def durationMYA = txn.duration.mya
				def durationKYA = txn.duration.kya
	
				if (durationKYA.size()) {
					dto.durationTimeScale = TimeScale.KYA
					String durationText = durationKYA.text()
					dto.durationText = durationText
				}
				else if (durationMYA.size()) {
					dto.durationTimeScale = TimeScale.MYA
					String durationText = durationMYA.text()
					dto.durationText = durationText
				}
				else if (durationGYA.size()) {
					dto.durationTimeScale = TimeScale.GYA
					String durationText = durationGYA.text()
					dto.durationText = durationText
				}			
			}

			//description lines
			def description = txn.description
			if (description.size()) {
				def paragraphs = description.p
				paragraphs.each {p ->
					String para = p.text()
					dto.descriptionList << para
				}
			}

			return dto
	}
}
