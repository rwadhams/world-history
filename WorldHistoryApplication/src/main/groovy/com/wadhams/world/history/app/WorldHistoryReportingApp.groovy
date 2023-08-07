package com.wadhams.world.history.app

import com.wadhams.world.history.controller.WorldHistoryController

class WorldHistoryReportingApp {
	static main(args) {
		println 'WorldHistoryReportingApp started...'
		println ''

		WorldHistoryController controller = new WorldHistoryController()
		controller.execute()
		
		println ''
		println 'WorldHistoryReportingApp ended.'
	}
}
