package kr.youhost.utils;

import static org.junit.Assert.assertEquals;

import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestSysInfo {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		System.out.println("Properties >>>>>>>>>>>>>>");
		Properties ps = System.getProperties();
		Enumeration<?> e = ps.propertyNames();
		while (e.hasMoreElements()) {
			String tag = (String)e.nextElement();
			System.out.format("%s=%s\n", tag, System.getProperty(tag));
		}
		
		System.out.println("Env >>>>>>>>>>>>>>");
		Map<String, String> env = System.getenv();
		
		for (String envName : env.keySet()) {
			System.out.format("%s=%s%n", envName, env.get(envName));
		}
		
		assertEquals(SysInfo.getOsName(), "Windows 7");
		assertEquals(SysInfo.getOsVersion(), "6.1");
		assertEquals(SysInfo.getOsArch(), "amd64");	}

}
