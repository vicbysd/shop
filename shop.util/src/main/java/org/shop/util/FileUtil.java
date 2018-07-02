package org.shop.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * 文件工具类
 * @author VIC
 *
 */
public class FileUtil {
	
	/**
	 * 加载属性文件
	 * @param fileName
	 * @return
	 */
	public static Properties loadProperties(String fileName){
		Properties properties = new Properties();
		try {
			properties.load(FileUtil.class.getResourceAsStream(fileName));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return properties;
	}

	/**
	 * 加载文本文件内容
	 * @param fileName
	 * @return
	 */
	public static String loadFileContent(String fileName) {
		StringBuffer content = new StringBuffer();
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(FileUtil.class.getResourceAsStream(fileName)));
			while (bufferedReader.ready()) {
				content.append(bufferedReader.readLine());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(null != bufferedReader){
				try{
					bufferedReader.close();
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return content.toString();
	}

}
