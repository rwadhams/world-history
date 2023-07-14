package com.wadhams.world.history.comparator

import com.wadhams.world.history.dto.HistoryDTO
import com.wadhams.world.history.service.HistoryXMLService
import java.time.Year
import java.time.YearMonth
import java.time.LocalDate
import spock.lang.Specification
import spock.lang.Ignore
import com.wadhams.world.history.date.TimeScale

class HistoryDTOStartComparatorSpec extends Specification {
	static List<HistoryDTO> historyList
	
	def setupSpec() {
		HistoryXMLService service = new HistoryXMLService()
		service.historyFilename = 'TestHistoryForSorting.xml'
		
		historyList = service.loadHistoryData()
		Collections.sort(historyList, new HistoryDTONameComparator())	//sort by name to randomize
	}
	
	def "correct number of DTOs"() {
		expect:
			historyList.size() == 8
	}

	def "correct start sort order"() {
		given:
			HistoryDTOStartComparator comparator = new HistoryDTOStartComparator()
			
		when:
			Collections.sort(historyList, comparator)
			
		then:
			historyList[0].name == 'Age of the Earth'		//first
			historyList[1].name == 'Triassic'				//second
			historyList[2].name == 'Holocene'
			historyList[3].name == 'Alexander the Great'
			historyList[4].name == 'Augustus'
			historyList[5].name == 'Marcus Aurelius'
			historyList[6].name == 'Elizabeth I'
			historyList[7].name == 'Steve Jobs'				//last
	}
	
	def "correct GYA sort order"() {
		given:
			HistoryDTOStartComparator comparator = new HistoryDTOStartComparator()
			HistoryDTO first = new HistoryDTO(name: 'first', startTimeScale: TimeScale.GYA, startValue: new BigDecimal('13.8'))
			HistoryDTO middle = new HistoryDTO(name: 'middle', startTimeScale: TimeScale.GYA, startValue: new BigDecimal('9'))
			HistoryDTO last = new HistoryDTO(name: 'last', startTimeScale: TimeScale.GYA, startValue: new BigDecimal('4.52'))
			List<HistoryDTO> dtoList = []
			dtoList << middle
			dtoList << last
			dtoList << first
			
		when:
			Collections.sort(dtoList, comparator)
			
		then:
			dtoList[0].name == 'first'
			dtoList[1].name == 'middle'
			dtoList[2].name == 'last'
	}
	
	def "correct MYA sort order"() {
		given:
			HistoryDTOStartComparator comparator = new HistoryDTOStartComparator()
			HistoryDTO first = new HistoryDTO(name: 'first', startTimeScale: TimeScale.MYA, startValue: new BigDecimal('400'))
			HistoryDTO middle = new HistoryDTO(name: 'middle', startTimeScale: TimeScale.MYA, startValue: new BigDecimal('252'))
			HistoryDTO last = new HistoryDTO(name: 'last', startTimeScale: TimeScale.MYA, startValue: new BigDecimal('90'))
			List<HistoryDTO> dtoList = []
			dtoList << middle
			dtoList << last
			dtoList << first
			
		when:
			Collections.sort(dtoList, comparator)
			
		then:
			dtoList[0].name == 'first'
			dtoList[1].name == 'middle'
			dtoList[2].name == 'last'
	}
	
	def "correct KYA sort order"() {
		given:
			HistoryDTOStartComparator comparator = new HistoryDTOStartComparator()
			HistoryDTO first = new HistoryDTO(name: 'first', startTimeScale: TimeScale.KYA, startValue: new BigDecimal('45'))
			HistoryDTO middle = new HistoryDTO(name: 'middle', startTimeScale: TimeScale.KYA, startValue: new BigDecimal('23'))
			HistoryDTO last = new HistoryDTO(name: 'last', startTimeScale: TimeScale.KYA, startValue: new BigDecimal('11.6'))
			List<HistoryDTO> dtoList = []
			dtoList << middle
			dtoList << last
			dtoList << first
			
		when:
			Collections.sort(dtoList, comparator)
			
		then:
			dtoList[0].name == 'first'
			dtoList[1].name == 'middle'
			dtoList[2].name == 'last'
	}
	
	def "correct BCE sort order"() {
		given:
			HistoryDTOStartComparator comparator = new HistoryDTOStartComparator()
			HistoryDTO first = new HistoryDTO(name: 'first', startTimeScale: TimeScale.BCE, startYear: Year.of(-456))
			HistoryDTO middle = new HistoryDTO(name: 'middle', startTimeScale: TimeScale.BCE, startYear: Year.of(-345))
			HistoryDTO last = new HistoryDTO(name: 'last', startTimeScale: TimeScale.BCE, startYear: Year.of(-123))
			List<HistoryDTO> dtoList = []
			dtoList << middle
			dtoList << last
			dtoList << first
			
		when:
			Collections.sort(dtoList, comparator)
			
		then:
			dtoList[0].name == 'first'
			dtoList[1].name == 'middle'
			dtoList[2].name == 'last'
	}
	
	def "correct CE sort order"() {
		given:
			HistoryDTOStartComparator comparator = new HistoryDTOStartComparator()
			HistoryDTO first = new HistoryDTO(name: 'first', startTimeScale: TimeScale.CE, startYear: Year.of(123))
			HistoryDTO middle = new HistoryDTO(name: 'middle', startTimeScale: TimeScale.CE, startYear: Year.of(345))
			HistoryDTO last = new HistoryDTO(name: 'last', startTimeScale: TimeScale.CE, startYear: Year.of(678))
			List<HistoryDTO> dtoList = []
			dtoList << middle
			dtoList << last
			dtoList << first
			
		when:
			Collections.sort(dtoList, comparator)
			
		then:
			dtoList[0].name == 'first'
			dtoList[1].name == 'middle'
			dtoList[2].name == 'last'
	}
	
	def "correct YearMonth sort order"() {
		given:
			HistoryDTOStartComparator comparator = new HistoryDTOStartComparator()
			HistoryDTO first = new HistoryDTO(name: 'first', startTimeScale: TimeScale.YearMonth, startYearMonth: YearMonth.of(345, 6))
			HistoryDTO middle = new HistoryDTO(name: 'middle', startTimeScale: TimeScale.YearMonth, startYearMonth: YearMonth.of(1214, 9))
			HistoryDTO last = new HistoryDTO(name: 'last', startTimeScale: TimeScale.YearMonth, startYearMonth: YearMonth.of(1914, 2))
			List<HistoryDTO> dtoList = []
			dtoList << middle
			dtoList << last
			dtoList << first
			
		when:
			Collections.sort(dtoList, comparator)
			
		then:
			dtoList[0].name == 'first'
			dtoList[1].name == 'middle'
			dtoList[2].name == 'last'
	}
	
	def "correct LocalDate sort order"() {
		given:
			HistoryDTOStartComparator comparator = new HistoryDTOStartComparator()
			HistoryDTO first = new HistoryDTO(name: 'first', startTimeScale: TimeScale.LocalDate, startLocalDate: LocalDate.of(345, 6, 5))
			HistoryDTO middle = new HistoryDTO(name: 'middle', startTimeScale: TimeScale.LocalDate, startLocalDate: LocalDate.of(1214, 9, 5))
			HistoryDTO last = new HistoryDTO(name: 'last', startTimeScale: TimeScale.LocalDate, startLocalDate: LocalDate.of(1914, 2, 5))
			List<HistoryDTO> dtoList = []
			dtoList << middle
			dtoList << last
			dtoList << first
			
		when:
			Collections.sort(dtoList, comparator)
			
		then:
			dtoList[0].name == 'first'
			dtoList[1].name == 'middle'
			dtoList[2].name == 'last'
	}
	
	def "equal DTOs sort correctly"() {
		given:
			HistoryDTOStartComparator comparator = new HistoryDTOStartComparator()
			HistoryDTO gya1 = new HistoryDTO(name: 'gya', startTimeScale: TimeScale.GYA, startValue: new BigDecimal('13.8'))
			HistoryDTO gya2 = new HistoryDTO(name: 'gya', startTimeScale: TimeScale.GYA, startValue: new BigDecimal('13.8'))
			HistoryDTO mya1 = new HistoryDTO(name: 'mya', startTimeScale: TimeScale.MYA, startValue: new BigDecimal('252'))
			HistoryDTO mya2 = new HistoryDTO(name: 'mya', startTimeScale: TimeScale.MYA, startValue: new BigDecimal('252'))
			HistoryDTO kya1 = new HistoryDTO(name: 'kya', startTimeScale: TimeScale.KYA, startValue: new BigDecimal('11.6'))
			HistoryDTO kya2 = new HistoryDTO(name: 'kya', startTimeScale: TimeScale.KYA, startValue: new BigDecimal('11.6'))
			HistoryDTO bce1 = new HistoryDTO(name: 'bce', startTimeScale: TimeScale.BCE, startYear: Year.of(-345))
			HistoryDTO bce2 = new HistoryDTO(name: 'bce', startTimeScale: TimeScale.BCE, startYear: Year.of(-345))
			HistoryDTO ce1 = new HistoryDTO(name: 'ce', startTimeScale: TimeScale.CE, startYear: Year.of(252))
			HistoryDTO ce2 = new HistoryDTO(name: 'ce', startTimeScale: TimeScale.CE, startYear: Year.of(252))
			HistoryDTO ym1 = new HistoryDTO(name: 'ym', startTimeScale: TimeScale.YearMonth, startYearMonth: YearMonth.of(1914, 9))
			HistoryDTO ym2 = new HistoryDTO(name: 'ym', startTimeScale: TimeScale.YearMonth, startYearMonth: YearMonth.of(1914, 9))
			HistoryDTO ld1 = new HistoryDTO(name: 'ld', startTimeScale: TimeScale.LocalDate, startLocalDate: LocalDate.of(2011, 5, 24))
			HistoryDTO ld2 = new HistoryDTO(name: 'ld', startTimeScale: TimeScale.LocalDate, startLocalDate: LocalDate.of(2011, 5, 24))
			
		expect:
			//gya
			comparator.compare(gya1, gya2) == 0
			comparator.compare(gya2, gya1) == 0
			//mya
			comparator.compare(mya1, mya2) == 0
			comparator.compare(mya2, mya1) == 0
			//kya
			comparator.compare(kya1, kya2) == 0
			comparator.compare(kya2, kya1) == 0
			//bce
			comparator.compare(bce1, bce2) == 0
			comparator.compare(bce2, bce1) == 0
			//ce
			comparator.compare(ce1, ce2) == 0
			comparator.compare(ce2, ce1) == 0
			//ym
			comparator.compare(ym1, ym2) == 0
			comparator.compare(ym2, ym1) == 0
			//ld
			comparator.compare(ld1, ld2) == 0
			comparator.compare(ld2, ld1) == 0
	}
	
	def "TimeScales sorts correctly among others TimeScales"() {
		given:
			HistoryDTOStartComparator comparator = new HistoryDTOStartComparator()
			HistoryDTO gya = new HistoryDTO(name: 'gya', startTimeScale: TimeScale.GYA, startValue: new BigDecimal('13.8'))
			HistoryDTO mya = new HistoryDTO(name: 'mya', startTimeScale: TimeScale.MYA, startValue: new BigDecimal('252'))
			HistoryDTO kya = new HistoryDTO(name: 'kya', startTimeScale: TimeScale.KYA, startValue: new BigDecimal('11.6'))
			HistoryDTO bce = new HistoryDTO(name: 'bce', startTimeScale: TimeScale.BCE, startYear: Year.of(-345))
			HistoryDTO ce = new HistoryDTO(name: 'ce', startTimeScale: TimeScale.CE, startYear: Year.of(252))
			HistoryDTO ym = new HistoryDTO(name: 'ym', startTimeScale: TimeScale.YearMonth, startYearMonth: YearMonth.of(1914, 9))
			HistoryDTO ld = new HistoryDTO(name: 'ld', startTimeScale: TimeScale.LocalDate, startLocalDate: LocalDate.of(2011, 5, 24))
			
		expect:
			//gya
			comparator.compare(gya, mya) == -1
			comparator.compare(gya, kya) == -1
			comparator.compare(gya, bce) == -1
			comparator.compare(gya, ce) == -1
			comparator.compare(gya, ym) == -1
			comparator.compare(gya, ld) == -1
			comparator.compare(mya, gya) == 1
			comparator.compare(kya, gya) == 1
			comparator.compare(bce, gya) == 1
			comparator.compare(ce, gya) == 1
			comparator.compare(ym, gya) == 1
			comparator.compare(ld, gya) == 1
			//mya
			comparator.compare(mya, kya) == -1
			comparator.compare(mya, bce) == -1
			comparator.compare(mya, ce) == -1
			comparator.compare(mya, ym) == -1
			comparator.compare(mya, ld) == -1
			comparator.compare(kya, mya) == 1
			comparator.compare(bce, mya) == 1
			comparator.compare(ce, mya) == 1
			comparator.compare(ym, mya) == 1
			comparator.compare(ld, mya) == 1
			//kya
			comparator.compare(kya, bce) == -1
			comparator.compare(kya, ce) == -1
			comparator.compare(kya, ym) == -1
			comparator.compare(kya, ld) == -1
			comparator.compare(bce, kya) == 1
			comparator.compare(ce, kya) == 1
			comparator.compare(ym, kya) == 1
			comparator.compare(ld, kya) == 1
			//bce
			comparator.compare(bce, ce) == -1
			comparator.compare(bce, ym) == -1
			comparator.compare(bce, ld) == -1
			comparator.compare(ce, bce) == 1
			comparator.compare(ym, bce) == 1
			comparator.compare(ld, bce) == 1
			//ce
			comparator.compare(ce, ym) == -1
			comparator.compare(ce, ld) == -1
			comparator.compare(ym, ce) == 1
			comparator.compare(ld, ce) == 1
			//ym
			comparator.compare(ym, ld) == -1
			comparator.compare(ld, ym) == 1
	}
}
