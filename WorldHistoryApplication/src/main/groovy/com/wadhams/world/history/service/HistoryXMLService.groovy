package com.wadhams.world.history.service

import com.wadhams.world.history.dto.HistoryDTO
import java.time.LocalDate
import java.time.YearMonth
import java.time.Year
import java.time.format.DateTimeFormatter
import com.wadhams.world.history.date.TimeScale

class HistoryXMLService {
	DateTimeFormatter localDateDTF = DateTimeFormatter.ofPattern('dd/MM/yyyy')
	DateTimeFormatter yearMonthDTF = DateTimeFormatter.ofPattern('MM/yyyy')
	
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
			
			String name = txn.name.text()
			dto.name = name
			
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
				String value = startDT.text()
				dto.startLocalDate = LocalDate.parse(value, localDateDTF)
				dto.startYearMonth = YearMonth.from(dto.startLocalDate) 
				dto.startYear = Year.from(dto.startLocalDate) 
			}
			else if (startYM.size()) {
				dto.startTimeScale = TimeScale.YearMonth
				String value = startYM.text()
				dto.startYearMonth = YearMonth.parse(value, yearMonthDTF)
				dto.startYear = Year.from(dto.startYearMonth) 
			}
			else if (startCE.size()) {
				dto.startTimeScale = TimeScale.CE
				String value = startCE.text()
				Integer year = Integer.parseInt(value)
				dto.startYear = Year.of(year)
			}
			else if (startBCE.size()) {
				dto.startTimeScale = TimeScale.BCE
				String value = startBCE.text()
				Integer year = Integer.parseInt(value)
				dto.startYear = Year.of(year.unaryMinus())	//BCE years are negative
			}
			else if (startKYA.size()) {
				dto.startTimeScale = TimeScale.KYA
				String value = startKYA.text()
				dto.startValue = new BigDecimal(value)
			}
			else if (startMYA.size()) {
				dto.startTimeScale = TimeScale.MYA
				String value = startMYA.text()
				dto.startValue = new BigDecimal(value)
			}
			else if (startGYA.size()) {
				dto.startTimeScale = TimeScale.GYA
				String value = startGYA.text()
				dto.startValue = new BigDecimal(value)
			}
			
			//end
			def endBCE = txn.end.bce
			def endCE  = txn.end.ce
			def endYM = txn.end.ym
			def endDT = txn.end.dt
			
			if (endDT.size()) {
				dto.endTimeScale = TimeScale.LocalDate
				String value = endDT.text()
				dto.endLocalDate = LocalDate.parse(value, localDateDTF)
				dto.endYearMonth = YearMonth.from(dto.endLocalDate) 
				dto.endYear = Year.from(dto.endLocalDate) 
			}
			else if (endYM.size()) {
				dto.endTimeScale = TimeScale.YearMonth
				String value = endYM.text()
				dto.endYearMonth = YearMonth.parse(value, yearMonthDTF)
				dto.endYear = Year.from(dto.endYearMonth) 
			}
			else if (endCE.size()) {
				dto.endTimeScale = TimeScale.CE
				String value = endCE.text()
				Integer year = Integer.parseInt(value)
				dto.endYear = Year.of(year)
			}
			else if (endBCE.size()) {
				dto.endTimeScale = TimeScale.BCE
				String value = endBCE.text()
				Integer year = Integer.parseInt(value)
				dto.endYear = Year.of(year.unaryMinus())	//BCE years are negative
			}
			
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
