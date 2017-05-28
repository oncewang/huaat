package net.huaat.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Description: TODO
 * @author zhiqiang yang
 * @date 2012-10-29 下午3:57:09
 * @version V1.0
 */
public class PropertiesUtil {

	public static Properties getProperties(String propertiesName)
			throws IOException {
		InputStream inputStream = PropertiesUtil.class.getClassLoader()
				.getResourceAsStream(propertiesName);
		Properties properties = new Properties();
		properties.load(inputStream);
		return properties;
	}

	public static void main(String[] args) throws IOException {
		Properties properties = getProperties("successfull_sso.properties");
		System.out.println(properties.get("sso.isuse"));
		System.out.println(properties.get("sso.time"));
		System.out.println(properties.get("sso.logUrl"));
		System.out.println(properties.get("sso.idstring"));
	}

}
