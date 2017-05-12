/**
 * 
 */
package com.alexa.springboot;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;


/**
 *
 */
public class BOTLogger {

	private Logger log;

	private static final Map<String, BOTLogger> loggerMap = new HashMap<String, BOTLogger>();

	public BOTLogger(Class<?> clazz) {
		this.log = LoggerFactory.getLogger(clazz);
	}

	public static BOTLogger getLogger(Class<?> clazz) {
		String loggerKey = clazz.getName();
		if (!loggerMap.containsKey(loggerKey)) {
			BOTLogger iommLogger = new BOTLogger(clazz);
			loggerMap.put(loggerKey, iommLogger);
			return iommLogger;
		} else {
			return loggerMap.get(loggerKey);
		}
	}

	/**
	 * 
	 * @param message
	 */
	public void debug(String message) {
		log.debug(message);
	}

	/**
	 * 
	 * @param message
	 * @param object
	 */
	public void debug(String message, Object object) {
		log.debug(message, object);
	}

	/**
	 * 
	 * @param message
	 * @param object
	 */
	public void debug(String message, Object... object) {
		log.debug(message, object);
	}

	/**
	 * 
	 * @param message
	 * @param throwable
	 */
	public void debug(String message, Throwable throwable) {
		log.debug(message, throwable);
	}

	/**
	 * 
	 * @param message
	 */
	public void info(String message) {
		log.info(message);
	}

	/**
	 * 
	 * @param message
	 * @param object
	 */
	public void info(String message, Object object) {
		log.info(message, object);
	}

	/**
	 * 
	 * @param message
	 * @param object
	 */
	public void info(String message, Object... object) {
		log.info(message, object);
	}

	/**
	 * 
	 * @param message
	 * @param throwable
	 */
	public void info(String message, Throwable throwable) {
		log.info(message, throwable);
	}

	/**
	 * 
	 * @param message
	 */
	public void warn(String message) {
		log.warn(message);
	}

	/**
	 * 
	 * @param message
	 * @param object
	 */
	public void warn(String message, Object object) {
		log.warn(message, object);
	}

	/**
	 * 
	 * @param message
	 * @param object
	 */
	public void warn(String message, Object... object) {
		log.warn(message, object);
	}

	/**
	 * 
	 * @param message
	 * @param throwable
	 */
	public void warn(String message, Throwable throwable) {
		log.warn(message, throwable);
	}

	/**
	 * 
	 * @param message
	 */
	public void error(String message) {
		Marker marker = MarkerFactory.getMarker("ERROR");
		log.error(marker, message);
	}

	/**
	 * 
	 * @param message
	 * @param object
	 */
	public void error(String message, Object object) {
		Marker marker = MarkerFactory.getMarker("ERROR");
		log.error(marker, message, object);
	}

	/**
	 * 
	 * @param message
	 * @param object
	 */
	public void error(String message, Object... object) {
		Marker marker = MarkerFactory.getMarker("ERROR");
		log.error(marker, message, object);
	}

	/**
	 * 
	 * @param message
	 * @param throwable
	 */
	public void error(String message, Throwable throwable) {
		log.error(message, throwable);
	}
}
