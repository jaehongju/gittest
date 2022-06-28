package kr.youhost.utils;

public class StringUtil {
	static public String getStringNullReplace(final String str, final String replace) {
		return str==null ? replace : str;
	}
	static public String getStringNullReplace(final String str) {
		return getStringNullReplace(str, "null");
	}
}
