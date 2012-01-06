/*  
 *  Ep1c G4m3 -- A parody platformer
 * 
 *  Copyright (C) 2011  Voracious Softworks
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.

 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>. 
 */

package com.voracious.ep1cG4m3.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {
	public static final int TYPE_ERROR = 0;
	public static final int TYPE_WARNING = 1;
	public static final int TYPE_INFO = 2;
	public static final int TYPE_DEBUG = 3;

	private static File logfile;
	private static int loglevel;
	private static BufferedWriter log;

	
	/**
	 * Adds a log message to the log file
	 * 
	 * @param type The type of message EX: TYPE_ERROR
	 * @param message The message to put in the log file
	 */
	public static void log(int type, String message) {
		try {
			if (log == null) {
				if (getLogFile() == null) {
					throw new IOException("ERROR: logfile (" + getLogFile().toString() + ") is null");
				} else {
					if (!getLogFile().exists()) {
						FileWriter fstream = new FileWriter(logfile);
						log = new BufferedWriter(fstream);
						log.write("");
					} else {
						if (!getLogFile().isFile()) {
							throw new IOException("ERROR: logfile (" + getLogFile().toString() + ") is a directory");
						} else {
							if (!getLogFile().canWrite()) {
								throw new IOException("ERROR: Logile (" + getLogFile().toString() + ") not writable");
							} else {
								FileWriter fstream = new FileWriter(logfile);
								log = new BufferedWriter(fstream);
							}
						}
					}
				}
			}

			if (type < getLogLevel()) {
				String prefix = "";
				switch (type) {
				case TYPE_INFO:
					prefix += "INFO: ";
					break;
				case TYPE_ERROR:
					prefix += "ERROR: ";
					break;
				case TYPE_WARNING:
					prefix += "WARNING: ";
					break;
				case TYPE_DEBUG:
					prefix += "DEBUG: ";
					break;
				}

				log.append(prefix + message);
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			if (log != null) {
				try {
					log.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	/**
	 * @return the log file
	 */
	public static File getLogFile() {
		return logfile;
	}

	/**
	 * @param logfile the log file to set
	 */
	public static void setLogFile(File logfile) {
		Logger.logfile = logfile;
	}

	/**
	 * @return the log level
	 */
	public static int getLogLevel() {
		return loglevel;
	}

	/**
	 * @param loglevel the log level to set
	 */
	public static void setLogLevel(int loglevel) {
		Logger.loglevel = loglevel;
		log(TYPE_INFO, "Log level is " + loglevel);
	}

	
	/**
	 * Closes the log file (Should be called at shutdown)
	 */
	public static void close() {
		log(TYPE_DEBUG, "Closing log file");
		if (log != null) {
			try {
				log.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
