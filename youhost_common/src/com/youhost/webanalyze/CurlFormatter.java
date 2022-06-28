package com.youhost.webanalyze;

import java.util.ArrayList;
import java.util.List;

public class CurlFormatter {
	private List<String> attributes = new ArrayList<String>();
	
	public CurlFormatter(){
		attributes.add("url_effective");
		attributes.add("http_code");
		attributes.add("http_connect");
		attributes.add("time_total");
		attributes.add("time_namelookup");
		attributes.add("time_connect");
		attributes.add("time_appconnect");
		attributes.add("time_starttransfer");
		attributes.add("size_download");
		attributes.add("size_upload");
		attributes.add("size_header");
		attributes.add("size_request");
		attributes.add("speed_download");
		attributes.add("speed_upload");
		attributes.add("content_type");
		attributes.add("num_connects");
		attributes.add("num_redirects");
		attributes.add("redirect_url");
		attributes.add("ftp_entry_path");
		attributes.add("ssl_verify_result");
	}
	
	public static void main(String[] args){
		CurlFormatter cf = new CurlFormatter();
		StringBuffer sb = new StringBuffer();
		sb.append("curl -w \"");
		for(String s : cf.attributes){
			sb.append(s).append("=%{").append(s).append("}\\n");
		}
		sb.append("\" http://www.naver.com");
		System.out.println(sb);
	}
}
