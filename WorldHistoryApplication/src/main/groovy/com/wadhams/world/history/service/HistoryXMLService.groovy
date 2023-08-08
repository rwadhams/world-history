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
			
			//born
			def bornBCE = txn.born.bce
			def bornCE  = txn.born.ce
			def bornYM = txn.born.ym
			def bornDT = txn.born.dt
			boolean bornNotFound = false	//not used
			
			if (bornDT.size()) {
				dto.bornTimeScale = TimeScale.LocalDate
				String bornText = bornDT.text()
				dto.bornText = bornText
			}
			else if (bornYM.size()) {
				dto.bornTimeScale = TimeScale.YearMonth
				String bornText = bornYM.text()
				dto.bornText = bornText
			}
			else if (bornCE.size()) {
				dto.bornTimeScale = TimeScale.CE
				String bornText = bornCE.text()
				dto.bornText = bornText
			}
			else if (bornBCE.size()) {
				dto.bornTimeScale = TimeScale.BCE
				String bornText = bornBCE.text()
				dto.bornText = bornText
			}
			else {
				bornNotFound = true
			}

			//died
			def diedBCE = txn.died.bce
			def diedCE  = txn.died.ce
			def diedYM = txn.died.ym
			def diedDT = txn.died.dt
			boolean diedNotFound = false	//not used
			
			if (diedDT.size()) {
				dto.diedTimeScale = TimeScale.LocalDate
				String diedText = diedDT.text()
				dto.diedText = diedText
			}
			else if (diedYM.size()) {
				dto.diedTimeScale = TimeScale.YearMonth
				String diedText = diedYM.text()
				dto.diedText = diedText
			}
			else if (diedCE.size()) {
				dto.diedTimeScale = TimeScale.CE
				String diedText = diedCE.text()
				dto.diedText = diedText
			}
			else if (diedBCE.size()) {
				dto.diedTimeScale = TimeScale.BCE
				String diedText = diedBCE.text()
				dto.diedText = diedText
			}
			else {
				diedNotFound = true
			}
			
			//start
			def startGYA = txn.start.gya
			def startMYA = txn.start.mya
			def startKYA = txn.start.kya
			def startBCE = txn.start.bce
			def startCE  = txn.start.ce
			def startYM = txn.start.ym
			def startDT = txn.start.dt
			boolean startNotFound = false	//not used
			
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
			else {
				startNotFound = true
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
				String duration = txn.duration.text()
				if (duration.size()) {
					dto.durationText = duration
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
