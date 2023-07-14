package com.wadhams.world.history.date

import java.time.Year
import java.time.YearMonth
import java.time.LocalDate
import spock.lang.Specification

class HistoryDateComparatorSpec extends Specification {
	def "correct GYA sort order"() {
		given:
			HistoryDateComparator comparator = new HistoryDateComparator()
			
			HistoryDate first = new HistoryDate(TimeScale.GYA, '13.8')
			HistoryDate middle = new HistoryDate(TimeScale.GYA, '9') 
			HistoryDate last = new HistoryDate(TimeScale.GYA, '4.58')
			
			List<HistoryDate> hdList = []
			hdList << middle << last << first
			
		when:
			Collections.sort(hdList, comparator)
			
		then:
			hdList[0] == first
			hdList[1] == middle
			hdList[2] == last
	}
	
	def "correct MYA sort order"() {
		given:
			HistoryDateComparator comparator = new HistoryDateComparator()
			
			HistoryDate first = new HistoryDate(TimeScale.MYA, '400')
			HistoryDate middle = new HistoryDate(TimeScale.MYA, '252') 
			HistoryDate last = new HistoryDate(TimeScale.MYA, '90')
			
			List<HistoryDate> hdList = []
			hdList << middle << last << first
			
		when:
			Collections.sort(hdList, comparator)
			
		then:
			hdList[0] == first
			hdList[1] == middle
			hdList[2] == last
	}
	
	def "correct KYA sort order"() {
		given:
			HistoryDateComparator comparator = new HistoryDateComparator()
			
			HistoryDate first = new HistoryDate(TimeScale.KYA, '45')
			HistoryDate middle = new HistoryDate(TimeScale.KYA, '23') 
			HistoryDate last = new HistoryDate(TimeScale.KYA, '11.6')
			
			List<HistoryDate> hdList = []
			hdList << middle << last << first
			
		when:
			Collections.sort(hdList, comparator)
			
		then:
			hdList[0] == first
			hdList[1] == middle
			hdList[2] == last
	}
	
	def "correct BCE sort order"() {
		given:
			HistoryDateComparator comparator = new HistoryDateComparator()
			
			HistoryDate first = new HistoryDate(TimeScale.BCE, '456')
			HistoryDate middle = new HistoryDate(TimeScale.BCE, '345') 
			HistoryDate last = new HistoryDate(TimeScale.BCE, '123')
			
			List<HistoryDate> hdList = []
			hdList << middle << last << first
			
		when:
			Collections.sort(hdList, comparator)
			
		then:
			hdList[0] == first
			hdList[1] == middle
			hdList[2] == last
	}
	
	def "correct CE sort order"() {
		given:
			HistoryDateComparator comparator = new HistoryDateComparator()
			
			HistoryDate first = new HistoryDate(TimeScale.CE, '123')
			HistoryDate middle = new HistoryDate(TimeScale.CE, '345') 
			HistoryDate last = new HistoryDate(TimeScale.CE, '678')
			
			List<HistoryDate> hdList = []
			hdList << middle << last << first
			
		when:
			Collections.sort(hdList, comparator)
			
		then:
			hdList[0] == first
			hdList[1] == middle
			hdList[2] == last
	}
	
	def "correct YearMonth sort order"() {
		given:
			HistoryDateComparator comparator = new HistoryDateComparator()
			
			HistoryDate first = new HistoryDate(TimeScale.YearMonth, '06/0345')
			HistoryDate middle = new HistoryDate(TimeScale.YearMonth, '09/1214') 
			HistoryDate last = new HistoryDate(TimeScale.YearMonth, '02/1914')
			
			List<HistoryDate> hdList = []
			hdList << middle << last << first
			
		when:
			Collections.sort(hdList, comparator)
			
		then:
			hdList[0] == first
			hdList[1] == middle
			hdList[2] == last
	}
	
	def "correct LocalDate sort order"() {
		given:
			HistoryDateComparator comparator = new HistoryDateComparator()
			
			HistoryDate first = new HistoryDate(TimeScale.LocalDate, '05/06/0345')
			HistoryDate middle = new HistoryDate(TimeScale.LocalDate, '05/09/1214') 
			HistoryDate last = new HistoryDate(TimeScale.LocalDate, '05/02/1914')
			
			List<HistoryDate> hdList = []
			hdList << middle << last << first
			
		when:
			Collections.sort(hdList, comparator)
			
		then:
			hdList[0] == first
			hdList[1] == middle
			hdList[2] == last
	}
	
	def "equal DTOs sort correctly"() {
		given:
			HistoryDateComparator comparator = new HistoryDateComparator()
			HistoryDate gya1 = new HistoryDate(TimeScale.GYA, '13.8')
			HistoryDate gya2 = new HistoryDate(TimeScale.GYA, '13.8')
			HistoryDate mya1 = new HistoryDate(TimeScale.MYA, '252')
			HistoryDate mya2 = new HistoryDate(TimeScale.MYA, '252')
			HistoryDate kya1 = new HistoryDate(TimeScale.KYA, '11.6')
			HistoryDate kya2 = new HistoryDate(TimeScale.KYA, '11.6')
			HistoryDate bce1 = new HistoryDate(TimeScale.BCE, '345')
			HistoryDate bce2 = new HistoryDate(TimeScale.BCE, '345')
			HistoryDate ce1 = new HistoryDate(TimeScale.CE, '252')
			HistoryDate ce2 = new HistoryDate(TimeScale.CE, '252')
			HistoryDate ym1 = new HistoryDate(TimeScale.YearMonth, '09/1914')
			HistoryDate ym2 = new HistoryDate(TimeScale.YearMonth, '09/1914')
			HistoryDate ld1 = new HistoryDate(TimeScale.LocalDate, '24/05/2011')
			HistoryDate ld2 = new HistoryDate(TimeScale.LocalDate, '24/05/2011')
			
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
			HistoryDateComparator comparator = new HistoryDateComparator()
			HistoryDate gya = new HistoryDate(TimeScale.GYA, '13.8')
			HistoryDate mya = new HistoryDate(TimeScale.MYA, '252')
			HistoryDate kya = new HistoryDate(TimeScale.KYA, '11.6')
			HistoryDate bce = new HistoryDate(TimeScale.BCE, '345')
			HistoryDate ce = new HistoryDate(TimeScale.CE, '252')
			HistoryDate ym = new HistoryDate(TimeScale.YearMonth, '09/1914')
			HistoryDate ld = new HistoryDate(TimeScale.LocalDate, '24/05/2011')
			
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
