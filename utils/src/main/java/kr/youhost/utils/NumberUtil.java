package kr.youhost.utils;

import java.text.DecimalFormat;

public class NumberUtil {

	public static String toFormattedInteger(Integer n) {
		return String.format("%,d", n);
	}
	public static String toFormattedInteger(Integer n, String format) {
		DecimalFormat df = new DecimalFormat(format);
		return df.format(n);
	}
	
	public static String toFormattedSizeWithUnit(Long size) {
		// byte, kb, mb, gb, tb
		if(size <= 0) return "0";
	    final String[] units = new String[] { "B", "kB", "MB", "GB", "TB" };
	    int digitGroups = (int) (Math.log10(size)/Math.log10(1024));
	    return new DecimalFormat("#,##0").format(size/Math.pow(1024, digitGroups)) + " " + units[digitGroups];
	}
}
