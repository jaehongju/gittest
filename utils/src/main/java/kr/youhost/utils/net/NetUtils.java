package kr.youhost.utils.net;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

public class NetUtils {
	/**
	 * check if url has ip
	 * 
	 * @param url
	 * @param ip
	 * @return
	 */
	public static boolean isAvailUrlInIp(String url, String ip) {
		String urlIp = getIp(url);
		if( urlIp!=null && urlIp.equals(ip) ) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * get ip for url
	 * 
	 * @param url
	 * @return
	 */
	public static String getIp(String url) {
		String ip = null;
		try {
			InetAddress address = InetAddress.getByName(new URL(url).getHost());
			ip = address.getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch ( MalformedURLException e) {
			e.printStackTrace();
		}
		return ip;
	}
}
