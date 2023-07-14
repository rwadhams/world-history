package com.wadhams.world.history.date

import java.time.LocalDate
import java.time.Year
import java.time.YearMonth
import java.time.format.DateTimeFormatter

import groovy.transform.ToString

@ToString(includeNames=true)
class HistoryDate {
	static DateTimeFormatter localDateDTF = DateTimeFormatter.ofPattern('dd/MM/yyyy')
	static DateTimeFormatter yearMonthDTF = DateTimeFormatter.ofPattern('MM/yyyy')

	TimeScale timeScale
	BigDecimal value
	Year year
	YearMonth yearMonth
	LocalDate localDate
	
	def HistoryDate(TimeScale timeScale, String text) {
		this.timeScale = timeScale
		switch (timeScale) {
			case TimeScale.LocalDate :
				localDate = LocalDate.parse(text, localDateDTF)
				yearMonth = YearMonth.from(localDate)
				year = Year.from(localDate)
				return
			case TimeScale.YearMonth :
				yearMonth = YearMonth.parse(text, yearMonthDTF)
				year = Year.from(yearMonth)
				return
			case TimeScale.CE :
				Integer y = Integer.parseInt(text)
				year = Year.of(y)
				return
			case TimeScale.BCE :
				Integer y = Integer.parseInt(text)
				year = Year.of(y.unaryMinus())	//BCE years are negative
				return
			case TimeScale.KYA :
			case TimeScale.MYA :
			case TimeScale.GYA :
				value = new BigDecimal(text)
				return
			default :
				throw new RuntimeException('Unknown TimeScale')	
		}
	}

	@Override
	public boolean equals(Object other) {
		if (other == null) return false
		
		if (this.is(other)) return true
		
		if (!(other instanceof HistoryDate)) return false
		
		if (timeScale != other.timeScale) return false
		if (value != other.value) return false
		if (year != other.year) return false
		if (yearMonth != other.yearMonth) return false
		if (localDate != other.localDate) return false
		return true
	}

	@Override
	public int hashCode() {
		if (value) {
			return value.hashCode()
		}
		else {
			return year.hashCode()
		}
	}
}
