package com.wadhams.world.history.comparator

import com.wadhams.world.history.dto.HistoryDTO
import com.wadhams.world.history.date.TimeScale

class HistoryDTOStartComparator implements Comparator<HistoryDTO> {

	@Override
	public int compare(HistoryDTO h1, HistoryDTO h2) {
		//Both GYA
		if (h1.startTimeScale == TimeScale.GYA && h2.startTimeScale == TimeScale.GYA) {
			return h2.startValue.compareTo(h1.startValue)
		}
		//Both MYA
		if (h1.startTimeScale == TimeScale.MYA && h2.startTimeScale == TimeScale.MYA) {
			return h2.startValue.compareTo(h1.startValue)
		}
		//Both KYA
		if (h1.startTimeScale == TimeScale.KYA && h2.startTimeScale == TimeScale.KYA) {
			return h2.startValue.compareTo(h1.startValue)
		}
		//Both BCE
		if (h1.startTimeScale == TimeScale.BCE && h2.startTimeScale == TimeScale.BCE) {
			return h1.startYear.compareTo(h2.startYear)
		}
		//Both CE
		if (h1.startTimeScale == TimeScale.CE && h2.startTimeScale == TimeScale.CE) {
			return h1.startYear.compareTo(h2.startYear)
		}
		//Both YearMonth
		if (h1.startTimeScale == TimeScale.YearMonth && h2.startTimeScale == TimeScale.YearMonth) {
			return h1.startYearMonth.compareTo(h2.startYearMonth)
		}
		//Both LocalDate
		if (h1.startTimeScale == TimeScale.LocalDate && h2.startTimeScale == TimeScale.LocalDate) {
			return h1.startLocalDate.compareTo(h2.startLocalDate)
		}
		//One GYA, the other is different TimeScale
		if (h1.startTimeScale == TimeScale.GYA) {
			return -1
		}
		else if (h2.startTimeScale == TimeScale.GYA) {
			return 1
		}
		//One MYA, the other is different TimeScale
		if (h1.startTimeScale == TimeScale.MYA) {
			return -1
		}
		else if (h2.startTimeScale == TimeScale.MYA) {
			return 1
		}
		//One KYA, the other is different TimeScale
		if (h1.startTimeScale == TimeScale.KYA) {
			return -1
		}
		else if (h2.startTimeScale == TimeScale.KYA) {
			return 1
		}
		//One BCE, the other is different TimeScale
		if (h1.startTimeScale == TimeScale.BCE) {
			return -1
		}
		else if (h2.startTimeScale == TimeScale.BCE) {
			return 1
		}
		//One CE, the other is different TimeScale
		if (h1.startTimeScale == TimeScale.CE) {
			return -1
		}
		else if (h2.startTimeScale == TimeScale.CE) {
			return 1
		}
		//One YearMonth, the other is different TimeScale
		if (h1.startTimeScale == TimeScale.YearMonth) {
			return -1
		}
		else if (h2.startTimeScale == TimeScale.YearMonth) {
			return 1
		}

		return 0;
	}
}
