package com.wadhams.world.history.app

import com.wadhams.world.history.controller.WorldHistoryController
import com.wadhams.world.history.dto.HistoryDTO
import com.wadhams.world.history.service.HistoryXMLService

class TestApp {
	static main(args) {
		println 'TestApp started...'
		println ''

		TestApp app = new TestApp()
		app.execute()
		
		println ''
		println 'TestApp ended.'
	}
	
	def execute() {
		HistoryXMLService historyXMLService = new HistoryXMLService()
		historyXMLService.historyFilename = 'TestHistory02.xml'
		List<HistoryDTO> historyDTOList = historyXMLService.loadHistoryData()
		historyDTOList.each {dto ->
			println dto
		}
		println ''

	}
}
