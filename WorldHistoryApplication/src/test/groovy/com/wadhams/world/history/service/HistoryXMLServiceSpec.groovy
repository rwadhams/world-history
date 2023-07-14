package com.wadhams.world.history.service

import com.wadhams.world.history.date.TimeScale
import com.wadhams.world.history.dto.HistoryDTO
import java.time.LocalDate
import java.time.Year
import java.time.YearMonth
import spock.lang.Specification
import spock.lang.Ignore

class HistoryXMLServiceSpec extends Specification {
	static List<HistoryDTO> historyList
	
	def setupSpec() {
		HistoryXMLService service = new HistoryXMLService()
		service.historyFilename = 'TestHistory01.xml'
		
		historyList = service.loadHistoryData()
	}
	
	def "correct number of DTOs"() {
		expect:
			historyList.size() == 7
	}

	def "'Age of the Earth' dto has correct values"() {
		given:
			//historyList has been created at setup...
			String startText = '4.52'
			
		when:
			HistoryDTO dto = historyList.find {it.name == 'Age of the Earth'}
			
		then:
			dto.categoryList == ['Geology']
			dto.startTimeScale == TimeScale.GYA
			dto.startText == startText
			dto.descriptionList.size() == 0
	}

	def "'Triassic' dto has correct values"() {
		given:
			//historyList has been created at setup...
			String startText = '252'
			
		when:
			HistoryDTO dto = historyList.find {it.name == 'Triassic'}
			
		then:
			dto.categoryList == ['Geology']
			dto.startTimeScale == TimeScale.MYA
			dto.startText == startText
			dto.descriptionList.size() == 0
	}

	def "'Holocene' dto has correct values"() {
		given:
			//historyList has been created at setup...
			String startText = '11.8'
			
		when:
			HistoryDTO dto = historyList.find {it.name == 'Holocene'}
			
		then:
			dto.categoryList == ['Geology']
			dto.startTimeScale == TimeScale.KYA
			dto.startText == startText
			dto.descriptionList.size() == 0
	}

	def "'Alexander the Great' dto has correct values"() {
		given:
			//historyList has been created at setup...
			String startText = '356'
			String endText = '323'
			
		when:
			HistoryDTO dto = historyList.find {it.name == 'Alexander the Great'}
			
		then:
			dto.categoryList == ['Person']
			dto.startTimeScale == TimeScale.BCE
			dto.startText == startText
			dto.endTimeScale == TimeScale.BCE
			dto.endText == endText
			dto.descriptionList[0] == 'Alexander III of Macedon, commonly known as Alexander the Great...'
			dto.descriptionList[1] == 'He succeeded his father Philip II...'
	}

	def "'Marcus Aurelius' dto has correct values"() {
		given:
			//historyList has been created at setup...
			String startText = '121'
			String endText = '180'

		when:
			HistoryDTO dto = historyList.find {it.name == 'Marcus Aurelius'}
			
		then:
			dto.categoryList == ['Person']
			dto.startTimeScale == TimeScale.CE
			dto.startText == startText
			dto.endTimeScale == TimeScale.CE
			dto.endText == endText
			dto.descriptionList.size() == 0
	}

	def "'Elizabeth I' dto has correct values"() {
		given:
			//historyList has been created at setup...
			String startText = '09/1533'
			String endText = '03/1603'

		when:
			HistoryDTO dto = historyList.find {it.name == 'Elizabeth I'}
			
		then:
			dto.categoryList == ['Person']
			dto.startTimeScale == TimeScale.YearMonth
			dto.startText == startText
			dto.endTimeScale == TimeScale.YearMonth
			dto.endText == endText
			dto.descriptionList.size() == 0
	}

	def "'Matthew Flinders' dto has correct values"() {
		given:
			//historyList has been created at setup...
			String startText = '16/03/1774'
			String endText = '19/07/1814'

		when:
			HistoryDTO dto = historyList.find {it.name == 'Matthew Flinders'}
			
		then:
			dto.categoryList == ['Person','Explorer','Australia']
			dto.startTimeScale == TimeScale.LocalDate
			dto.startText == startText
			dto.endTimeScale == TimeScale.LocalDate
			dto.endText == endText
			dto.descriptionList.size() == 0
	}

}
