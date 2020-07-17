package com.qa.automation.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerUtility {

	public  Logger log() {
		return LogManager.getLogger(Thread.currentThread().getStackTrace()[2].getClassName());
	}
}
