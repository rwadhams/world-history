package com.wadhams.world.history.comparator

import com.wadhams.world.history.dto.HistoryDTO
import com.wadhams.world.history.service.HistoryXMLService
import spock.lang.Specification

class HistoryDTONameComparatorSpec extends Specification {
	static List<HistoryDTO> historyList
	
	def setupSpec() {
		HistoryXMLService service = new HistoryXMLService()
		service.historyFilename = 'TestHistoryForSorting.xml'
		
		historyList = service.loadHistoryData()
	}
	
	def "correct number of DTOs"() {
		expect:
			historyList.size() == 8
	}
	
	def "correct name sort order"() {
		given:
			HistoryDTONameComparator comparator = new HistoryDTONameComparator()
			
		when:
			Collections.sort(historyList, comparator)
			
		then:
			historyList[0].name == 'Age of the Earth'		//first
			historyList[2].name == 'Augustus'				//third
			historyList[7].name == 'Triassic'				//last
	}
}
