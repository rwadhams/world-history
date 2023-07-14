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
			historyList.size() == 9
	}

	def "'Age of the Earth' dto has correct values"() {
		given:
			//historyList has been created at setup...
			BigDecimal startValue = new BigDecimal('4.52')
			
		when:
			HistoryDTO boe = historyList.find {it.name == 'Age of the Earth'}
			
		then:
			boe.categoryList == ['Geology']
			boe.startTimeScale == TimeScale.GYA
			boe.startValue == startValue
			//null DTO values
			boe.startYear == null
			boe.startYearMonth == null
			boe.startLocalDate == null
			boe.endTimeScale == null
			boe.endValue == null
			boe.endYear == null
			boe.endYearMonth == null
			boe.endLocalDate == null
			boe.descriptionList.size() == 0
	}

	def "'Triassic' dto has correct values"() {
		given:
			//historyList has been created at setup...
			BigDecimal startValue = new BigDecimal('252')
			
		when:
			HistoryDTO boe = historyList.find {it.name == 'Triassic'}
			
		then:
			boe.categoryList == ['Geology']
			boe.startTimeScale == TimeScale.MYA
			boe.startValue == startValue
			//null DTO values
			boe.startYear == null
			boe.startYearMonth == null
			boe.startLocalDate == null
			boe.endTimeScale == null
			boe.endValue == null
			boe.endYear == null
			boe.endYearMonth == null
			boe.endLocalDate == null
	}

	def "'Holocene' dto has correct values"() {
		given:
			//historyList has been created at setup...
			BigDecimal startValue = new BigDecimal('11.8')
			
		when:
			HistoryDTO boe = historyList.find {it.name == 'Holocene'}
			
		then:
			boe.categoryList == ['Geology']
			boe.startTimeScale == TimeScale.KYA
			boe.startValue == startValue
			//null DTO values
			boe.startYear == null
			boe.startYearMonth == null
			boe.startLocalDate == null
			boe.endTimeScale == null
			boe.endValue == null
			boe.endYear == null
			boe.endYearMonth == null
			boe.endLocalDate == null
	}

	def "'Alexander the Great' dto has correct values"() {
		given:
			//historyList has been created at setup...
			Year startYear = Year.of(-356)
			Year endYear = Year.of(-323)
			
		when:
			HistoryDTO boe = historyList.find {it.name == 'Alexander the Great'}
			
		then:
			boe.categoryList == ['Person']
			boe.startTimeScale == TimeScale.BCE
			boe.startYear == startYear
			boe.endTimeScale == TimeScale.BCE
			boe.endYear == endYear
			//null DTO values
			boe.startValue == null
			boe.startYearMonth == null			
			boe.startLocalDate == null
			boe.endValue == null
			boe.endYearMonth == null
			boe.endLocalDate == null
			boe.descriptionList[0] == 'Alexander III of Macedon, commonly known as Alexander the Great...'
			boe.descriptionList[1] == 'He succeeded his father Philip II...'
	}

	def "'Augustus' dto has correct values"() {
		given:
			//historyList has been created at setup...
			Year startYear = Year.of(-63)
			Year endYear = Year.of(14)
			
		when:
			HistoryDTO boe = historyList.find {it.name == 'Augustus'}
			
		then:
			boe.categoryList == ['Person']
			boe.startTimeScale == TimeScale.BCE
			boe.startYear == startYear
			boe.endTimeScale == TimeScale.CE
			boe.endYear == endYear
			//null DTO values
			boe.startValue == null
			boe.startYearMonth == null
			boe.startLocalDate == null
			boe.endValue == null
			boe.endYearMonth == null
			boe.endLocalDate == null
	}

	def "'Marcus Aurelius' dto has correct values"() {
		given:
			//historyList has been created at setup...
			Year startYear = Year.of(121)
			Year endYear = Year.of(180)
			
		when:
			HistoryDTO boe = historyList.find {it.name == 'Marcus Aurelius'}
			
		then:
			boe.categoryList == ['Person']
			boe.startTimeScale == TimeScale.CE
			boe.startYear == startYear
			boe.endTimeScale == TimeScale.CE
			boe.endYear == endYear
			//null DTO values
			boe.startValue == null
			boe.startYearMonth == null
			boe.startLocalDate == null
			boe.endValue == null
			boe.endYearMonth == null
			boe.endLocalDate == null
	}

	def "'Elizabeth I' dto has correct values"() {
		given:
			//historyList has been created at setup...
			YearMonth startYearMonth = YearMonth.of(1533, 9)
			Year startYear = Year.from(startYearMonth)
			YearMonth endYearMonth = YearMonth.of(1603, 3)
			Year endYear = Year.from(endYearMonth)
			
		when:
			HistoryDTO boe = historyList.find {it.name == 'Elizabeth I'}
			
		then:
			boe.categoryList == ['Person']
			boe.startTimeScale == TimeScale.YearMonth
			boe.startYearMonth == startYearMonth
			boe.startYear == startYear
			boe.endTimeScale == TimeScale.YearMonth
			boe.endYearMonth == endYearMonth
			boe.endYear == endYear
			//null DTO values
			boe.startValue == null
			boe.startLocalDate == null
			boe.endValue == null
			boe.endLocalDate == null
	}

	def "'Matthew Flinders' dto has correct values"() {
		given:
			//historyList has been created at setup...
			LocalDate startDt = LocalDate.of(1774,3,16)
			YearMonth startYearMonth = YearMonth.from(startDt)
			Year startYear = Year.from(startDt)
			LocalDate endDt = LocalDate.of(1814,7,19)
			YearMonth endYearMonth = YearMonth.from(endDt)
			Year endYear = Year.from(endDt)

		when:
			HistoryDTO boe = historyList.find {it.name == 'Matthew Flinders'}
			
		then:
			boe.categoryList == ['Person','Explorer','Australia']
			boe.startTimeScale == TimeScale.LocalDate
			boe.startLocalDate == startDt
			boe.startYearMonth == startYearMonth
			boe.startYear == startYear
			boe.endTimeScale == TimeScale.LocalDate
			boe.endLocalDate == endDt
			boe.endYearMonth == endYearMonth
			boe.endYear == endYear
			//null DTO values
			boe.startValue == null
			boe.endValue == null
	}

	def "'Steve Jobs' dto has correct values"() {
		given:
			//historyList has been created at setup...
			LocalDate startDt = LocalDate.of(1955,2,24)
			YearMonth startYearMonth = YearMonth.from(startDt)
			Year startYear = Year.from(startDt)
			LocalDate endDt = LocalDate.of(2011,10,5)
			YearMonth endYearMonth = YearMonth.from(endDt)
			Year endYear = Year.from(endDt)

		when:
			HistoryDTO boe = historyList.find {it.name == 'Steve Jobs'}
			
		then:
			boe.categoryList == ['Person']
			boe.startTimeScale == TimeScale.LocalDate
			boe.startLocalDate == startDt
			boe.startYearMonth == startYearMonth
			boe.startYear == startYear
			boe.endTimeScale == TimeScale.LocalDate
			boe.endLocalDate == endDt
			boe.endYearMonth == endYearMonth
			boe.endYear == endYear
			//null DTO values
			boe.startValue == null
			boe.endValue == null
	}
}
