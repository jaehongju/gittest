package kr.youhost.utils;

import java.util.Map;

public class SysInfo {	
	public static String getOsName() {
		return System.getProperty("os.name");
	}
	public static String getOsVersion() {
		return System.getProperty("os.version");		
	}
	public static String getOsArch() {
		return System.getProperty("os.arch");
	}	
	
	public static boolean isWindows() {
		String osName = System.getProperty("os.name");
		if ( osName!=null && osName.contains("Window") ) {
			return true;
		} else {
			return false;
		}
	}
	
	public static String getEnv(String envName) {
		Map<String, String> env = System.getenv();
		return env.get( envName ) ;
	}
}
