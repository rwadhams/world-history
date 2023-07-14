package com.wadhams.world.history.date

import java.time.LocalDate
import java.time.Year
import java.time.YearMonth
import spock.lang.Specification

class HistoryDateSpec extends Specification {
	
	def "constructor for GYA TimeScale populates correct fields"() {
		given:
			String text = '13.8'
			HistoryDate hd = new HistoryDate(TimeScale.GYA, text)
			BigDecimal bd = new BigDecimal(text)
		
		expect:
			hd.timeScale == TimeScale.GYA
			hd.value == bd
			hd.year == null
			hd.yearMonth == null
			hd.localDate == null
	}
	
	def "constructor for MYA TimeScale populates correct fields"() {
		given:
			String text = '252'
			HistoryDate hd = new HistoryDate(TimeScale.MYA, text)
			BigDecimal bd = new BigDecimal(text)
		
		expect:
			hd.timeScale == TimeScale.MYA
			hd.value == bd
			hd.year == null
			hd.yearMonth == null
			hd.localDate == null
	}
	
	def "constructor for KYA TimeScale populates correct fields"() {
		given:
			String text = '11.6'
			HistoryDate hd = new HistoryDate(TimeScale.KYA, text)
			BigDecimal bd = new BigDecimal(text)
		
		expect:
			hd.timeScale == TimeScale.KYA
			hd.value == bd
			hd.year == null
			hd.yearMonth == null
			hd.localDate == null
	}
	
	def "constructor for BCE TimeScale populates correct fields"() {
		given:
			String text = '345'
			HistoryDate hd = new HistoryDate(TimeScale.BCE, text)
			Integer value = Integer.parseInt(text).unaryMinus()
			Year year = Year.of(value)
		
		expect:
			hd.timeScale == TimeScale.BCE
			hd.value == null
			hd.year == year
			hd.yearMonth == null
			hd.localDate == null
	}
	
	def "constructor for CE TimeScale populates correct fields"() {
		given:
			String text = '121'
			HistoryDate hd = new HistoryDate(TimeScale.CE, text)
			Year year = Year.of(Integer.parseInt(text))
		
		expect:
			hd.timeScale == TimeScale.CE
			hd.value == null
			hd.year == year
			hd.yearMonth == null
			hd.localDate == null
	}
	
	def "constructor for YearMonth TimeScale populates correct fields"() {
		given:
			String text = '09/1533'
			HistoryDate hd = new HistoryDate(TimeScale.YearMonth, text)
			YearMonth ym = YearMonth.of(1533,9)
			Year year = Year.from(ym)
		
		expect:
			hd.timeScale == TimeScale.YearMonth
			hd.value == null
			hd.year == year
			hd.yearMonth == ym
			hd.localDate == null
	}
	
	def "constructor for LocalDate TimeScale populates correct fields"() {
		given:
			String text = '18/06/1962'
			HistoryDate hd = new HistoryDate(TimeScale.LocalDate, text)
			LocalDate ld = LocalDate.of(1962,6,18)
			YearMonth ym = YearMonth.from(ld)
			Year year = Year.from(ld)
		
		expect:
			hd.timeScale == TimeScale.LocalDate
			hd.value == null
			hd.year == year
			hd.yearMonth == ym
			hd.localDate == ld
	}
}
