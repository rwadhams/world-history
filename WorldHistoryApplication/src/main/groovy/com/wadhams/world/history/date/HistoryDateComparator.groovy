package com.wadhams.world.history.date


class HistoryDateComparator implements Comparator<HistoryDate> {

	@Override
	public int compare(HistoryDate hd1, HistoryDate hd2) {
		//Both GYA
		if (hd1.timeScale == TimeScale.GYA && hd2.timeScale == TimeScale.GYA) {
			return hd2.value.compareTo(hd1.value)
		}
		//Both MYA
		if (hd1.timeScale == TimeScale.MYA && hd2.timeScale == TimeScale.MYA) {
			return hd2.value.compareTo(hd1.value)
		}
		//Both KYA
		if (hd1.timeScale == TimeScale.KYA && hd2.timeScale == TimeScale.KYA) {
			return hd2.value.compareTo(hd1.value)
		}
		//Both BCE
		if (hd1.timeScale == TimeScale.BCE && hd2.timeScale == TimeScale.BCE) {
			return hd1.year.compareTo(hd2.year)
		}
		//Both CE
		if (hd1.timeScale == TimeScale.CE && hd2.timeScale == TimeScale.CE) {
			return hd1.year.compareTo(hd2.year)
		}
		//Both YearMonth
		if (hd1.timeScale == TimeScale.YearMonth && hd2.timeScale == TimeScale.YearMonth) {
			return hd1.yearMonth.compareTo(hd2.yearMonth)
		}
		//Both LocalDate
		if (hd1.timeScale == TimeScale.LocalDate && hd2.timeScale == TimeScale.LocalDate) {
			return hd1.localDate.compareTo(hd2.localDate)
		}
		//One GYA, the other is different TimeScale
		if (hd1.timeScale == TimeScale.GYA) {
			return -1
		}
		else if (hd2.timeScale == TimeScale.GYA) {
			return 1
		}
		//One MYA, the other is different TimeScale
		if (hd1.timeScale == TimeScale.MYA) {
			return -1
		}
		else if (hd2.timeScale == TimeScale.MYA) {
			return 1
		}
		//One KYA, the other is different TimeScale
		if (hd1.timeScale == TimeScale.KYA) {
			return -1
		}
		else if (hd2.timeScale == TimeScale.KYA) {
			return 1
		}
		//One BCE, the other is different TimeScale
		if (hd1.timeScale == TimeScale.BCE) {
			return -1
		}
		else if (hd2.timeScale == TimeScale.BCE) {
			return 1
		}
		//One CE, the other is different TimeScale
		if (hd1.timeScale == TimeScale.CE) {
			return -1
		}
		else if (hd2.timeScale == TimeScale.CE) {
			return 1
		}
		//One YearMonth, the other is different TimeScale
		if (hd1.timeScale == TimeScale.YearMonth) {
			return -1
		}
		else if (hd2.timeScale == TimeScale.YearMonth) {
			return 1
		}

		return 0;
	}
}
