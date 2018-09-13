package com.anwei.test.shiro;

import org.junit.Test;

import com.anwei.common.util.PropertiesFileUtil;

public class ConfigTest {
	@Test
	public void testName() throws Exception {
		PropertiesFileUtil pf = PropertiesFileUtil.getInstance("config");
		System.out.println(pf.get("app.name"));
	}
}
